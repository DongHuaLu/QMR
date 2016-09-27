package com.game.backend.handler;

import org.apache.log4j.Logger;

import com.game.backend.message.ResPlayerInfoToWorldMessage;
import com.game.command.Handler;

public class ResPlayerInfoToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ResPlayerInfoToWorldHandler.class);

	public void action(){
		try{
			ResPlayerInfoToWorldMessage msg = (ResPlayerInfoToWorldMessage)this.getMessage();
			
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}