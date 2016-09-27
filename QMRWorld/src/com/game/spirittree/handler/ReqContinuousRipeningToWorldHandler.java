package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ReqContinuousRipeningToWorldMessage;
import com.game.command.Handler;

public class ReqContinuousRipeningToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqContinuousRipeningToWorldHandler.class);

	public void action(){
		try{
			ReqContinuousRipeningToWorldMessage msg = (ReqContinuousRipeningToWorldMessage)this.getMessage();
			ManagerPool.spiritTreeManager.continuousripening(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}