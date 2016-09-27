package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.message.ReqSyncPlayerEquipMessage;
import com.game.command.Handler;

public class ReqSyncPlayerEquipHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerEquipHandler.class);

	public void action(){
		try{
			ReqSyncPlayerEquipMessage msg = (ReqSyncPlayerEquipMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}