package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ResSyncPlayerToDataServerMessage;

public class ResSyncPlayerToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ResSyncPlayerToDataServerHandler.class);

	public void action(){
		try{
			ResSyncPlayerToDataServerMessage msg = (ResSyncPlayerToDataServerMessage)this.getMessage();
			DataServerManager.getInstance().resSyncPlayerToDataServer(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}