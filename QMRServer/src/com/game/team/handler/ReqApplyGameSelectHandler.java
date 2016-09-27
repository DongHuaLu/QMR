package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.team.message.ReqApplyGameSelectMessage;
import com.game.command.Handler;

public class ReqApplyGameSelectHandler extends Handler{

	Logger log = Logger.getLogger(ReqApplyGameSelectHandler.class);

	public void action(){
		try{
			ReqApplyGameSelectMessage msg = (ReqApplyGameSelectMessage)this.getMessage();
			ManagerPool.teamManager.stReqApplyGameSelect(msg.getTeamid(),msg.getPlayerid(),msg.getSelect());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}