package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ReqRipeningFruitToWorldMessage;
import com.game.command.Handler;

public class ReqRipeningFruitToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqRipeningFruitToWorldHandler.class);

	public void action(){
		try{
			ReqRipeningFruitToWorldMessage msg = (ReqRipeningFruitToWorldMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqRipeningFruitToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}