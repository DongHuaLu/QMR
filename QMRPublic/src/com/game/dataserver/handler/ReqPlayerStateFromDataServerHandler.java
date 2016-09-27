package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ReqPlayerStateFromDataServerMessage;
import com.game.command.Handler;

public class ReqPlayerStateFromDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlayerStateFromDataServerHandler.class);

	public void action(){
		try{
			ReqPlayerStateFromDataServerMessage msg = (ReqPlayerStateFromDataServerMessage)this.getMessage();
			DataServerManager.getInstance().reqPlayerState(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}