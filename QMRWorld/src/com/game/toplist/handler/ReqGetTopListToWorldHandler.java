package com.game.toplist.handler;

import org.apache.log4j.Logger;

import com.game.toplist.message.ReqGetTopListToWorldMessage;
import com.game.command.Handler;
import com.game.toplist.manager.TopListManager;

public class ReqGetTopListToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetTopListToWorldHandler.class);

	public void action(){
		try{
			ReqGetTopListToWorldMessage msg = (ReqGetTopListToWorldMessage)this.getMessage();
			TopListManager.getInstance().reqGetTopListToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}