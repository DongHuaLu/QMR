package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqDelPlayerToWorldMessage;
import com.game.command.Handler;

public class ReqDelPlayerToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqDelPlayerToWorldHandler.class);

	public void action(){
		try{
			ReqDelPlayerToWorldMessage msg = (ReqDelPlayerToWorldMessage)this.getMessage();
			ManagerPool.playerManager.stReqDelPlayerToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}