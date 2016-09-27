package com.game.backend.handler;

import org.apache.log4j.Logger;

import com.game.backend.manager.BackendManager;
import com.game.backend.message.ResPlayerMoneyGoldToWorldMessage;
import com.game.command.Handler;

public class ResPlayerMoneyGoldToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ResPlayerMoneyGoldToWorldHandler.class);

	public void action(){
		try{
			ResPlayerMoneyGoldToWorldMessage msg = (ResPlayerMoneyGoldToWorldMessage)this.getMessage();

		}catch(ClassCastException e){
			log.error(e);
		}
	}
}