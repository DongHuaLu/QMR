package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.stalls.message.ReqStallsOffShelfToWorldMessage;
import com.game.command.Handler;

public class ReqStallsOffShelfToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsOffShelfToWorldHandler.class);

	public void action(){
		try{
			ReqStallsOffShelfToWorldMessage msg = (ReqStallsOffShelfToWorldMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsOffShelfToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}