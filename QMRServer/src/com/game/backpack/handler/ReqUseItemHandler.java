package com.game.backpack.handler;

import org.apache.log4j.Logger;

import com.game.backpack.message.ReqUseItemMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqUseItemHandler extends Handler{

	Logger log = Logger.getLogger(ReqUseItemHandler.class);

	public void action(){
		try{
			ReqUseItemMessage msg = (ReqUseItemMessage)this.getMessage();
			
			ManagerPool.backpackManager.useItem((Player)this.getParameter(), msg.getItemId(),msg.getNum());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}