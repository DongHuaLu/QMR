package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.message.ReqSyncPlayerGemMessage;
import com.game.command.Handler;

public class ReqSyncPlayerGemHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerGemHandler.class);

	public void action(){
		try{
			ReqSyncPlayerGemMessage msg = (ReqSyncPlayerGemMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}