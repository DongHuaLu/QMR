package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.login.message.ReqJustCreateCharacterMessage;
import com.game.manager.ManagerPool;
import com.game.command.Handler;

public class ReqJustCreateCharacterHandler extends Handler{

	Logger log = Logger.getLogger(ReqJustCreateCharacterHandler.class);

	public void action(){
		try{
			ReqJustCreateCharacterMessage msg = (ReqJustCreateCharacterMessage)this.getMessage();
			ManagerPool.playerManager.createCharacterNotEnterMap(msg.getSession(), msg.getName(), msg.getIcon(), msg.getSex(), (byte)0, msg.getAuto());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}