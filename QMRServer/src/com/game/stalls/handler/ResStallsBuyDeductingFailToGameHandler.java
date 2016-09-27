package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.stalls.message.ResStallsBuyDeductingFailToGameMessage;

public class ResStallsBuyDeductingFailToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResStallsBuyDeductingFailToGameHandler.class);

	public void action(){
		try{
			ResStallsBuyDeductingFailToGameMessage msg = (ResStallsBuyDeductingFailToGameMessage)this.getMessage();
			ManagerPool.stallsManager.stResStallsBuyDeductingFailToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}