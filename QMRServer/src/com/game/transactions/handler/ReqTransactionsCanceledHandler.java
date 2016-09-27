package com.game.transactions.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
//import com.game.transactions.message.ReqTransactionsCanceledMessage;
import com.game.command.Handler;

public class ReqTransactionsCanceledHandler extends Handler{

	Logger log = Logger.getLogger(ReqTransactionsCanceledHandler.class);

	public void action(){
		try{
			//ReqTransactionsCanceledMessage msg = (ReqTransactionsCanceledMessage)this.getMessage();
			ManagerPool.transactionsManager.stTransactionsCanceled((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}