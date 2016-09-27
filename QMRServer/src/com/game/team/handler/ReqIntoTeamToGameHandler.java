package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.team.message.ReqIntoTeamToGameMessage;
import com.game.command.Handler;

public class ReqIntoTeamToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqIntoTeamToGameHandler.class);

	public void action(){
		try{
			ReqIntoTeamToGameMessage msg = (ReqIntoTeamToGameMessage)this.getMessage();
			ManagerPool.teamManager.stReqIntoTeamToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}