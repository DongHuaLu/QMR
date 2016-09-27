package com.game.server.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.User;

public class SaveThread extends Timer {
	
	private Logger log = Logger.getLogger(SaveThread.class);

	public SaveThread(String name){
		super(name);
	}
	
	public void start(){
		this.schedule(new TimerTask() {
			
			@Override
			public void run() {
				try{
					ConcurrentHashMap<String, User> users = PlayerManager.getUserTimes();
					
					List<User> insert = new ArrayList<User>();
					List<User> update = new ArrayList<User>();
					List<User> delete = new ArrayList<User>();
					
					synchronized (users) {
						//迭代器
						Iterator<User> iter = users.values().iterator();
						while (iter.hasNext()) {
							User user = (User) iter.next();
							if(user.getState()==0){
								update.add(user);
							}else if(user.getState()==1){
								user.setState(0);
								insert.add(user);
							}else if(user.getState()==2){
								iter.remove();
								delete.add(user);
							}
						}
					}
					
					for (int i = 0; i < insert.size(); i++) {
						ManagerPool.playerManager.insertUserOnline(insert.get(i));
					}
					
					for (int i = 0; i < update.size(); i++) {
						ManagerPool.playerManager.updateUserOnline(update.get(i));
					}
					
					for (int i = 0; i < delete.size(); i++) {
						ManagerPool.playerManager.deleteUserOnline(delete.get(i));
					}
				}catch (Exception e) {
					log.error(e, e);
				}
			}
		}, 5 * 60 * 1000, 5 * 60 * 1000);
	}

}
