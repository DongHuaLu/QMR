package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.stalls.message.ResStallsProductWasAddedFailToGameMessage;

public class ResStallsProductWasAddedFailToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResStallsProductWasAddedFailToGameHandler.class);

	public void action(){
		try{
			ResStallsProductWasAddedFailToGameMessage msg = (ResStallsProductWasAddedFailToGameMessage)this.getMessage();
			ManagerPool.stallsManager.stResStallsProductWasAddedFailToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}