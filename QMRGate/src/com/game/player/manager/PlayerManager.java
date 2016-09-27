package com.game.player.manager;

import java.net.InetSocketAddress;
import java.security.MessageDigest;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.game.config.Config;
import com.game.db.bean.GameMaster;
import com.game.db.bean.Role;
import com.game.db.bean.User;
import com.game.db.dao.GameMasterDao;
import com.game.db.dao.RoleDao;
import com.game.db.dao.UserDao;
import com.game.dblog.LogService;
import com.game.gm.message.GmLevelMessage;
import com.game.login.bean.CharacterInfo;
import com.game.login.message.ReqCreateCharacterToGameMessage;
import com.game.login.message.ReqJustCreateCharacterToGameMessage;
import com.game.login.message.ReqLoginCharacterToGameMessage;
import com.game.login.message.ReqLoginForPlatformMessage;
import com.game.login.message.ReqQuitToGameMessage;
import com.game.login.message.ResCharacterInfosMessage;
import com.game.login.message.ResCreateFailedMessage;
import com.game.login.message.ResLoginFailedMessage;
import com.game.login.message.ResSubstituteMessage;
import com.game.player.loader.VerifyConfigXmlLoader;
import com.game.player.log.AllRegLog;
import com.game.player.log.RegLog;
import com.game.player.message.ReqDelPlayerToGameMessage;
import com.game.player.structs.Player;
import com.game.player.structs.UserState;
import com.game.player.structs.VerifyConfig;
import com.game.server.GateServer;
import com.game.util.SessionUtil;
import com.game.utils.MessageUtil;

public class PlayerManager {

	private Logger log = Logger.getLogger(PlayerManager.class);
	
	private static Logger closelog = Logger.getLogger("GATESESSIONCLOSE");
	
	private static Object obj = new Object();
	//玩家管理类实例
	private static PlayerManager manager;
	
	//用户登录玩家列表 1层key为服务器id 2层key为userid
	private static ConcurrentHashMap<Integer, ConcurrentHashMap<String, Player>> user_players = new ConcurrentHashMap<Integer, ConcurrentHashMap<String, Player>>();
	//玩家列表
	private static ConcurrentHashMap<Long, Player> players = new ConcurrentHashMap<Long, Player>();
	//用户登录玩家列表 1层key为服务器id 2层key为userid
	private static ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>> user_states = new ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>>();
		
	public static int MAX_PLAYER = 4000;
	
	public static String USER_ID = "userId";
	
	public static String USER_NAME = "userName";
	
	public static String SERVER_ID = "serverId";
	
	public static String PLAYER_ID = "roleId";
	
	public static String IS_ADULT = "isAdult";
	
	public static String SESSION_ID = "sessionId";
	
	public static String SESSION_IP = "sessionIp";
	
	public static String SESSION_LOGINTYPE = "session_Logintype";
	
	public static String AGENTPLUSDATA = "agentPlusdata";
	
	public static String AGENTColDATA = "agentColdatas";
	
	public static String PRELOGINTIME = "prelogintime";
	
	public static String PRECREATETIME = "precreatetime";
	
	private static volatile int sessionId = 0;
	
	private PlayerManager(){}
	
	public static PlayerManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new PlayerManager();
			}
		}
		return manager;
	}
	
	public Player getPlayer(long playerId){
		return players.get(playerId);
	}
	
	public ConcurrentHashMap<Long, Player> getPlayers(){
		return players;
	}
	
	/**
	 * 用户登录
	 * @param server 登录服务器
	 * @param user 登录用户
	 */
	public void login(IoSession session, int createServer, String userId, String password){
		//这里的userid 就是 username
		String ip = null;
		try{
			InetSocketAddress remoteAddress = (InetSocketAddress) session.getRemoteAddress();
			if(remoteAddress!=null){
				ip = remoteAddress.getAddress().getHostAddress();
			}
			//非局域网环境需要验证登录密码  IP未取到 或者 IP取到了但不是内网IP
			if(ip==null || (!ip.startsWith("192.168.") && !ip.startsWith("127.0.0.1") && !ip.equals("221.237.152.208"))){
				if(StringUtils.isBlank(password) || !password.equalsIgnoreCase(config.getToken().toUpperCase())){  //密码验证不通过 返回登录失败消息 忽略大小写
					log.error("后门登录验证不通过"+"\t"+"errorpwd:"+password+"\t"+ip);
					ResLoginFailedMessage fmsg  = new ResLoginFailedMessage();
					fmsg.setReason((byte)5); session.write(fmsg);
					return;
				}
			}
			
			User user = null;
			synchronized (obj) {
				//记录登陆账号
				UserDao dao = new UserDao();
				user = dao.select(userId, createServer);
				if(user!=null){
					user.setLastlogintime(System.currentTimeMillis());
					dao.update(user);
				}else{
					user = new User();
					user.setCreatetime(System.currentTimeMillis());
					user.setLastlogintime(System.currentTimeMillis());
					user.setUserid(Config.getId());
					user.setUsername(userId);
					user.setServer(createServer);
					dao.insert(user);
					streglog(user,createServer);
				}
			}
			
			if(user.getIsForbid()!=null && user.getIsForbid()==1){
				ResLoginFailedMessage fmsg  = new ResLoginFailedMessage();
				fmsg.setReason((byte)2);
				session.write(fmsg);
				return;
			}
			
			if(players.size() > MAX_PLAYER){
				ResLoginFailedMessage fmsg  = new ResLoginFailedMessage();
				fmsg.setReason((byte)3);
				session.write(fmsg);
				return;
			}
			
			userId = Long.toString(user.getUserid());
			
			synchronized (user_states) {
				int state = getUserState(createServer, userId);
				if(state!=0){
					log.error("User " + userId + session + " " + getUserStateInfo(state));
					return;
				}
				setUserState(createServer, userId, UserState.LOGINING.getValue());
				log.error("User " + userId + session + " login set state " + getUserStateInfo(UserState.LOGINING.getValue()));
			}

			//检查是否当前有同服务器同账号在线session
			IoSession oldSession = GateServer.getInstance().getSessionByUser(createServer, userId);
			if(oldSession!=null && oldSession.getId()!=session.getId()){
				//被人顶替下线
				closelog.error("player " + userId + "(" + oldSession.getRemoteAddress() + ") replace by {" + userId + "}(" + session.getRemoteAddress() + ")");
				
				if(session!=null && session.getRemoteAddress()!=null && oldSession.isConnected()){
					ResSubstituteMessage msg = new ResSubstituteMessage();
					try{
						msg.setIp(ip);
					}catch (Exception e) {
						log.error(e, e);
					}
					oldSession.write(msg);
				}
				
				long roleId = 0;
				if(oldSession.containsAttribute(PLAYER_ID)){
					try{
						roleId = (Long)oldSession.removeAttribute(PLAYER_ID);
						GateServer.getInstance().removeRoleSession(roleId);
					}catch (Exception e) {
						log.error(e, e);
					}
				}
				if(oldSession.containsAttribute(USER_ID)){
					try{
						String oldUserId = (String)oldSession.getAttribute(USER_ID);
						GateServer.getInstance().removeUserSession(oldUserId);
					}catch (Exception e) {
						log.error(e, e);
					}
				}
				//关闭连接
//				oldSession.close(true);
				SessionUtil.closeSession(oldSession, "被顶号", false);
			}
			
			try{
				session.setAttribute(SESSION_IP, ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress());
			}catch (Exception e) {
				log.error(e, e);
			}
			
			sessionId++;
			session.setAttribute(SESSION_ID, sessionId);
			session.setAttribute(USER_NAME, user.getUsername());
			
			//注册当前玩家
			GateServer.getInstance().registerUser(session, createServer, userId, 0);
			
			//gm状态
			try{
				GameMasterDao masterdao = new GameMasterDao();
				GameMaster master = masterdao.selectByUserid(user.getUserid());
				if(master!=null){
					GmLevelMessage gmmsg = new GmLevelMessage();
					gmmsg.setLevel(master.getGmlevel());
					session.write(gmmsg);
				}
			}catch (Exception e) {
				log.error(e, e);
			}
			
			//当前用户是否有角色在线
			ConcurrentHashMap<String, Player> players = user_players.get(createServer);
			if(players!=null){
				Player player = players.get(userId);
				if(player!=null){
					synchronized (user_states) {
						int state = removeUserState(createServer, userId);
						log.error("User " + userId + session + " remove " + getUserStateInfo(state) + " for select online " + player.getId());
					}
					//通知游戏服务器登录角色
					selectCharacter(session, player.getId());
					return;
				}
			}
			
			//检查服务器是否包含所选区
			if(GateServer.getGameConfig().getCountryByServer(createServer)==0){
				ResLoginFailedMessage msg  = new ResLoginFailedMessage();
				msg.setReason((byte)1);
				session.write(msg);
				synchronized (user_states) {
					int state = removeUserState(createServer, userId);
					log.error("User " + userId + session + " remove " + getUserStateInfo(state) + "for not have server");
				}
				return;
			}

			//查询当前用户可登录角色列表
			RoleDao roledao = new RoleDao();
			List<Role> characters = roledao.selectByUser(userId, createServer);
			ResCharacterInfosMessage return_msg = new ResCharacterInfosMessage();
			for (int i = 0; i < characters.size(); i++) {
				Role role = characters.get(i);
				CharacterInfo info = new CharacterInfo();
				info.setName(role.getName());
				info.setPlayerId(role.getRoleid());
				info.setLevel(role.getLevel());
				info.setSex((byte)(int)role.getSex());
				if (role.getLogintime() == null) {
					info.setLongintime(0);
				}else {
					info.setLongintime((int)(role.getLogintime()/1000));
				}
				
				return_msg.getCharacters().add(info);
			}

			//返回角色列表
			session.write(return_msg);
			log.error(session + "发送登陆消息,时间:" + System.currentTimeMillis());
			
			synchronized (user_states) {
				int state = removeUserState(createServer, userId);
				log.error("User " + userId + session + " remove " + getUserStateInfo(state) + " for login finish");
			}
		}catch (Exception e) {
			log.error(e, e);
			//断开连接
			SessionUtil.closeSession(session, e.getMessage());
		}finally{
			synchronized (user_states) {
				int state = removeUserState(createServer, userId);
				log.error("User " + userId + session + " remove " + getUserStateInfo(state) + " for error");
			}
		}
	}
	private VerifyConfig config = new VerifyConfigXmlLoader().load("gate-config/verify-config.xml"); //读取验证配置
	/**
	 * 用户登录
	 * @param msg 登录消息
	 */
	public void login(IoSession session, ReqLoginForPlatformMessage msg){
		int createServer = Integer.parseInt(msg.getServerId());
		String userId = msg.getUsername();
		if(msg.getAgentPlusdata()!=null && msg.getAgentPlusdata().length() > 1000){
			msg.setAgentPlusdata(msg.getAgentPlusdata().substring(0, 1000));
		}
		if(msg.getAgentColdatas()!=null && msg.getAgentColdatas().length() > 1000){
			msg.setAgentColdatas(msg.getAgentColdatas().substring(0, 1000));
		}
		if(msg.getAgent()!=null && msg.getAgent().length() > 1000){
			msg.setAgent(msg.getAgent().substring(0, 1000));
		}
		if(msg.getAd()!=null && msg.getAd().length() > 1000){
			msg.setAd(msg.getAd().substring(0, 1000));
		}
		if(msg.getAdregtime()!=null && msg.getAdregtime().length() > 1000){
			msg.setAdregtime(msg.getAdregtime().substring(0, 1000));
		}
		long start = System.currentTimeMillis();
		String ip = null;
		try{
			InetSocketAddress remoteAddress = (InetSocketAddress) session.getRemoteAddress();
			if(remoteAddress!=null){
				ip = remoteAddress.getAddress().getHostAddress();
			}
			//IP未取到 或者 IP取到了但是不是内网IP
			if(ip==null || (!ip.startsWith("192.168.") && !ip.startsWith("127.0.0.1"))){
				boolean verify = true;  //是否通过登陆验证
				String time = msg.getTime(); //登陆时间
				if(!StringUtils.isNumeric(time)){ verify = false;}  //登陆时间是否是数字 
				int parseLong = Integer.parseInt(time);
				if(Math.abs(System.currentTimeMillis()/1000-parseLong)>24*60*60){
					verify=false;
				}
				//if(System.currentTimeMillis()-Long.valueOf(time)*1000>config.getLosttime()){ verify = false; } //登陆时间是否过期
				String md5key = config.getMd5key();     //MD5key
				String verifystr = userId+time+md5key;  //待加密字符串
				verifystr = md5(verifystr); 		    //MD5加密
				String sign = msg.getSign();			//登陆标识
				//log.info("config.key="+config.getMd5key()+"\nconfig.losttime="+config.getLosttime()+"\nlogintime="+time+"\nloginuserid="+userId+"\nverifystr="+verifystr+"\nsign="+sign);
				if(!sign.equalsIgnoreCase(verifystr.toUpperCase())){ verify=false;  } //MD5加密后 与 标识不一致-失败
				if(!verify){  //未通过登陆验证 返回失败
					log.error(verify+"/t"+time+"/t"+verifystr+"/t"+sign+"/t"+userId+"/t"+remoteAddress.getAddress().getHostAddress());
					ResLoginFailedMessage fmsg  = new ResLoginFailedMessage();
					fmsg.setReason((byte)4);
					session.write(fmsg);
					return;
				}  
			}

			int country = GateServer.getGameConfig().getCountryByServer(createServer);
			//检查服务器是否包含所选区
			if(country==0){
				ResLoginFailedMessage return_msg  = new ResLoginFailedMessage();
				return_msg.setReason((byte)1);
				session.write(return_msg);
				return;
			}else{
				int server = GateServer.getGameConfig().getServerByCountry(country);
				List<IoSession> sessions = GateServer.getInstance().getGameSession(server);
				if(sessions==null || sessions.size()==0){
					ResLoginFailedMessage return_msg  = new ResLoginFailedMessage();
					return_msg.setReason((byte)1);
					session.write(return_msg);
					return;
				}
			}
			
			User user = null;
			synchronized (obj) {
				//记录登陆账号
				UserDao dao = new UserDao();
				user = dao.select(userId, createServer);
				if(user!=null){
					user.setLastlogintime(System.currentTimeMillis());
					dao.update(user);
				}else{
					if(userId.startsWith("#gm")){ //排除内置GM账号  规则  以 #gm 开头  
						ResLoginFailedMessage fmsg = new ResLoginFailedMessage();
						fmsg.setReason((byte)6);
						session.write(fmsg);
						return;
					}
					user = new User();
					user.setCreatetime(System.currentTimeMillis());
					user.setLastlogintime(System.currentTimeMillis());
					user.setUserid(Config.getId());
					user.setUsername(userId);
					user.setServer(createServer);
					user.setAgentPlusdata(msg.getAgentPlusdata());
					user.setAgentColdatas(msg.getAgentColdatas());
					user.setAgent(msg.getAgent());
					user.setAd(msg.getAd());
					user.setAdregtime(msg.getAdregtime());
					dao.insert(user);
					
					//---------------最后注册时间日志
					RegLog regLog = new RegLog();
					regLog.setUserid(user.getUserid());
					regLog.setUsername(user.getUsername());
					regLog.setType(1);
					regLog.setRevisetime(user.getCreatetime());
					regLog.setCreateserver(createServer);
					regLog.setAgentColdatas(user.getAgentColdatas());
					regLog.setAgentPlusdata(user.getAgentPlusdata());
					regLog.setAgent(msg.getAgent());
					regLog.setAd(msg.getAd());
					LogService.getInstance().execute(regLog);
					streglog(user,createServer);
				}
			}
			
			if(user.getIsForbid()!=null && user.getIsForbid()==1){
				ResLoginFailedMessage fmsg  = new ResLoginFailedMessage();
				fmsg.setReason((byte)2);
				session.write(fmsg);
				return;
			}
			
			if(players.size() > MAX_PLAYER){
				ResLoginFailedMessage fmsg  = new ResLoginFailedMessage();
				fmsg.setReason((byte)3);
				session.write(fmsg);
				return;
			}
			
			userId = Long.toString(user.getUserid());
			
			synchronized (user_states) {
				int state = getUserState(createServer, userId);
				if(state!=0){
					log.error("Login User " + userId + session + " " + getUserStateInfo(state) + " on logining");
					return;
				}
				setUserState(createServer, userId, UserState.LOGINING.getValue());
				log.error("User " + userId + session + " login set state " + getUserStateInfo(UserState.LOGINING.getValue()));
				if(session.containsAttribute(PRELOGINTIME)){
					long time = (Long)session.getAttribute(PRELOGINTIME);
					if(System.currentTimeMillis() - time < 1000){
						ResLoginFailedMessage return_msg  = new ResLoginFailedMessage();
						return_msg.setReason((byte)20);
						session.write(return_msg);
						return;
					}
				}
				session.setAttribute(PRELOGINTIME, System.currentTimeMillis());
			}
			
			//检查是否当前有同服务器同账号在线session
			IoSession oldSession = GateServer.getInstance().getSessionByUser(createServer, userId);
			if(oldSession!=null && oldSession.getId()!=session.getId()){
				//被人顶替下线
				closelog.error("player " + userId + "(" + oldSession.getRemoteAddress() + ") replace by {" + userId + "}(" + session.getRemoteAddress() + ")");
				
				if(oldSession.isConnected()){
					ResSubstituteMessage closemsg = new ResSubstituteMessage();
					try{
						closemsg.setIp(ip);
					}catch (Exception e) {
						log.error(e, e);
					}
					oldSession.write(closemsg);
				}
				
				long roleId = 0;
				if(oldSession.containsAttribute(PLAYER_ID)){
					try{
						roleId = (Long)oldSession.removeAttribute(PLAYER_ID);
						GateServer.getInstance().removeRoleSession(roleId);
					}catch (Exception e) {
						log.error(e, e);
					}
				}
				if(oldSession.containsAttribute(USER_ID)){
					try{
						String oldUserId = (String)oldSession.getAttribute(USER_ID);
						GateServer.getInstance().removeUserSession(oldUserId);
					}catch (Exception e) {
						log.error(e, e);
					}
				}
				//关闭连接
//				oldSession.close(false);
				new CloseThread(oldSession).start();
			}
			try{
				if(ip!=null) session.setAttribute(SESSION_IP, ip);
			}catch (Exception e) {
				log.error(e, e);
			}
			sessionId++;
			session.setAttribute(SESSION_ID, sessionId);
			session.setAttribute(USER_NAME, msg.getUsername());
			session.setAttribute(AGENTPLUSDATA, msg.getAgentPlusdata());
			session.setAttribute(AGENTColDATA, msg.getAgentColdatas());
			try{
				int loginType = Integer.parseInt(msg.getLogintype());
				log.error("login:484\t"+msg.getUsername()+"\t登录类型-"+loginType);
				session.setAttribute(SESSION_LOGINTYPE, loginType);
			}catch (Exception e) {
				log.error(e, e);
			}
			
			//注册当前玩家
			GateServer.getInstance().registerUser(session, createServer, userId, Integer.parseInt(msg.getIsadult()));
			
			//发送GM状态
			try{
				GameMasterDao masterdao = new GameMasterDao();
				GameMaster master = masterdao.selectByUserid(user.getUserid());
				if(master!=null){
					GmLevelMessage gmmsg = new GmLevelMessage();
					gmmsg.setLevel(master.getGmlevel());
					session.write(gmmsg);
				}
			}catch (Exception e) {
				log.error(e, e);
			}
			
			//当前用户是否有角色在线
			ConcurrentHashMap<String, Player> players = user_players.get(createServer);
			if(players!=null){
				Player player = players.get(userId);
				if(player!=null){
					synchronized (user_states) {
						int state = removeUserState(createServer, userId);
						log.error("User " + userId + session + " remove " + getUserStateInfo(state) + " for select online " + player.getId());
					}
					log.error("User " + userId + session + " have player" + player.getId() + "online!");
					//通知游戏服务器登录角色
					selectCharacter(session, player.getId());
					return;
				}
			}
			
			//查询当前用户可登录角色列表
			RoleDao roledao = new RoleDao();
			List<Role> characters = roledao.selectByUser(userId, createServer);
			ResCharacterInfosMessage return_msg = new ResCharacterInfosMessage();
			for (int i = 0; i < characters.size(); i++) {
				Role role = characters.get(i);
				CharacterInfo info = new CharacterInfo();
				info.setName(role.getName());
				info.setPlayerId(role.getRoleid());
				info.setLevel(role.getLevel());
				info.setSex((byte)(int)role.getSex());
				if (role.getLogintime() == null) {
					info.setLongintime(0);
				}else {
					info.setLongintime((int)(role.getLogintime()/1000));
				}
				
				return_msg.getCharacters().add(info);
			}

			//返回角色列表
			session.write(return_msg);
			log.error(session + "发送登陆消息,时间:" + System.currentTimeMillis());
			synchronized (user_states) {
				int state = removeUserState(createServer, userId);
				log.error("User " + userId + session + " remove " + getUserStateInfo(state) + " for login finish");
			}
			long end = System.currentTimeMillis();
			log.error("login cost:" + (end -start));
			log.debug("user login "+ userId);
		}catch (Exception e) {
			log.error(e, e);
			//断开连接
			SessionUtil.closeSession(session, e.getMessage());
		}finally{
			synchronized (user_states) {
				int state = removeUserState(createServer, userId);
				log.error("User " + userId + session + " remove " + getUserStateInfo(state) + " finally");
			}
		}
	}

	
//	/**
//	 * 用户登录
//	 * @param msg 登录消息
//	 */
//	public void login(IoSession session, ReqLoginForNoUserMessage msg){
//		int createServer = Integer.parseInt(msg.getServerId());
//		String userId = msg.getUsername();
//		try{
//			byte guest = 0;
//			if(userId==null || "".equals(userId)){
//				userId = Long.toHexString(Config.getId());
//				ResCreateUserMessage remsg = new ResCreateUserMessage();
//				remsg.setUserId(userId);
//				session.write(remsg);
//				
//				guest = 1;
//			}
//
//			//记录登陆账号
//			UserDao dao = new UserDao();
//			User user = dao.select(userId, createServer);
//			if(user!=null){
//				user.setLastlogintime(System.currentTimeMillis());
//				dao.update(user);
//			}else{
//				user = new User();
//				user.setCreatetime(System.currentTimeMillis());
//				user.setLastlogintime(System.currentTimeMillis());
//				user.setUserid(Config.getId());
//				user.setUsername(userId);
//				user.setServer(createServer);
//				dao.insert(user);
//				guest = 1;
//				streglog(user,createServer);
//			}
//			
//			if(user.getIsForbid()!=null && user.getIsForbid()==1){
//				ResLoginFailedMessage fmsg  = new ResLoginFailedMessage();
//				fmsg.setReason((byte)2);
//				session.write(fmsg);
//				return;
//			}
//			
//			if(players.size() > MAX_PLAYER){
//				ResLoginFailedMessage fmsg  = new ResLoginFailedMessage();
//				fmsg.setReason((byte)3);
//				session.write(fmsg);
//				return;
//			}
//			
//			userId = Long.toString(user.getUserid());
//			
//			synchronized (user_states) {
//				synchronized (user_states) {
//					int state = getUserState(createServer, userId);
//					if(state!=0){
//						log.error("User " + userId + session + " " + getUserStateInfo(state));
//						return;
//					}
//					setUserState(createServer, userId, UserState.LOGINING.getValue());
//					log.debug("User " + userId + session + " logining");
//				}
//			}
//			
//			//检查是否当前有同服务器同账号在线session
//			IoSession oldSession = GateServer.getInstance().getSessionByUser(createServer, userId);
//			if(oldSession!=null && oldSession.getId()!=session.getId()){
//				//被人顶替下线
//				closelog.error("player " + userId + "(" + oldSession.getRemoteAddress() + ") replace by {" + userId + "}(" + session.getRemoteAddress() + ")");
//				
//				if(session!=null && session.getRemoteAddress()!=null && oldSession.isConnected()){
//					ResSubstituteMessage closemsg = new ResSubstituteMessage();
//					try{
//						closemsg.setIp(((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress());
//					}catch (Exception e) {
//						log.error(e, e);
//					}
//					oldSession.write(closemsg);
//				}
//				
//				if(oldSession.containsAttribute(PLAYER_ID)){
//					try{
//						long roleId = (Long)oldSession.removeAttribute(PLAYER_ID);
//						GateServer.getInstance().removeRoleSession(roleId);
//					}catch (Exception e) {
//						log.error(e, e);
//					}
//				}
//				if(oldSession.containsAttribute(USER_ID)){
//					try{
//						String oldUserId = (String)oldSession.getAttribute(USER_ID);
//						GateServer.getInstance().removeUserSession(oldUserId);
//					}catch (Exception e) {
//						log.error(e, e);
//					}
//				}
//				//关闭连接
////				oldSession.close(false);
//				SessionUtil.closeSession(oldSession, "被顶号");
//			}
//			
//			try{
//				session.setAttribute(SESSION_IP, ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress());
//			}catch (Exception e) {
//				log.error(e, e);
//			}
//			
//			sessionId++;
//			session.setAttribute(SESSION_ID, sessionId);
//			
//			//注册当前玩家
//			GateServer.getInstance().registerUser(session, createServer, userId, Integer.parseInt(msg.getIsadult()));
//			//当前用户是否有角色在线
//			ConcurrentHashMap<String, Player> players = user_players.get(createServer);
//			if(players!=null){
//				Player player = players.get(userId);
//				if(player!=null){
//					synchronized (user_states) {
//						int state = removeUserState(createServer, userId);
//						log.debug("User " + userId + session + " remove " + getUserStateInfo(state));
//					}
//					//通知游戏服务器登录角色
//					selectCharacter(session, player.getId());
//					return;
//				}
//			}
//			
//			//检查服务器是否包含所选区
//			if(GateServer.getGameConfig().getCountryByServer(createServer)==0){
//				ResLoginFailedMessage return_msg  = new ResLoginFailedMessage();
//				return_msg.setReason((byte)1);
//				session.write(return_msg);
//				synchronized (user_states) {
//					int state = removeUserState(createServer, userId);
//					log.debug("User " + userId + session + " remove " + getUserStateInfo(state));
//				}
//				return;
//			}
//
//			//查询当前用户可登录角色列表
//			RoleDao roledao = new RoleDao();
//			List<Role> characters = roledao.selectByUser(userId, createServer);
////			ResCharacterInfosMessage return_msg = new ResCharacterInfosMessage();
////			for (int i = 0; i < characters.size(); i++) {
////				Role role = characters.get(i);
////				CharacterInfo info = new CharacterInfo();
////				info.setName(role.getName());
////				info.setPlayerId(role.getRoleid());
////				info.setLevel(role.getLevel());
////				info.setSex((byte)(int)role.getSex());
////				if (role.getLongintime() == null) {
////					info.setLongintime(0);
////				}else {
////					info.setLongintime((int)(role.getLongintime()/1000));
////				}
////				
////				return_msg.getCharacters().add(info);
////			}
////			//返回角色列表
////			session.write(return_msg);
//			if(characters.size() > 0){
//				Role role = characters.get(0);
//				synchronized (user_states) {
//					int state = removeUserState(createServer, userId);
//					log.debug("User " + userId + session + " remove " + getUserStateInfo(state));
//				}
//				//通知游戏服务器登录角色
//				selectCharacter(session, role.getRoleid());
//				return;
//			}else{
//				synchronized (user_states) {
//					int state = removeUserState(createServer, userId);
//					log.debug("User " + userId + session + " remove " + getUserStateInfo(state));
//				}
//				
//				//通知游戏服务器登录角色
//				createCharacter(session, msg.getName(), msg.getIcon(), msg.getSex(), guest, (byte)1);
//				return;
//			}
//			
////			synchronized (user_states) {
////				int state = removeUserState(createServer, userId);
////				log.debug("User " + userId + session + " remove " + getUserStateInfo(state));
////			}
////			log.debug("user login "+ userId);
//		}catch (SQLException e) {
//			log.error(e, e);
//		}catch (Exception e) {
//			log.error(e, e);
//		}finally{
//			synchronized (user_states) {
//				int state = removeUserState(createServer, userId);
//				log.debug("User " + userId + session + " remove " + getUserStateInfo(state));
//			}
//		}
//	}
	
	/**
	 * 创建角色
	 * @param session
	 * @param name
	 * @param icon
	 * @param sex
	 */
	public void createCharacter(IoSession session, String name, String icon, byte sex, byte guest, byte auto){
		String userId = (String)session.getAttribute(USER_ID);
		int createServer = (Integer)session.getAttribute(SERVER_ID);
		int isAdult = 0;
		if(session.containsAttribute(IS_ADULT)){
			isAdult = (Integer)session.getAttribute(IS_ADULT);
		}
		String agentPlusdata = "";
		if(session.containsAttribute(AGENTPLUSDATA)){
			agentPlusdata = (String)session.getAttribute(AGENTPLUSDATA);
		}
		
		String agentColdata = "";
		if(session.containsAttribute(AGENTColDATA)){
			agentColdata = (String)session.getAttribute(AGENTColDATA);
		}
		
		String userName = "";
		if(session.containsAttribute(USER_NAME)){
			userName = (String)session.getAttribute(USER_NAME);
		}
		
		synchronized (user_states) {
			int state = getUserState(createServer, userId);
			if(state!=0){
				log.error("User " + userId + session + " " + getUserStateInfo(state) + " on creating");
				return;
			}
			setUserState(createServer, userId, UserState.CREATING.getValue());
			log.error("User " + userId + session + " creating set state " + getUserStateInfo(UserState.CREATING.getValue()));
			
			if(session.containsAttribute(PRECREATETIME)){
				long time = (Long)session.getAttribute(PRECREATETIME);
				if(System.currentTimeMillis() - time < 1000){
					ResLoginFailedMessage return_msg  = new ResLoginFailedMessage();
					return_msg.setReason((byte)21);
					session.write(return_msg);
					return;
				}
			}
			session.setAttribute(PRECREATETIME, System.currentTimeMillis());
		}
		//发送到游戏服务器
		ReqCreateCharacterToGameMessage msg = new ReqCreateCharacterToGameMessage();
		msg.setGateId(GateServer.getInstance().getServerId());
		msg.setUserId(userId);
		msg.setUserName(userName);
		msg.setCreateServer(createServer);
		msg.setName(name);
		msg.setSex(sex);
		msg.setAuto(auto);
		msg.setIcon(icon);
		msg.setIsAdult((byte)isAdult);
		msg.setIsGuest(guest);
		msg.setAgentColdatas(agentColdata);
		msg.setAgentPlusdata(agentPlusdata);
		String hostAddress = "";
		try{
			hostAddress = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
		}catch (Exception e) {
			log.error(e, e);
		}
		msg.setOptIp(hostAddress);
		if (session.containsAttribute(SESSION_LOGINTYPE)) {
			int logintype = (Integer) session.getAttribute(SESSION_LOGINTYPE);
			log.error("in createCharacter:"+msg.getUserName()+"\t登录类型-"+logintype);
			msg.setLogintype(logintype);
		}
		
		int country = GateServer.getGameConfig().getCountryByServer(createServer);
		int server = GateServer.getGameConfig().getServerByCountry(country);//(Integer)session.getAttribute(SERVER_ID);
		int sessionId = (Integer)session.getAttribute(SESSION_ID);
		boolean success = MessageUtil.send_to_game(server, sessionId, msg);
		if(!success){
			log.error("create " + userId + " failed by send message");
			synchronized (user_states) {
				int state = removeUserState(createServer, userId);
				log.error("User " + userId + session + " remove " + getUserStateInfo(state) + " for create send message failed!");
			}
			ResLoginFailedMessage fail_msg  = new ResLoginFailedMessage();
			fail_msg.setReason((byte)1);
			session.write(fail_msg);
		}
		log.debug("createRole "+msg);
	}
	
	/**
	 * 创建角色
	 * @param session
	 * @param name
	 * @param icon
	 * @param sex
	 */
	public void createCharacterNotEnterMap(IoSession session, String name, String icon, byte sex, byte guest, byte auto){
		String userId = (String)session.getAttribute(USER_ID);
		int createServer = (Integer)session.getAttribute(SERVER_ID);
		int isAdult = 0;
		if(session.containsAttribute(IS_ADULT)){
			isAdult = (Integer)session.getAttribute(IS_ADULT);
		}
		String agentPlusdata = "";
		if(session.containsAttribute(AGENTPLUSDATA)){
			agentPlusdata = (String)session.getAttribute(AGENTPLUSDATA);
		}
		
		String agentColdata = "";
		if(session.containsAttribute(AGENTColDATA)){
			agentColdata = (String)session.getAttribute(AGENTColDATA);
		}
		
		String userName = "";
		if(session.containsAttribute(USER_NAME)){
			userName = (String)session.getAttribute(USER_NAME);
		}
		
		synchronized (user_states) {
			int state = getUserState(createServer, userId);
			if(state!=0){
				log.error("User " + userId + session + " " + getUserStateInfo(state) + " on creating");
				return;
			}
			setUserState(createServer, userId, UserState.CREATING.getValue());
			log.error("User " + userId + session + " creating set state " + getUserStateInfo(UserState.CREATING.getValue()));
			
			if(session.containsAttribute(PRECREATETIME)){
				long time = (Long)session.getAttribute(PRECREATETIME);
				if(System.currentTimeMillis() - time < 1000){
					ResLoginFailedMessage return_msg  = new ResLoginFailedMessage();
					return_msg.setReason((byte)21);
					session.write(return_msg);
					return;
				}
			}
			session.setAttribute(PRECREATETIME, System.currentTimeMillis());
		}
		//发送到游戏服务器
		ReqJustCreateCharacterToGameMessage msg = new ReqJustCreateCharacterToGameMessage();
		msg.setGateId(GateServer.getInstance().getServerId());
		msg.setUserId(userId);
		msg.setUserName(userName);
		msg.setCreateServer(createServer);
		msg.setName(name);
		msg.setSex(sex);
		msg.setAuto(auto);
		msg.setIcon(icon);
		msg.setIsAdult((byte)isAdult);
		msg.setIsGuest(guest);
		msg.setAgentColdatas(agentColdata);
		msg.setAgentPlusdata(agentPlusdata);
		String hostAddress = "";
		try{
			hostAddress = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
		}catch (Exception e) {
			log.error(e, e);
		}
		msg.setOptIp(hostAddress);
		if (session.containsAttribute(SESSION_LOGINTYPE)) {
			int logintype = (Integer) session.getAttribute(SESSION_LOGINTYPE);
			log.error("in createCharacter:"+msg.getUserName()+"\t登录类型-"+logintype);
			msg.setLogintype(logintype);
		}
		
		int country = GateServer.getGameConfig().getCountryByServer(createServer);
		int server = GateServer.getGameConfig().getServerByCountry(country);//(Integer)session.getAttribute(SERVER_ID);
		int sessionId = (Integer)session.getAttribute(SESSION_ID);
		boolean success = MessageUtil.send_to_game(server, sessionId, msg);
		if(!success){
			log.error("create " + userId + " failed by send message");
			synchronized (user_states) {
				int state = removeUserState(createServer, userId);
				log.error("User " + userId + session + " remove " + getUserStateInfo(state) + " for create send message failed!");
			}
			ResLoginFailedMessage fail_msg  = new ResLoginFailedMessage();
			fail_msg.setReason((byte)1);
			session.write(fail_msg);
		}
		log.debug("createRole "+msg);
	}
	
	/**
	 * 选择角色
	 * @param session
	 * @param playerId
	 */
	public void selectCharacter(IoSession session, long playerId){
		String userId = (String)session.getAttribute(USER_ID);
		String userName= (String) session.getAttribute(USER_NAME);
		int createServer = (Integer)session.getAttribute(SERVER_ID);
		int isAdult = 0;
		if(session.containsAttribute(IS_ADULT)){
			isAdult = (Integer)session.getAttribute(IS_ADULT);
		}
		synchronized (user_states) {
			int state = getUserState(createServer, userId);
			if(state!=0){
				log.error("User " + userId + session + " " + getUserStateInfo(state) + " on selecting");
				return;
			}
			setUserState(createServer, userId, UserState.SELECTING.getValue());
			log.error("User " + userId + session + " selecting set state " + getUserStateInfo(UserState.SELECTING.getValue()));
		}
		//发送到游戏服务器
		ReqLoginCharacterToGameMessage msg = new ReqLoginCharacterToGameMessage();
		msg.setGateId(GateServer.getInstance().getServerId());
		msg.setServerId(createServer);
		msg.setUserId(userId);
		msg.setPlayerId(playerId);
		msg.setIsAdult((byte)isAdult);
		msg.setUserName(userName);
		if (session.containsAttribute(SESSION_LOGINTYPE)) {
			int logintype = (Integer) session.getAttribute(SESSION_LOGINTYPE);
			log.error("selectCharacter:898\t"+msg.getUserName()+"\t登录类型-"+logintype);
			msg.setLogintype(logintype);
		}
		try{
			String hostAddress = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
			msg.setLoginIp(hostAddress);
		}catch (Exception e) {log.error(e,e);}
		int server = 0;//(Integer)session.getAttribute(SERVER_ID);
		Player player = players.get(playerId);
		if(player!=null){
			server = player.getServer();
		}else{
			//查询当前用户可登录角色列表
			RoleDao roledao = new RoleDao();
			try{
				Role character = roledao.selectById(playerId);
				server = GateServer.getGameConfig().getServerByCountry(character.getLocate());
			}catch (Exception e) {
				log.error(e, e);
				//关闭连接
				SessionUtil.closeSession(session, e.getMessage());
				return;
			}
		}
		
		int sessionId = (Integer)session.getAttribute(SESSION_ID);
		boolean success = MessageUtil.send_to_game(server, sessionId, msg);		
		if(!success){
			log.error("select " + userId + " failed by send message");
			synchronized (user_states) {
				int state = removeUserState(createServer, userId);
				log.error("User " + userId + session + " remove " + getUserStateInfo(state) + " for select send message failed");
			}
			ResLoginFailedMessage fail_msg  = new ResLoginFailedMessage();
			fail_msg.setReason((byte)1);
			session.write(fail_msg);
		}
	}
	
	/**
	 * 选择角色
	 * @param session
	 * @param playerId
	 */
	public void reselectCharacter(long playerId, int createServer, String userId, int isAdult){
		synchronized (user_states) {
			int state = getUserState(createServer, userId);
			if(state!=0){
				log.debug("User " + userId + " " + getUserStateInfo(state));
			}
			setUserState(createServer, userId, UserState.SELECTING.getValue());
			log.debug("User " + userId + " selecting");
		}
		
		//发送到游戏服务器
		ReqLoginCharacterToGameMessage msg = new ReqLoginCharacterToGameMessage();
		msg.setGateId(GateServer.getInstance().getServerId());
		msg.setServerId(createServer);
		msg.setUserId(userId);
		msg.setPlayerId(playerId);
		msg.setIsAdult((byte)isAdult);
		
		IoSession session = GateServer.getInstance().getSessionByUser(createServer, userId);
		
		int server = 0;//(Integer)session.getAttribute(SERVER_ID);
		Player player = players.get(playerId);
		if(player!=null){
			server = player.getServer();
		}else{
			//查询当前用户可登录角色列表
			RoleDao roledao = new RoleDao();
			try{
				Role character = roledao.selectById(playerId);
				server = GateServer.getGameConfig().getServerByCountry(character.getLocate());
			}catch (Exception e) {
				log.error(e, e);
				//关闭连接
				SessionUtil.closeSession(session, e.getMessage());
				return;
			}
		}
		
		try{
			msg.setLoginIp(((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress());	
		}catch (Exception e) {
			log.error(e,e);
		}
		
		
		
		int sessionId = 0;
		if(session!=null){
			sessionId = (Integer)session.getAttribute(SESSION_ID);
			String userName  = (String) session.getAttribute(USER_NAME);
			if(userName!=null){
				msg.setUserName(userName);	
			}
		}
		
		boolean success = MessageUtil.send_to_game(server, sessionId, msg);
		if(!success){
			log.error("reselect " + userId + " failed by send message");
			synchronized (user_states) {
				int state = removeUserState(createServer, userId);
				log.debug("User " + userId + " remove " + getUserStateInfo(state));
			}
			ResLoginFailedMessage fail_msg  = new ResLoginFailedMessage();
			fail_msg.setReason((byte)1);
			//获取session
			if(session!=null){
				session.write(fail_msg);
			}
		}
		log.debug("role Login "+msg);
	}
	
	/**
	 * 删除角色
	 * @param session
	 * @param playerId
	 */
	public void deleteCharacter(IoSession session, long playerId){
		String userId = (String)session.getAttribute(USER_ID);
		int createServer = (Integer)session.getAttribute(SERVER_ID);
		String hostAddress = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
		ReqDelPlayerToGameMessage smsg = new ReqDelPlayerToGameMessage();
		smsg.setCreateServer(createServer);
		smsg.setPlayerId(playerId);
		smsg.setUserId(userId);
		smsg.setGateId(GateServer.getInstance().getServerId());
		smsg.setOptIp(hostAddress);
		int sessionId = (Integer)session.getAttribute(SESSION_ID);
		MessageUtil.send_to_game(createServer,sessionId, smsg);		
		return;
//		synchronized (user_states) {
//			int state = getUserState(createServer, userId);
//			if(state!=0){
//				log.error("User " + userId + session + " " + getUserStateInfo(state));
//				return;
//			}
//			setUserState(createServer, userId, UserState.DELETEING.getValue());
//			log.debug("User " + userId + session + " deleteing");
//		}
//		
//
//		//查询当前用户可登录角色列表
//		RoleDao roledao = new RoleDao();
//		Role character = null;
//		try{
//			character = roledao.selectById(playerId);
//		}catch (SQLException e) {
//			log.error(e, e);
//		}
//		
//		if(character==null){
//			removeUserState(createServer, userId);
//			return;
//		}
//		
//		if(!character.getUserid().equals(userId)){
//			removeUserState(createServer, userId);
//			return;
//		}
//		
//		try{
//			roledao.update(character);
//			//查询当前用户可登录角色列表
//			List<Role> characters = roledao.selectByUser(userId, createServer);
//			ResCharacterInfosMessage return_msg = new ResCharacterInfosMessage();
//			for (int i = 0; i < characters.size(); i++) {
//				Role role = characters.get(i);
//				CharacterInfo info = new CharacterInfo();
//				info.setName(role.getName());
//				info.setPlayerId(role.getRoleid());
//				info.setLevel(role.getLevel());
//				info.setSex((byte)(int)role.getSex());
//				return_msg.getCharacters().add(info);
//			}
//
//			//返回角色列表
//			session.write(return_msg);
//		}catch (SQLException e) {
//			log.error(e, e);
//		}
//
//		synchronized (user_states) {
//			int state = removeUserState(createServer, userId);
//			log.debug("User " + userId + session + " remove " + getUserStateInfo(state));
//		}
	}
	
	public void quit(Player player, boolean force){
		String userId = player.getUserId();
		int createServer = player.getCreateServer();
		
		ReqQuitToGameMessage msg = new ReqQuitToGameMessage();
		msg.getRoleId().add(player.getId());
		msg.setForce((byte)(force?1:0));
		
		IoSession session = GateServer.getInstance().getSessionByUser(createServer, userId);
		
		int sessionId = 0;
		if(session!=null){
			sessionId = (Integer)session.getAttribute(SESSION_ID);
		}
		
		MessageUtil.send_to_game(player.getServer(), sessionId, msg);
	}
	
	public void quit(IoSession session){
		String userId = (String)session.getAttribute(USER_ID);
		int createServer = (Integer)session.getAttribute(SERVER_ID);
//		synchronized (user_states) {
//			int state = getUserState(createServer, userId);
//			if(state==UserState.CREATING.getValue() || state==UserState.SELECTING.getValue()){
//				log.error("User " + userId + session + " " + getUserStateInfo(state));
//				setUserState(createServer, userId, UserState.WAITQUITING.getValue());
//				log.debug("User " + userId + session + " waitquiting");
//			}
//		}
		ConcurrentHashMap<String, Player> players = user_players.get(createServer);
		if(players!=null){
			Player player = players.get(userId);
			if(player!=null){
				quit(player, true);
			}
		}
		
	}
	
	/**
	 * 注册玩家信息
	 * @param server
	 * @param userId
	 * @param playerId
	 */
	public void registerPlayer(int server, int createServer, String userId, long playerId){
		//注册角色线路信息
		Player player = null;
		if(players.containsKey(playerId)){
			player = players.get(playerId);
		}else{
			player = new Player();
			player.setId(playerId);
			players.put(playerId, player);
		}
		player.setServer(server);
		player.setCreateServer(createServer);
		player.setUserId(userId);
		//获取session
		IoSession session = GateServer.getInstance().getSessionByUser(createServer, userId);
		if(session==null || !session.isConnected()){
			closelog.error("返回注册玩家(playerId=" + playerId + ", userId=" + userId + ")时找不到该玩家连接！");
			quit(player, true);
			return;
		}//注册角色
		else if(session.getAttribute(PLAYER_ID)==null){
			GateServer.getInstance().registerRole(session, playerId);
		}
		
		ConcurrentHashMap<String, Player> sPlayers = user_players.get(createServer);
		if(sPlayers==null){
			sPlayers = new ConcurrentHashMap<String, Player>();
			user_players.put(createServer, sPlayers);
		}
		sPlayers.put(userId, player);
		
		synchronized (user_states) {
			int state = removeUserState(createServer, userId);
			log.error("User " + userId + session + " remove " + getUserStateInfo(state) + " for " + playerId);
		}
	}
	
	public void createSuccess(int createServer, String userId){
		synchronized (user_states) {
			int state = removeUserState(createServer, userId);
			log.error("User " + userId + " remove " + getUserStateInfo(state) + " for create success");
		}
	}
	
	public void createFailed(int createServer, String userId, byte reason){
		log.error("creating " + userId + " failed reason " + reason);
		synchronized (user_states) {
			int state = removeUserState(createServer, userId);
			log.error("User " + userId + " remove " + getUserStateInfo(state) + " for create failed by " + reason);
		}
		ResCreateFailedMessage msg  = new ResCreateFailedMessage();
		msg.setReason(reason);
		//获取session
		IoSession session = GateServer.getInstance().getSessionByUser(createServer, userId);
		if(session!=null){
			session.write(msg);
		}
	}
	
	public void kickPlayer(long playerId){
		IoSession session = GateServer.getInstance().getSessionByRole(playerId);
		if(session!=null){
//			closelog.error(session + " be kicked!");
//			session.close(true);
			SessionUtil.closeSession(session, "GM指令踢出");
		}
	}
	
	public void loginFailed(int createServer, String userId, byte reason){
		log.error("select " + userId + " failed reason " + reason);
		synchronized (user_states) {
			int state = removeUserState(createServer, userId);
			log.error("User " + userId + " remove " + getUserStateInfo(state) + " for login failed");
		}
		ResLoginFailedMessage msg  = new ResLoginFailedMessage();
		msg.setReason(reason);
		//获取session
		IoSession session = GateServer.getInstance().getSessionByUser(createServer, userId);
		if(session!=null){
			session.write(msg);
		}
	}
	
	/**
	 * 移除玩家信息
	 * @param server
	 * @param userId
	 * @param playerId
	 */
	public void removePlayer(long playerId){
		//移除角色信息
		Player player = players.remove(playerId);
		
		if(player!=null){
			ConcurrentHashMap<String, Player> sPlayers = user_players.get(player.getCreateServer());
			if(sPlayers!=null){
				sPlayers.remove(player.getUserId());
			}
			removeUserState(player.getCreateServer(), player.getUserId());
			IoSession session = GateServer.getInstance().getSessionByUser(player.getCreateServer(), player.getUserId());
			
			if(session!=null){
				if(session.containsAttribute(PLAYER_ID)){
					try{
						long roleId = (Long)session.removeAttribute(PLAYER_ID);
						GateServer.getInstance().removeRoleSession(roleId);
					}catch (Exception e) {
						log.error(e, e);
					}
				}
				if(session.containsAttribute(USER_ID)){
					try{
						String oldUserId = (String)session.getAttribute(USER_ID);
						GateServer.getInstance().removeUserSession(oldUserId);
					}catch (Exception e) {
						log.error(e, e);
					}
				}
//				closelog.error("player " + playerId + " quit!");
//				//关闭连接
//				session.close(true);
				SessionUtil.closeSession(session, "玩家退出");
			}
		}
	}
	
	private String getUserStateInfo(int state){
		switch (state) {
			case 1:
				return "logining";
			case 2:
				return "creating";
			case 3:
				return "selecting";
			case 4:
				return "waitquiting";
			case 5:
				return "quiting";
		}
		
		return "";
	}
	
	private void setUserState(int server, String userId, int value){
		if(user_states.containsKey(server)){
			user_states.get(server).put(userId, value);
		}else{
			ConcurrentHashMap<String, Integer> states = new ConcurrentHashMap<String, Integer>();
			states.put(userId, value);
			user_states.put(server, states);
		}
	}

	public int removeUserState(int server, String userId){
		if(user_states.containsKey(server)){
			ConcurrentHashMap<String, Integer> states = user_states.get(server);
			if(states.containsKey(userId)){
				return states.remove(userId);
			}
		}
		return 0;
	}
	
	private int getUserState(int server, String userId){
		if(user_states.containsKey(server)){
			ConcurrentHashMap<String, Integer> states = user_states.get(server);
			if(states.containsKey(userId)){
				return states.get(userId);
			}
		}
		return 0;
	}
	
	//MD5验证
	protected String md5(String str){
		if(str==null) return null;
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(str.getBytes("UTF-8"));
			StringBuilder ret=new StringBuilder(bytes.length<<1);
			for(int i=0;i<bytes.length;i++){
			  ret.append(Character.forDigit((bytes[i]>>4)&0xf,16));
			  ret.append(Character.forDigit(bytes[i]&0xf,16));
			}
			return ret.toString();
		}catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
	
	public void setToken(String newToken){
		config.setToken(newToken);
	}
	
	public void streglog(User user,int createServer){
		
		//---------------最后注册时间日志
		AllRegLog regLog = new AllRegLog();
		regLog.setUserid(user.getUserid());
		regLog.setType(1);
		regLog.setRevisetime(user.getCreatetime());
		regLog.setCreateserver(createServer);
		regLog.setUsername(user.getUsername());
		LogService.getInstance().execute(regLog);
		
	}
	
	private class CloseThread extends Thread{
		
		private IoSession session;
		
		public CloseThread(IoSession session){
			this.session = session;
		}
		
		@Override
		public void run() {
			try{
				Thread.sleep(5000);
			}catch (Exception e) {
				log.error(e, e);
			}
			
			SessionUtil.closeSession(session, "被顶号", false);
		}
	}
}
