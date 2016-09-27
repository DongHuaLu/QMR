package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqSyncPlayerMapMessage;
import com.game.command.Handler;

public class ReqSyncPlayerMapHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerMapHandler.class);

	public void action(){
		try{
			ReqSyncPlayerMapMessage msg = (ReqSyncPlayerMapMessage)this.getMessage();
			//同步玩家map
			ManagerPool.playerManager.syncPlayerMap(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}