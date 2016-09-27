package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.team.message.ReqToleaveGameMessage;
import com.game.command.Handler;

public class ReqToleaveGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqToleaveGameHandler.class);

	public void action(){
		try{
			ReqToleaveGameMessage msg = (ReqToleaveGameMessage)this.getMessage();
			ManagerPool.teamManager.stLeavetheteam((Player)this.getParameter(),msg.getPlayerid(),msg.getType());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}