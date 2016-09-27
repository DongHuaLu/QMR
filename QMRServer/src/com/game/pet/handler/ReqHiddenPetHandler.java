package com.game.pet.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.pet.manager.PetOptManager;
import com.game.pet.message.ReqHiddenPetMessage;
import com.game.player.structs.Player;

public class ReqHiddenPetHandler extends Handler{

	Logger log = Logger.getLogger(ReqHiddenPetHandler.class);

	public void action(){
		try{
			ReqHiddenPetMessage msg = (ReqHiddenPetMessage)this.getMessage();
			log.error("角色[" + ((Player) getParameter()).getId() + "]美人[" + msg.getPetId() + "]操作[player msg hide]");
			PetOptManager.getInstance().hidePet((Player) getParameter(),msg.getPetId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}