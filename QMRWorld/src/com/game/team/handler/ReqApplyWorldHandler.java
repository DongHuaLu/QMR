package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.team.message.ReqApplyWorldMessage;

public class ReqApplyWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqApplyWorldHandler.class);

	public void action(){
		try{
			ReqApplyWorldMessage msg = (ReqApplyWorldMessage)this.getMessage();
			ManagerPool.teamManager.stReqApply(msg.getPlayerid(),msg.getTeamid());

		}catch(ClassCastException e){
			log.error(e);
		}
	}
}