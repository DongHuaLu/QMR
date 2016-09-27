package com.game.transactions.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.transactions.message.ReqTransactionsRemoveItemMessage;
import com.game.command.Handler;

public class ReqTransactionsRemoveItemHandler extends Handler{

	Logger log = Logger.getLogger(ReqTransactionsRemoveItemHandler.class);

	public void action(){
		try{
			ReqTransactionsRemoveItemMessage msg = (ReqTransactionsRemoveItemMessage)this.getMessage();
			ManagerPool.transactionsManager.stTransactionsRemoveItem((Player)this.getParameter(), msg.getItemid());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}