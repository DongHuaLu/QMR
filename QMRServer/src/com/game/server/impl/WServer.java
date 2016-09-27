package com.game.server.impl;

import java.lang.management.ManagementFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.game.cache.AbstractWork;
import com.game.cache.executor.NonOrderedQueuePoolExecutor;
import com.game.cache.executor.OrderedQueuePoolExecutor;
import com.game.cache.impl.MemoryCache;
import com.game.command.Handler;
import com.game.data.bean.Q_mapBean;
import com.game.data.manager.DataManager;
import com.game.db.bean.Gold;
import com.game.dblog.LogService;
import com.game.json.JSONserializable;
import com.game.manager.ManagerPool;
import com.game.message.Message;
import com.game.message.pool.MessagePool;
import com.game.mina.IServer;
import com.game.mina.impl.ClientServer;
import com.game.pet.struts.Pet;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.server.config.CheckConfig;
import com.game.server.config.GameConfig;
import com.game.server.config.MapConfig;
import com.game.server.config.ServerHeartConfig;
import com.game.server.config.ServerType;
import com.game.server.loader.CheckConfigXmlLoader;
import com.game.server.loader.GameConfigXmlLoader;
import com.game.server.loader.ServerHeartConfigXmlLoader;
import com.game.server.log.ServerFinancialLog;
import com.game.server.log.ServerOnlineCountLog;
import com.game.server.log.ServerStartAndStopLog;
import com.game.server.message.ReqRegisterGameForPublicMessage;
import com.game.server.message.ReqRegisterGateMessage;
import com.game.server.message.ReqRegisterWorldMessage;
import com.game.server.message.ResDiscardMsgMessage;
import com.game.server.script.IServerStartScript;
import com.game.server.thread.SaveGoldThread;
import com.game.server.thread.SaveMailThread;
import com.game.server.thread.SaveMarriageThread;
import com.game.server.thread.SavePlayerThread;
import com.game.server.thread.SaveServerParamThread;
import com.game.server.thread.SaveThread;
import com.game.server.thread.SaveWeddingThread;
import com.game.server.thread.SchedularThread;
import com.game.server.thread.ServerThread;
import com.game.server.timer.ServerHeartTimer;
import com.game.systemgrant.manager.SystemgrantManager;
import com.game.utils.FileUtil;
import com.game.utils.Global;
import com.game.utils.HttpUtil;
import com.game.utils.MessageUtil;
import com.game.utils.ServerParamUtil;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;
import com.game.zones.timer.ZoneTeamTimer;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * 游戏服务器
 */
public class WServer extends ClientServer {
	
	private static Logger log = Logger.getLogger(WServer.class);
	
	private static Logger flowlog = Logger.getLogger("SERVERFLOW");
	
	private static Logger steplog = Logger.getLogger("SERVERSTEP");
	
	private static Logger closelog = Logger.getLogger("SERVERSESSIONCLOSE");
	
	private static Logger droplog = Logger.getLogger("DROPCOMMAND");
	
	private static Object obj = new Object();
	//服务器实例
	private static WServer server;
	
	private static MessagePool message_pool = new MessagePool();

	private ServerThread wServerThread;
	
	private SchedularThread wSchedularThread;
	
	private SavePlayerThread wSavePlayerThread;
	
	private SaveThread wSaveThread;
	
	private SaveMailThread wSaveMailThread;
	
	private SaveServerParamThread wSaveServerParamThread;
	
	private SaveGoldThread wSaveGoldThread;
	
	private ServerThread wTeamZoneThread;

	//结婚信息保存线程
	private SaveMarriageThread wSaveMarriageThread;
	//婚宴列表保存线程
	private SaveWeddingThread wSaveWeddingThread;
	
	
	//服务器启动线程组
	private ThreadGroup thread_group;
	//默认服务器配置文件
	private static final String defaultGameConfig="server-config/game-config.xml";
	//默认内部客户服务器配置文件
	private static final String defaultClientServerConfig="server-config/client-server-config.xml";
	//默认公共服务器配置文件
	private static final String defaultPublicServerConfig="server-config/public-server-config.xml";
	//服务器地图线程组
	private static ConcurrentHashMap<Integer, MServer> mServers = new ConcurrentHashMap<Integer, MServer>();
	
	private static GameConfig config;
	
	//玩家检查参数配置
	public static CheckConfig checkconfig;
	//服务器心跳参数配置
	public static ServerHeartConfig serverheartconfig;
	//连接公共服务器
	private static ClientServer publicServer = null;
	//连接公共服务器Session
	private static IoSession publicSession = null;

	public  static String startidentity=""; //启动唯一标识
	
 	public static AtomicLong sconsumebindgold = new AtomicLong(0L);   //累积消耗礼金
	public static AtomicLong sconsumemoney = new AtomicLong(0L);	  //累积消耗铜币
	public static AtomicLong sgeneratebindgold = new AtomicLong(0L);  //累积生成礼金
	public static AtomicLong sgeneratemoney = new AtomicLong(0L);	  //累积生成铜币
	
	public  static ConcurrentHashMap<String, Integer> delay = new ConcurrentHashMap<String, Integer>();
	
	private OrderedQueuePoolExecutor decodeExcutor = new OrderedQueuePoolExecutor("网关消息解析队列", 100, 10000);
	
	private OrderedQueuePoolExecutor worldExcutor = new OrderedQueuePoolExecutor("世界消息解析队列", 1, -1);
	
	private NonOrderedQueuePoolExecutor commandExcutor = new NonOrderedQueuePoolExecutor(10);
	
	private NonOrderedQueuePoolExecutor worldCommandExcutor = new NonOrderedQueuePoolExecutor(10);
	
	private boolean connnetSuccess = false;
//	//副本数量
//	private int zones_number = 0;
//	//副本总创建数量
//	private int zones_top = 0;
	
	public static boolean STARTFINISH = false;
	
	public static int VERSION = 10000;
	/**
	 * 开始启动的时间 
	 */
	private static long startTime=0;
	//是否开启跨服
	private static boolean openPublic = false;
	
	public WServer(String serverConfig){
		super(serverConfig);
	}
	
	public WServer(){
		this(defaultClientServerConfig);
	}
	
	public static WServer getInstance(String serverConfig, String gameConfig){
		synchronized (obj) {
			if(server == null){
				config = new GameConfigXmlLoader().load(gameConfig);
				server = new WServer(serverConfig);
				startTime=System.currentTimeMillis();
			}
		}
		return server;
	}
	
	public static WServer getInstance(){
		synchronized (obj) {
			if(server == null){
				config = new GameConfigXmlLoader().load(defaultGameConfig);
				server = new WServer();
				startTime=System.currentTimeMillis();
			}
		}
		return server;
	}
	
	@Override
	protected void init(){
		super.init();
		
		if(openPublic && FileUtil.isExists(defaultPublicServerConfig)){
			publicServer = new PublicConnectServer(defaultPublicServerConfig);
		}
		
		log.info("-->数据加载开始");
		/**
		 * 数据加载开始
		 */
		DataManager.getInstance();
		/**
		 * 数据加载结束
		 */
		log.info("-->数据加载结束");
		
		/**
		 * 按地图初始化线程
		 */
		//获取map列表
		List<Q_mapBean> maps = ManagerPool.dataManager.q_mapContainer.getList();
		Iterator<Q_mapBean> iter = maps.iterator();
		while (iter.hasNext()) {
			Q_mapBean q_mapBean = (Q_mapBean) iter.next();
			//副本地图
			if(q_mapBean.getQ_map_zones()==1) continue;
			//公共地图
			if(q_mapBean.getQ_map_public()==1 && config.getServerByCountry(ServerType.PUBLIC.getValue())!=this.getServerId()) continue;
			//普通地图
			if(q_mapBean.getQ_map_public()!=1 && config.getServerByCountry(ServerType.PUBLIC.getValue())==this.getServerId()) continue;
			
			if(q_mapBean.getQ_map_lines()==null || ("").equals(q_mapBean.getQ_map_lines())) continue;
			
			String[] lines = q_mapBean.getQ_map_lines().split(Symbol.SHUXIAN_REG);
			for (int i = 0; i < lines.length; i++) {
				List<MapConfig> configs = new ArrayList<MapConfig>();
				MapConfig config = new MapConfig();
				int line = Integer.parseInt(lines[i]);
				config.setServerId(this.getServerId());
				config.setLineId(line);
				config.setMapModelId(q_mapBean.getQ_map_id());
				configs.add(config);
				MServer m = new MServer(q_mapBean.getQ_map_name() + "(" + line + "线)", 0, 0, configs);
				for (int j = 0; j < m.getMapConfigs().size(); j++) {
					config = m.getMapConfigs().get(j);
					mServers.put(getKey(config.getLineId(), config.getMapId()), m);
				}
			}
		}

		//加载服务器参数
		ServerParamUtil.loadServerParam(getServerId());
		//载入本国王城信息
		ManagerPool.countryManager.loadkingcity(getServerId());
		//载入本国帮旗争夺战领地信息
		ManagerPool.guildFlagManager.loadguildfiag(getServerId());
		//设置攻城和领地战距离开区天数
		ManagerPool.countryManager.stSiegeIntervalDay(getServerId());
		//加载服务器禁言列表
		ManagerPool.chatManager.loadChatBlackList();
		
		//加载全服邮件列表
		SystemgrantManager.getInstance().system_GrantBean_load();
		//加载结婚信息列表
		ManagerPool.marriageManager.loadAllMarriage();
		//加载婚宴列表
		ManagerPool.marriageManager.loadAllWedding();
		
		//初始化服务器线程
		thread_group = new ThreadGroup(this.getServerName());
		wServerThread = new ServerThread(thread_group, this.getServerName(), 1000);
		
		wSavePlayerThread = new SavePlayerThread("Save-Player-Thread");
		
		wSaveThread = new SaveThread("Save-Timer");
		
		wSaveGoldThread = new SaveGoldThread("Save-Gold-Thread");
		
		wSaveMailThread = new SaveMailThread("Save-Mail-Thread");
		
		wSaveServerParamThread = new SaveServerParamThread("Save-ServerParam-Thread");
		//初始化定时线程
		wSchedularThread = new SchedularThread();
		
		wTeamZoneThread = new ServerThread(thread_group, this.getServerName() + "-TeamZone", 1000);
		
		wSaveMarriageThread = new SaveMarriageThread("Save-Marriage-Thread");
		
		wSaveWeddingThread = new SaveWeddingThread("Save-Wedding-Thread");
		
		try{
			//初始化角色加速检查参数
			checkconfig = new CheckConfigXmlLoader().load("server-config/check-config.xml");
			Global.CHECK_BETWEEN = checkconfig.getCheckbetween();
			Global.DISTANCE = checkconfig.getDistance();
			Global.BASE_SPEED = checkconfig.getBasespeed();
			//初始化服务器心跳参数
			serverheartconfig = new ServerHeartConfigXmlLoader().load("server-config/server-heart-config.xml");
			Global.HEART_PARA = serverheartconfig.getHeart_para();
			Global.HEART_WEB  = serverheartconfig.getHeart_web();
		}catch (Exception e) {
			log.error(e, e);
		}
//		//副本测试使用
//		new Timer("Zones-Timer").schedule(new TimerTask(){
//			
//			@Override
//			public void run() {
//				Thread[] threads = new Thread[4000];
//				Thread.enumerate(threads);
//				int count = 0;
//				for (int i = 0; i < threads.length; i++) {
//					if(threads[i]!=null && threads[i].getName()!=null && threads[i].getName().indexOf("藏娇")!=-1){
//						count++;
//					}
//				}
//				log.error("运行线程数量:" + Thread.activeCount());//executor.getPoolSize());
//				log.error("运行副本线程数量:" + count);//executor.getPoolSize());
//				log.error("副本总数量:" + zones_top);
//				log.error("副本运行数量:" + zones_number);
//			}
//		}, 60* 1000, 60 * 1000);
		
//		
//		//设置系统多倍经验等。。。(转移到Timer)
//		new Timer("Double-Timer").schedule(new TimerTask(){
//			@Override
//			public void run() {
//				//打坐双倍
//				if(ManagerPool.dazuoManager.isDaZuoDouble() != null){
//					ManagerPool.dazuoManager.setDaZuoDoubleStatus((byte) 1);
//				}else {
//					ManagerPool.dazuoManager.setDaZuoDoubleStatus((byte) 0);
//				}
//				
//				//打普通怪双倍
//				String douString = ManagerPool.monsterManager.isDaguaiDouble();
//				if(douString != null){
//					if (ManagerPool.monsterManager.getDaguaiDoubleStatus() == 0) {
//						ReqMonsterDoubleNoticeMessage wmsg = new ReqMonsterDoubleNoticeMessage();
//						wmsg.setContent(douString);
//						wmsg.setStatus((byte) 1);
//						wmsg.setType((byte) 1);
//						MessageUtil.send_to_world(wmsg);
//					}
//					ManagerPool.monsterManager.setDaguaiDoubleStatus((byte) 1);
//				}else {
//					if (ManagerPool.monsterManager.getDaguaiDoubleStatus() == 1) {
//						ReqMonsterDoubleNoticeMessage wmsg = new ReqMonsterDoubleNoticeMessage();
//						wmsg.setStatus((byte) 0);
//						wmsg.setType((byte) 1);
//						MessageUtil.send_to_world(wmsg);
//					}
//					ManagerPool.monsterManager.setDaguaiDoubleStatus((byte) 0);
//				}
//			}
//		}, 60* 1000, 60 * 1000);

		//帧间隔测试使用
		new Timer("Step-Timer").schedule(new TimerTask(){
			@Override
			public void run() {
				Object[] objs = delay.values().toArray();
				Arrays.sort(objs, new Comparator<Object>() {
					@Override
					public int compare(Object o1, Object o2) {
						int entry1 = (Integer)o1;
						int entry2 = (Integer)o1;
						return entry1 - entry2 > 0? -1: 1;
					}
				});
				if(objs.length > 0) steplog.error("帧间隔最大值:" + ((Integer)objs[0]));
			}
		}, 60* 1000, 60 * 1000);
		
		//流量测试使用
		new Timer("Quantity-Timer").schedule(new TimerTask(){
			
			@Override
			public void run() {
				if(socket==null || socket.getStatistics()==null) return;
				
				socket.getStatistics().updateThroughput(System.currentTimeMillis());
				
				StringBuffer buf = new StringBuffer();
				buf.append("WR:" + socket.getScheduledWriteBytes()).append(",");
				buf.append("MWR:" + socket.getScheduledWriteMessages()).append(",");
				buf.append("WT:" + socket.getStatistics().getWrittenBytes()).append(",");
				buf.append("RT:" + socket.getStatistics().getReadBytes()).append(",");
				buf.append("MWT:" + socket.getStatistics().getWrittenMessages()).append(",");
				buf.append("MRT:" + socket.getStatistics().getReadMessages()).append(",");
				buf.append("WS:" + socket.getStatistics().getWrittenBytesThroughput()).append(",");
				buf.append("RS:" + socket.getStatistics().getReadBytesThroughput());
				
				flowlog.error(buf.toString());
			}
		}, 5 * 1000, 5 * 1000);
		
		//等待分配线程命令
		new Timer("WAIT-COMMAND-Timer").schedule(new TimerTask(){
			
			@Override
			public void run() {
				flowlog.error("等待解码命令：" + decodeExcutor.getTaskCounts());
				flowlog.error("等待执行并行命令：" + commandExcutor.getActiveCount());
				flowlog.error("等待执行世界并行命令：" + worldCommandExcutor.getActiveCount());
			}
		}, 5 * 1000, 5 * 1000);
		
		new Timer("CountOnlinePlayer-Timmer").schedule(new TimerTask() {
			@Override
			public void run() {
				Long[] onlineRolesId = PlayerManager.getInstance().getOnlineRolesId();
				int male=0;
				int famale=0;
				String countrycount="";
				int teamrolecount=0;
				int petcount=0;				
				int rechargeer=0;//有过充值的玩家	
				HashMap<String, Integer> guojia=new HashMap<String, Integer>();
				for (Long roleId : onlineRolesId) {
					Player player = PlayerManager.getInstance().getPlayer(roleId);
					if(player!=null){
						if(player.getSex()==1){
							male++;
						}
						if(player.getSex()==2){
							famale++;
						}
						int country = player.getCountry();
						Integer gjcount= guojia.get(String.valueOf(country));
						gjcount=gjcount==null?0:gjcount;
						gjcount++;
						guojia.put(String.valueOf(country),gjcount);
						long teamid = player.getTeamid();
						if(teamid!=0){
							teamrolecount++;
						}
						List<Pet> petList = player.getPetList();
						if(petList!=null){
							for (Pet pet : petList) {
								if(pet.isShow()){
									petcount++;
								}
							}
						}
						Gold gold = player.getGold();
						if(gold!=null&&gold.getTotalGold()>0){
							rechargeer++;
						}
					}
				}
				countrycount= JSONserializable.toString(guojia);
				ServerOnlineCountLog log=new ServerOnlineCountLog();
				log.setCountrycount(countrycount);
				log.setDatetimes(TimeUtil.getNowStringDate());
				log.setFemale(famale);
				log.setMale(male);
				log.setNowcount(onlineRolesId.length);
				log.setPetcount(petcount);
				log.setRechargeer(rechargeer);
				log.setTeamrolecount(teamrolecount);
				LogService.getInstance().execute(log);
				
				//服务器 铜币绑定元宝获得和消费日志
				ServerFinancialLog financiallog =  new ServerFinancialLog();
				financiallog.setDatetimes(TimeUtil.getNowStringDate());
				financiallog.setSgeneratebindgold(sgeneratebindgold.longValue());
				financiallog.setSgeneratemoney(sgeneratemoney.longValue());
				financiallog.setSconsumebindgold(sconsumebindgold.longValue());
				financiallog.setSconsumemoney(sconsumemoney.longValue());
				LogService.getInstance().execute(financiallog);
			}
		},2*60*1000,2*60*1000);
		
		PlayerManager.getInstance().loadNames();
	}
	
	/**
	 * 创建副本地图线程服务器
	 * @param mapModelId
	 * @return
	 */
	public MServer createMapServer(String name, long zoneId, int zoneModelId, List<MapConfig> mapConfigs){
		try{
			MServer m = new MServer(name, zoneId, zoneModelId, mapConfigs);
			for (int i = 0; i < m.getMapConfigs().size(); i++) {
				MapConfig config = m.getMapConfigs().get(i);
				mServers.put(getKey(config.getLineId(), config.getMapId()), m);
			}
			
	//		synchronized (obj) {
	//			zones_number++;
	//			zones_top++;
	//		}
			
			log.error("map threads " + mServers.size());
			new Thread(m).start();
		
			return m;
		}catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
	
	/**
	 * 移除副本地图线程服务器
	 * @param mapModelId
	 * @return
	 */
	public void removeMapServer(MServer server){
		//销毁副本
		for (int i = 0; i < server.getMapConfigs().size(); i++) {
			MapConfig config = server.getMapConfigs().get(i);
			mServers.remove(getKey(config.getLineId(), config.getMapId()));
		}
		server.stop(true);
//		synchronized (obj) {
//			zones_number--;
//		}
		log.error("销毁副本，副本总数量:" + mServers.size());
		
//		log.error("map threads " + mServers.size());
	}
	
	@Override
	public void run(){
		long begin = System.currentTimeMillis();
		super.run();
		
		Iterator<MServer> iter = mServers.values().iterator();
		HashSet<MServer> already = new HashSet<MServer>();
		while (iter.hasNext()) {
			MServer mServer = (MServer) iter.next();
			if(!already.contains(mServer)){
				new Thread(mServer).start();
				already.add(mServer);
			}
		} 
		wServerThread.start();
		wServerThread.addTimerEvent(new ServerHeartTimer(this.getServerId(), this.getServerWeb()));
		
		wTeamZoneThread.start();
		wTeamZoneThread.addTimerEvent(new ZoneTeamTimer(this.getServerId()));
		
		wSchedularThread.start();
		
		wSavePlayerThread.start();
		wSaveThread.start();
		wSaveGoldThread.start();
		wSaveMailThread.start();
		wSaveMarriageThread.start();
		wSaveServerParamThread.start();
		wSaveWeddingThread.start();
		
		ManagerPool.schedularManager.init();
		ManagerPool.mailServerManager.startClearMail();
		
		//内网消息定时发送
		new Timer("Inner-Send-Timer").schedule(new TimerTask(){
			@Override
			public void run() {
				List<IoSession> sessions = new ArrayList<IoSession>();
				synchronized (gateSessions) {
					Iterator<List<IoSession>> iter = gateSessions.values().iterator();
					while (iter.hasNext()) {
						List<IoSession> list = (List<IoSession>) iter.next();
						sessions.addAll(list);
					}
				}
				synchronized (worldSessions) {
					sessions.addAll(worldSessions);
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
		
		if(publicServer!=null){
			new Thread(publicServer, "公共服连接").start();
		}
		
		while(!connnetSuccess){
			try{
				Thread.sleep(1000);
			}catch (Exception e) {
				log.error(e, e);
			}
		}
		
		try{
			HttpUtil.post(Global.HEART_WEB, String.format(Global.HEART_PARA, WServer.getInstance().getServerWeb(), WServer.getInstance().getServerId(), 1));
		}catch (Exception e) {
			log.error(e, e);
		}
		
		IServerStartScript script = (IServerStartScript)ManagerPool.scriptManager.getScript(ScriptEnum.SERVER_START);
		if(script != null) {
			try {
				script.onStart(this.getServerWeb(), this.getServerId());
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("找不到服务器启动脚本");
		}
		
		//内网消息定时发送
		new Timer("Server-Alive-Timer").schedule(new TimerTask(){
			@Override
			public void run() {
				try{
					HttpUtil.post(Global.HEART_WEB, String.format(Global.HEART_PARA, WServer.getInstance().getServerWeb(), WServer.getInstance().getServerId(), 2));
				}catch (Exception e) {
					log.error(e, e);
				}
			}
		}, 60 * 1000, 60 * 1000);
		
		STARTFINISH = true;
		
		log.info("server startup in "+(System.currentTimeMillis()-startTime)+" ms");
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
			log.setAppName("gameserver");
			log.setIdentity(WServer.startidentity);
			log.setPid(pid);
			LogService.getInstance().execute(log);
		}catch (Exception e) {
			log.error(e,e);
		}
	}

	@Override
	protected void stop() {
		long begin = System.currentTimeMillis();
		
		//服务器停止操作
		MServer[] servers = mServers.values().toArray(new MServer[0]);
		for (int i = 0; i < servers.length; i++) {
			servers[i].stop(false);
		}
		wServerThread.stop(true);
		wTeamZoneThread.stop(true);
		wSchedularThread.stop(true);
		
		wSaveThread.stop(true);
		
		wSavePlayerThread.stop(true);
		wSaveGoldThread.stop(true);
		wSaveMailThread.stop(true);
		wSaveServerParamThread.stop(true);
		wSaveMarriageThread.stop(true);
		wSaveWeddingThread.stop(true);
		
		try{
			Thread.sleep(10000);
		}catch (Exception e) {
			log.error(e, e);
		}
		
		MemoryCache<Long, Player> players = ManagerPool.playerManager.getPlayers();
		//事件迭代器
		Player[] saveplayers = players.getCache().values().toArray(new Player[0]);
		
		log.error("保存玩家开始，保存数量：" + saveplayers);
		
		int count = 0;
		//派发事件
		for (Player player : saveplayers) {
			//Player player = saves.get(i);
			count++;
			try{
				ManagerPool.playerManager.quit(player);
				ManagerPool.playerManager.updatePlayerSync(player);	
			}catch(Exception ex){
				log.error(ex, ex);
			}
			if(count % 100 == 0) log.error("已经保存数量：" + count);
		}
		//保存服务器参数(暂时不要现在没同步)
		//ServerParamUtil.saveServerParam();
		
		try{
			URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
			ServerStartAndStopLog log=new ServerStartAndStopLog();
			String runname = ManagementFactory.getRuntimeMXBean().getName();
	        String pid = runname.substring(0, runname.indexOf("@")); 
			log.setAction("stop");
			log.setDatatime(TimeUtil.getNowStringDate());
			log.setLocal(location.toString());
			log.setServerId(String.valueOf(server_id));
			log.setConsuming((int) (System.currentTimeMillis()-begin));
			log.setAppName("gameserver");
			log.setIdentity(WServer.startidentity);
			log.setPid(pid);
			LogService.getInstance().execute(log);
		}catch (Exception e) {
			log.error(e,e);
		}
		LogService.getInstance().shutdown();
		
		try{
			HttpUtil.post(Global.HEART_WEB, String.format(Global.HEART_PARA, WServer.getInstance().getServerWeb(), WServer.getInstance().getServerId(), 3));
		}catch (Exception e) {
			log.error(e, e);
		}
		
		log.error("游戏服务器" + server_id + "停止成功！");
	}
	
	public void addSchedularTask(String cron, String className){
		wSchedularThread.addSchedulerTask(cron, className);
	}
	
	@Override
	public void sessionClosed(IoSession session){
		closelog.error(session + " closed!");
		//发生错误关闭连接
		int id = (Integer)session.getAttribute("connect-server");
		if(id!=0){
			removeSession(session, id, IServer.GATE_SERVER);
		}else{
			removeSession(session, id, IServer.WORLD_SERVER);
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
			
			if(sessionId > 0){
				decodeExcutor.addTask(sessionId, new Work(id, iosession, buf));
			}else{
				worldExcutor.addTask(0l, new Work(id, iosession, buf));
			}
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
				
				//if(msg.getRoleId().size()>0 && msg.getRoleId().get(0)==504365526429977l) 
//				log.debug(iosession.toString() + "收到消息" + msg.getId() + "_" + msg.getClass().getSimpleName() + ":" + msg.toString());
				
				//生成处理函数
				Handler handler = message_pool.getHandler(id);
				handler.setMessage(msg);
				handler.setCreateTime(System.currentTimeMillis());
				
				if("Local".equalsIgnoreCase(msg.getQueue())){
					//handler.action();
					commandExcutor.execute(handler);
				}else if("Server".equalsIgnoreCase(msg.getQueue())){
					Player player = null;
					if(msg.getRoleId().size()>0) player=ManagerPool.playerManager.getPlayer(msg.getRoleId().get(0));
					if(player==null){
						//服务器之间消息直接执行
						if((msg.getId() % 1000) >= 300){
							//handler.action();
							worldCommandExcutor.execute(handler);
						}else{
							if(msg.getRoleId().size() > 0){
								droplog.error("丢弃玩家(" + msg.getRoleId().get(0) + ")Server指令(" + msg.getId() + ")：" + msg.toString());
							}else{
								droplog.error("丢弃Server指令：" + msg.toString());
							}
						}
						return;
					}
					
					handler.setParameter(player);
					wServerThread.addCommand(handler);
				}else{
					Player player = null;
					if(msg.getRoleId().size()>0) player=ManagerPool.playerManager.getPlayer(msg.getRoleId().get(0));
					if(player==null){
						//服务器之间消息直接执行
						if((msg.getId() % 1000) >= 300){
							//handler.action();
							worldCommandExcutor.execute(handler);
						}else{
							if(msg.getRoleId().size() > 0){
								droplog.error("丢弃玩家(" + msg.getRoleId().get(0) + ")Map指令(" + msg.getId() + ")：" + msg.toString());
							}else{
								droplog.error("丢弃Map指令：" + msg.toString());
							}
						}
						return;
					}
					
					handler.setParameter(player);
					
					MServer mServer = getMServer(player.getLine(), player.getMap());
					if(mServer==null){
						//服务器之间消息直接执行
						if((msg.getId() % 1000) >= 300){
							//handler.action();
							worldCommandExcutor.execute(handler);
						}else{
							droplog.error("丢弃玩家(" + player.getId() + ",line=" + player.getLine() + ",map=" + player.getMap() + ",mapModel=" + player.getMapModelId() + ")Map指令：(" + msg.getId() + ")" + msg.toString());
							ResDiscardMsgMessage dropmsg = new ResDiscardMsgMessage();
							dropmsg.setMsgid(msg.getId());
							dropmsg.setMsgcont(msg.toString());
							MessageUtil.tell_player_message(player, dropmsg);
						}
						return;
					}
					mServer.addCommand(handler);
				}
			}catch (Exception e) {
				log.error(e,e);
			}
		}
	}
	
	public MServer[] getMServers(){
		return mServers.values().toArray(new MServer[0]);
	}
	
	public MServer getMServer(int lineId, int mapId){
		return mServers.get(getKey(lineId, mapId));
	}
	
	public static GameConfig getGameConfig(){
		return config;
	}

	@Override
	public void register(IoSession session, int type) {
		switch (type) {
			case IServer.GATE_SERVER:
				ReqRegisterGateMessage msg = new ReqRegisterGateMessage();
				msg.setServerId(this.getServerId());
				msg.setServerName(this.getServerName());
				session.write(msg);
				break;
			case IServer.WORLD_SERVER:
				ReqRegisterWorldMessage mesg = new ReqRegisterWorldMessage();
				mesg.setServerId(this.getServerId());
				mesg.setServerName(this.getServerName());
				session.write(mesg);
				break;
		}
	}

	@Override
	public void sessionIdle(IoSession iosession, IdleStatus idlestatus) {
		log.error("Clientserver session " + iosession + " idle " + idlestatus);
	}
	
	private int getKey(int lineId, int mapId){
		return lineId * 10000000 + mapId;
	}
	
	public static MessagePool getMessage_pool() {
		return message_pool;
	}
	
	public boolean isConnectWorld(){
		if(worldSessions==null || worldSessions.size()==0) return false;
		return worldSessions.get(0).isConnected();
	}

	public SavePlayerThread getSavePlayerThread() {
		return wSavePlayerThread;
	}

	public SaveGoldThread getSaveGoldThread() {
		return wSaveGoldThread;
	}

	public SaveMailThread getwSaveMailThread() {
		return wSaveMailThread;
	}
	
	public SaveServerParamThread getwSaveServerParamThread(){
		return wSaveServerParamThread;
	}

	public ServerThread getServerThread() {
		return wServerThread;
	}

	@Override
	public void sessionCreate(IoSession iosession) {
	}

	@Override
	public void sessionOpened(IoSession iosession) {
	}

	@Override
	protected void connectComplete() {
		connnetSuccess = true;
	}
	
	public boolean isConnectPublic(){
		if(publicSession==null){
			return false;
		}
		if(!publicSession.isConnected()){
			return false;
		}
		return true;
	}
	
	public IoSession getPublicSession(){
		return publicSession;
	}
	
	public SaveMarriageThread getwSaveMarriageThread() {
		return wSaveMarriageThread;
	}

	public void setwSaveMarriageThread(SaveMarriageThread wSaveMarriageThread) {
		this.wSaveMarriageThread = wSaveMarriageThread;
	}

	public SaveWeddingThread getwSaveWeddingThread() {
		return wSaveWeddingThread;
	}

	public void setwSaveWeddingThread(SaveWeddingThread wSaveWeddingThread) {
		this.wSaveWeddingThread = wSaveWeddingThread;
	}

	private class PublicConnectServer extends ClientServer{

		public PublicConnectServer(String serverConfig) {
			super(serverConfig);
		}

		@Override
		public void sessionClosed(IoSession session) {
			//publiccloselog.error("world " + iosession + " closed!");
			publicSession = null;
			if(session.containsAttribute("connect-server-id")
					){
				int id = (Integer)session.getAttribute("connect-server-id");
				String ip = (String)session.getAttribute("connect-server-ip");
				int port = (Integer)session.getAttribute("connect-server-port");
				reconnectPublic(id, ip, port);
			}
		}

		@Override
		public void exceptionCaught(IoSession session, Throwable cause) {
			//publiccloselog.error("world " + session + " cause " + cause, cause);
//			session.close(true);
		}

		@Override
		public void doCommand(IoSession iosession, IoBuffer buf) {
			//消息处理
			try{
				int id = buf.getInt();
				
				long sessionId = buf.getLong();
				
				if(sessionId > 0){
					decodeExcutor.addTask(sessionId, new Work(id, iosession, buf));
				}else{
					worldExcutor.addTask(0l, new Work(id, iosession, buf));
				}
//				
//				int roleNum = buf.getInt();
//				List<Long> roles = new ArrayList<Long>();
//				for (int i = 0; i < roleNum; i++) {
//					roles.add(buf.getLong());
//				}
//
//				//生成处理函数
//				Handler handler = message_pool.getHandler(id);
//				
//				if(handler!=null){
//					//获取消息体
//					Message msg = message_pool.getMessage(id);
//					msg.read(buf);
//					msg.setSession(iosession);
//					//自身处理消息
//					handler.setMessage(msg);
//					
//				}
			}catch (Exception e) {
				log.error(e,e);
			}
		}

		@Override
		protected void stop() {}

		@Override
		public void register(IoSession session, int type) {
			ReqRegisterGameForPublicMessage msg = new ReqRegisterGameForPublicMessage();
			msg.setServerId(this.getServerId());
			msg.setServerName(this.getServerName());
			msg.setWebName(this.getServerWeb());
			session.write(msg);
			publicSession = session;
		}

		@Override
		public void sessionIdle(IoSession iosession, IdleStatus idlestatus) {
			
		}

		@Override
		public void sessionCreate(IoSession iosession) {
		}

		@Override
		public void sessionOpened(IoSession iosession) {
			
		}

		@Override
		protected void connectComplete() {
			
		}
	}
}
