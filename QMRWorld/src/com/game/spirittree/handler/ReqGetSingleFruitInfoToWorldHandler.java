package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ReqGetSingleFruitInfoToWorldMessage;
import com.game.command.Handler;

public class ReqGetSingleFruitInfoToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetSingleFruitInfoToWorldHandler.class);

	public void action(){
		try{
			ReqGetSingleFruitInfoToWorldMessage msg = (ReqGetSingleFruitInfoToWorldMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqGetSingleFruitInfoToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}