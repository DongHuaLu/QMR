package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.team.message.ReqMapSearchTeamInfoWorldMessage;
import com.game.command.Handler;

public class ReqMapSearchTeamInfoWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqMapSearchTeamInfoWorldHandler.class);

	public void action(){
		try{
			ReqMapSearchTeamInfoWorldMessage msg = (ReqMapSearchTeamInfoWorldMessage)this.getMessage();
			ManagerPool.teamManager.stMapSearchTeamInfo(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}