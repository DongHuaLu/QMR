package com.game.toplist.handler;

import org.apache.log4j.Logger;

import com.game.toplist.message.ReqWorShipToWorldMessage;
import com.game.command.Handler;
import com.game.toplist.manager.TopListManager;

public class ReqWorShipToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqWorShipToWorldHandler.class);

	public void action(){
		try{
			ReqWorShipToWorldMessage msg = (ReqWorShipToWorldMessage)this.getMessage();
			TopListManager.getInstance().reqWorShipToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}