package com.game.toplist.handler;

import org.apache.log4j.Logger;

import com.game.toplist.message.ReqChangeTitleToServerMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.toplist.manager.TopListManager;

public class ReqChangeTitleToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangeTitleToServerHandler.class);

	public void action(){
		try{
			ReqChangeTitleToServerMessage msg = (ReqChangeTitleToServerMessage)this.getMessage();
			TopListManager.getInstance().reqChangeTitleToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}