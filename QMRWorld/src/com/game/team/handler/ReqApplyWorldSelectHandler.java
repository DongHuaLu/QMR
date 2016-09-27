package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.team.message.ReqApplyWorldSelectMessage;

public class ReqApplyWorldSelectHandler extends Handler{

	Logger log = Logger.getLogger(ReqApplyWorldSelectHandler.class);

	public void action(){
		try{
			ReqApplyWorldSelectMessage msg = (ReqApplyWorldSelectMessage)this.getMessage();
			ManagerPool.teamManager.applydealwith(msg.getTeamid(), msg.getPlayerid(), msg.getSelect());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}