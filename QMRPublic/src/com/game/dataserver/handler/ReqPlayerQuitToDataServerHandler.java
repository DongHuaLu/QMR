package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ReqPlayerQuitToDataServerMessage;
import com.game.command.Handler;

public class ReqPlayerQuitToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlayerQuitToDataServerHandler.class);

	public void action(){
		try{
			ReqPlayerQuitToDataServerMessage msg = (ReqPlayerQuitToDataServerMessage)this.getMessage();
			DataServerManager.getInstance().reqPlayerQuit(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}