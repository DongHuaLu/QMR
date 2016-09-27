package com.game.transactions.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.transactions.message.ReqTransactionsChangeGoldMessage;
import com.game.command.Handler;

public class ReqTransactionsChangeGoldHandler extends Handler{

	Logger log = Logger.getLogger(ReqTransactionsChangeGoldHandler.class);

	public void action(){
		try{
			ReqTransactionsChangeGoldMessage msg = (ReqTransactionsChangeGoldMessage)this.getMessage();
			ManagerPool.transactionsManager.stTransactionsChangeGold((Player)this.getParameter(), msg.getGold());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}