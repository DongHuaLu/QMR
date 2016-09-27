package com.game.horse.handler;

import org.apache.log4j.Logger;

import com.game.horse.message.ReqSkillInfoMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqSkillInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqSkillInfoHandler.class);

	public void action(){
		try{
			ReqSkillInfoMessage msg = (ReqSkillInfoMessage)this.getMessage();
			ManagerPool.horseManager.stReqSkillInfoMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}