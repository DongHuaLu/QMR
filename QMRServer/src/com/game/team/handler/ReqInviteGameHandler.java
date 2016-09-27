package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.team.message.ReqInviteGameMessage;
import com.game.command.Handler;

public class ReqInviteGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqInviteGameHandler.class);

	public void action(){
		try{
			ReqInviteGameMessage msg = (ReqInviteGameMessage)this.getMessage();
		
			ManagerPool.teamManager.stReqInviteGame((Player)this.getParameter(),msg.getTeamid(),msg.getPlayerid());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}