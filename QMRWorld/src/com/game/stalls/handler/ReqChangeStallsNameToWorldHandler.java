package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.stalls.message.ReqChangeStallsNameToWorldMessage;
import com.game.command.Handler;

public class ReqChangeStallsNameToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangeStallsNameToWorldHandler.class);

	public void action(){
		try{
			ReqChangeStallsNameToWorldMessage msg = (ReqChangeStallsNameToWorldMessage)this.getMessage();
			ManagerPool.stallsManager.stReqChangeStallsNameToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}