package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ReqPlayerRewardFromDataServerMessage;
import com.game.command.Handler;

public class ReqPlayerRewardFromDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlayerRewardFromDataServerHandler.class);

	public void action(){
		try{
			ReqPlayerRewardFromDataServerMessage msg = (ReqPlayerRewardFromDataServerMessage)this.getMessage();
			//获得玩家奖励信息
			DataServerManager.getInstance().reqPlayerRewardFromDataServer(msg.getSession(), msg.getWeb(), msg.getUserId(), msg.getPlayerId(),System.currentTimeMillis());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}