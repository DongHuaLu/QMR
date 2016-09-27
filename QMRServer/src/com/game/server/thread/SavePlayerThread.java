package com.game.server.thread;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.game.db.bean.Role;
import com.game.db.dao.RoleDao;
import com.game.utils.VersionUpdateUtil;

public class SavePlayerThread extends Thread {

	//日志
	private Logger log = LogManager.getLogger("SAVEPLAYER");
		
	//命令执行队列
	private LinkedBlockingQueue<Long> role_queue = new LinkedBlockingQueue<Long>();
	
	private HashMap<Long, Role> role_map = new HashMap<Long, Role>();
	//运行标志
	private boolean stop;
	
	//线程名称
	protected String threadName;
	
	private RoleDao dao = new RoleDao();
	
	private static int MAX_SIZE = 10000;
		
	public SavePlayerThread(String threadName){
		super(threadName);
		this.threadName = threadName;
	}
	
	public void run(){
		stop = false;
		while(!stop || role_queue.size() > 0){
			Role role = null;
			synchronized (this) {
				Object o = role_queue.poll();
				if(o!=null){
					long roleId = (Long)o;
					role = role_map.remove(roleId);
				}
			}
			if(role == null){
				try{
					synchronized (this) {
						wait();
					}
				}catch (InterruptedException e) {
					log.error("Save Role Thread " + threadName + " Wait Exception:" + e.getMessage());
				}
			}else{
				if(role_queue.size() > MAX_SIZE){
					role_queue.clear();
					role_map.clear();
				}
				try{
					if(dao.update(role)==0){
						dao.insert(role);
					}
					//log.error("保存后， 保存玩家队列剩余：" + this.role_queue.size());
				}catch (Exception e) {
					try{
						log.error("Role Exception:" + role.getRoleid() + "[" + VersionUpdateUtil.dateLoad(role.getData()) + "]", e);
					}catch (Exception ex) {
						log.error(ex, ex);
					}
					synchronized (this) {
						if(!role_map.containsKey(role.getRoleid())){
							this.role_queue.add(role.getRoleid());
							this.role_map.put(role.getRoleid(), role);
						}
					}
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
			log.error("Role Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}
	
	/**
	 * 添加金币数据
	 * @param role 金币数据
	 * @param insert 是否插入
	 */
	public void addRole(Role role){
		try{
			//log.error("插入中， 保存玩家队列剩余：" + this.role_queue.size());
			synchronized (this) {
				if(!role_map.containsKey(role.getRoleid())){
					this.role_queue.add(role.getRoleid());
				}
				this.role_map.put(role.getRoleid(), role);
				notify();
			}
		}catch (Exception e) {
			log.error("Role Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}
	
}
