package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.stalls.message.ReqStallsAdjustPricesMessage;
import com.game.command.Handler;

public class ReqStallsAdjustPricesHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsAdjustPricesHandler.class);

	public void action(){
		try{
			ReqStallsAdjustPricesMessage msg = (ReqStallsAdjustPricesMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsAdjustPricesMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}