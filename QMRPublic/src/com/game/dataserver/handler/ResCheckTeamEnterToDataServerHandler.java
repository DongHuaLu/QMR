package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.message.ResCheckTeamEnterToDataServerMessage;
import com.game.enter.manager.EnterManager;
import com.game.command.Handler;

public class ResCheckTeamEnterToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ResCheckTeamEnterToDataServerHandler.class);

	public void action(){
		try{
			ResCheckTeamEnterToDataServerMessage msg = (ResCheckTeamEnterToDataServerMessage)this.getMessage();
			//队伍检查返回
			EnterManager.getInstance().resCheckTeamEnterToDataServer(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}