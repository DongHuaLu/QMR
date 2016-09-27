package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.stalls.message.ReqStallsPlayerIdLookMessage;
import com.game.command.Handler;

public class ReqStallsPlayerIdLookHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsPlayerIdLookHandler.class);

	public void action(){
		try{
			ReqStallsPlayerIdLookMessage msg = (ReqStallsPlayerIdLookMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsPlayerIdLookMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}