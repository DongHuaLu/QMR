package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.message.ReqPlayerEnterToDataServerMessage;
import com.game.enter.manager.EnterManager;
import com.game.command.Handler;

public class ReqPlayerEnterToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlayerEnterToDataServerHandler.class);

	public void action(){
		try{
			ReqPlayerEnterToDataServerMessage msg = (ReqPlayerEnterToDataServerMessage)this.getMessage();
			//个人报名
			EnterManager.getInstance().reqPlayerEnterToDataServer(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}