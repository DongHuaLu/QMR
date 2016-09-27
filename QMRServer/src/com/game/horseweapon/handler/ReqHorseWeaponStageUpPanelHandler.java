package com.game.horseweapon.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqHorseWeaponStageUpPanelHandler extends Handler{

	Logger log = Logger.getLogger(ReqHorseWeaponStageUpPanelHandler.class);

	public void action(){
		try{
			//打开坐骑升阶面板
			ManagerPool.horseWeaponManager.openHorseWeaponStageUp((Player) this.getParameter());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}