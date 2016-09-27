package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ResPlayerQuitToDataServerMessage;
import com.game.command.Handler;

public class ResPlayerQuitToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ResPlayerQuitToDataServerHandler.class);

	public void action(){
		try{
			ResPlayerQuitToDataServerMessage msg = (ResPlayerQuitToDataServerMessage)this.getMessage();
			DataServerManager.getInstance().resPlayerQuitToDataServer(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}