package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.message.ResChangePlayerNameToGameMessage;

public class ResChangePlayerNameToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResChangePlayerNameToGameHandler.class);

	public void action(){
		try{
			ResChangePlayerNameToGameMessage msg = (ResChangePlayerNameToGameMessage)this.getMessage();
			ManagerPool.playerManager.stResChangePlayerNameToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}