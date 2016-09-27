package com.game.epalace.handler;

import org.apache.log4j.Logger;

import com.game.epalace.message.ReqEpalaceAbandonTaskToGameMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqEpalaceAbandonTaskToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqEpalaceAbandonTaskToGameHandler.class);

	public void action(){
		try{
			ReqEpalaceAbandonTaskToGameMessage msg = (ReqEpalaceAbandonTaskToGameMessage)this.getMessage();
			ManagerPool.epalaceManeger.stReqEpalaceAbandonTaskToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}