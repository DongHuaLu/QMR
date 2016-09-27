package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqSyncPlayerPositionMessage;
import com.game.command.Handler;

public class ReqSyncPlayerPositionHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerPositionHandler.class);

	public void action(){
		try{
			ReqSyncPlayerPositionMessage msg = (ReqSyncPlayerPositionMessage)this.getMessage();
			//同步玩家map
			ManagerPool.playerManager.syncPlayerPosition(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}