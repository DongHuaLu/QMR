package com.game.server.thread;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.game.cache.impl.MemoryCache;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class SaveThread extends Timer {
	
	private Logger log = Logger.getLogger(SaveThread.class);
	//定时任务
	private TimerTask task;
	
	public SaveThread(String name){
		super(name);
	}
	
	public void start(){
		task = new TimerTask() {
			@Override
			public void run() {
				try{
					MemoryCache<Long, Player> players = ManagerPool.playerManager.getPlayers();
					
					int size = ManagerPool.playerManager.getOnlineCount() / 100;
					if(size <= 0) size = 1;
					else if(size > 25) size = 25;
					//事件迭代器
					List<Player> saves = players.getWaitingSave(size);
					//派发事件
					for (int i = 0; i < saves.size(); i++) {
						Player player = saves.get(i);
						try{
//							ManagerPool.playerManager.updatePlayer(player);	
							player.setSave(true);
							Thread.sleep(100);
						}catch(Exception ex){
							log.error(ex, ex);
						}
						
					}
				}catch (Exception e) {
					log.error(e, e);
				}
			}
		};
		this.schedule(task, 0, 5000);
	}

	public void stop(boolean flag){
		task.cancel();
	}
}
