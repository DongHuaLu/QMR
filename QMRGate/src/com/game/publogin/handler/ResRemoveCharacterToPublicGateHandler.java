package com.game.publogin.handler;

import org.apache.log4j.Logger;

import com.game.publogin.message.ResRemoveCharacterToPublicGateMessage;
import com.game.command.Handler;

public class ResRemoveCharacterToPublicGateHandler extends Handler{

	Logger log = Logger.getLogger(ResRemoveCharacterToPublicGateHandler.class);

	public void action(){
		try{
			ResRemoveCharacterToPublicGateMessage msg = (ResRemoveCharacterToPublicGateMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}