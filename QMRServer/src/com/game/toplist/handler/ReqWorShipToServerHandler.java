package com.game.toplist.handler;

import org.apache.log4j.Logger;

import com.game.toplist.message.ReqWorShipToServerMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.toplist.manager.TopListManager;

public class ReqWorShipToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqWorShipToServerHandler.class);

	public void action(){
		try{
			ReqWorShipToServerMessage msg = (ReqWorShipToServerMessage)this.getMessage();
			TopListManager.getInstance().reqWorShipToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}