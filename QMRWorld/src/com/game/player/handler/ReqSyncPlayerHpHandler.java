package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqSyncPlayerHpMessage;
import com.game.command.Handler;

public class ReqSyncPlayerHpHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerHpHandler.class);

	public void action(){
		try{
			ReqSyncPlayerHpMessage msg = (ReqSyncPlayerHpMessage)this.getMessage();
			//同步玩家hp
			ManagerPool.playerManager.syncPlayerHp(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}