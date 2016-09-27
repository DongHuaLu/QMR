package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.stalls.message.ReqStallsBuycheckToWorldMessage;
import com.game.command.Handler;

public class ReqStallsBuycheckToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsBuycheckToWorldHandler.class);

	public void action(){
		try{
			ReqStallsBuycheckToWorldMessage msg = (ReqStallsBuycheckToWorldMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsBuycheckToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}