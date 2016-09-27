package com.game.card.handler;

import com.game.card.manager.CardManager;
import org.apache.log4j.Logger;

import com.game.card.message.ResInnerCardToServerMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ResInnerCardToServerHandler extends Handler{

	Logger log = Logger.getLogger(ResInnerCardToServerHandler.class);

	public void action(){
		try{
			ResInnerCardToServerMessage msg = (ResInnerCardToServerMessage)this.getMessage();
			CardManager.getInstance().resInnerCardToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}