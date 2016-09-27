package com.game.transactions.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.transactions.message.ReqTransactionsIntoItemMessage;
import com.game.command.Handler;

public class ReqTransactionsIntoItemHandler extends Handler{

	Logger log = Logger.getLogger(ReqTransactionsIntoItemHandler.class);

	public void action(){
		try{
			ReqTransactionsIntoItemMessage msg = (ReqTransactionsIntoItemMessage)this.getMessage();
			ManagerPool.transactionsManager.stTransactionsIntoItem((Player)this.getParameter(), msg.getItemposition(), msg.getItemid());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}