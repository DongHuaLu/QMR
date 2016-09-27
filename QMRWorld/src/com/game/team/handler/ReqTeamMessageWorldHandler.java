package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.team.message.ReqTeamMessageWorldMessage;
import com.game.command.Handler;

public class ReqTeamMessageWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqTeamMessageWorldHandler.class);

	public void action(){
		try{
			ReqTeamMessageWorldMessage msg = (ReqTeamMessageWorldMessage)this.getMessage();
			ManagerPool.teamManager.stReqTeamMessageWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}