package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqSyncPlayerOrderInfoMessage;
import com.game.command.Handler;

public class ReqSyncPlayerOrderInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerOrderInfoHandler.class);

	public void action(){
		try{
			ReqSyncPlayerOrderInfoMessage msg = (ReqSyncPlayerOrderInfoMessage)this.getMessage();
			//同步玩家排行消息
			ManagerPool.playerManager.syncPlayerOrderInfo(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}