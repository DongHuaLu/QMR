package com.game.horseweapon.handler;

import org.apache.log4j.Logger;

import com.game.horseweapon.message.ReqWearHorseWeaponStateMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqWearHorseWeaponStateHandler extends Handler{

	Logger log = Logger.getLogger(ReqWearHorseWeaponStateHandler.class);

	public void action(){
		try{
			ReqWearHorseWeaponStateMessage msg = (ReqWearHorseWeaponStateMessage)this.getMessage();
			//穿脱装备
			if(msg.getStatus()==0){
				ManagerPool.horseWeaponManager.unwearHorseWeapon((Player) this.getParameter());
			}else{
				ManagerPool.horseWeaponManager.wearHorseWeapon((Player) this.getParameter(), msg.getCurlayer());
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}