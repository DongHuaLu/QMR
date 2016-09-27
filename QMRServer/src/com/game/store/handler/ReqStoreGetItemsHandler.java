package com.game.store.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.store.message.ReqStoreGetItemsMessage;

public class ReqStoreGetItemsHandler extends Handler{

	Logger log = Logger.getLogger(ReqStoreGetItemsHandler.class);

	public void action(){
		try{
			ReqStoreGetItemsMessage msg=(ReqStoreGetItemsMessage) getMessage();
			ManagerPool.storeManager.sendAllItem((Player)this.getParameter(),msg.getNpcid());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}