package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.login.message.ResRemoveCharacterToGateMessage;
import com.game.manager.ManagerPool;
import com.game.command.Handler;

public class ResRemoveCharacterToGateHandler extends Handler{

	Logger log = Logger.getLogger(ResRemoveCharacterToGateHandler.class);

	public void action(){
		try{
			ResRemoveCharacterToGateMessage msg = (ResRemoveCharacterToGateMessage)this.getMessage();
			//移除玩家
			ManagerPool.playerManager.removePlayer(msg.getPlayerId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}