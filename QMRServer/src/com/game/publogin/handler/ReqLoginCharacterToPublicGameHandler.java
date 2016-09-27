package com.game.publogin.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ReqSyncPlayerInfoFromDataServerMessage;
import com.game.publogin.message.ReqLoginCharacterToPublicGameMessage;
import com.game.server.impl.WServer;

public class ReqLoginCharacterToPublicGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqLoginCharacterToPublicGameHandler.class);

	public void action(){
		try{
			ReqLoginCharacterToPublicGameMessage msg = (ReqLoginCharacterToPublicGameMessage)this.getMessage();
			DataServerManager.getInstance().reqSyncPlayerInfoFromDataServer(msg.getUserId(), msg.getWeb(), msg.getPlayerId(), msg.getGateId());
		}catch(Exception e){
			log.error(e, e);
		}
	}
}