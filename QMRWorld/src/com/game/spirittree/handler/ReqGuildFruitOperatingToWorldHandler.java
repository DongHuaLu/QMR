package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ReqGuildFruitOperatingToWorldMessage;
import com.game.command.Handler;

public class ReqGuildFruitOperatingToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildFruitOperatingToWorldHandler.class);

	public void action(){
		try{
			ReqGuildFruitOperatingToWorldMessage msg = (ReqGuildFruitOperatingToWorldMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqGuildFruitOperatingToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}