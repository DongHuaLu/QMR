package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.message.ReqTeamEnterToDataServerMessage;
import com.game.enter.manager.EnterManager;
import com.game.command.Handler;

public class ReqTeamEnterToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqTeamEnterToDataServerHandler.class);

	public void action(){
		try{
			ReqTeamEnterToDataServerMessage msg = (ReqTeamEnterToDataServerMessage)this.getMessage();
			//队伍报名申请
			EnterManager.getInstance().reqTeamEnterToDataServer(msg);			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}