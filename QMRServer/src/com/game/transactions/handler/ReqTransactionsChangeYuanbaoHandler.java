package com.game.transactions.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.transactions.message.ReqTransactionsChangeYuanbaoMessage;
import com.game.command.Handler;

public class ReqTransactionsChangeYuanbaoHandler extends Handler{

	Logger log = Logger.getLogger(ReqTransactionsChangeYuanbaoHandler.class);

	public void action(){
		try{
			ReqTransactionsChangeYuanbaoMessage msg = (ReqTransactionsChangeYuanbaoMessage)this.getMessage();
			ManagerPool.transactionsManager.stTransactionsChangeYuanbao((Player)this.getParameter(), msg.getYuanbao());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}