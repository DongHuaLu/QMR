package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.team.message.ResTeamchangeToGameMessage;
import com.game.command.Handler;

public class ResTeamchangeToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResTeamchangeToGameHandler.class);

	public void action(){
		try{
			ResTeamchangeToGameMessage msg = (ResTeamchangeToGameMessage)this.getMessage();
			ManagerPool.teamManager.stResTeamchangeToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}