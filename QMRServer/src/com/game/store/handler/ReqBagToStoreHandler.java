package com.game.store.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.store.message.ReqBagToStoreMessage;

public class ReqBagToStoreHandler extends Handler{

	Logger log = Logger.getLogger(ReqBagToStoreHandler.class);

	public void action(){
		try{
			ReqBagToStoreMessage msg = (ReqBagToStoreMessage)this.getMessage();
			ManagerPool.storeManager.bagToStoreMsg((Player)this.getParameter(),msg.getBagCellId(),msg.getNpcid());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}