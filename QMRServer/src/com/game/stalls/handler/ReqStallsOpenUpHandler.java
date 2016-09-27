package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.stalls.message.ReqStallsOpenUpMessage;
import com.game.command.Handler;

public class ReqStallsOpenUpHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsOpenUpHandler.class);

	public void action(){
		try{
			ReqStallsOpenUpMessage msg = (ReqStallsOpenUpMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsOpenUpMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}