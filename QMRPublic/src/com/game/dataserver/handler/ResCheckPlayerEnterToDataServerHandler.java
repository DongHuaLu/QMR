package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.message.ResCheckPlayerEnterToDataServerMessage;
import com.game.enter.manager.EnterManager;
import com.game.command.Handler;

public class ResCheckPlayerEnterToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ResCheckPlayerEnterToDataServerHandler.class);

	public void action(){
		try{
			ResCheckPlayerEnterToDataServerMessage msg = (ResCheckPlayerEnterToDataServerMessage)this.getMessage();
			//个人检查返回
			EnterManager.getInstance().resCheckPlayerEnterToDataServer(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}