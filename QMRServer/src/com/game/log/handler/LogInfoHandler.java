package com.game.log.handler;

import org.apache.log4j.Logger;

import com.game.log.message.LogInfoMessage;
import com.game.command.Handler;

public class LogInfoHandler extends Handler{

	Logger log = Logger.getLogger(LogInfoHandler.class);

	public void action(){
		try{
			LogInfoMessage msg = (LogInfoMessage)this.getMessage();
			log.info(msg.toString());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}