package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ReqOpenGuildFruitToWorldMessage;
import com.game.command.Handler;

public class ReqOpenGuildFruitToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqOpenGuildFruitToWorldHandler.class);

	public void action(){
		try{
			ReqOpenGuildFruitToWorldMessage msg = (ReqOpenGuildFruitToWorldMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqOpenGuildFruitToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}