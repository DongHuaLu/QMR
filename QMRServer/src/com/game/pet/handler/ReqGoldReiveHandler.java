package com.game.pet.handler;

import org.apache.log4j.Logger;

import com.game.pet.manager.PetOptManager;
import com.game.pet.message.ReqGoldReiveMessage;
import com.game.command.Handler;

public class ReqGoldReiveHandler extends Handler{

	Logger log = Logger.getLogger(ReqGoldReiveHandler.class);

	public void action(){
		try{
			ReqGoldReiveMessage msg = (ReqGoldReiveMessage)this.getMessage();
			//TODO 宠物花钱复活添加消息处理
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}