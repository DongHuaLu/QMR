package com.game.server.thread;

import com.game.db.bean.ServerParam;
import com.game.db.dao.ServerParamDao;
import com.game.json.JSONserializable;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SaveServerParamThread extends Thread {

	//数据库
	private ServerParamDao dao = new ServerParamDao();
	//日志
	private Logger log = LogManager.getLogger(SaveServerParamThread.class);
	//日志
	private Logger failedlog = LogManager.getLogger("SAVESERVERPARAMFAILED");
	//命令执行队列
	private LinkedBlockingQueue<ServerParamInfo> serverParam_queue = new LinkedBlockingQueue<ServerParamInfo>();
	//运行标志
	private boolean stop;
	//线程名称
	protected String threadName;
	
	boolean insertDB = true;
	
	private static int MAX_SIZE = 1000;
	
	public static int ServerParam_UPDATE = 0;
	public static int ServerParam_INSERT = 1;

	public SaveServerParamThread(String threadName) {
		super(threadName);
		this.threadName = threadName;
	}

	public void run() {
		stop = false;
		while (!stop || serverParam_queue.size() > 0) {
			ServerParamInfo serverParamInfo = serverParam_queue.poll();
			if (serverParamInfo == null) {
				try {
					synchronized (this) {
						wait();
					}
				} catch (InterruptedException e) {
					log.error("Save serverParam Thread " + threadName + " Wait Exception:" + e.getMessage());
				}
			} else {
				try {
					log.error(String.format("ServerParam数据执行步骤[1]，ServerParam_Key[%s]，ServerID[%d]", serverParamInfo.getServerParam().getParamkey(), serverParamInfo.getServerParam().getServerid()));
					if(serverParam_queue.size() > MAX_SIZE){
						insertDB = false;
					}
					if(insertDB){
						log.error(String.format("ServerParam数据执行步骤[2]，ServerParam_Key[%s]，ServerID[%d]", serverParamInfo.getServerParam().getParamkey(), serverParamInfo.getServerParam().getServerid()));
						if (serverParamInfo.getDeal() == ServerParam_UPDATE) {
							log.error(String.format("ServerParam数据执行步骤[3]，ServerParam_Key[%s]，ServerID[%d]，ServerParam_Value[%s]", serverParamInfo.getServerParam().getParamkey(), serverParamInfo.getServerParam().getServerid(), serverParamInfo.getServerParam().getParamvalue()));
							if (dao.update(serverParamInfo.getServerParam()) == 0) {
								log.error(String.format("ServerParam数据执行步骤[4]，ServerParam_Key[%s]，ServerID[%d]", serverParamInfo.getServerParam().getParamkey(), serverParamInfo.getServerParam().getServerid()));
								log.error(String.format("ServerParam数据更新错误，ServerParam_Key[%s]，ServerID[%d]，ServerParam_Value[%s]", serverParamInfo.getServerParam().getParamkey(), serverParamInfo.getServerParam().getServerid(), serverParamInfo.getServerParam().getParamvalue()));
							}
						} else if (serverParamInfo.getDeal() == ServerParam_INSERT) {
							log.error(String.format("ServerParam数据执行步骤[5]，ServerParam_Key[%s]，ServerID[%d]，ServerParam_Value[%s]", serverParamInfo.getServerParam().getParamkey(), serverParamInfo.getServerParam().getServerid(), serverParamInfo.getServerParam().getParamvalue()));
							if (dao.insert(serverParamInfo.getServerParam()) == 0) {
								log.error(String.format("ServerParam数据执行步骤[6]，ServerParam_Key[%s]，ServerID[%d]", serverParamInfo.getServerParam().getParamkey(), serverParamInfo.getServerParam().getServerid()));
								log.error(String.format("ServerParam数据保存错误，ServerParam_Key[%s]，ServerID[%d]，ServerParam_Value[%s]", serverParamInfo.getServerParam().getParamkey(), serverParamInfo.getServerParam().getServerid(), serverParamInfo.getServerParam().getParamvalue()));
							}
						}
						log.error(String.format("ServerParam数据执行步骤[6]，ServerParam_Key[%s]，ServerID[%d]", serverParamInfo.getServerParam().getParamkey(), serverParamInfo.getServerParam().getServerid()));
					}else{
						log.error(String.format("ServerParam数据执行步骤[7]，ServerParam_Key[%s]，ServerID[%d]", serverParamInfo.getServerParam().getParamkey(), serverParamInfo.getServerParam().getServerid()));
						failedlog.debug(serverParamInfo.toString());
					}
				} catch (Exception e) {
					log.error(String.format("ServerParam数据执行步骤[8]，ServerParam_Key[%s]，ServerID[%d]，ServerParam_Value[%s]", serverParamInfo.getServerParam().getParamkey(), serverParamInfo.getServerParam().getServerid(), serverParamInfo.getServerParam().getParamvalue()));
					log.error("serverParam Exception:", e);
					serverParam_queue.add(serverParamInfo);
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
			log.error("ServerParam Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}

	/**
	 * 处理ServerParam
	 *
	 * @param ServerParam ServerParam
	 * @param deal
	 */
	public void dealServerParam(ServerParam serverParam, int deal) {
		try {
			this.serverParam_queue.add(new ServerParamInfo(serverParam, deal));
			synchronized (this) {
				notify();
			}
		} catch (Exception e) {
			log.error("ServerParam Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}

	private class ServerParamInfo {

		private ServerParam serverParam;
		private int deal;

		public ServerParamInfo(ServerParam serverParam, int deal) {
			this.serverParam = serverParam;
			this.deal = deal;
		}

		public ServerParam getServerParam() {
			return serverParam;
		}

		public int getDeal() {
			return deal;
		}
		
		public String toString(){
			return JSONserializable.toString(this);
		}
	}
}
