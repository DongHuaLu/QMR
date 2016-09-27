package com.game.transactions.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.transactions.message.ReqTransactionsSetStateMessage;
import com.game.command.Handler;

public class ReqTransactionsSetStateHandler extends Handler{

	Logger log = Logger.getLogger(ReqTransactionsSetStateHandler.class);

	public void action(){
		try{
			ReqTransactionsSetStateMessage msg = (ReqTransactionsSetStateMessage)this.getMessage();
			ManagerPool.transactionsManager.stTransactionsSetState((Player)this.getParameter(),msg.getState());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}