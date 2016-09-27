package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqSyncPlayerAppearanceInfoMessage;
import com.game.command.Handler;

public class ReqSyncPlayerAppearanceInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerAppearanceInfoHandler.class);

	public void action(){
		try{
			ReqSyncPlayerAppearanceInfoMessage msg = (ReqSyncPlayerAppearanceInfoMessage)this.getMessage();
			//同步玩家外观
			ManagerPool.playerManager.syncPlayerAppearanceInfo(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}