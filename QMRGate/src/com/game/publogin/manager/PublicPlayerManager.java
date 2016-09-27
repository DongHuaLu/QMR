package com.game.publogin.manager;

import java.net.InetSocketAddress;
import java.security.MessageDigest;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.game.login.message.ResLoginFailedMessage;
import com.game.login.message.ResSubstituteMessage;
import com.game.player.loader.VerifyConfigXmlLoader;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.UserState;
import com.game.player.structs.VerifyConfig;
import com.game.publogin.message.ReqLoginCharacterToPublicGameMessage;
import com.game.publogin.message.ReqLoginForPublicMessage;
import com.game.server.GateServer;
import com.game.util.SessionUtil;
import com.game.utils.MessageUtil;

public class PublicPlayerManager {
	
	private Logger log = Logger.getLogger(PlayerManager.class);
	
	private static Logger closelog = Logger.getLogger("GATESESSIONCLOSE");
	
	private static Object obj = new Object();
	//玩家管理类实例
	private static PublicPlayerManager manager;

	//用户登录玩家列表 1层key为服务器id 2层key为userid
	private static ConcurrentHashMap<Integer, ConcurrentHashMap<String, Player>> user_players = new ConcurrentHashMap<Integer, ConcurrentHashMap<String, Player>>();
	//玩家列表
	private static ConcurrentHashMap<Long, Player> players = new ConcurrentHashMap<Long, Player>();
	//用户登录玩家列表 1层key为服务器id 2层key为userid
	private static ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>> user_states = new ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>>();
	
	public static int MAX_PLAYER = 4000;
	
	public static String USER_ID = "userId";
	
	public static String WEB_NAME = "web";
	
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
	
	private VerifyConfig config = new VerifyConfigXmlLoader().load("gate-config/verify-config.xml"); //读取验证配置
	
	private PublicPlayerManager(){}
	
	public static PublicPlayerManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new PublicPlayerManager();
			}
		}
		return manager;
	}
	
	/**
	 * 公共服务器用户登录
	 * @param msg 登录消息
	 */
	public void login(IoSession session, ReqLoginForPublicMessage msg){
		int publicServer = Integer.parseInt(msg.getServerId());
				
		String userName = msg.getUsername();
		String userId = msg.getUserId();
		String web = msg.getWeb();
		
		long playerId = Long.parseLong(msg.getPlayerId());

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
//			if(ip==null || (!ip.startsWith("192.168.") && !ip.startsWith("127.0.0.1"))){
//				boolean verify = true;  //是否通过登陆验证
//				String time = msg.getTime(); //登陆时间
//				if(!StringUtils.isNumeric(time)){ verify = false;}  //登陆时间是否是数字 
//				int parseLong = Integer.parseInt(time);
//				if(Math.abs(System.currentTimeMillis()/1000-parseLong)>24*60*60){
//					verify=false;
//				}
//				//if(System.currentTimeMillis()-Long.valueOf(time)*1000>config.getLosttime()){ verify = false; } //登陆时间是否过期
//				String md5key = config.getMd5key();     //MD5key
//				String verifystr = userName+time+md5key;  //待加密字符串
//				verifystr = md5(verifystr); 		    //MD5加密
//				String sign = msg.getSign();			//登陆标识
//				//log.info("config.key="+config.getMd5key()+"\nconfig.losttime="+config.getLosttime()+"\nlogintime="+time+"\nloginuserid="+userId+"\nverifystr="+verifystr+"\nsign="+sign);
//				if(!sign.equalsIgnoreCase(verifystr.toUpperCase())){ verify=false;  } //MD5加密后 与 标识不一致-失败
//				if(!verify){  //未通过登陆验证 返回失败
//					log.error(verify+"/t"+time+"/t"+verifystr+"/t"+sign+"/t"+userName+"/t"+remoteAddress.getAddress().getHostAddress());
//					ResLoginFailedMessage fmsg  = new ResLoginFailedMessage();
//					fmsg.setReason((byte)4);
//					session.write(fmsg);
//					return;
//				}  
//			}
			
			if(players.size() > MAX_PLAYER){
				ResLoginFailedMessage fmsg  = new ResLoginFailedMessage();
				fmsg.setReason((byte)3);
				session.write(fmsg);
				return;
			}
			
			synchronized (user_states) {
				int state = getUserState(publicServer, web, userId);
				if(state!=0){
					log.error("Login User " + web + " " + userId + session + " " + getUserStateInfo(state));
					return;
				}
				setUserState(publicServer, web, userId, UserState.LOGINING.getValue());
				log.error("User " + web + " " + userId + session + " login set state " + getUserStateInfo(UserState.LOGINING.getValue()));
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
			IoSession oldSession = GateServer.getInstance().getSessionByUser(publicServer, web, userId);
			if(oldSession!=null && oldSession.getId()!=session.getId()){
				//被人顶替下线
				closelog.error("player " + web + " " + userId + "(" + oldSession.getRemoteAddress() + ") replace by {" + userId + "}(" + session.getRemoteAddress() + ")");
				
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
			
			//注册当前玩家
			GateServer.getInstance().registerUser(session, publicServer, web, userId, 0);//Integer.parseInt(msg.getIsadult())
			
			synchronized (user_states) {
				int state = removeUserState(publicServer, web, userId);
				log.error("User "+ web + " " + userId + session + " remove " + getUserStateInfo(state) + " for select online " + playerId);
			}
			selectCharacter(session, playerId);

			long end = System.currentTimeMillis();
			log.error("login cost:" + (end -start));
			log.debug("user login " + web + " " + userId);
		}catch (Exception e) {
			log.error(e, e);
			//断开连接
			SessionUtil.closeSession(session, e.getMessage());
		}finally{
			synchronized (user_states) {
				int state = removeUserState(publicServer, web, userId);
				log.error("User " + web + " " + userId + session + " remove " + getUserStateInfo(state) + " finally");
			}
		}
	}
	
	/**
	 * 选择角色(跨服服务器)
	 * @param session
	 * @param playerId
	 */
	public void selectCharacter(IoSession session, long playerId){
		String userId = (String)session.getAttribute(USER_ID);
		String web = (String)session.getAttribute(WEB_NAME);
		String userName= (String) session.getAttribute(USER_NAME);
		int publicServer = (Integer)session.getAttribute(SERVER_ID);
		int isAdult = 0;
		if(session.containsAttribute(IS_ADULT)){
			isAdult = (Integer)session.getAttribute(IS_ADULT);
		}
		synchronized (user_states) {
			int state = getUserState(publicServer, web, userId);
			if(state!=0){
				log.error("User " + web + " " + userId + session + " " + getUserStateInfo(state));
				return;
			}
			setUserState(publicServer, web, userId, UserState.SELECTING.getValue());
			log.error("User " + web + " " + userId + session + " selecting set state " + getUserStateInfo(UserState.SELECTING.getValue()));
		}
		//发送到游戏服务器
		ReqLoginCharacterToPublicGameMessage msg = new ReqLoginCharacterToPublicGameMessage();
		msg.setGateId(GateServer.getInstance().getServerId());
		msg.setServerId(publicServer);
		msg.setWeb(web);
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
		
		int sessionId = (Integer)session.getAttribute(SESSION_ID);
		boolean success = MessageUtil.send_to_game(publicServer, sessionId, msg);		
		if(!success){
			log.error("select " + userId + " failed by send message");
			synchronized (user_states) {
				int state = removeUserState(publicServer, web, userId);
				log.error("User " + web + " " + userId + session + " remove " + getUserStateInfo(state) + " for select send message failed");
			}
			ResLoginFailedMessage fail_msg  = new ResLoginFailedMessage();
			fail_msg.setReason((byte)1);
			session.write(fail_msg);
		}
	}
	
	public Player getPlayer(long playerId){
		return players.get(playerId);
	}
	
	public ConcurrentHashMap<Long, Player> getPlayers(){
		return players;
	}
	
	/**
	 * 注册玩家信息
	 * @param server
	 * @param userId
	 * @param playerId
	 */
	public void registerPlayer(int server, String web, String userId, long playerId){
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
		player.setWeb(web);
		player.setUserId(userId);
		//获取session
		IoSession session = GateServer.getInstance().getSessionByUser(server, web, userId);
		if(session==null || !session.isConnected()){
			closelog.error("返回注册玩家(playerId=" + playerId + ", userId=" + userId + ")时找不到该玩家连接！");
			//TODO 
			//quit(player, true);
			return;
		}//注册角色
		else if(session.getAttribute(PLAYER_ID)==null){
			GateServer.getInstance().registerRole(session, playerId);
		}
		
		ConcurrentHashMap<String, Player> sPlayers = user_players.get(server);
		if(sPlayers==null){
			sPlayers = new ConcurrentHashMap<String, Player>();
			user_players.put(server, sPlayers);
		}
		sPlayers.put(userId, player);
		
		synchronized (user_states) {
			int state = removeUserState(server, web, userId);
			log.error("User " + web + " " + userId + session + " remove " + getUserStateInfo(state) + " for " + playerId);
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
	
	private void setUserState(int server, String web, String userId, int value){
		String userkey = web + "_" + userId;
		if(user_states.containsKey(server)){
			user_states.get(server).put(userkey, value);
		}else{
			ConcurrentHashMap<String, Integer> states = new ConcurrentHashMap<String, Integer>();
			states.put(userkey, value);
			user_states.put(server, states);
		}
	}

	public int removeUserState(int server, String web, String userId){
		String userkey = web + "_" + userId;
		if(user_states.containsKey(server)){
			ConcurrentHashMap<String, Integer> states = user_states.get(server);
			if(states.containsKey(userkey)){
				return states.remove(userkey);
			}
		}
		return 0;
	}
	
	private int getUserState(int server, String web, String userId){
		String userkey = web + "_" + userId;
		if(user_states.containsKey(server)){
			ConcurrentHashMap<String, Integer> states = user_states.get(server);
			if(states.containsKey(userkey)){
				return states.get(userkey);
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
