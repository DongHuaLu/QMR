package com.game.transactions.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.transactions.message.ReqTransactionsAcceptMessage;
import com.game.command.Handler;

public class ReqTransactionsAcceptHandler extends Handler{

	Logger log = Logger.getLogger(ReqTransactionsAcceptHandler.class);

	public void action(){
		try{
			ReqTransactionsAcceptMessage msg = (ReqTransactionsAcceptMessage)this.getMessage();
			ManagerPool.transactionsManager.stTransactionsAccept((Player)this.getParameter(),msg.getTransid());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}