package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.task.message.ResGiveUpTaskMessage;
import com.game.command.Handler;

public class ResGiveUpTaskHandler extends Handler{

	Logger log = Logger.getLogger(ResGiveUpTaskHandler.class);

	public void action(){
		try{
			ResGiveUpTaskMessage msg = (ResGiveUpTaskMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}