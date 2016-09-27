package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ReqSyncPlayerToDataServerMessage;
import com.game.command.Handler;

public class ReqSyncPlayerToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerToDataServerHandler.class);

	public void action(){
		try{
			ReqSyncPlayerToDataServerMessage msg = (ReqSyncPlayerToDataServerMessage)this.getMessage();
			DataServerManager.getInstance().recSyncPlayerData(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}