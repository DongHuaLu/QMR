package com.game.pet.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.pet.manager.PetOptManager;
import com.game.pet.message.ReqShowPetMessage;
import com.game.player.structs.Player;

public class ReqShowPetHandler extends Handler{

	Logger log = Logger.getLogger(ReqShowPetHandler.class);

	public void action(){
		try{
			ReqShowPetMessage msg = (ReqShowPetMessage)this.getMessage();
			log.error("角色[" + ((Player) getParameter()).getId() + "]美人[" + msg.getPetId() + "]操作[player msg show]");
			PetOptManager.getInstance().showPet((Player) getParameter(), msg.getPetId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}