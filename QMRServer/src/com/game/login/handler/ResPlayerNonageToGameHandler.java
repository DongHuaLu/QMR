package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.login.message.ResPlayerNonageToGameMessage;
import com.game.manager.ManagerPool;
import com.game.command.Handler;

public class ResPlayerNonageToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResPlayerNonageToGameHandler.class);

	public void action(){
		try{
			ResPlayerNonageToGameMessage msg = (ResPlayerNonageToGameMessage)this.getMessage();
			//防沉迷状态
			ManagerPool.playerManager.setPlayerNonage(msg.getPlayerId(), msg.getNonage());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}