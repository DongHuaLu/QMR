package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.team.message.ReqMapSearchMemberNameWorldMessage;
import com.game.command.Handler;

public class ReqMapSearchMemberNameWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqMapSearchMemberNameWorldHandler.class);

	public void action(){
		try{
			ReqMapSearchMemberNameWorldMessage msg = (ReqMapSearchMemberNameWorldMessage)this.getMessage();
			ManagerPool.teamManager.stReqMapSearchMemberNameWorldMessage( msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}