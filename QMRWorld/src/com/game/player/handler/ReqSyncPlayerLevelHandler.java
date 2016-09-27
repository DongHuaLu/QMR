package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqSyncPlayerLevelMessage;
import com.game.command.Handler;

public class ReqSyncPlayerLevelHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerLevelHandler.class);

	public void action(){
		try{
			ReqSyncPlayerLevelMessage msg = (ReqSyncPlayerLevelMessage)this.getMessage();
			//同步玩家level
			ManagerPool.playerManager.syncPlayerLevel(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}