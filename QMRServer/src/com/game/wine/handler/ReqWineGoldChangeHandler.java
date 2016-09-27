package com.game.wine.handler;

import org.apache.log4j.Logger;

import com.game.wine.message.ReqWineGoldChangeMessage;
import com.game.command.Handler;

public class ReqWineGoldChangeHandler extends Handler{

	Logger log = Logger.getLogger(ReqWineGoldChangeHandler.class);

	public void action(){
		try{
			ReqWineGoldChangeMessage msg = (ReqWineGoldChangeMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}