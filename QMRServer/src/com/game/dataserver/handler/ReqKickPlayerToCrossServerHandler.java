package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ReqKickPlayerToCrossServerMessage;
import com.game.command.Handler;

public class ReqKickPlayerToCrossServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqKickPlayerToCrossServerHandler.class);

	public void action(){
		try{
			ReqKickPlayerToCrossServerMessage msg = (ReqKickPlayerToCrossServerMessage)this.getMessage();
			DataServerManager.getInstance().reqKickPlayerToCrossServer(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}