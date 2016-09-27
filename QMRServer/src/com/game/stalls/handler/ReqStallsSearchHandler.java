package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.stalls.message.ReqStallsSearchMessage;
import com.game.command.Handler;

public class ReqStallsSearchHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsSearchHandler.class);

	public void action(){
		try{
			ReqStallsSearchMessage msg = (ReqStallsSearchMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsSearchMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}