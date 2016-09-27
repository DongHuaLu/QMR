package com.game.store.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.store.message.ReqStoreToBagMessage;

public class ReqStoreToBagHandler extends Handler{

	Logger log = Logger.getLogger(ReqStoreToBagHandler.class);

	public void action(){
		try{
			ReqStoreToBagMessage msg = (ReqStoreToBagMessage)this.getMessage();
			ManagerPool.storeManager.storeToBagMsg((Player)this.getParameter(), msg.getStoreCellId(),msg.getNpcid());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}