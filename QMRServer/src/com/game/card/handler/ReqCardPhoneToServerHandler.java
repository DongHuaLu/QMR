package com.game.card.handler;

import com.game.card.manager.CardManager;
import org.apache.log4j.Logger;

import com.game.card.message.ReqCardPhoneToServerMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqCardPhoneToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqCardPhoneToServerHandler.class);

	public void action(){
		try{
			ReqCardPhoneToServerMessage msg = (ReqCardPhoneToServerMessage)this.getMessage();
			CardManager.getInstance().reqCardPhoneToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}