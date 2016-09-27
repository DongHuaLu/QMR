package com.game.server.thread;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.game.db.bean.SpirittreeBean;
import com.game.db.dao.SpirittreeDao;

public class SaveFruitThread extends Thread {

	//数据库
	private SpirittreeDao dao = new SpirittreeDao();
	//日志
	private Logger log = LogManager.getLogger(SaveFruitThread.class);
	//命令执行队列
	private LinkedBlockingQueue<Long> spiritTree_queue = new LinkedBlockingQueue<Long>();
	//缓存map(key roleid)
	private HashMap<Long, spirittreeBeanInfo> spiritTree_map = new HashMap<Long, spirittreeBeanInfo>();
	//运行标志
	private boolean stop;
	//线程名称
	protected String threadName;
	public static int SPIRI_UPDATE = 0;	//更新灵树
	public static int SPIRI_INSERT = 1;	//插入灵树

	private static int MAX_SIZE = 10000;
	
	public SaveFruitThread(String threadName) {
		super(threadName);
		this.threadName = threadName;
	}

	public void run() {
		stop = false;
		while (!stop || spiritTree_queue.size() > 0) {
			spirittreeBeanInfo spirittree = null;
			synchronized (this) {
				Object o = spiritTree_queue.poll();
				if(o!=null){
					long roleId = (Long)o;
					if(roleId > 0) spirittree = spiritTree_map.remove(roleId);
				}
			}
			if (spirittree == null) {
				try {
					synchronized (this) {
						wait();
					}
				} catch (InterruptedException e) {
					log.error("Save spirittreebeanInfo Thread " + threadName + " Wait Exception:" + e.getMessage());
				}
			} else {
				try {
					if(spiritTree_queue.size() > MAX_SIZE){
						spiritTree_queue.clear();
						spiritTree_map.clear();
					}
					if (spirittree.getDeal() == 0) {
						if (dao.update(spirittree.getSpirittreeBean()) == 0) {
							log.error("update生命灵树保存出错-ID:"+spirittree.getSpirittreeBean().getRoleid()+"数据:"+spirittree.getSpirittreeBean().getData());
						}
					} else if (spirittree.getDeal() == 1) {
						if (dao.insert(spirittree.getSpirittreeBean()) == 0) {
							log.error("insert生命灵树保存出错-ID:"+spirittree.getSpirittreeBean().getRoleid()+"数据:"+spirittree.getSpirittreeBean().getData());
						}
					}
					
				} catch (Exception e) {
					log.error("Friend Exception:", e);
					synchronized (this) {
						if(!spiritTree_map.containsKey(spirittree.getSpirittreeBean().getRoleid())){
							this.spiritTree_queue.add(spirittree.getSpirittreeBean().getRoleid());
							this.spiritTree_map.put(spirittree.getSpirittreeBean().getRoleid(), spirittree);
						}
					}
				}
			}
		}
	}

	public void stop(boolean flag) {
		stop = flag;
		try {
			synchronized (this) {
				notify();
			}
		} catch (Exception e) {
			log.error("Mail Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}
	
	
	/**
	 * 处理灵树
	 *
	 * @param spirittreeBean 灵树
	 * @param deal 0-update 1-insert
	 */
	public void dealMail(SpirittreeBean spirittreeBean, int deal) {
		try {
			//this.spiritTree_queue.add(new spirittreeBeanInfo(spirittreeBean, deal));
			synchronized (this) {
				if(!spiritTree_map.containsKey(spirittreeBean.getRoleid())){
					this.spiritTree_queue.add(spirittreeBean.getRoleid());
				}
				this.spiritTree_map.put(spirittreeBean.getRoleid(), new spirittreeBeanInfo(spirittreeBean, deal));
				notify();
			}
		} catch (Exception e) {
			log.error("Role Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}


	private class spirittreeBeanInfo {
		private SpirittreeBean spirittreeBean;
		private int deal;

		public spirittreeBeanInfo(SpirittreeBean spirittreeBean, int deal) {
			this.spirittreeBean = spirittreeBean;
			this.deal = deal;
		}

		public SpirittreeBean getSpirittreeBean() {
			return spirittreeBean;
		}

		public int getDeal() {
			return deal;
		}
	}
}
