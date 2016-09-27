package com.game.ybcard.handler;

import org.apache.log4j.Logger;

import com.game.ybcard.message.ReqYBCardToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqYBCardToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqYBCardToGameHandler.class);

	public void action(){
		try{
			ReqYBCardToGameMessage msg = (ReqYBCardToGameMessage)this.getMessage();
			ManagerPool.ybcardManager.stReqYBCardToGameMessage((Player)this.getParameter(), msg.getType(), 1);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}