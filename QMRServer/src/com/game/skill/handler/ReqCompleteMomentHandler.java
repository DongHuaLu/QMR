package com.game.skill.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.skill.manager.SkillManager;

public class ReqCompleteMomentHandler extends Handler{

	Logger log = Logger.getLogger(ReqCompleteMomentHandler.class);

	public void action(){
		try{
			SkillManager.getInstance().goldEndUpLevel((Player)this.getParameter());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}