package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqChangePlayerNameToWorldMessage;
import com.game.command.Handler;

public class ReqChangePlayerNameToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangePlayerNameToWorldHandler.class);

	public void action(){
		try{
			ReqChangePlayerNameToWorldMessage msg = (ReqChangePlayerNameToWorldMessage)this.getMessage();
			ManagerPool.playerManager.stReqChangePlayerNameToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}