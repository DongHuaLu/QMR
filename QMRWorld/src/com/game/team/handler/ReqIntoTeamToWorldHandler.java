package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.team.message.ReqIntoTeamToWorldMessage;
import com.game.command.Handler;

public class ReqIntoTeamToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqIntoTeamToWorldHandler.class);

	public void action(){
		try{
			ReqIntoTeamToWorldMessage msg = (ReqIntoTeamToWorldMessage)this.getMessage();
			ManagerPool.teamManager.stReqIntoTeamToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}