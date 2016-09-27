package com.game.hiddenweapon.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqGetHiddenWeaponInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetHiddenWeaponInfoHandler.class);

	public void action(){
		try{
			ManagerPool.hiddenWeaponManager.sendHiddenWeaponInfo((Player) this.getParameter());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}