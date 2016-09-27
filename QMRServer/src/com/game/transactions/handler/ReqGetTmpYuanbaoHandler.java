package com.game.transactions.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqGetTmpYuanbaoHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetTmpYuanbaoHandler.class);

	public void action(){
		try{
			//ReqGetTmpYuanbaoMessage msg = (ReqGetTmpYuanbaoMessage)this.getMessage();
			ManagerPool.transactionsManager.stReqGetTmpYuanbaoMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}