package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.team.message.ReqAutoTeaminvitedWorldMessage;

public class ReqAutoTeaminvitedWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqAutoTeaminvitedWorldHandler.class);

	public void action(){
		try{
			ReqAutoTeaminvitedWorldMessage msg = (ReqAutoTeaminvitedWorldMessage)this.getMessage();

			ManagerPool.teamManager.stAutoTeaminvited(msg.getPlayerid(), msg.getAutoTeaminvited());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}