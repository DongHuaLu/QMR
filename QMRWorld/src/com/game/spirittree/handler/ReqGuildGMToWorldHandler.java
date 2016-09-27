package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ReqGuildGMToWorldMessage;
import com.game.command.Handler;

public class ReqGuildGMToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildGMToWorldHandler.class);

	public void action(){
		try{
			ReqGuildGMToWorldMessage msg = (ReqGuildGMToWorldMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqGuildGMToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}