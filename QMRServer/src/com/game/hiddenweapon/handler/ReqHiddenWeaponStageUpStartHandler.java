package com.game.hiddenweapon.handler;

import org.apache.log4j.Logger;

import com.game.hiddenweapon.message.ReqHiddenWeaponStageUpStartMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqHiddenWeaponStageUpStartHandler extends Handler{

	Logger log = Logger.getLogger(ReqHiddenWeaponStageUpStartHandler.class);

	public void action(){
		try{
			ReqHiddenWeaponStageUpStartMessage msg = (ReqHiddenWeaponStageUpStartMessage)this.getMessage();
			ManagerPool.hiddenWeaponManager.levelupHiddenWeapon((Player) this.getParameter(), msg.getType());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}