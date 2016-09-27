package com.game.transactions.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.transactions.message.ReqTransactionsLaunchMessage;
import com.game.command.Handler;

public class ReqTransactionsLaunchHandler extends Handler{

	Logger log = Logger.getLogger(ReqTransactionsLaunchHandler.class);

	public void action(){
		try{
			ReqTransactionsLaunchMessage msg = (ReqTransactionsLaunchMessage)this.getMessage();
			ManagerPool.transactionsManager.stTransactionsLaunch((Player)this.getParameter(),msg.getPlayerid());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}