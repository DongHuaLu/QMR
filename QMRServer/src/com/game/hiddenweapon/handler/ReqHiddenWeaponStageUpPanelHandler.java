package com.game.hiddenweapon.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqHiddenWeaponStageUpPanelHandler extends Handler{

	Logger log = Logger.getLogger(ReqHiddenWeaponStageUpPanelHandler.class);

	public void action(){
		try{
			ManagerPool.hiddenWeaponManager.openHiddenWeaponStageUp((Player) this.getParameter());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}