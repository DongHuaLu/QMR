package com.game.fight.handler;

import org.apache.log4j.Logger;

import com.game.fight.manager.FightManager;
import com.game.fight.message.ReqChangeAttackTargetMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqChangeAttackTargetHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangeAttackTargetHandler.class);

	public void action(){
		try{
			ReqChangeAttackTargetMessage msg = (ReqChangeAttackTargetMessage)this.getMessage();
			FightManager.getInstance().chanteAttackTarget((Player)getParameter(),msg.getTargetId(),msg.getTargetType());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}