package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.stalls.message.ReqStallsSearchToWorldMessage;
import com.game.command.Handler;

public class ReqStallsSearchToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsSearchToWorldHandler.class);

	public void action(){
		try{
			ReqStallsSearchToWorldMessage msg = (ReqStallsSearchToWorldMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsSearchToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}