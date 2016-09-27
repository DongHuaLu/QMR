package com.game.pet.handler;

import org.apache.log4j.Logger;

import com.game.pet.manager.PetOptManager;
import com.game.pet.message.ReqPetHeTiMessage;
import com.game.player.structs.Player;
import com.game.utils.MessageUtil;
import com.game.command.Handler;

public class ReqPetHeTiHandler extends Handler{

	Logger log = Logger.getLogger(ReqPetHeTiHandler.class);

	public void action(){
		try{
			ReqPetHeTiMessage msg = (ReqPetHeTiMessage)this.getMessage();
			PetOptManager.getInstance().heti((Player)getParameter(), msg.getPetId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}