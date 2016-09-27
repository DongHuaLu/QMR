package com.game.pet.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.pet.manager.PetOptManager;
import com.game.pet.message.ReqHTCDClearMessage;
import com.game.player.structs.Player;

public class ReqHTCDClearHandler extends Handler{

	Logger log = Logger.getLogger(ReqHTCDClearHandler.class);

	public void action(){
		try{
			ReqHTCDClearMessage msg = (ReqHTCDClearMessage)this.getMessage();
			PetOptManager.getInstance().clearHtCD((Player)getParameter(), msg.getModelId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}