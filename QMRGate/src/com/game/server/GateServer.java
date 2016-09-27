package com.game.server;

import java.lang.management.ManagementFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.game.cache.AbstractWork;
import com.game.cache.executor.NonOrderedQueuePoolExecutor;
import com.game.cache.executor.OrderedQueuePoolExecutor;
import com.game.command.Handler;
import com.game.dblog.LogService;
import com.game.login.config.GateHeartConfig;
import com.game.login.config.HeartConfig;
import com.game.login.handler.ReqHeartHandler;
import com.game.login.loader.GateHeartConfigXmlLoader;
import com.game.login.loader.HeartConfigXmlLoader;
import com.game.manager.ManagerPool;
import com.game.message.Message;
import com.game.message.TransfersMessage;
import com.game.message.pool.MessagePool;
import com.game.mina.impl.ClientServer;
import com.game.mina.impl.InnerServer;
import com.game.mina.impl.MinaServer;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.publogin.manager.PublicPlayerManager;
import com.game.server.config.GameConfig;
import com.game.server.loader.GameConfigXmlLoader;
import com.game.server.log.ServerStartAndStopLog;
import com.game.server.message.ReqRegisterWorldForGateMessage;
import com.game.util.SessionUtil;
import com.game.utils.Global;
import com.game.utils.HttpUtil;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;

public class GateServer extends MinaServer {
	
	private static Logger log = Logger.getLogger(GateServer.class);
	
	private static Logger closelog = Logger.getLogger("GATESESSIONCLOSE");
	
	private static Logger flowlog = Logger.getLogger("GATEFLOW");
	
	private static Logger innerflowlog = Logger.getLogger("GATEINNERFLOW");
	
	private static Logger messagelog = Logger.getLogger("GATEMESSAGE");
	
	private static Logger sessionlog = Logger.getLogger("GATESESSIONCREATE");
	
	private static Logger innercloselog = Logger.getLogger("INNERSESSIONCLOSE");
	
	private static Logger closenumlog = Logger.getLogger("GATESESSIONCLOSENUM");
	
//	private static Logger timercloselog = Logger.getLogger("TIMERSESSIONCLOSE");
	
	private static Object obj = new Object();
	//服务器实例
	private static GateServer server;
	//角色通信列表
	private static ConcurrentHashMap<Long, IoSession> player_sessions = new ConcurrentHashMap<Long, IoSession>();
	//玩家通信列表
	private static ConcurrentHashMap<String, IoSession> user_sessions = new ConcurrentHashMap<String, IoSession>();
	
	private static MessagePool message_pool = new MessagePool();
	//GameServer通信列表
	private static ConcurrentHashMap<Integer, List<IoSession>> gameSessions = new ConcurrentHashMap<Integer, List<IoSession>>();
	//内部通讯服务器
	private static InnerServer innerServer = null;
	//连接世界服务器
	private static ClientServer clientServer = null;
	//默认Mina服务器配置文件
	private static final String defaultMinaServerConfig="gate-config/mina-server-config.xml";
	//默认内部服务器配置文件
	private static final String defaultInnerServerConfig="gate-config/inner-server-config.xml";
	//默认内部客户服务器配置文件
	private static final String defaultClientServerConfig="gate-config/client-server-config.xml";
	
	private static GameConfig config;
	//默认服务器配置文件
	private static final String defaultGameConfig="gate-config/game-config.xml";
	
	private static String SERVER_ID = "serverId";
	
	public  static String startidentity = "";
	//心跳计时开始
	public static long HEART_START = System.currentTimeMillis();
	//心跳配置
	public static HeartConfig heartconfig;
	//服务器心跳配置
	public static GateHeartConfig gateheartconfig;
	//上一次心跳时间
	private static final String PRE_HEART = "pre_heart"; 
	
	private volatile int max_connect = 0;
	
	private volatile int max_message = 0;
	
	private OrderedQueuePoolExecutor sendExcutor = new OrderedQueuePoolExecutor("消息发送队列", 100, -1);
	
	private OrderedQueuePoolExecutor recvExcutor = new OrderedQueuePoolExecutor("消息接收队列", 100, 10000);
		
	private NonOrderedQueuePoolExecutor actionExcutor = new NonOrderedQueuePoolExecutor(500);
	
	public static int MAX_SESSION = 20000;
	
	private boolean connnetSuccess = false;
//	private static boolean connected = false;
	
	public GateServer(){
		this(defaultMinaServerConfig, defaultInnerServerConfig, defaultClientServerConfig, defaultGameConfig);
	}
	
	public GateServer(String minaServerConfig, String innerServerConfig, String clientServerConfig, String gameConfig){
		super(minaServerConfig);
		innerServer = new InnerConnectServer(innerServerConfig);
		clientServer = new ClientConnectServer(clientServerConfig);
		config = new GameConfigXmlLoader().load(gameConfig);
	}
	
	public static GameConfig getGameConfig(){
		return config;
	}
	
	public static GateServer getInstance(String minaServerConfig, String innerServerConfig, String clientServerConfig, String gameConfig){
		synchronized (obj) {
			if(server == null){
				server = new GateServer(minaServerConfig, innerServerConfig, clientServerConfig, gameConfig);
			}
		}
		return server;
	}
	
	public static GateServer getInstance(){
		synchronized (obj) {
			if(server == null){
				server = new GateServer();
			}
		}
		return server;
	}
	
	@Override
	public void run() {
		long begin =System.currentTimeMillis();
		super.run();
		new Thread(innerServer).start();
		new Thread(clientServer).start();
		
		//加载心跳配置
		try{
			heartconfig = new HeartConfigXmlLoader().load("gate-config/heart-config.xml");
			ReqHeartHandler.HEART_TIME = heartconfig.getHearttime();
			ReqHeartHandler.ALLOW_TIME = heartconfig.getAllowtime();
			ReqHeartHandler.SUCCESS    = heartconfig.getSuccess();
			ReqHeartHandler.ERROR      = heartconfig.getError();
			ReqHeartHandler.CLOSE_TIME = heartconfig.getClosetime();
		}catch (Exception e) {
			log.error("启动加载心跳配置异常" + e, e);
		}
		try{
			gateheartconfig = new GateHeartConfigXmlLoader().load("gate-config/gate-heart-config.xml");
			Global.HEART_PARA = gateheartconfig.getHeart_para();
			Global.HEART_WEB  = gateheartconfig.getHeart_web();
		}catch (Exception e) {
			log.error("启动加载服务器心跳配置异常"+e, e);
		}
		//流量测试使用
		new Timer("Quantity-Timer").schedule(new TimerTask(){
			
			@Override
			public void run() {
				if(acceptor==null || acceptor.getStatistics()==null) return;
				acceptor.getStatistics().updateThroughput(System.currentTimeMillis());
				
				StringBuffer buf = new StringBuffer();
				buf.append("WR:" + acceptor.getScheduledWriteBytes()).append(",");
				buf.append("MWR:" + acceptor.getScheduledWriteMessages()).append(",");
				buf.append("WT:" + acceptor.getStatistics().getWrittenBytes()).append(",");
				buf.append("RT:" + acceptor.getStatistics().getReadBytes()).append(",");
				buf.append("MWT:" + acceptor.getStatistics().getWrittenMessages()).append(",");
				buf.append("MRT:" + acceptor.getStatistics().getReadMessages()).append(",");
				buf.append("WS:" + acceptor.getStatistics().getWrittenBytesThroughput()).append(",");
				buf.append("RS:" + acceptor.getStatistics().getReadBytesThroughput());
				
				flowlog.error(buf.toString());
			}
		}, 5 * 1000, 5 * 1000);
		

		//流量测试使用
		new Timer("AllQuantity-Timer").schedule(new TimerTask(){
			
			@Override
			public void run() {
				ConcurrentHashMap<Integer, Long> packages = MessageUtil.packages;
				Iterator<Entry<Integer, Long>> iter = packages.entrySet().iterator();
				long total = 0;
				while (iter.hasNext()) {
					Map.Entry<java.lang.Integer, java.lang.Long> entry = (Map.Entry<java.lang.Integer, java.lang.Long>) iter
							.next();
					total+=entry.getValue();
				}
				
				Object[] objs = packages.entrySet().toArray();
				Arrays.sort(objs, new Comparator<Object>() {
					@Override
					public int compare(Object o1, Object o2) {
						@SuppressWarnings("unchecked")
						Entry<Integer, Long> entry1 = (Entry<Integer, Long>)o1;
						@SuppressWarnings("unchecked")
						Entry<Integer, Long> entry2 = (Entry<Integer, Long>)o2;
						return entry1.getValue() - entry2.getValue() > 0? -1: 1;
					}
				});
				messagelog.debug("currenttime:" + System.currentTimeMillis() + "--> total:" + total + "-->sessions:" + acceptor.getManagedSessionCount() + "-->user:" + user_sessions.size() + "-->player:" + player_sessions.size());
				for (int i = 0; i < objs.length; i++) {
					@SuppressWarnings("unchecked")
					Entry<Integer, Long> entry = (Entry<Integer, Long>)objs[i];
					messagelog.debug(entry.getKey() + "-->" + entry.getValue() + "-->" + MessageUtil.packagenums.get(entry.getKey()) + "-->" + MessageUtil.packagemax.get(entry.getKey()) + "-->" + MessageUtil.packagemin.get(entry.getKey()));
				}
				
				//messagelog.debug("压缩前数据:" + MessageUtil.unzliblength + ", 压缩后数据:" + MessageUtil.zliblength);
			}
		}, 60 * 1000, 60 * 1000);
		
		//发送任务使用
		new Timer("Send-Task-Timer").schedule(new TimerTask(){
			
			@Override
			public void run() {
				flowlog.error("等待执行个数:" + actionExcutor.getActiveCount());
				flowlog.error("等待发送个数:" + sendExcutor.getTaskCounts());
				flowlog.error("等待解码个数:" + recvExcutor.getTaskCounts());
			}
		}, 5 * 1000, 5 * 1000);
		
		
		//消息数量用
		new Timer("AllMessage-Timer").schedule(new TimerTask(){
			@Override
			public void run() {
				messagelog.error("发送消息个数：" + max_message);
				max_message = 0;
			}
		}, 1 * 1000, 1 * 1000);
				
		
		//外网消息定时发送
		new Timer("Send-Timer").schedule(new TimerTask(){
			@Override
			public void run() {
				List<IoSession> sessions = new ArrayList<IoSession>();
				synchronized (player_sessions) {
					sessions.addAll(player_sessions.values());
				}
				
				for (IoSession ioSession : sessions) {
					IoBuffer sendbuf = null;
					synchronized (ioSession) {
						if(ioSession.containsAttribute("SEND_BUF")){
							sendbuf = (IoBuffer)ioSession.getAttribute("SEND_BUF");
							ioSession.removeAttribute("SEND_BUF");
						}
					}
					try{
						if (sendbuf != null && sendbuf.position() > 0) {
							sendbuf.flip();
							ioSession.write(sendbuf);
						}
					}catch (Exception e) {
						continue;
					}
				}
			}
		}, 1, 1);
		
		//内网消息定时发送
		new Timer("Inner-Send-Timer").schedule(new TimerTask(){
			@Override
			public void run() {
				List<IoSession> sessions = new ArrayList<IoSession>();
				synchronized (gameSessions) {
					Iterator<List<IoSession>> iter = gameSessions.values().iterator();
					while (iter.hasNext()) {
						List<IoSession> list = (List<IoSession>) iter.next();
						sessions.addAll(list);
					}
				}
				for (IoSession ioSession : sessions) {
					IoBuffer sendbuf = null;
					synchronized (ioSession) {
						if(ioSession.containsAttribute("SEND_BUF")){
							sendbuf = (IoBuffer)ioSession.getAttribute("SEND_BUF");
							ioSession.removeAttribute("SEND_BUF");
						}
					}
					try{
						if (sendbuf != null && sendbuf.position() > 0) {
							sendbuf.flip();
							WriteFuture wf = ioSession.write(sendbuf);
							wf.await();
						}
					}catch (Exception e) {
						continue;
					}
				}
			}
		}, 1, 1);
				
		//关闭session中空的连接
		new Timer("Close-Session-Timer").schedule(new TimerTask(){
			
			@Override
			public void run() {
				if(acceptor==null || acceptor.getManagedSessions()==null || acceptor.getManagedSessions().size()==0) return;
				long now = System.currentTimeMillis();
				
				IoSession[] sessionArray = acceptor.getManagedSessions().values().toArray(new IoSession[0]);
				for (IoSession ioSession : sessionArray) {
					if(ioSession!=null && ioSession.isConnected()){
						if(now - ioSession.getCreationTime() > 10 * 1000 && !ioSession.containsAttribute(PlayerManager.USER_ID)){
							SessionUtil.closeSession(ioSession, "10秒内没有发送登陆信息");
						}else if(acceptor.getManagedSessionCount() > 5000 && ioSession.containsAttribute(PRE_HEART)){
							long pre = (Long)ioSession.getAttribute(PRE_HEART);
							if(now - pre > 5 * 60 * 1000){
								SessionUtil.closeSession(ioSession, "5分钟内没有发送心跳信息");
							}
						}
					}
				}
			}
		}, 5 * 1000, 5 * 1000);
		
		while(!connnetSuccess){
			try{
				Thread.sleep(1000);
			}catch (Exception e) {
				log.error(e, e);
			}
		}
		
		try{
			HttpUtil.post(Global.HEART_WEB, String.format(Global.HEART_PARA, GateServer.getInstance().getServerWeb(), GateServer.getInstance().getServerId(), 1));
		}catch (Exception e) {
			log.error(e, e);
		}
		
		//内网消息定时发送
		new Timer("Server-Alive-Timer").schedule(new TimerTask(){
			@Override
			public void run() {
				try{
					HttpUtil.post(Global.HEART_WEB, String.format(Global.HEART_PARA, GateServer.getInstance().getServerWeb(), GateServer.getInstance().getServerId(), 2));
				}catch (Exception e) {
					log.error(e, e);
				}
			}
		}, 60 * 1000, 60 * 1000);
		
		log.info("GateServer " + this.getServerId() + " Started");
		try{
			URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
			String runname = ManagementFactory.getRuntimeMXBean().getName();
	        String pid = runname.substring(0, runname.indexOf("@")); 
			ServerStartAndStopLog log=new ServerStartAndStopLog();
			log.setAction("start");
			log.setDatatime(TimeUtil.getNowStringDate());
			log.setLocal(location.toString());
			log.setServerId(String.valueOf(server_id));
			log.setConsuming((int) (System.currentTimeMillis()-begin));
			log.setAppName("gateserver");
			log.setIdentity(GateServer.startidentity);
			log.setPid(pid);
			LogService.getInstance().execute(log);
		}catch (Exception e) {
			log.error(e,e);
		}
	}

	@Override
	public void doCommand(IoSession iosession, IoBuffer buf) {
		//消息处理
		try{
			if(!connnetSuccess){
				SessionUtil.closeSession(iosession, "连接世界服没有完成");
				return;
			}
			int id = buf.getInt();
			
			long sessionId = iosession.getId();
			
			if(id==100201 || id==100209 || id==100208 || id==204202){
				log.error(iosession + "收到登陆消息,时间:" + System.currentTimeMillis());
			}
			
			if(id!=100201 && id!=100209 && id!=100208 && id!=204202 && !iosession.containsAttribute(PlayerManager.USER_ID)){
//				closelog.error(iosession + " is close because send message is not loginmessage!" );
//				iosession.close(true);
				
				SessionUtil.closeSession(iosession, "没有发送登陆消息");
				return;
			}
			
			iosession.setAttribute("PREMESSAGE", id);
			
			recvExcutor.addTask(sessionId, new RWork(id, iosession, buf));
		}catch (Exception e) {
			log.error(e,e);
		}
	}
	
	private class RWork extends AbstractWork{

		private int id;
		
		private IoSession iosession;
		
		private IoBuffer buf;
		
		public RWork(int id, IoSession iosession, IoBuffer buf){
			this.id = id;
			this.iosession = iosession;
			this.buf = buf;
		}
		
		@Override
		public void run() {
			try{
				//生成处理函数
				Handler handler = message_pool.getHandler(id);
				
				if(handler!=null){
					//获取消息体
					Message msg = message_pool.getMessage(id);
					msg.read(buf);
					msg.setSession(iosession);
					//自身处理消息
					handler.setMessage(msg);
					//消息接收时间
					handler.setCreateTime(System.currentTimeMillis());
					actionExcutor.execute(handler);
				}else{
					Object roleId = iosession.getAttribute(PlayerManager.PLAYER_ID);
					if(roleId==null){
						log.error("session:" + iosession + "未绑定角色！");
						return;
					}
					long playerId = (Long)roleId;
					
					TransfersMessage msg = new TransfersMessage();
					msg.setId(id);
					msg.getRoleIds().add(playerId);
					msg.setBytes(new byte[buf.remaining()]);
					buf.get(msg.getBytes());
					
					Player player = ManagerPool.playerManager.getPlayer(playerId);
					if(player==null){
						player = ManagerPool.publicPlayerManager.getPlayer(playerId);
						if(player==null){
							log.error("角色" + playerId + iosession + "未注册！");
							return;
						}
					}
					
					max_message++;
					int sessionId = (Integer)iosession.getAttribute(PlayerManager.SESSION_ID);
					
					MessageUtil.send_to_game(player.getServer(), sessionId, msg);
				}
			}catch (Exception e) {
				log.error(e,e);
			}
		}
	}
	
	/**
	 * 游戏服务器注册
	 * @param id 游戏服务器编号
	 * @param session 通讯接口
	 */
	public synchronized void registerGameServer(int id, IoSession session){
		session.setAttribute(SERVER_ID, id);
		synchronized (gameSessions) {
			List<IoSession> sessions = gameSessions.get(id);
			if(sessions==null){
				sessions = new ArrayList<IoSession>();
				gameSessions.put(id, sessions);
			}
			sessions.add(session);	
		}
	}
	
	/**
	 * 游戏服务器移除
	 * @param id 游戏服务器编号
	 * @param session 通讯接口
	 */
	private void removeGameServer(int id, IoSession session){
		synchronized (gameSessions) {
			List<IoSession> sessions = gameSessions.get(id);
			if(sessions!=null){
				sessions = new ArrayList<IoSession>();
				sessions.remove(session);
			}
		}
	}
	
	public void registerRole(IoSession session, long roleId){
		log.info(roleId + " login!");
		synchronized (player_sessions) {
			session.setAttribute(PlayerManager.PLAYER_ID, roleId);
			player_sessions.put(roleId, session);
		}
		
	}
	
	public void removeRoleSession(long roleId){
		synchronized (player_sessions) {
			player_sessions.remove(roleId);
		}
	}
	
	public void removeUserSession(String userId){
		user_sessions.remove(userId);
	}
	
	public void registerUser(IoSession session, int server, String userId, int isAdult){
		synchronized (session) {
			session.setAttribute(PlayerManager.SERVER_ID, server);
			session.setAttribute(PlayerManager.USER_ID, userId);
			session.setAttribute(PlayerManager.IS_ADULT, isAdult);
			
			user_sessions.put(userId, session);
		}
		
		log.error("USER:" + userId + " -->LOGIN:" + session + " -->USER:" + user_sessions.size() + " -->ONLINE:" + player_sessions.size());
	}
	
	public void removeUserSession(String web, String userId){
		user_sessions.remove(web + "_" + userId);
	}
	
	public void registerUser(IoSession session, int server, String web, String userId, int isAdult){
		synchronized (session) {
			session.setAttribute(PublicPlayerManager.SERVER_ID, server);
			session.setAttribute(PublicPlayerManager.WEB_NAME, web);
			session.setAttribute(PublicPlayerManager.USER_ID, userId);
			session.setAttribute(PublicPlayerManager.IS_ADULT, isAdult);
			
			user_sessions.put(web + "_" + userId, session);
		}
		
		log.error("USER:" + web+ " " + userId + " -->LOGIN:" + session + " -->USER:" + user_sessions.size() + " -->ONLINE:" + player_sessions.size());
	}
	
	public IoSession getSessionByRole(long roleId){
		return player_sessions.get(roleId);
	}
	
	public IoSession getSessionByUser(int server, String userId){
		if(user_sessions.containsKey(userId)){
			return user_sessions.get(userId);
		}
		return null;
	}
	
	public IoSession getSessionByUser(int server, String web, String userId){
		if(user_sessions.containsKey(web + "_" + userId)){
			return user_sessions.get(web + "_" + userId);
		}
		return null;
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable throwable) {
		closelog.debug(session, throwable);
//		if(!(throwable instanceof WriteTimeoutException)){
//		session.close(true);
		SessionUtil.closeSession(session, "发生错误");
//		}
	}

	private int lastCloseTime;
	
	private int closeNum;
	
	@Override
	public void sessionClosed(IoSession iosession) {
		int time = (int)(System.currentTimeMillis()/1000/5);
		if(lastCloseTime!=time){
			closenumlog.error("关闭连接数:" + closeNum + ", 时间:" + lastCloseTime);
			closeNum = 0;
		}
		closeNum++;
		lastCloseTime = time;
		
		StringBuffer buffer = new StringBuffer();
		if(iosession.containsAttribute(PlayerManager.SESSION_IP)){
			buffer.append("ip:" + iosession.getAttribute(PlayerManager.SESSION_IP) + ",");
		}
		if(iosession.containsAttribute(PlayerManager.PLAYER_ID)){
			buffer.append("player:" + iosession.getAttribute(PlayerManager.PLAYER_ID) + ",");
		}
		if(iosession.containsAttribute(PlayerManager.USER_ID)){
			buffer.append("user:" + iosession.getAttribute(PlayerManager.USER_ID) + ",");
		}
		closelog.debug(iosession + "[nowTime:" + System.currentTimeMillis() + ",lastIdleTime:" + iosession.getLastWriterIdleTime() + ", lastTime:" + iosession.getLastWriteTime() + ", waitWrite:" + iosession.getScheduledWriteBytes() + "](" + buffer.toString() + ")" + " closed!");
		boolean quit=false;
		synchronized (iosession) {
			//下线
			if(iosession.containsAttribute(PlayerManager.PLAYER_ID)){
				long roleId = (Long)iosession.getAttribute(PlayerManager.PLAYER_ID);
				
				Player player = ManagerPool.playerManager.getPlayer(roleId);
				if(player==null){
					log.error("角色" + roleId + "未注册！");
				}else{
					ManagerPool.playerManager.quit(player, true);
					quit = true;
					
					//log.info(roleId + " quiting!");
					IoSession session = player_sessions.get(roleId);
					if(session!=null && iosession.getId()==session.getId()){
						removeRoleSession(roleId);
					}
				}
			}
			
			if(iosession.containsAttribute(PlayerManager.USER_ID)){
				//int serverId = (Integer)iosession.getAttribute(PlayerManager.SERVER_ID);
				String userId = (String)iosession.getAttribute(PlayerManager.USER_ID);
				
				IoSession session = user_sessions.get(userId);
				if(session!=null && iosession.getId()==session.getId()){
					user_sessions.remove(userId);
					if(!quit) ManagerPool.playerManager.quit(iosession);
				}
				
				log.error("USER:" + userId + " -->QUIT:" + iosession + " -->USER:" + user_sessions.size() + " -->ONLINE:" + player_sessions.size());
			}
		}
	}

	@Override
	protected void stop() {
		try{
			long begin =System.currentTimeMillis();
			URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
			String runname = ManagementFactory.getRuntimeMXBean().getName();
	        String pid = runname.substring(0, runname.indexOf("@")); 
			ServerStartAndStopLog log=new ServerStartAndStopLog();
			log.setAction("stop");
			log.setDatatime(TimeUtil.getNowStringDate());
			log.setLocal(location.toString());
			log.setServerId(String.valueOf(server_id));
			log.setConsuming((int) (System.currentTimeMillis()-begin));
			log.setAppName("gateserver");
			log.setIdentity(GateServer.startidentity);
			log.setPid(pid);
			LogService.getInstance().execute(log);
			LogService.getInstance().shutdown();
		}catch (Exception e) {
			log.error(e,e);
		}
		//发关闭心跳
		try{
			HttpUtil.post(Global.HEART_WEB, String.format(Global.HEART_PARA, GateServer.getInstance().getServerWeb(), GateServer.getInstance().getServerId(), 3));
		}catch (Exception e) {
			log.error(e, e);
		}
	}
	
	/**
	 * 获得与世界服务器通讯session
	 * @return
	 */
	public IoSession getWorldSession(){
		return clientServer.getWorldSessions().get(0);
	}
	
	/**
	 * 获得与游戏服务器通讯session
	 * @return
	 */
	public List<IoSession> getGameSession(int server){
		return gameSessions.get(server);
	}

	private class InnerConnectServer extends InnerServer{

		protected InnerConnectServer(String serverConfig) {
			super(serverConfig);
			
			new Timer("Inner-Quantity-Timer").schedule(new TimerTask(){
				
			@Override
			public void run() {
					if(acceptor==null || acceptor.getStatistics()==null) return;
					acceptor.getStatistics().updateThroughput(System.currentTimeMillis());
					
					StringBuffer buf = new StringBuffer();
					buf.append("WR:" + acceptor.getScheduledWriteBytes()).append(",");
					buf.append("MWR:" + acceptor.getScheduledWriteMessages()).append(",");
					buf.append("WT:" + acceptor.getStatistics().getWrittenBytes()).append(",");
					buf.append("RT:" + acceptor.getStatistics().getReadBytes()).append(",");
					buf.append("MWT:" + acceptor.getStatistics().getWrittenMessages()).append(",");
					buf.append("MRT:" + acceptor.getStatistics().getReadMessages()).append(",");
					buf.append("WS:" + acceptor.getStatistics().getWrittenBytesThroughput()).append(",");
					buf.append("RS:" + acceptor.getStatistics().getReadBytesThroughput());
					
					innerflowlog.error(buf.toString());
				}
			}, 5 * 1000, 5 * 1000);
		}

		@Override
		public void sessionClosed(IoSession session) {
			innercloselog.error("InnerServer " + session + " closed!");
			//发生错误关闭连接
			if(session.containsAttribute(SERVER_ID)){
				int id = (Integer)session.getAttribute(SERVER_ID);
				removeGameServer(id, session);
			}
		}

		@Override
		public void exceptionCaught(IoSession session, Throwable cause) {
			innercloselog.error("InnerServer error " + session, cause);
//			session.close(true);
		}

		@Override
		public void doCommand(IoSession iosession, IoBuffer buf) {
			//消息处理
			try{
				int id = buf.getInt();
				
				long sendId = buf.getLong();
				
				int roleNum = buf.getInt();
				List<Long> roles = new ArrayList<Long>();
				for (int i = 0; i < roleNum; i++) {
					roles.add(buf.getLong());
				}
				
				//log.debug("<-["+msg.getClass().getSimpleName()+"-" +MessageUtil.castListToString(msg.getRoleId())+"]"+ msg.toString());
				//生成处理函数
				Handler handler = message_pool.getHandler(id);
				
				if(handler!=null){
					//获取消息体
					Message msg = message_pool.getMessage(id);
					msg.read(buf);
					msg.setSession(iosession);
					//自身处理消息
					handler.setMessage(msg);
					actionExcutor.execute(handler);
				}else{

					TransfersMessage msg = new TransfersMessage();
					msg.setId(id);
					msg.setBytes(new byte[buf.remaining()]);
					buf.get(msg.getBytes());
					
					Work work = new Work(roles, msg);
					sendExcutor.addTask(sendId, work);
					
//					//转发给用户
//					if(roles.size() == 0){
//						MessageUtil.tell_world_message(msg);
//					}else{
//						//个别发送
//						for (int i = 0; i < roles.size(); i++) {
//							MessageUtil.tell_player_message(roles.get(i), msg);
//						}
//					}
				}
			}catch (Exception e) {
				log.error(e,e);
			}
		}

		@Override
		protected void stop() {}

		@Override
		public void sessionIdle(IoSession iosession, IdleStatus idlestatus) {
			//log.debug("Innerserver session " + iosession + " idle " + idlestatus);
		}

		@Override
		public void sessionCreate(IoSession iosession) {
		}

		@Override
		public void sessionOpened(IoSession iosession) {
		}
		
	}
	
	private class ClientConnectServer extends ClientServer{

		protected ClientConnectServer(String serverConfig) {
			super(serverConfig);
		}

		@Override
		public void sessionClosed(IoSession iosession) {
			innercloselog.error("world " + iosession + " closed!");
			removeWorldServer(iosession);
		}

		@Override
		public void exceptionCaught(IoSession session, Throwable cause) {
			innercloselog.error("world " + session + " cause " + cause, cause);
//			session.close(true);
		}

		@Override
		public void doCommand(IoSession iosession, IoBuffer buf) {
			//消息处理
			try{
				int id = buf.getInt();
				
				long sendId = buf.getLong();
				
				int roleNum = buf.getInt();
				List<Long> roles = new ArrayList<Long>();
				for (int i = 0; i < roleNum; i++) {
					roles.add(buf.getLong());
				}

				//生成处理函数
				Handler handler = message_pool.getHandler(id);
				
				if(handler!=null){
					//获取消息体
					Message msg = message_pool.getMessage(id);
					msg.read(buf);
					msg.setSession(iosession);
					//自身处理消息
					handler.setMessage(msg);
//					handler.action();
					actionExcutor.execute(handler);
				}else{
					TransfersMessage msg = new TransfersMessage();
					msg.setId(id);
					msg.setBytes(new byte[buf.remaining()]);
					buf.get(msg.getBytes());
					
					Work work = new Work(roles, msg);
					sendExcutor.addTask(sendId, work);
//					//转发给用户
//					if(roles.size() == 0){
//						MessageUtil.tell_world_message(msg);
//					}else{
//						//个别发送
//						for (int i = 0; i < roles.size(); i++) {
//							MessageUtil.tell_player_message(roles.get(i), msg);
//						}
//					}
				}
			}catch (Exception e) {
				log.error(e,e);
			}
		}

		@Override
		protected void stop() {}

		@Override
		public void register(IoSession session, int type) {
			ReqRegisterWorldForGateMessage msg = new ReqRegisterWorldForGateMessage();
			msg.setServerId(this.getServerId());
			msg.setServerName(this.getServerName());
			session.write(msg);
		}

		@Override
		public void sessionIdle(IoSession iosession, IdleStatus idlestatus) {
			//log.debug("Clientserver session " + iosession + " idle " + idlestatus);
		}

		@Override
		public void sessionCreate(IoSession iosession) {
		}

		@Override
		public void sessionOpened(IoSession iosession) {
		}
		
		/**
		 * 游戏服务器移除
		 * @param id 游戏服务器编号
		 * @param session 通讯接口
		 */
		private void removeWorldServer(IoSession session){
			synchronized (worldSessions) {
				if(worldSessions!=null){
					worldSessions.remove(session);
				}
			}
		}

		@Override
		protected void connectComplete() {
			connnetSuccess = true;
		}
	}
	
	private class Work extends AbstractWork{

		private List<Long> roles;
		
		private TransfersMessage msg;
		
		public Work(List<Long> roles, TransfersMessage msg){
			this.roles = roles;
			this.msg = msg;
		}
		
		@Override
		public void run() {
			try{
				//转发给用户
				if(roles.size() == 0){
					MessageUtil.tell_world_message(msg);
				}else{
					//个别发送
					for (int i = 0; i < roles.size(); i++) {
						MessageUtil.tell_player_message(roles.get(i), msg);
					}
				}
			}catch (Exception e) {
				log.error(e,e);
			}
		}
	}

	@Override
	public void sessionIdle(IoSession iosession, IdleStatus idlestatus) {
		//log.debug("Minaserver session " + iosession + " idle " + idlestatus);
		//iosession.close(true);
	}

	@Override
	public void sessionCreate(IoSession iosession) {
//		sessionlog.error(iosession + " create, total" + acceptor.getManagedSessionCount());
//		if(acceptor.getManagedSessionCount() > MAX_SESSION){
//			closelog.debug(iosession + " because too much session(" + acceptor.getManagedSessionCount() + ") closed!");
//			iosession.close(true);
//		}
//		if(max_connect < acceptor.getManagedSessionCount()){
//			max_connect = acceptor.getManagedSessionCount();
//		}
//		sessionlog.error("session max create:" + max_connect);
	}

	@Override
	public void sessionOpened(IoSession iosession) {
		sessionlog.error(iosession + " open, total" + acceptor.getManagedSessionCount());
		if(acceptor.getManagedSessionCount() > MAX_SESSION){
//			closelog.debug(iosession + " because too much session(" + acceptor.getManagedSessionCount() + ") closed!");
//			iosession.close(true);
			SessionUtil.closeSession(iosession, "连接数过多(" + acceptor.getManagedSessionCount() + ")");
		}
		if(max_connect < acceptor.getManagedSessionCount()){
			max_connect = acceptor.getManagedSessionCount();
		}
		if(!iosession.containsAttribute(PRE_HEART)){
			iosession.setAttribute(PRE_HEART, System.currentTimeMillis());
		}
		sessionlog.error("session max create:" + max_connect);
	}
}


