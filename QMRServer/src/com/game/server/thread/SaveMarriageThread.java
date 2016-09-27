package com.game.server.thread;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.game.db.bean.MarriageBean;
import com.game.db.dao.MarriageDao;

/**结婚信息异步储存
 * 
 * @author zhangrong
 *
 */
public class SaveMarriageThread extends Thread {

	//数据库
	private MarriageDao dao = new MarriageDao();
	//日志
	private Logger log = LogManager.getLogger(SaveMarriageThread.class);
	//命令执行队列
	private LinkedBlockingQueue<Long> marriage_queue = new LinkedBlockingQueue<Long>();
	//缓存map(key roleid)
	private HashMap<Long, marriageBeanInfo> marriage_map = new HashMap<Long, marriageBeanInfo>();
	//运行标志
	private boolean stop;
	//线程名称
	protected String threadName;
	public static int SPIRI_UPDATE = 0;	//更新结婚信息
	public static int SPIRI_INSERT = 1;	//插入结婚信息

	private static int MAX_SIZE = 10000;
	
	public SaveMarriageThread(String threadName) {
		super(threadName);
		this.threadName = threadName;
	}

	public void run() {
		stop = false;
		while (!stop || marriage_queue.size() > 0) {
			marriageBeanInfo marriage = null;
			synchronized (this) {
				Object o = marriage_queue.poll();
				if(o!=null){
					long roleId = (Long)o;
					if(roleId > 0) marriage = marriage_map.remove(roleId);
				}
			}
			if (marriage == null) {
				try {
					synchronized (this) {
						wait();
					}
				} catch (InterruptedException e) {
					log.error("Save marriageBeanInfo Thread " + threadName + " Wait Exception:" + e.getMessage());
				}
			} else {
				try {
					if(marriage_queue.size() > MAX_SIZE){
						marriage_queue.clear();
						marriage_map.clear();
					}
					if (marriage.getDeal() == 0) {
						if (marriage.getMarriageBean().getDeleted() == 1) {	//如果是删除状态
							if (dao.delete(marriage.getMarriageBean().getId())== 0 ){//进行一次删除操作
								dao.update(marriage.getMarriageBean());
							}
						}else {
							if (dao.update(marriage.getMarriageBean()) == 0) {
								log.error("update婚姻数据保存出错-ID:"+marriage.getMarriageBean().getId()+"数据:"+marriage.getMarriageBean().getData());
							}
						}
					} else if (marriage.getDeal() == 1) {
						if (dao.insert(marriage.getMarriageBean()) == 0) {
							log.error("insert婚姻数据保存出错-ID:"+marriage.getMarriageBean().getId()+"数据:"+marriage.getMarriageBean().getData());
						}
					}
					
				} catch (Exception e) {
					log.error("Marriage Exception:", e);
					synchronized (this) {
						if(!marriage_map.containsKey(marriage.getMarriageBean().getId())){
							this.marriage_queue.add(marriage.getMarriageBean().getId());
							this.marriage_map.put(marriage.getMarriageBean().getId(), marriage);
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
	 * 处理结婚
	 *
	 * @param marriageBean 结婚信息
	 * @param deal 0-update 1-insert
	 */
	public void dealMarriage(MarriageBean marriageBean, int deal) {
		try {
			//this.spiritTree_queue.add(new spirittreeBeanInfo(spirittreeBean, deal));
			synchronized (this) {
				if(!marriage_map.containsKey(marriageBean.getId())){
					this.marriage_queue.add(marriageBean.getId());
				}
				this.marriage_map.put(marriageBean.getId(), new marriageBeanInfo(marriageBean, deal));
				notify();
			}
		} catch (Exception e) {
			log.error("marriage Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}


	private class marriageBeanInfo {
		private MarriageBean marriageBean;
		private int deal;

		public marriageBeanInfo(MarriageBean marriageBean, int deal) {
			this.marriageBean = marriageBean;
			this.deal = deal;
		}

		public MarriageBean getMarriageBean() {
			return marriageBean;
		}

		public int getDeal() {
			return deal;
		}
	}
}
