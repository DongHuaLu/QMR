package com.game.pet.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.pet.manager.PetOptManager;
import com.game.pet.message.ReqGotPetMessage;
import com.game.player.structs.Player;

public class ReqGotPetHandler extends Handler{
	Logger log = Logger.getLogger(ReqGotPetHandler.class);
	public void action() {
		try {
			ReqGotPetMessage msg = (ReqGotPetMessage) this.getMessage();
			PetOptManager.getInstance().gotPet((Player) getParameter(), msg.getModelId());
		} catch (ClassCastException e) {
			log.error(e);
		}
	}
}