package com.game.horseweapon.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqGetHorseWeaponInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetHorseWeaponInfoHandler.class);

	public void action(){
		try{
			//获得骑战兵器信息
			ManagerPool.horseWeaponManager.sendHorseWeaponInfo((Player) this.getParameter());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}