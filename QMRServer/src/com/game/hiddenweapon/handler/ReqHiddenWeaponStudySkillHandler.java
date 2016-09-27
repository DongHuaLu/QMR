package com.game.hiddenweapon.handler;

import org.apache.log4j.Logger;

import com.game.hiddenweapon.manager.HiddenWeaponManager;
import com.game.hiddenweapon.message.ReqHiddenWeaponStudySkillMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqHiddenWeaponStudySkillHandler extends Handler{

	Logger log = Logger.getLogger(ReqHiddenWeaponStudySkillHandler.class);

	public void action(){
		try{
			// TODO 通过脚本做
//			ReqHiddenWeaponStudySkillMessage msg = (ReqHiddenWeaponStudySkillMessage)this.getMessage();
//			HiddenWeaponManager.getInstance().studySkill((Player) this.getParameter(), msg.getItem());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}