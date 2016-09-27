package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.stalls.message.ResStallsBuycheckToGameMessage;

public class ResStallsBuycheckToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResStallsBuycheckToGameHandler.class);

	public void action(){
		try{
			ResStallsBuycheckToGameMessage msg = (ResStallsBuycheckToGameMessage)this.getMessage();
			ManagerPool.stallsManager.stResStallsBuycheckToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}