package com.game.version.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.version.message.ReqVersionUpdateToWorldMessage;

public class ReqVersionUpdateToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqVersionUpdateToWorldHandler.class);

	public void action(){
		try{
			ReqVersionUpdateToWorldMessage msg = (ReqVersionUpdateToWorldMessage)this.getMessage();
			ManagerPool.versionManager.stReqVersionUpdateToWorldMessage(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}