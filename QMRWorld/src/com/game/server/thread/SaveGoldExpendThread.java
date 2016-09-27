package com.game.server.thread;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.game.db.bean.GoldExpend;
import com.game.db.dao.GoldExpendDao;

/**
 * 记录和查询玩家在指定时间段内的元宝消耗(系统消耗)
 * @author Administrator
 *
 */
public class SaveGoldExpendThread extends Thread {

	//日志
	private Logger log = LogManager.getLogger(SaveGoldExpendThread.class);
	
	//日志
	private Logger failedlog = LogManager.getLogger("SAVEGOLDFAILED");
		
	// 保存队列
	private LinkedBlockingQueue<GoldExpend> save_queue = new LinkedBlockingQueue<GoldExpend>();
	
	//运行标志
	private boolean stop;
	
	//线程名称
	protected String threadName;
	
	boolean insertDB = true;
	
	private static int MAX_SIZE = 10000;
	
	private GoldExpendDao dao = new GoldExpendDao();
		
	public SaveGoldExpendThread(String threadName) {
		super(threadName);
		this.threadName = threadName;
		}
	
	public void run() {
		stop = false;
		while (!stop || save_queue.size() > 0) {
			GoldExpend expend = save_queue.poll();
			if(expend == null) {
				try {
					synchronized (this) { wait(); }
				} catch (InterruptedException e) {
					log.error("Save Gold Expend Thread " + threadName + " Wait Exception:" + e.getMessage());
				}
			}else {
				try {
					if(save_queue.size() > MAX_SIZE) {
						insertDB = false;
					}
					if(insertDB) {
						dao.insert(expend);
					}
				} catch (Exception e) {
					failedlog.error("Gold Exception:" + expend.getRole() + "[" + expend.getGold() + "]", e);
					save_queue.add(expend);
				}
			}
		}
	}
	
	public void stop(boolean flag) {
		stop = flag;
		try {
			synchronized (this) { notify(); }
		} catch (Exception e) {
			log.error("Gold Expend Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}

	/**
	 * 添加一个保存任务
	 * @param player
	 * @param goldnum
	 * @param reason
	 */
	public void addSaveTask(GoldExpend expend) {
		save_queue.add(expend);
		try {
			synchronized (this) { notify(); }
		} catch (Exception e) {
			log.error("Gold Expend Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}
}
