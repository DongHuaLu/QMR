package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.stalls.message.ReqStallsPlayerIdLookToWorldMessage;
import com.game.command.Handler;

public class ReqStallsPlayerIdLookToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsPlayerIdLookToWorldHandler.class);

	public void action(){
		try{
			ReqStallsPlayerIdLookToWorldMessage msg = (ReqStallsPlayerIdLookToWorldMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsPlayerIdLookToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}