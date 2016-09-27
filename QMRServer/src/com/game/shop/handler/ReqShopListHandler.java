package com.game.shop.handler;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.shop.manager.ShopManager;
import com.game.shop.message.ReqShopListMessage;
import com.game.command.Handler;

public class ReqShopListHandler extends Handler{

	Logger log = Logger.getLogger(ReqShopListHandler.class);

	public void action(){
		try{
			ReqShopListMessage msg = (ReqShopListMessage)this.getMessage();
			ShopManager.getInstance().shopList((Player)getParameter(),msg.getShopModelId(),msg.getPage(),msg.getGradeLimit()==0);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}