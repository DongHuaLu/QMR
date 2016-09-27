package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ResSyncPlayerCrossToDataServerMessage;
import com.game.command.Handler;

public class ResSyncPlayerCrossToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ResSyncPlayerCrossToDataServerHandler.class);

	public void action(){
		try{
			ResSyncPlayerCrossToDataServerMessage msg = (ResSyncPlayerCrossToDataServerMessage)this.getMessage();
			DataServerManager.getInstance().resSyncPlayerCrossToDataServer(msg);			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}