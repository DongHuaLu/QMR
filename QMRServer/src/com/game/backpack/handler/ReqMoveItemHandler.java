package com.game.backpack.handler;

import org.apache.log4j.Logger;

import com.game.backpack.message.ReqMoveItemMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqMoveItemHandler extends Handler{

	Logger log = Logger.getLogger(ReqMoveItemHandler.class);

	public void action(){
		try{
			ReqMoveItemMessage msg = (ReqMoveItemMessage)this.getMessage();
			
			ManagerPool.backpackManager.moveItem((Player)this.getParameter(), msg.getItemId(), msg.getNum(), msg.getToGridId());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}