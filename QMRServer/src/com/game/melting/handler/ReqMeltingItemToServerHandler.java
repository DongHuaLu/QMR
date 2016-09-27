package com.game.melting.handler;

import org.apache.log4j.Logger;

import com.game.melting.message.ReqMeltingItemToServerMessage;
import com.game.command.Handler;
import com.game.melting.manager.MeltingManager;
import com.game.player.structs.Player;

public class ReqMeltingItemToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqMeltingItemToServerHandler.class);

	public void action(){
		try{
			ReqMeltingItemToServerMessage msg = (ReqMeltingItemToServerMessage)this.getMessage();
			MeltingManager.getInstance().reqMeltingItemToServer((Player) this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}