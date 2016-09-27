package com.game.server.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.util.ConcurrentHashSet;

import com.game.cache.AbstractWork;
import com.game.cache.executor.OrderedQueuePoolExecutor;
import com.game.command.Handler;
import com.game.enter.timer.EnterTimer;
import com.game.message.Message;
import com.game.message.pool.MessagePool;
import com.game.mina.impl.InnerServer;
import com.game.server.command.ServerCloseCommand;
import com.game.server.config.PublicGameServerInfo;
import com.game.server.loader.PublicGameServersConfigXmlLoader;
import com.game.server.thread.ServerThread;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * 游戏服务器
 */
public class PublicServer extends InnerServer {
	
	private static Logger log = Logger.getLogger(PublicServer.class);
	
	private static Logger flowlog = Logger.getLogger("SERVERFLOW");
	
	private static Logger closelog = Logger.getLogger("SERVERSESSIONCLOSE");
	
	private static String SESSION_GAME = "session_game";
	
	private static String SESSION_WEB = "session_web";
	
	private static String SESSION_VERSION = "session_version";
	
	private static Object obj = new Object();
	//服务器实例
	private static PublicServer server;
	
	private static MessagePool message_pool = new MessagePool();
	//服务器线程
	private ServerThread wServerThread;
	//服务器启动线程组
	private ThreadGroup thread_group;
	//默认Mina服务器配置文件
	private static final String defaultInnerServerConfig="public-config/inner-server-config.xml";
	//默认公共游戏服务器配置文件
	private static final String defaultPublicGameServerConfig="public-config/public-servers.xml";
	//启动唯一标识
	public  static String startidentity=""; 
	
	//GameServer通信列表
	private static ConcurrentHashMap<String, IoSession> gameSessions = new ConcurrentHashMap<String, IoSession>();
	//公共游戏服务器信息列表
	private static ConcurrentHashMap<Integer, PublicGameServerInfo> publicGameServers = new ConcurrentHashMap<Integer, PublicGameServerInfo>();
	//公共游戏服务器通信列表
	private static ConcurrentHashMap<Integer, ConcurrentHashSet<Integer>> publicSessions = new ConcurrentHashMap<Integer, ConcurrentHashSet<Integer>>();
	
	private OrderedQueuePoolExecutor decodeExcutor = new OrderedQueuePoolExecutor("消息解析队列", 100, 10000);
	
	public static boolean STARTFINISH = false;
	
	public static int VERSION = 10000;
	/**
	 * 开始启动的时间 
	 */
	private static long startTime=0;
	
	public PublicServer(String innerServerConfig){
		super(innerServerConfig);
	}
	
	public PublicServer(){
		this(defaultInnerServerConfig);
	}
	
	public static PublicServer getInstance(String innerServerConfig){
		synchronized (obj) {
			if(server == null){
				server = new PublicServer(innerServerConfig);
				startTime=System.currentTimeMillis();
			}
		}
		return server;
	}
	
	public static PublicServer getInstance(){
		synchronized (obj) {
			if(server == null){
				server = new PublicServer();
				startTime=System.currentTimeMillis();
			}
		}
		return server;
	}
	
	@Override
	protected void init(){
		super.init();

		//初始化服务器线程
		thread_group = new ThreadGroup(this.getServerName());
		wServerThread = new ServerThread(thread_group, this.getServerName(), 1000);
		
		publicGameServers = new PublicGameServersConfigXmlLoader().load(defaultPublicGameServerConfig);
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
		
		//等待分配线程命令
		new Timer("WAIT-COMMAND-Timer").schedule(new TimerTask(){
			
			@Override
			public void run() {
				flowlog.error("等待解码命令：" + decodeExcutor.getTaskCounts());
			}
		}, 5 * 1000, 5 * 1000);
	}
	
	@Override
	public void run(){
//		long begin = System.currentTimeMillis();
		super.run();
		
		wServerThread.start();
		wServerThread.addTimerEvent(new EnterTimer());
		
		//内网消息定时发送
		new Timer("Inner-Send-Timer").schedule(new TimerTask(){
			@Override
			public void run() {
				List<IoSession> sessions = new ArrayList<IoSession>();
				synchronized (gameSessions) {
					sessions.addAll(gameSessions.values());
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
		
		
//		try{
//			HttpUtil.post(Global.HEART_WEB, String.format(Global.HEART_PARA, PublicServer.getInstance().getServerWeb(), PublicServer.getInstance().getServerId(), 1));
//		}catch (Exception e) {
//			log.error(e, e);
//		}
		
		//内网消息定时发送
		new Timer("Server-Alive-Timer").schedule(new TimerTask(){
			@Override
			public void run() {
//				try{
//					HttpUtil.post(Global.HEART_WEB, String.format(Global.HEART_PARA, PublicServer.getInstance().getServerWeb(), PublicServer.getInstance().getServerId(), 2));
//				}catch (Exception e) {
//					log.error(e, e);
//				}
			}
		}, 60 * 1000, 60 * 1000);
		
		STARTFINISH = true;
		
		log.info("server startup in "+(System.currentTimeMillis()-startTime)+" ms");
//		try{
//			URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
//			String runname = ManagementFactory.getRuntimeMXBean().getName();
//	        String pid = runname.substring(0, runname.indexOf("@")); 
//			ServerStartAndStopLog log=new ServerStartAndStopLog();
//			log.setAction("start");
//			log.setDatatime(TimeUtil.getNowStringDate());
//			log.setLocal(location.toString());
//			log.setServerId(String.valueOf(server_id));
//			log.setConsuming((int) (System.currentTimeMillis()-begin));
//			log.setAppName("gameserver");
//			log.setIdentity(PublicServer.startidentity);
//			log.setPid(pid);
//			LogService.getInstance().execute(log);
//		}catch (Exception e) {
//			log.error(e,e);
//		}
	}

	@Override
	protected void stop() {
//		long begin = System.currentTimeMillis();
		
		//服务器停止操作

		wServerThread.stop(true);
		
		try{
			Thread.sleep(10000);
		}catch (Exception e) {
			log.error(e, e);
		}
		
//		MemoryCache<Long, Player> players = ManagerPool.playerManager.getPlayers();
//		//事件迭代器
//		Player[] saveplayers = players.getCache().values().toArray(new Player[0]);
//		
//		log.error("保存玩家开始，保存数量：" + saveplayers);
//		
//		int count = 0;
//		//派发事件
//		for (Player player : saveplayers) {
//			//Player player = saves.get(i);
//			count++;
//			try{
//				ManagerPool.playerManager.quit(player);
//				ManagerPool.playerManager.updatePlayerSync(player);	
//			}catch(Exception ex){
//				log.error(ex, ex);
//			}
//			if(count % 100 == 0) log.error("已经保存数量：" + count);
//		}
//		//保存服务器参数(暂时不要现在没同步)
//		//ServerParamUtil.saveServerParam();
//		
//		try{
//			URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
//			ServerStartAndStopLog log=new ServerStartAndStopLog();
//			String runname = ManagementFactory.getRuntimeMXBean().getName();
//	        String pid = runname.substring(0, runname.indexOf("@")); 
//			log.setAction("stop");
//			log.setDatatime(TimeUtil.getNowStringDate());
//			log.setLocal(location.toString());
//			log.setServerId(String.valueOf(server_id));
//			log.setConsuming((int) (System.currentTimeMillis()-begin));
//			log.setAppName("gameserver");
//			log.setIdentity(PublicServer.startidentity);
//			log.setPid(pid);
//			LogService.getInstance().execute(log);
//		}catch (Exception e) {
//			log.error(e,e);
//		}
//		LogService.getInstance().shutdown();
//		
//		try{
//			HttpUtil.post(Global.HEART_WEB, String.format(Global.HEART_PARA, PublicServer.getInstance().getServerWeb(), PublicServer.getInstance().getServerId(), 3));
//		}catch (Exception e) {
//			log.error(e, e);
//		}
		
		log.error("公共服务器" + server_id + "停止成功！");
	}
		
	@Override
	public void sessionClosed(IoSession session){
		closelog.error(session + " closed!");
		//发生错误关闭连接
		if(session.containsAttribute(SESSION_GAME)){
			int game = (Integer)session.getAttribute(SESSION_GAME);
			String web = (String)session.getAttribute(SESSION_WEB);
			int version = (Integer)session.getAttribute(SESSION_VERSION);
			removeGame(game, web, version);
			wServerThread.addCommand(new ServerCloseCommand(web, game, version));
		}
	}
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		closelog.error(session + " cause " + cause, cause);
		//发生错误关闭连接
//		session.close(true);
	}
	
	@Override
	public void doCommand(IoSession iosession, IoBuffer buf) {
		//消息处理
		try{
			int id = buf.getInt();
			long sessionId = buf.getLong();
			
			decodeExcutor.addTask(sessionId, new Work(id, iosession, buf));
		}catch (Exception e) {
			log.error(e,e);
		}
	}
	
	private class Work extends AbstractWork{

		private int id;
		
		private IoSession iosession;
		
		private IoBuffer buf;
		
		public Work(int id, IoSession iosession, IoBuffer buf){
			this.id = id;
			this.iosession = iosession;
			this.buf = buf;
		}
		
		@Override
		public void run() {
			try{
				int sendTime = buf.getInt();
				//获取消息体
				Message msg = message_pool.getMessage(id);
				if(msg==null){
					log.error("收到了不存在的消息："+ id);
					return;
				}
				int roleNum = buf.getInt();
				for (int i = 0; i < roleNum; i++) {
					msg.getRoleId().add(buf.getLong());
				}
				msg.read(buf);
				msg.setSession(iosession);
				msg.setSendTime(sendTime);
				
				//if(msg.getRoleId().size()>0 && msg.getRoleId().get(0)==504365526429977l) 
//				log.debug(iosession.toString() + "收到消息" + msg.getId() + "_" + msg.getClass().getSimpleName() + ":" + msg.toString());
				
				//生成处理函数
				Handler handler = message_pool.getHandler(id);
				handler.setMessage(msg);
				handler.setCreateTime(System.currentTimeMillis());
				
//				if("Local".equalsIgnoreCase(msg.getQueue())){
					//handler.action();
				wServerThread.addCommand(handler);
//				}
			}catch (Exception e) {
				log.error(e,e);
			}
		}
	}
	
	public void registerGame(IoSession session, int game, String web, int version) {
		synchronized (gameSessions){
			session.setAttribute(SESSION_GAME, game);
			session.setAttribute(SESSION_WEB, web);
			session.setAttribute(SESSION_VERSION, version);
			gameSessions.put(web + "_" + game, session);
		}
		synchronized (publicSessions){
			if(getPublicGameServers().containsKey(game)){
				ConcurrentHashSet<Integer> games = null;
				if(publicSessions.containsKey(version)){
					games = publicSessions.get(version);
				}else{
					games = new ConcurrentHashSet<Integer>();
					publicSessions.put(version, games);
				}
				games.add(game);
			}
		}
	}
	
	public void removeGame(int game, String web, int version) {
		synchronized (gameSessions){
			gameSessions.remove(web + "_" + game);
		}
		synchronized (publicSessions){
			if(getPublicGameServers().containsKey(game)){
				if(publicSessions.containsKey(version)){
					ConcurrentHashSet<Integer> games = publicSessions.get(version);
					games.remove(game);
				}
			}
		}
	}
	
	public boolean isConnectGate(int game, String web){
		synchronized (gameSessions){
			if(!gameSessions.containsKey(web + "_" + game)) return false;
			return gameSessions.get(web + "_" + game).isConnected();
		}
	}
	
	public IoSession getGame(int game, String web){
		return gameSessions.get(web + "_" + game);
	}

	@Override
	public void sessionIdle(IoSession iosession, IdleStatus idlestatus) {
		log.debug("Clientserver session " + iosession + " idle " + idlestatus);
	}
	
	public static MessagePool getMessage_pool() {
		return message_pool;
	}
	
	public ServerThread getServerThread() {
		return wServerThread;
	}
	
	public PublicGameServerInfo getPublicGameServerInfo(int serverId){
		return publicGameServers.get(serverId);
	}
	
	public static ConcurrentHashMap<Integer, PublicGameServerInfo> getPublicGameServers() {
		return publicGameServers;
	}

	public static ConcurrentHashMap<Integer, ConcurrentHashSet<Integer>> getPublicSessions() {
		return publicSessions;
	}

	@Override
	public void sessionCreate(IoSession iosession) {
	}

	@Override
	public void sessionOpened(IoSession iosession) {
	}
}
