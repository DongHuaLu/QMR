package com.game.skill.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.skill.message.LevelUpSkillMessage;

public class LevelUpSkillHandler extends Handler{

	Logger log = Logger.getLogger(LevelUpSkillHandler.class);

	public void action(){
		try{
			LevelUpSkillMessage msg = (LevelUpSkillMessage)this.getMessage();

			ManagerPool.skillManager.levelUp((Player)this.getParameter(), msg.getSkillModelId());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}