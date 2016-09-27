package com.game.protect.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqSelectIsSetToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqSelectIsSetToGameHandler.class);

	public void action(){
		try{
			//ReqSelectIsSetToGameMessage msg = (ReqSelectIsSetToGameMessage)this.getMessage();
			//ManagerPool.protectManager.stReqSelectIsSetToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}