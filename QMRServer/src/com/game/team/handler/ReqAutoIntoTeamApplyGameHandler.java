package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.team.message.ReqAutoIntoTeamApplyGameMessage;
import com.game.command.Handler;

public class ReqAutoIntoTeamApplyGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqAutoIntoTeamApplyGameHandler.class);

	public void action(){
		try{
			ReqAutoIntoTeamApplyGameMessage msg = (ReqAutoIntoTeamApplyGameMessage)this.getMessage();

			ManagerPool.teamManager.AutoIntoTeamApply((Player)this.getParameter(), msg.getAutointoteamapply());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}