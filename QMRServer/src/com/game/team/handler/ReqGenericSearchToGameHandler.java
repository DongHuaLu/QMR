package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.team.message.ReqGenericSearchToGameMessage;
import com.game.command.Handler;

public class ReqGenericSearchToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqGenericSearchToGameHandler.class);

	public void action(){
		try{
			ReqGenericSearchToGameMessage msg = (ReqGenericSearchToGameMessage)this.getMessage();
			ManagerPool.teamManager.stReqGenericSearchToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}