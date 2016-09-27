package com.game.epalace.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqEpalaceDiceToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqEpalaceDiceToGameHandler.class);

	public void action(){
		try{
			//ReqEpalaceDiceToGameMessage msg = (ReqEpalaceDiceToGameMessage)this.getMessage();
			ManagerPool.epalaceManeger.stReqEpalaceDiceToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}