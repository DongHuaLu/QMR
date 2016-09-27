package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.stalls.message.ReqStallsBuyToWorldMessage;
import com.game.command.Handler;

public class ReqStallsBuyToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsBuyToWorldHandler.class);

	public void action(){
		try{
			ReqStallsBuyToWorldMessage msg = (ReqStallsBuyToWorldMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsBuyToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}