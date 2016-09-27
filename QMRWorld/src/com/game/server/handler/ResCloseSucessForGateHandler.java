package com.game.server.handler;

import org.apache.log4j.Logger;

import com.game.server.message.ResCloseSucessForGateMessage;
import com.game.command.Handler;

public class ResCloseSucessForGateHandler extends Handler{

	Logger log = Logger.getLogger(ResCloseSucessForGateHandler.class);

	public void action(){
		try{
			ResCloseSucessForGateMessage msg = (ResCloseSucessForGateMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}