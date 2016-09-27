package com.game.shop.handler;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.shop.manager.ShopManager;
import com.game.shop.message.ReqNotSaleMessage;
import com.game.command.Handler;

public class ReqNotSaleHandler extends Handler{

	Logger log = Logger.getLogger(ReqNotSaleHandler.class);

	public void action() {
		try {
			ReqNotSaleMessage msg = (ReqNotSaleMessage) this.getMessage();
			ShopManager.getInstance().shopItemList((Player)this.getParameter(), msg.getSellId());
		} catch (ClassCastException e) {
			log.error(e,e);
		}
	}
}