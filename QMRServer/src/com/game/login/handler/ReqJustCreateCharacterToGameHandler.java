package com.game.login.handler;

import org.apache.log4j.Logger;
import com.game.login.manager.LoginManager;
import com.game.login.message.ReqJustCreateCharacterToGameMessage;
import com.game.command.Handler;

public class ReqJustCreateCharacterToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqJustCreateCharacterToGameHandler.class);

	public void action(){
		try{
			ReqJustCreateCharacterToGameMessage msg = (ReqJustCreateCharacterToGameMessage)this.getMessage();
			LoginManager.getInstance().createCharacter(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}