package com.game.server.thread;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.game.db.bean.WeddingBean;
import com.game.db.dao.WeddingDao;

/**婚宴列表 异步储存
 * 
 * @author zhangrong
 *
 */
public class SaveWeddingThread extends Thread {

	//数据库
	private WeddingDao dao = new WeddingDao();
	//日志
	private Logger log = LogManager.getLogger(SaveWeddingThread.class);
	//命令执行队列
	private LinkedBlockingQueue<Long> wedding_queue = new LinkedBlockingQueue<Long>();
	//缓存map(key roleid)
	private HashMap<Long, weddingBeanInfo> wedding_map = new HashMap<Long, weddingBeanInfo>();
	//运行标志
	private boolean stop;
	//线程名称
	protected String threadName;
	public static int SPIRI_UPDATE = 0;	//更新结婚信息
	public static int SPIRI_INSERT = 1;	//插入结婚信息

	private static int MAX_SIZE = 10000;
	
	public SaveWeddingThread(String threadName) {
		super(threadName);
		this.threadName = threadName;
	}

	public void run() {
		stop = false;
		while (!stop || wedding_queue.size() > 0) {
			weddingBeanInfo wedding = null;
			synchronized (this) {
				Object o = wedding_queue.poll();
				if(o!=null){
					long roleId = (Long)o;
					if(roleId > 0) wedding = wedding_map.remove(roleId);
				}
			}
			if (wedding == null) {
				try {
					synchronized (this) {
						wait();
					}
				} catch (InterruptedException e) {
					log.error("Save marriageBeanInfo Thread " + threadName + " Wait Exception:" + e.getMessage());
				}
			} else {
				try {
					if(wedding_queue.size() > MAX_SIZE){
						wedding_queue.clear();
						wedding_map.clear();
					}
					if (wedding.getDeal() == 0) {
						if (wedding.getWeddingBean().getDeleted() == 1) {//如果是删除状态，
							if(dao.delete(wedding.getWeddingBean().getId()) == 0){//先进行一次删除，如果删除失败
								dao.update(wedding.getWeddingBean());
							}
						}else {
							if (dao.update(wedding.getWeddingBean()) == 0) {
								//log.error("update生命灵树保存出错-ID:"+marriage.getMarriageBean().getRoleid()+"数据:"+spirittree.getSpirittreeBean().getData());
							}
						}
					} else if (wedding.getDeal() == 1) {
						if (dao.insert(wedding.getWeddingBean()) == 0) {	
							//log.error("insert婚宴数据保存出错-ID:"+spirittree.getSpirittreeBean().getRoleid()+"数据:"+spirittree.getSpirittreeBean().getData());
							//如果已经有数据存在，又往里面插入，只会出现在重新举办婚宴的情况
							dao.update(wedding.getWeddingBean());//覆盖原来的数据
						
						}
					}
					
				} catch (Exception e) {
					log.error("Marriage Exception:", e);
					synchronized (this) {
						if(!wedding_map.containsKey(wedding.getWeddingBean().getId())){
							this.wedding_queue.add(wedding.getWeddingBean().getId());
							this.wedding_map.put(wedding.getWeddingBean().getId(), wedding);
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
			log.error("Marriage Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}
	
	
	/**
	 * 处理婚宴列表
	 *
	 * @param marriageBean 结婚信息
	 * @param deal 0-update 1-insert
	 */
	public void dealWedding(WeddingBean weddingBean, int deal) {
		try {
			//this.spiritTree_queue.add(new spirittreeBeanInfo(spirittreeBean, deal));
			synchronized (this) {
				if(!wedding_map.containsKey(weddingBean.getId())){
					this.wedding_queue.add(weddingBean.getId());
				}
				this.wedding_map.put(weddingBean.getId(), new weddingBeanInfo(weddingBean, deal));
				notify();
			}
		} catch (Exception e) {
			log.error("Role Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}


	private class weddingBeanInfo {
		private WeddingBean weddingBean;
		private int deal;

		public weddingBeanInfo(WeddingBean weddingBean, int deal) {
			this.weddingBean = weddingBean;
			this.deal = deal;
		}

		public WeddingBean getWeddingBean() {
			return weddingBean;
		}

		public int getDeal() {
			return deal;
		}
	}
}
