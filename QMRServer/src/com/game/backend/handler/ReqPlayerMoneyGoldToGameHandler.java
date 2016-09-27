package com.game.backend.handler;

import org.apache.log4j.Logger;

import com.game.backend.message.ReqPlayerMoneyGoldToGameMessage;
import com.game.command.Handler;

public class ReqPlayerMoneyGoldToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlayerMoneyGoldToGameHandler.class);

	public void action(){
		try{
			ReqPlayerMoneyGoldToGameMessage msg = (ReqPlayerMoneyGoldToGameMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}