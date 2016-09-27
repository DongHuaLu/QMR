package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.login.message.ResCreateCharacterFailedMessage;
import com.game.manager.ManagerPool;
import com.game.command.Handler;

public class ResCreateCharacterFailedHandler extends Handler{

	Logger log = Logger.getLogger(ResCreateCharacterFailedHandler.class);

	public void action(){
		try{
			ResCreateCharacterFailedMessage msg = (ResCreateCharacterFailedMessage)this.getMessage();
			//创建角色失败
			ManagerPool.playerManager.createFailed(msg.getCreateServerId(), msg.getUserId(), msg.getReason());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}