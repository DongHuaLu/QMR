package com.game.gift.handler;

import org.apache.log4j.Logger;

import com.game.gift.message.ReqStartShuffleRaffleMessage;
import com.game.player.structs.Player;
import com.game.utils.ScriptsUtils;
import com.game.command.Handler;

public class ReqStartShuffleRaffleHandler extends Handler{

	Logger log = Logger.getLogger(ReqStartShuffleRaffleHandler.class);

	public void action(){
		try{
			ReqStartShuffleRaffleMessage msg = (ReqStartShuffleRaffleMessage)this.getMessage();
			ScriptsUtils.call(1009152, "flopreward", (Player)this.getParameter(),msg.getItemid());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}