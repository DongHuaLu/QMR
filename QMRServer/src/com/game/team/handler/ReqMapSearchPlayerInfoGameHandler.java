package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.team.message.ReqMapSearchPlayerInfoGameMessage;
import com.game.command.Handler;

public class ReqMapSearchPlayerInfoGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqMapSearchPlayerInfoGameHandler.class);

	public void action(){
		try{
			ReqMapSearchPlayerInfoGameMessage msg = (ReqMapSearchPlayerInfoGameMessage)this.getMessage();
			ManagerPool.teamManager.stMapSearchPlayerInfo((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}