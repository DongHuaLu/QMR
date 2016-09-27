package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.dataserver.message.ReqCancelTeamEnterToDataServerMessage;
import com.game.enter.manager.EnterManager;
import com.game.command.Handler;

public class ReqCancelTeamEnterToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqCancelTeamEnterToDataServerHandler.class);

	public void action(){
		try{
			ReqCancelTeamEnterToDataServerMessage msg = (ReqCancelTeamEnterToDataServerMessage)this.getMessage();
			//取消队伍报名
			EnterManager.getInstance().reqCancelTeamEnterToDataServer(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}