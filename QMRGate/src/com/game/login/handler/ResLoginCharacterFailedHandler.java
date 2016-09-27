package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.login.message.ResLoginCharacterFailedMessage;
import com.game.manager.ManagerPool;
import com.game.command.Handler;

public class ResLoginCharacterFailedHandler extends Handler{

	Logger log = Logger.getLogger(ResLoginCharacterFailedHandler.class);

	public void action(){
		try{
			ResLoginCharacterFailedMessage msg = (ResLoginCharacterFailedMessage)this.getMessage();
			//登陆失败
			ManagerPool.playerManager.loginFailed(msg.getCreateServerId(), msg.getUserId(), msg.getReason());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}