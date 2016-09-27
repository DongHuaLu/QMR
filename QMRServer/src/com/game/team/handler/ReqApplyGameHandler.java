package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.team.message.ReqApplyGameMessage;
import com.game.command.Handler;

public class ReqApplyGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqApplyGameHandler.class);

	public void action(){
		try{
			ReqApplyGameMessage msg = (ReqApplyGameMessage)this.getMessage();
			ManagerPool.teamManager.stReqApplyGame((Player)this.getParameter(),msg.getTeamid());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}