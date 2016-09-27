package com.game.server.thread;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.game.db.bean.Gold;
import com.game.db.dao.GoldDao;

public class SaveGoldThread extends Thread {

	//日志
	private Logger log = LogManager.getLogger(SaveGoldThread.class);
	
	//日志
	private Logger failedlog = LogManager.getLogger("SAVEGOLDFAILED");
		
	//命令执行队列
	private LinkedBlockingQueue<GoldInfo> gold_queue = new LinkedBlockingQueue<GoldInfo>();
	
	//运行标志
	private boolean stop;
	
	//线程名称
	protected String threadName;
	
	boolean insertDB = true;
	
	private static int MAX_SIZE = 10000;
	
	private GoldDao dao = new GoldDao();
		
	public SaveGoldThread(String threadName){
		super(threadName);
		this.threadName = threadName;
	}
	
	public void run(){
		stop = false;
		while(!stop || gold_queue.size() > 0){
			GoldInfo gold = gold_queue.poll();
			if(gold == null){
				try{
					synchronized (this) {
						wait();
					}
				}catch (InterruptedException e) {
					log.error("Save Gold Thread " + threadName + " Wait Exception:" + e.getMessage());
				}
			}else{
				try{
					if(gold_queue.size() > MAX_SIZE){
						insertDB = false;
					}
					if(insertDB){
						if(gold.isInsert()){
							dao.insert(gold.getGold());
						}else{
							dao.update(gold.getGold());
						}
						log.debug("[" + gold.insert + "]" + gold.getGold().toString());
					}else{
						failedlog.debug("[" + gold.insert + "]" + gold.getGold().toString());
					}
				}catch (Exception e) {
					failedlog.error("Gold Exception:" + gold.getGold().getUserId() + "[" + gold.getGold().getGold() + "]", e);
					gold_queue.add(gold);
				}
			}
		}
	}
	
	public void stop(boolean flag){
		stop = flag;
		try{
			synchronized (this) {
				notify();
			}
		}catch (Exception e) {
			log.error("Gold Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}
	
	/**
	 * 添加金币数据
	 * @param gold 金币数据
	 * @param insert 是否插入
	 */
	public void addGold(Gold gold, boolean insert){
		try{
			this.gold_queue.add(new GoldInfo(gold, insert));
			synchronized (this) {
				notify();
			}
		}catch (Exception e) {
			log.error("Gold Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}
	
	private class GoldInfo{
		
		private Gold gold;
		
		private boolean insert;

		public GoldInfo(Gold gold, boolean insert){
			this.gold = gold;
			this.insert = insert;
		}
		
		public Gold getGold() {
			return gold;
		}

		public boolean isInsert() {
			return insert;
		}

	}
}
