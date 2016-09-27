package com.game.fight.handler;

import org.apache.log4j.Logger;

import com.game.fight.message.ReqAttackPetMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqAttackPetHandler extends Handler{
	Logger log = Logger.getLogger(ReqAttackPetHandler.class);
	public void action(){
		try{
			ReqAttackPetMessage msg = (ReqAttackPetMessage)this.getMessage();
			ManagerPool.fightManager.playerAttackPet((Player)getParameter(), msg.getOwherId(), msg.getFightTarget(), msg.getFightType(), msg.getFightDirection());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}