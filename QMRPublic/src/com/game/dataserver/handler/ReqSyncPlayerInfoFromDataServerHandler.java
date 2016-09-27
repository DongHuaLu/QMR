package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ReqSyncPlayerInfoFromDataServerMessage;
import com.game.command.Handler;

public class ReqSyncPlayerInfoFromDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerInfoFromDataServerHandler.class);

	public void action(){
		try{
			ReqSyncPlayerInfoFromDataServerMessage msg = (ReqSyncPlayerInfoFromDataServerMessage)this.getMessage();
			DataServerManager.getInstance().reqPlayerData(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}