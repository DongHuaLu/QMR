package com.game.transactions.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqOpenTmpYuanbaoPanelHandler extends Handler{

	Logger log = Logger.getLogger(ReqOpenTmpYuanbaoPanelHandler.class);

	public void action(){
		try{
			//ReqOpenTmpYuanbaoPanelMessage msg = (ReqOpenTmpYuanbaoPanelMessage)this.getMessage();
			ManagerPool.transactionsManager.stReqOpenTmpYuanbaoPanelMessage((Player)this.getParameter() );
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}