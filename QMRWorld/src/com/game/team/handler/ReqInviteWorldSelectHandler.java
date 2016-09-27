package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.team.message.ReqInviteWorldSelectMessage;

public class ReqInviteWorldSelectHandler extends Handler{

	Logger log = Logger.getLogger(ReqInviteWorldSelectHandler.class);

	public void action(){
		try{
			ReqInviteWorldSelectMessage msg = (ReqInviteWorldSelectMessage)this.getMessage();
			ManagerPool.teamManager.Invitedealwith(msg.getTeamid(),msg.getPlayerid(),msg.getCaptainid(),msg.getSelect());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}