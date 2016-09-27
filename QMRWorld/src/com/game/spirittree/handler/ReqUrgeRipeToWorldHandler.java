package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ReqUrgeRipeToWorldMessage;
import com.game.command.Handler;

public class ReqUrgeRipeToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqUrgeRipeToWorldHandler.class);

	public void action(){
		try{
			ReqUrgeRipeToWorldMessage msg = (ReqUrgeRipeToWorldMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqUrgeRipeToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}