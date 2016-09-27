package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.team.message.ReqMapSearchTeamInfoGameMessage;
import com.game.command.Handler;

public class ReqMapSearchTeamInfoGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqMapSearchTeamInfoGameHandler.class);

	public void action(){
		try{
			ReqMapSearchTeamInfoGameMessage msg = (ReqMapSearchTeamInfoGameMessage)this.getMessage();
			ManagerPool.teamManager.stMapSearchTeamInfo((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}