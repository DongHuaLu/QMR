package com.game.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.command.Handler;
import com.game.server.config.MapConfig;
import com.game.server.thread.ServerThread;
import com.game.server.thread.config.ThreadConfig;
import com.game.server.thread.loader.ThreadConfigXmlLoader;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-10-6
 * 
 * 类说明 
 */
public abstract class MapServer implements Runnable {
	//服务线名字
	private String name;
	//副本ID
	private long zoneId;
	//副本模板Id
	private int zoneModelId;
	//地图信息列表
	private List<MapConfig> mapConfigs = new ArrayList<MapConfig>();
	//默认服务器线程配置文件
	private static final String defaultThreadConfigFile = "thread-config.xml";
	//服务器线程配置信息
	protected List<ThreadConfig> threadConfigs;
	//服务器启动线程池
	protected HashMap<String, Thread> thread_pool = new HashMap<String, Thread>();
	//服务器启动线程组
	protected ThreadGroup thread_group;
	
	protected MapServer(String name, long zoneId, int zoneModelId, List<MapConfig> mapConfigs){
		this(name, zoneId, zoneModelId, mapConfigs, defaultThreadConfigFile);
	}
	
	protected MapServer(String name, long zoneId, int zoneModelId, List<MapConfig> mapConfigs, String threadConfig){
		this.name = name;
		this.zoneId = zoneId;
		this.zoneModelId = zoneModelId;
		this.mapConfigs = mapConfigs;
		
		if(threadConfig!=null) this.threadConfigs = new ThreadConfigXmlLoader().load(threadConfig);
		else this.threadConfigs = new ThreadConfigXmlLoader().load(defaultThreadConfigFile);
		
		thread_group = new ThreadGroup(name);
		//初始化服务器线程
		for (int i = 0; i < this.threadConfigs.size(); i++) {
			ThreadConfig config = this.threadConfigs.get(i);
			ServerThread thread = new ServerThread(thread_group, this.getName() + "-->" + config.getThreadName(), config.getHeart());
			this.thread_pool.put(config.getThreadName(), thread);
		}
		
		init();
	}
	
	protected abstract void init();
	
	@Override
	public void run() {
		Iterator<Thread> iter = thread_pool.values().iterator();
		//启动服务器消息处理线程
		while (iter.hasNext()) {
			Thread thread = (Thread) iter.next();
			thread.start();
		}
	}
	
	public void stop(boolean flag){
		Iterator<Thread> iter = thread_pool.values().iterator();
		//启动服务器消息处理线程
		while (iter.hasNext()) {
			ServerThread thread = (ServerThread) iter.next();
			thread.stop(true);
		}
	}
	
	public void addCommand(Handler handler){
		//寻找处理队列
		ServerThread thread = null;
		if(handler.getMessage()!=null && handler.getMessage().getQueue()!=null){
			thread = (ServerThread)this.thread_pool.get(handler.getMessage().getQueue());
		}else{
			thread = (ServerThread)this.thread_pool.get(Server.DEFAULT_MAIN_THREAD);
		}
		
		if(thread!=null){
			//添加命令
			thread.addCommand(handler);
		}else{
			handler.action();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MapConfig> getMapConfigs() {
		return mapConfigs;
	}

	public void setMapConfigs(List<MapConfig> mapConfigs) {
		this.mapConfigs = mapConfigs;
	}

	public long getZoneId() {
		return zoneId;
	}

	public void setZoneId(long zoneId) {
		this.zoneId = zoneId;
	}

	public int getZoneModelId() {
		return zoneModelId;
	}

	public void setZoneModelId(int zoneModelId) {
		this.zoneModelId = zoneModelId;
	}
	
}
