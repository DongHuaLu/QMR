package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.team.message.ReqGenericSearchToWorldMessage;
import com.game.command.Handler;

public class ReqGenericSearchToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqGenericSearchToWorldHandler.class);

	public void action(){
		try{
			ReqGenericSearchToWorldMessage msg = (ReqGenericSearchToWorldMessage)this.getMessage();
			ManagerPool.teamManager.stReqGenericSearchToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}