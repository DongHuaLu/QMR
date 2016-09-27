package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.team.message.ReqInviteGameSelectMessage;
import com.game.command.Handler;

public class ReqInviteGameSelectHandler extends Handler{

	Logger log = Logger.getLogger(ReqInviteGameSelectHandler.class);

	public void action(){
		try{
			ReqInviteGameSelectMessage msg = (ReqInviteGameSelectMessage)this.getMessage();
			
			ManagerPool.teamManager.stReqInviteGameSelect((Player)this.getParameter(),msg.getTeamid(),msg.getCaptainid(),msg.getSelect());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}