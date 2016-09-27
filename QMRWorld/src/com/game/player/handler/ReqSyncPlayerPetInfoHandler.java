package com.game.player.handler;

import com.game.command.Handler;
import com.game.pet.struts.Pet;
import com.game.player.message.ReqSyncPlayerPetInfoMessage;
import com.game.toplist.manager.TopListManager;
import com.game.toplist.structs.PetTop;
import com.game.toplist.structs.TopPlayer;
import org.apache.log4j.Logger;

public class ReqSyncPlayerPetInfoHandler extends Handler {

	Logger log = Logger.getLogger(ReqSyncPlayerPetInfoHandler.class);

	public void action() {
		try {
			ReqSyncPlayerPetInfoMessage msg = (ReqSyncPlayerPetInfoMessage) this.getMessage();
			TopPlayer topPlayer = TopListManager.getTopplayerMap().get(msg.getPlayerId());
			if (topPlayer != null) {
				Pet maxPet = topPlayer.getMaxPet();
				if (maxPet != null && maxPet.getModelId()==msg.getPetId()) {
					PetTop petTop = new PetTop(msg.getPlayerId(), (int) msg.getPetId(), msg.getPetHeti(), msg.getPetLevel());
					TopListManager.getInstance().updateTopData(petTop);
				}
			}

		} catch (ClassCastException e) {
			log.error(e);
		}
	}
}