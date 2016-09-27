package com.game.server.thread;

import com.game.db.bean.Friend;
import com.game.db.dao.FriendDao;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SaveFriendThread extends Thread {

	//数据库
	private FriendDao dao = new FriendDao();
	//日志
	private Logger log = LogManager.getLogger(SaveFriendThread.class);
	//命令执行队列
	private LinkedBlockingQueue<Long> friend_queue = new LinkedBlockingQueue<Long>();
	//缓存map(key roleid)
	private HashMap<Long, FriendInfo> friend_map = new HashMap<Long, FriendInfo>();
	//运行标志
	private boolean stop;
	//线程名称
	protected String threadName;
	public static int FRIEND_UPDATE = 0;	//更新好友
	public static int FRIEND_INSERT = 1;	//插入好友

	private static int MAX_SIZE = 10000;
	
	public SaveFriendThread(String threadName) {
		super(threadName);
		this.threadName = threadName;
	}

	public void run() {
		stop = false;
		while (!stop || friend_queue.size() > 0) {
			FriendInfo friend = null;
			synchronized (this) {
				Object o = friend_queue.poll();
				if(o!=null){
					long roleId = (Long)o;
					if(roleId > 0) friend = friend_map.remove(roleId);
				}
			}
			if (friend == null) {
				try {
					synchronized (this) {
						wait();
					}
				} catch (InterruptedException e) {
					log.error("Save Friend Thread " + threadName + " Wait Exception:" + e.getMessage());
				}
			} else {
				try {
					if(friend_queue.size() > MAX_SIZE){
						friend_queue.clear();
						friend_map.clear();
					}
					if (friend.getDeal() == 0) {
						if (dao.update(friend.getFriend()) == 0) {
							log.error(String.format("数据更新错误，玩家ID[%s]", String.valueOf(friend.getFriend().getRoleid())));
						}
					} else if (friend.getDeal() == 1) {
						if (dao.insert(friend.getFriend()) == 0) {
							log.error(String.format("数据保存错误，玩家ID[%s]", String.valueOf(friend.getFriend().getRoleid())));
						}
					}
				} catch (Exception e) {
					log.error("Friend Exception:", e);
					synchronized (this) {
						if(!friend_map.containsKey(friend.getFriend().getRoleid())){
							this.friend_queue.add(friend.getFriend().getRoleid());
							this.friend_map.put(friend.getFriend().getRoleid(), friend);
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
	 * 处理好友
	 *
	 * @param friend 好友
	 * @param deal 0-update 1-insert
	 */
	public void dealMail(Friend friend, int deal) {
		try {
			//this.friend_queue.add(new FriendInfo(friend, deal));
			synchronized (this) {
				if(!friend_map.containsKey(friend.getRoleid())){
					this.friend_queue.add(friend.getRoleid());
				}
				this.friend_map.put(friend.getRoleid(), new FriendInfo(friend, deal));
				notify();
			}
		} catch (Exception e) {
			log.error("SaveFriend Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}

	private class FriendInfo {

		private Friend friend;
		private int deal;

		public FriendInfo(Friend friend, int deal) {
			this.friend = friend;
			this.deal = deal;
		}

		public Friend getFriend() {
			return friend;
		}

		public int getDeal() {
			return deal;
		}
	}
}
