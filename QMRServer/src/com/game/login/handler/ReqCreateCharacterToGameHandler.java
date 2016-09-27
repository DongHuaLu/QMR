package com.game.login.handler;

import org.apache.log4j.Logger;
import com.game.command.Handler;
import com.game.login.manager.LoginManager;
import com.game.login.message.ReqCreateCharacterToGameMessage;


public class ReqCreateCharacterToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqCreateCharacterToGameHandler.class);
	public void action(){
		try{
			ReqCreateCharacterToGameMessage msg = (ReqCreateCharacterToGameMessage)this.getMessage();
			LoginManager.getInstance().createCharacter(msg);
			
		}catch(Exception e){
			log.error(e, e);
		}
	}
}