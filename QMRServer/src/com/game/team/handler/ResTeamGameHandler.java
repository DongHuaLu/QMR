package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.team.message.ResTeamGameMessage;
import com.game.command.Handler;

public class ResTeamGameHandler extends Handler{

	Logger log = Logger.getLogger(ResTeamGameHandler.class);

	public void action(){
		try{
			ResTeamGameMessage msg = (ResTeamGameMessage)this.getMessage();
			ManagerPool.teamManager.getupdateteaminfo(msg.getTeamid(), msg.getMemberinfo(),msg.getType());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}