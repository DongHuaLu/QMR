package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.stalls.message.ReqStallsProductWasAddedToWorldMessage;
import com.game.command.Handler;

public class ReqStallsProductWasAddedToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsProductWasAddedToWorldHandler.class);

	public void action(){
		try{
			ReqStallsProductWasAddedToWorldMessage msg = (ReqStallsProductWasAddedToWorldMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsProductWasAddedToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}