package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ReqGuildFruitLogToWorldMessage;
import com.game.command.Handler;

public class ReqGuildFruitLogToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildFruitLogToWorldHandler.class);

	public void action(){
		try{
			ReqGuildFruitLogToWorldMessage msg = (ReqGuildFruitLogToWorldMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqGuildFruitLogToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}