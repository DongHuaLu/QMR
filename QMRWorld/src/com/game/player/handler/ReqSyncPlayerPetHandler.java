package com.game.player.handler;

import com.game.command.Handler;
import com.game.json.JSONserializable;
import com.game.pet.struts.Pet;
import com.game.player.message.ReqSyncPlayerPetMessage;
import com.game.toplist.manager.TopListManager;
import com.game.toplist.structs.PetTop;
import com.game.toplist.structs.TopPlayer;
import org.apache.log4j.Logger;

public class ReqSyncPlayerPetHandler extends Handler {

	Logger log = Logger.getLogger(ReqSyncPlayerPetHandler.class);

	public void action() {
		try {
			ReqSyncPlayerPetMessage msg = (ReqSyncPlayerPetMessage) this.getMessage();
			TopPlayer topPlayer = TopListManager.getTopplayerMap().get(msg.getPlayerId());
			if (topPlayer != null) {
				Pet pet = (Pet) JSONserializable.toObject(msg.getPet(), Pet.class);
				if (pet != null) {
					if (topPlayer.checkAndRetPet(pet.getModelId()) == null) {
						Pet maxPet = topPlayer.getMaxPet();
						if (maxPet != null && maxPet.getModelId() < pet.getModelId()) {
							PetTop petTop = new PetTop(msg.getPlayerId(), pet.getModelId(), pet.getHtcount(), pet.getLevel());
							TopListManager.getInstance().updateTopData(petTop);
						}
						topPlayer.getPetList().add(pet);
					}
				}
			}

		} catch (ClassCastException e) {
			log.error(e);
		}
	}
}