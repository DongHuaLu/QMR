package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.message.ReqSyncPlayerRankMessage;
import com.game.command.Handler;

public class ReqSyncPlayerRankHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerRankHandler.class);

	public void action(){
		try{
			ReqSyncPlayerRankMessage msg = (ReqSyncPlayerRankMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}