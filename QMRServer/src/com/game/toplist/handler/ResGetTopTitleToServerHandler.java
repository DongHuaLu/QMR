package com.game.toplist.handler;

import org.apache.log4j.Logger;

import com.game.toplist.message.ResGetTopTitleToServerMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.toplist.manager.TopListManager;

public class ResGetTopTitleToServerHandler extends Handler{

	Logger log = Logger.getLogger(ResGetTopTitleToServerHandler.class);

	public void action(){
		try{
			ResGetTopTitleToServerMessage msg = (ResGetTopTitleToServerMessage)this.getMessage();
			TopListManager.getInstance().resGetTopTitleToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}