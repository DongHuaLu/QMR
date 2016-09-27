package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.team.message.ReqAppointGameSelectMessage;
import com.game.command.Handler;

public class ReqAppointGameSelectHandler extends Handler{

	Logger log = Logger.getLogger(ReqAppointGameSelectHandler.class);

	public void action(){
		try{
			ReqAppointGameSelectMessage msg = (ReqAppointGameSelectMessage)this.getMessage();
	
			ManagerPool.teamManager.stReqAppointSelect((Player)this.getParameter(),msg.getTeamid(),msg.getSelect());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}