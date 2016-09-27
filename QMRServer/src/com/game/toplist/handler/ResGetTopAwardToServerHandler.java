package com.game.toplist.handler;

import org.apache.log4j.Logger;

import com.game.toplist.message.ResGetTopAwardToServerMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.toplist.manager.TopListManager;

public class ResGetTopAwardToServerHandler extends Handler{

	Logger log = Logger.getLogger(ResGetTopAwardToServerHandler.class);

	public void action(){
		try{
			ResGetTopAwardToServerMessage msg = (ResGetTopAwardToServerMessage)this.getMessage();
			TopListManager.getInstance().resGetTopAwardToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}