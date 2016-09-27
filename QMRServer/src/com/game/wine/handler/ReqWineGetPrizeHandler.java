package com.game.wine.handler;

import org.apache.log4j.Logger;

import com.game.wine.message.ReqWineGetPrizeMessage;
import com.game.command.Handler;

public class ReqWineGetPrizeHandler extends Handler{

	Logger log = Logger.getLogger(ReqWineGetPrizeHandler.class);

	public void action(){
		try{
			ReqWineGetPrizeMessage msg = (ReqWineGetPrizeMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}