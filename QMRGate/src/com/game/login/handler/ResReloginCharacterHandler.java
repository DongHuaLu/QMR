package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.login.message.ResReloginCharacterMessage;
import com.game.manager.ManagerPool;
import com.game.command.Handler;

public class ResReloginCharacterHandler extends Handler{

	Logger log = Logger.getLogger(ResReloginCharacterHandler.class);

	public void action(){
		try{
			ResReloginCharacterMessage msg = (ResReloginCharacterMessage)this.getMessage();
			//重新登陆
			ManagerPool.playerManager.reselectCharacter(msg.getPlayerId(), msg.getCreateServerId(), msg.getUserId(), msg.getIsAdult());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}