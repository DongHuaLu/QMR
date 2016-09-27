package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.stalls.message.ResStallsOffShelfToGameMessage;

public class ResStallsOffShelfToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResStallsOffShelfToGameHandler.class);

	public void action(){
		try{
			ResStallsOffShelfToGameMessage msg = (ResStallsOffShelfToGameMessage)this.getMessage();
			ManagerPool.stallsManager.stResStallsOffShelfToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}