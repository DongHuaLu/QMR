package com.game.hiddenweapon.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ReqSetHiddenWeaponSkillHandler extends Handler{

	Logger log = Logger.getLogger(ReqSetHiddenWeaponSkillHandler.class);

	public void action(){
		try{
			// 这个消息不要了
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}