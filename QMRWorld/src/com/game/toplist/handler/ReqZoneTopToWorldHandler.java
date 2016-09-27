package com.game.toplist.handler;

import org.apache.log4j.Logger;

import com.game.toplist.message.ReqZoneTopToWorldMessage;
import com.game.command.Handler;
import com.game.toplist.manager.TopListManager;

public class ReqZoneTopToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneTopToWorldHandler.class);

	public void action(){
		try{
			ReqZoneTopToWorldMessage msg = (ReqZoneTopToWorldMessage)this.getMessage();
			TopListManager.getInstance().reqZoneTopToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}