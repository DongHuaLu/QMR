package com.game.shop.handler;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.shop.manager.ShopManager;
import com.game.shop.message.ReqRebuyMessage;
import com.game.command.Handler;

public class ReqRebuyHandler extends Handler{

	Logger log = Logger.getLogger(ReqRebuyHandler.class);

	public void action(){
		try{
			ReqRebuyMessage msg = (ReqRebuyMessage)this.getMessage();
			ShopManager.getInstance().buyBack((Player)this.getParameter(), msg.getItemId());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}