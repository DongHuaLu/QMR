package com.game.toplist.handler;

import org.apache.log4j.Logger;

import com.game.toplist.message.ReqGetTopListToServerMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.toplist.manager.TopListManager;

public class ReqGetTopListToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetTopListToServerHandler.class);

	public void action(){
		try{
			ReqGetTopListToServerMessage msg = (ReqGetTopListToServerMessage)this.getMessage();
			TopListManager.getInstance().reqGetTopListToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}