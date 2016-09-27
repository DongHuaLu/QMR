package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.stalls.message.ResStallsBuyDeductingToGameMessage;
import com.game.command.Handler;

public class ResStallsBuyDeductingToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResStallsBuyDeductingToGameHandler.class);

	public void action(){
		try{
			ResStallsBuyDeductingToGameMessage msg = (ResStallsBuyDeductingToGameMessage)this.getMessage();
			ManagerPool.stallsManager.stResStallsBuyDeductingToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}