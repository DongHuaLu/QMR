package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.team.message.ReqInviteWorldMessage;

public class ReqInviteWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInviteWorldHandler.class);

	public void action(){
		try{
			ReqInviteWorldMessage msg = (ReqInviteWorldMessage)this.getMessage();
			ManagerPool.teamManager.stReqInviteWorld(msg.getTeamid(),msg.getPlayerid(),msg.getCaptainid());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}