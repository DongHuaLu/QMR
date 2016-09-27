package com.game.publogin.handler;

import org.apache.log4j.Logger;

import com.game.publogin.message.ResLoginCharacterFailedForPublicMessage;
import com.game.command.Handler;

public class ResLoginCharacterFailedForPublicHandler extends Handler{

	Logger log = Logger.getLogger(ResLoginCharacterFailedForPublicHandler.class);

	public void action(){
		try{
			ResLoginCharacterFailedForPublicMessage msg = (ResLoginCharacterFailedForPublicMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}