package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqGetPlayerAppearanceInfoToWorldMessage;
import com.game.command.Handler;

public class ReqGetPlayerAppearanceInfoToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetPlayerAppearanceInfoToWorldHandler.class);

	public void action(){
		try{
			ReqGetPlayerAppearanceInfoToWorldMessage msg = (ReqGetPlayerAppearanceInfoToWorldMessage)this.getMessage();
			ManagerPool.playerManager.stReqGetPlayerAppearanceInfoToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}