package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ResPlayerCrossFromDataServerMessage;
import com.game.command.Handler;

public class ResPlayerCrossFromDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ResPlayerCrossFromDataServerHandler.class);

	public void action(){
		try{
			ResPlayerCrossFromDataServerMessage msg = (ResPlayerCrossFromDataServerMessage)this.getMessage();
			DataServerManager.getInstance().resPlayerCrossFromDataServer(msg);			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}