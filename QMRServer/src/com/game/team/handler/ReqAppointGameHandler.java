package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.team.message.ReqAppointGameMessage;
import com.game.command.Handler;

public class ReqAppointGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqAppointGameHandler.class);

	public void action(){
		try{
			ReqAppointGameMessage msg = (ReqAppointGameMessage)this.getMessage();

			ManagerPool.teamManager.stReqAppoint((Player)this.getParameter(),msg.getTeamid(),msg.getPlayerid());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}