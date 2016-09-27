package com.game.horseweapon.handler;

import org.apache.log4j.Logger;

import com.game.horseweapon.message.ReqSetHorseWeaponSkillMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqSetHorseWeaponSkillHandler extends Handler{

	Logger log = Logger.getLogger(ReqSetHorseWeaponSkillHandler.class);

	public void action(){
		try{
			ReqSetHorseWeaponSkillMessage msg = (ReqSetHorseWeaponSkillMessage)this.getMessage();
			//设置技能
			ManagerPool.horseWeaponManager.setHorseWeaponSkill((Player) this.getParameter(), msg.getSkillId(), msg.getGrid());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}