package com.game.pet.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.pet.manager.PetInfoManager;
import com.game.player.structs.Player;

public class ReqPetListQueryHandler extends Handler{

	Logger log = Logger.getLogger(ReqPetListQueryHandler.class);

	public void action(){
		try{
//			ReqPetListQueryMessage msg = (ReqPetListQueryMessage)this.getMessage();
			PetInfoManager.getInstance().sendPetInfo((Player) getParameter());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}