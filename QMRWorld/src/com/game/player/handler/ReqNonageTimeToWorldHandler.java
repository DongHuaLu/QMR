package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqNonageTimeToWorldMessage;
import com.game.command.Handler;

public class ReqNonageTimeToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqNonageTimeToWorldHandler.class);

	public void action(){
		try{
			ReqNonageTimeToWorldMessage msg = (ReqNonageTimeToWorldMessage)this.getMessage();
			//请求防沉迷时间
			ManagerPool.playerManager.getUserNonageTime(msg.getUserId(), msg.getPlayerId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}