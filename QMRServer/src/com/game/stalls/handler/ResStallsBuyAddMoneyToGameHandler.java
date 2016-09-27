package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.stalls.message.ResStallsBuyAddMoneyToGameMessage;

public class ResStallsBuyAddMoneyToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResStallsBuyAddMoneyToGameHandler.class);

	public void action(){
		try{
			ResStallsBuyAddMoneyToGameMessage msg = (ResStallsBuyAddMoneyToGameMessage)this.getMessage();
			ManagerPool.stallsManager.stResStallsBuyAddMoneyToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}