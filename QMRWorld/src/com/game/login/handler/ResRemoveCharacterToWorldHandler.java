package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.login.message.ResRemoveCharacterToWorldMessage;
import com.game.manager.ManagerPool;

public class ResRemoveCharacterToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ResRemoveCharacterToWorldHandler.class);

	public void action(){
		try{
			ResRemoveCharacterToWorldMessage msg = (ResRemoveCharacterToWorldMessage)this.getMessage();
			//移除
			ManagerPool.playerManager.removePlayer(msg.getPlayerId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}