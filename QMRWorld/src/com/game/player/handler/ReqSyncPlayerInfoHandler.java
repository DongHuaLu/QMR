package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.message.ReqSyncPlayerInfoMessage;

public class ReqSyncPlayerInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerInfoHandler.class);

	public void action(){
		try{
			ReqSyncPlayerInfoMessage msg = (ReqSyncPlayerInfoMessage)this.getMessage();
			//同步玩家信息
			ManagerPool.playerManager.syncPlayerInfo(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}