package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.team.message.ReqMapSearchPlayerInfoWorldMessage;
import com.game.command.Handler;

public class ReqMapSearchPlayerInfoWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqMapSearchPlayerInfoWorldHandler.class);

	public void action(){
		try{
			ReqMapSearchPlayerInfoWorldMessage msg = (ReqMapSearchPlayerInfoWorldMessage)this.getMessage();
			ManagerPool.teamManager.stMapSearchPlayerInfo(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}