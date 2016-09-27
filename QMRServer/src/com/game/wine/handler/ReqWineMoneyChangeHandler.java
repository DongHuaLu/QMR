package com.game.wine.handler;

import org.apache.log4j.Logger;

import com.game.wine.message.ReqWineMoneyChangeMessage;
import com.game.command.Handler;

public class ReqWineMoneyChangeHandler extends Handler{

	Logger log = Logger.getLogger(ReqWineMoneyChangeHandler.class);

	public void action(){
		try{
			ReqWineMoneyChangeMessage msg = (ReqWineMoneyChangeMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}