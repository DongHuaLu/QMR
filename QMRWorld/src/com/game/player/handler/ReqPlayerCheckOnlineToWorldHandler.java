package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.message.ReqPlayerCheckOnlineToWorldMessage;

public class ReqPlayerCheckOnlineToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlayerCheckOnlineToWorldHandler.class);

	public void action(){
		try{
			ReqPlayerCheckOnlineToWorldMessage msg = (ReqPlayerCheckOnlineToWorldMessage)this.getMessage();
			ManagerPool.playerManager.stReqPlayerCheckOnlineToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}