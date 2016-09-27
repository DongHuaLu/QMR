package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ReqActivityCheckFruitToWorldMessage;
import com.game.command.Handler;

public class ReqActivityCheckFruitToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqActivityCheckFruitToWorldHandler.class);

	public void action(){
		try{
			ReqActivityCheckFruitToWorldMessage msg = (ReqActivityCheckFruitToWorldMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqActivityCheckFruitToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}