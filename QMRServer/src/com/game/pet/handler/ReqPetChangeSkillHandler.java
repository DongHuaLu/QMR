package com.game.pet.handler;

import org.apache.log4j.Logger;

import com.game.pet.manager.PetOptManager;
import com.game.pet.message.ReqPetChangeSkillMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqPetChangeSkillHandler extends Handler{

	Logger log = Logger.getLogger(ReqPetChangeSkillHandler.class);

	public void action(){
		try{
			ReqPetChangeSkillMessage msg = (ReqPetChangeSkillMessage)this.getMessage();
			PetOptManager.getInstance().changeSkill((Player)getParameter(),msg.getPetId(),msg.getIndex(),msg.getSkillModel());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}