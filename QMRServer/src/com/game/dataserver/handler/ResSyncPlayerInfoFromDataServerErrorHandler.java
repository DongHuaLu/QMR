package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ResSyncPlayerInfoFromDataServerErrorMessage;
import com.game.command.Handler;

public class ResSyncPlayerInfoFromDataServerErrorHandler extends Handler{

	Logger log = Logger.getLogger(ResSyncPlayerInfoFromDataServerErrorHandler.class);

	public void action(){
		try{
			ResSyncPlayerInfoFromDataServerErrorMessage msg = (ResSyncPlayerInfoFromDataServerErrorMessage)this.getMessage();
			DataServerManager.getInstance().resSyncPlayerInfoFromDataServerError(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}