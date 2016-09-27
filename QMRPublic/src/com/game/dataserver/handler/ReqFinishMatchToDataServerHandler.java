package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.message.ReqFinishMatchToDataServerMessage;
import com.game.enter.manager.EnterManager;
import com.game.command.Handler;

public class ReqFinishMatchToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqFinishMatchToDataServerHandler.class);

	public void action(){
		try{
			ReqFinishMatchToDataServerMessage msg = (ReqFinishMatchToDataServerMessage)this.getMessage();
			//请求完成比赛
			EnterManager.getInstance().reqFinishMatchToDataServer(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}