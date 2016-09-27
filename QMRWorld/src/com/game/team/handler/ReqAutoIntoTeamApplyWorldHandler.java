package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.team.message.ReqAutoIntoTeamApplyWorldMessage;

public class ReqAutoIntoTeamApplyWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqAutoIntoTeamApplyWorldHandler.class);

	public void action(){
		try{
			ReqAutoIntoTeamApplyWorldMessage msg = (ReqAutoIntoTeamApplyWorldMessage)this.getMessage();
			ManagerPool.teamManager.stAutoIntoTeamApply(msg.getPlayerid(), msg.getAutointoteamapply());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}