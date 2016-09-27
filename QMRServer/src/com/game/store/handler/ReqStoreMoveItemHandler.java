package com.game.store.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.store.message.ReqStoreMoveItemMessage;

public class ReqStoreMoveItemHandler extends Handler{

	Logger log = Logger.getLogger(ReqStoreMoveItemHandler.class);

	public void action(){
		try{
			ReqStoreMoveItemMessage msg = (ReqStoreMoveItemMessage)this.getMessage();
			ManagerPool.storeManager.moveItemMsg((Player)this.getParameter(), msg.getItemId(), msg.getToGridId(),msg.getNpcid());
		}catch(ClassCastException e){
			e.printStackTrace();
			log.error(e,e);
		}
	}
}