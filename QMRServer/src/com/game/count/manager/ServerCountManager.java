package com.game.count.manager;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.game.db.bean.ServerCount;
import com.game.db.dao.ServerCountDao;
import com.game.utils.BeanUtil;

/**
 * 
 * @author 赵聪慧
 * @2012-9-24 下午3:27:05
 */
public class ServerCountManager {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ServerCountManager.class);
	private static ServerCountManager manager=new ServerCountManager();
	public static ServerCountManager getInstance(){
		return manager;
	}
	private ConcurrentHashMap<Integer,ServerCount> countmap=new ConcurrentHashMap<Integer, ServerCount>();
	private ExecutorService executor ;
	private ServerCountDao dao;
	
	private ServerCountManager(){
		executor= Executors.newSingleThreadExecutor();
		dao=new ServerCountDao();
		load();
	}
	
	public long addCount(int key,long count){
		synchronized (countmap) {
			ServerCount serverCount=countmap.get(key);
			if(serverCount==null){
				serverCount=new ServerCount();
				serverCount.setCountkey(key);
				serverCount.setCount(count);
				countmap.put(key, serverCount);
				insert(serverCount);
				return count;
			}else{
				serverCount.setCount(serverCount.getCount()+count);
				update(serverCount);
				return serverCount.getCount();
			}
		}
	}
	
	public long getCount(int type) {
		synchronized (countmap) {
			if (countmap.containsKey(type)) {
				return countmap.get(type).getCount();
			} else {
				return 0;
			}
		}
	}
	
	private void update(final ServerCount count){
		executor.submit(new Runnable() {
			@Override
			public void run() {
				dao.update(count);
			}
		});
	}
	
	private void insert(final ServerCount count){
		executor.submit(new Runnable() {
			@Override
			public void run() {
				dao.insert(count);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private void load(){
		logger.info("开始加载服务器统计数据");
		List<ServerCount> select = dao.select();
		countmap=(ConcurrentHashMap<Integer, ServerCount>) BeanUtil.listToMap(select, "countkey", ConcurrentHashMap.class);
		logger.info("服务器统计数据加载结束");
	}
	
}
