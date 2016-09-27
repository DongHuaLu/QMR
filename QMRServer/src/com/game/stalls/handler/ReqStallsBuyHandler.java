package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.stalls.message.ReqStallsBuyMessage;
import com.game.command.Handler;

public class ReqStallsBuyHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsBuyHandler.class);

	public void action(){
		try{
			ReqStallsBuyMessage msg = (ReqStallsBuyMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsBuyMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}