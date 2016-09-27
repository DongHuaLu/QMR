package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqDelPlayerToGameMessage;
import com.game.command.Handler;

public class ReqDelPlayerToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqDelPlayerToGameHandler.class);

	public void action(){
		try{
			ReqDelPlayerToGameMessage msg = (ReqDelPlayerToGameMessage)this.getMessage();
			ManagerPool.playerManager.stReqDelPlayerToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}