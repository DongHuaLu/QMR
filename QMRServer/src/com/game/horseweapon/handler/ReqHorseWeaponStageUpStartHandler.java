package com.game.horseweapon.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.horseweapon.message.ReqHorseWeaponStageUpStartMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqHorseWeaponStageUpStartHandler extends Handler{

	Logger log = Logger.getLogger(ReqHorseWeaponStageUpStartHandler.class);

	public void action(){
		try{
			ReqHorseWeaponStageUpStartMessage msg = (ReqHorseWeaponStageUpStartMessage)this.getMessage();
			//升阶骑战兵器
			ManagerPool.horseWeaponManager.levelupHorseWeapon((Player) this.getParameter(), msg.getType());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}