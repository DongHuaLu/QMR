package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.team.message.ReqUpdateTeaminfoGameMessage;
import com.game.command.Handler;

public class ReqUpdateTeaminfoGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqUpdateTeaminfoGameHandler.class);

	public void action(){
		try{
			ReqUpdateTeaminfoGameMessage msg = (ReqUpdateTeaminfoGameMessage)this.getMessage();
			ManagerPool.teamManager.clientreqteaminfo((Player)this.getParameter(), msg.getTeamid());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}