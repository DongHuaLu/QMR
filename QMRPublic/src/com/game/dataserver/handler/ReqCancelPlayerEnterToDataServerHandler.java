package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.message.ReqCancelPlayerEnterToDataServerMessage;
import com.game.enter.manager.EnterManager;
import com.game.command.Handler;

public class ReqCancelPlayerEnterToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqCancelPlayerEnterToDataServerHandler.class);

	public void action(){
		try{
			ReqCancelPlayerEnterToDataServerMessage msg = (ReqCancelPlayerEnterToDataServerMessage)this.getMessage();
			//取消个人报名
			EnterManager.getInstance().reqCancelPlayerEnterToDataServerMessage(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}