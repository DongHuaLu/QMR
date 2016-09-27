package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.stalls.message.ReqStallsProductWasAddedMessage;
import com.game.command.Handler;

public class ReqStallsProductWasAddedHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsProductWasAddedHandler.class);

	public void action(){
		try{
			ReqStallsProductWasAddedMessage msg = (ReqStallsProductWasAddedMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsProductWasAddedMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}