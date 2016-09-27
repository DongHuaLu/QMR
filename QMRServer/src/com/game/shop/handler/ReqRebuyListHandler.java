package com.game.shop.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.shop.manager.ShopManager;

public class ReqRebuyListHandler extends Handler{

	Logger log = Logger.getLogger(ReqRebuyListHandler.class);

	public void action(){
		try{
			ShopManager.getInstance().buyBackList((Player)this.getParameter());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}