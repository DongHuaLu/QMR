package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ResPlayerCrossFromDataServerErrorMessage;
import com.game.command.Handler;

public class ResPlayerCrossFromDataServerErrorHandler extends Handler{

	Logger log = Logger.getLogger(ResPlayerCrossFromDataServerErrorHandler.class);

	public void action(){
		try{
			ResPlayerCrossFromDataServerErrorMessage msg = (ResPlayerCrossFromDataServerErrorMessage)this.getMessage();
			DataServerManager.getInstance().resPlayerCrossFromDataServerError(msg);			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}