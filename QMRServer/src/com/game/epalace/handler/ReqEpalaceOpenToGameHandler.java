package com.game.epalace.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqEpalaceOpenToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqEpalaceOpenToGameHandler.class);

	public void action(){
		try{
			//ReqEpalaceOpenToGameMessage msg = (ReqEpalaceOpenToGameMessage)this.getMessage();
			ManagerPool.epalaceManeger.stReqEpalaceOpenToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}