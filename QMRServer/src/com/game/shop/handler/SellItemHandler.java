package com.game.shop.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.shop.message.SellItemMessage;

public class SellItemHandler extends Handler{

	Logger log = Logger.getLogger(SellItemHandler.class);

	public void action(){
		try{
			SellItemMessage msg = (SellItemMessage)this.getMessage();
			
			ManagerPool.shopManager.sellItem((Player)this.getParameter(), msg.getItemId());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}