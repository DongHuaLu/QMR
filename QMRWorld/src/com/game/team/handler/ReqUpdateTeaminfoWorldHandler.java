package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.team.message.ReqUpdateTeaminfoWorldMessage;

public class ReqUpdateTeaminfoWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqUpdateTeaminfoWorldHandler.class);

	public void action(){
		try{
			ReqUpdateTeaminfoWorldMessage msg = (ReqUpdateTeaminfoWorldMessage)this.getMessage();
			ManagerPool.teamManager.teamchangetoplayer(msg.getTeamid(), msg.getPlayerid(), msg.getType());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}