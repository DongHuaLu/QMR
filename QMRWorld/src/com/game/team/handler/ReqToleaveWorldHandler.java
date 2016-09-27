package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.team.message.ReqToleaveWorldMessage;

public class ReqToleaveWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqToleaveWorldHandler.class);

	public void action(){
		try{
			ReqToleaveWorldMessage msg = (ReqToleaveWorldMessage)this.getMessage();
			ManagerPool.teamManager.stLeavetheteam(msg.getPlayerid(),msg.getType());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}