package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.stalls.message.ReqStallsAdjustPricesToWorldMessage;
import com.game.command.Handler;

public class ReqStallsAdjustPricesToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsAdjustPricesToWorldHandler.class);

	public void action(){
		try{
			ReqStallsAdjustPricesToWorldMessage msg = (ReqStallsAdjustPricesToWorldMessage)this.getMessage();
		
			ManagerPool.stallsManager.stReqStallsAdjustPricesToWorldMessage(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}