package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.stalls.message.ReqStallsOpenUpToWorldMessage;
import com.game.command.Handler;

public class ReqStallsOpenUpToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsOpenUpToWorldHandler.class);

	public void action(){
		try{
			ReqStallsOpenUpToWorldMessage msg = (ReqStallsOpenUpToWorldMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsOpenUpToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}