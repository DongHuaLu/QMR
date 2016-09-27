package com.game.epalace.handler;

import org.apache.log4j.Logger;

import com.game.epalace.message.ReqEpalaceTaskEndToGameMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqEpalaceTaskEndToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqEpalaceTaskEndToGameHandler.class);

	public void action(){
		try{
			ReqEpalaceTaskEndToGameMessage msg = (ReqEpalaceTaskEndToGameMessage)this.getMessage();
			ManagerPool.epalaceManeger.stReqEpalaceTaskEndToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}