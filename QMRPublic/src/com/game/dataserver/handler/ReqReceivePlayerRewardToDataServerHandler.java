package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ReqReceivePlayerRewardToDataServerMessage;
import com.game.command.Handler;

public class ReqReceivePlayerRewardToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqReceivePlayerRewardToDataServerHandler.class);

	public void action(){
		try{
			ReqReceivePlayerRewardToDataServerMessage msg = (ReqReceivePlayerRewardToDataServerMessage)this.getMessage();
			//领取玩家奖励信息
			DataServerManager.getInstance().reqReceivePlayerRewardToDataServer(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}