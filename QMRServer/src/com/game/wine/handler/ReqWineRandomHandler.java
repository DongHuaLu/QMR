package com.game.wine.handler;

import org.apache.log4j.Logger;

import com.game.wine.message.ReqWineRandomMessage;
import com.game.command.Handler;

public class ReqWineRandomHandler extends Handler{

	Logger log = Logger.getLogger(ReqWineRandomHandler.class);

	public void action(){
		try{
			ReqWineRandomMessage msg = (ReqWineRandomMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}