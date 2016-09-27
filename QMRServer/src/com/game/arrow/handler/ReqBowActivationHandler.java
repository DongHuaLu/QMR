package com.game.arrow.handler;

import com.game.arrow.manager.ArrowManager;
import org.apache.log4j.Logger;

import com.game.arrow.message.ReqBowActivationMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqBowActivationHandler extends Handler{

	Logger log = Logger.getLogger(ReqBowActivationHandler.class);

	public void action(){
		try{
			ReqBowActivationMessage msg = (ReqBowActivationMessage)this.getMessage();
			ArrowManager.getInstance().reqBowActivationToServer((Player) this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}