package com.game.skill.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.skill.message.StudySkillMessage;

public class StudySkillHandler extends Handler{

	Logger log = Logger.getLogger(StudySkillHandler.class);

	public void action(){
		try{
			StudySkillMessage msg = (StudySkillMessage)this.getMessage();
			//XXX 前端通过发消息来学习去掉了 技能全部自动触发增加
//			ManagerPool.skillManager.study((Player)this.getParameter(), msg.getSkillModelId(), msg.getBookId());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}