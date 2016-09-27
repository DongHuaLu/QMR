package com.game.shop.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.shop.message.ReqSellItemsMessage;
import com.game.command.Handler;

public class ReqSellItemsHandler extends Handler{

	Logger log = Logger.getLogger(ReqSellItemsHandler.class);

	public void action(){
		try{
			ReqSellItemsMessage msg = (ReqSellItemsMessage)this.getMessage();
			//添加消息处理
			ManagerPool.shopManager.sellItem((Player)this.getParameter(), msg.getItemId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}