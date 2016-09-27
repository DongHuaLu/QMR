package com.game.systemgrant.handler;

import org.apache.log4j.Logger;

import com.game.systemgrant.manager.SystemgrantManager;
import com.game.systemgrant.message.ResSystemgrantRefreshToGameMessage;
import com.game.command.Handler;

public class ResSystemgrantRefreshToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResSystemgrantRefreshToGameHandler.class);

	public void action(){
		try{
			ResSystemgrantRefreshToGameMessage msg = (ResSystemgrantRefreshToGameMessage)this.getMessage();
			SystemgrantManager.getInstance().operatingGrantmap(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}