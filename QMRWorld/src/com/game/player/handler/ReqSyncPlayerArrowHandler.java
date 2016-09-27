package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.message.ReqSyncPlayerArrowMessage;
import com.game.command.Handler;

public class ReqSyncPlayerArrowHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerArrowHandler.class);

	public void action(){
		try{
			ReqSyncPlayerArrowMessage msg = (ReqSyncPlayerArrowMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}