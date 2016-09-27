package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.message.ReqSyncPlayerRealMMessage;
import com.game.command.Handler;

public class ReqSyncPlayerRealMHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerRealMHandler.class);

	public void action(){
		try{
			ReqSyncPlayerRealMMessage msg = (ReqSyncPlayerRealMMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}