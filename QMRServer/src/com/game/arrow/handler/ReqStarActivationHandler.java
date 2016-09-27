package com.game.arrow.handler;

import com.game.arrow.manager.ArrowManager;
import org.apache.log4j.Logger;

import com.game.arrow.message.ReqStarActivationMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqStarActivationHandler extends Handler{

	Logger log = Logger.getLogger(ReqStarActivationHandler.class);

	public void action(){
		try{
			ReqStarActivationMessage msg = (ReqStarActivationMessage)this.getMessage();
			ArrowManager.getInstance().reqStarActivationToServer((Player) this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}