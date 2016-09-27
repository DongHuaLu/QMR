package com.game.team.handler;

import org.apache.log4j.Logger;


import com.game.manager.ManagerPool;
import com.game.team.message.ResToleaveWorldMessage;
import com.game.command.Handler;

public class ResToleaveWorldHandler extends Handler{

	Logger log = Logger.getLogger(ResToleaveWorldHandler.class);

	public void action(){
		try{
			ResToleaveWorldMessage msg = (ResToleaveWorldMessage)this.getMessage();

			ManagerPool.teamManager.stResToleave(msg.getTeamid(),msg.getPlayerid());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}