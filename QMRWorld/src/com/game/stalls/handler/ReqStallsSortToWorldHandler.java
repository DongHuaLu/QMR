package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.stalls.message.ReqStallsSortToWorldMessage;
import com.game.command.Handler;

public class ReqStallsSortToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsSortToWorldHandler.class);

	public void action(){
		try{
			ReqStallsSortToWorldMessage msg = (ReqStallsSortToWorldMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsSortToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}