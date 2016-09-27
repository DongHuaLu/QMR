package com.game.server.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ReqRegisterGameForPublicHandler extends Handler{

	Logger log = Logger.getLogger(ReqRegisterGameForPublicHandler.class);

	public void action(){
		try{
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}