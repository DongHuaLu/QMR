package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.team.message.ReqAppointWorldSelectMessage;

public class ReqAppointWorldSelectHandler extends Handler{

	Logger log = Logger.getLogger(ReqAppointWorldSelectHandler.class);

	public void action(){
		try{
			ReqAppointWorldSelectMessage msg = (ReqAppointWorldSelectMessage)this.getMessage();
			ManagerPool.teamManager.Appointdealwith(msg.getTeamid(),msg.getPlayerid(),msg.getSelect());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}