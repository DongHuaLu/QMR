package com.game.skill.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.skill.message.SetDefaultSkillMessage;
import com.game.command.Handler;

public class SetDefaultSkillHandler extends Handler{

	Logger log = Logger.getLogger(SetDefaultSkillHandler.class);

	public void action(){
		try{
			SetDefaultSkillMessage msg = (SetDefaultSkillMessage)this.getMessage();
			
			ManagerPool.skillManager.setDefaultSkill((Player)this.getParameter(), msg.getDefaultSkill());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}