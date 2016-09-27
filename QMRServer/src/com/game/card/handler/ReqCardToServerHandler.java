package com.game.card.handler;

import com.game.card.manager.CardManager;
import org.apache.log4j.Logger;

import com.game.card.message.ReqCardToServerMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqCardToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqCardToServerHandler.class);

	public void action(){
		try{
			ReqCardToServerMessage msg = (ReqCardToServerMessage)this.getMessage();
			CardManager.getInstance().reqCardToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}