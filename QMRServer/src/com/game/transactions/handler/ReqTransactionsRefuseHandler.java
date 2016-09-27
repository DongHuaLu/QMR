package com.game.transactions.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.transactions.message.ReqTransactionsRefuseMessage;
import com.game.command.Handler;

public class ReqTransactionsRefuseHandler extends Handler{

	Logger log = Logger.getLogger(ReqTransactionsRefuseHandler.class);

	public void action(){
		try{
			ReqTransactionsRefuseMessage msg = (ReqTransactionsRefuseMessage)this.getMessage();
			ManagerPool.transactionsManager.stTransactionsRefuse((Player)this.getParameter(),msg.getTransid());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}