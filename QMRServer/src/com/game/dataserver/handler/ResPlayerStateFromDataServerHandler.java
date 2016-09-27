package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ResPlayerStateFromDataServerMessage;
import com.game.command.Handler;

public class ResPlayerStateFromDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ResPlayerStateFromDataServerHandler.class);

	public void action(){
		try{
			ResPlayerStateFromDataServerMessage msg = (ResPlayerStateFromDataServerMessage)this.getMessage();
			DataServerManager.getInstance().resPlayerStateFromDataServer(msg);			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}