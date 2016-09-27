package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.marriage.message.ReqReplaceRingToGameMessage;
import com.game.command.Handler;

public class ReqReplaceRingToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqReplaceRingToGameHandler.class);

	public void action(){
		try{
			ReqReplaceRingToGameMessage msg = (ReqReplaceRingToGameMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}