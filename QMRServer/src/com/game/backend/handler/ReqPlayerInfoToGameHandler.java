package com.game.backend.handler;

import org.apache.log4j.Logger;

import com.game.backend.message.ReqPlayerInfoToGameMessage;
import com.game.command.Handler;

public class ReqPlayerInfoToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlayerInfoToGameHandler.class);

	public void action(){
		try{
			ReqPlayerInfoToGameMessage msg = (ReqPlayerInfoToGameMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}