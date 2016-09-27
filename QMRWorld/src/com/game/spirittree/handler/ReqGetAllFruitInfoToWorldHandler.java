package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ReqGetAllFruitInfoToWorldMessage;
import com.game.command.Handler;

public class ReqGetAllFruitInfoToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetAllFruitInfoToWorldHandler.class);

	public void action(){
		try{
			ReqGetAllFruitInfoToWorldMessage msg = (ReqGetAllFruitInfoToWorldMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqGetAllFruitInfoToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}