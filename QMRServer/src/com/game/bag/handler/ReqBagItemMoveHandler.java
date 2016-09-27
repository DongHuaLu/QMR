package com.game.bag.handler;

import org.apache.log4j.Logger;
import com.game.player.structs.Player;
import com.game.bag.manager.BagManager;
import com.game.bag.message.ReqBagItemMoveMessage;
import com.game.command.Handler;

public class ReqBagItemMoveHandler extends Handler{

	Logger log = Logger.getLogger(ReqBagItemMoveHandler.class);

	public void action(){
		try{
			ReqBagItemMoveMessage msg = (ReqBagItemMoveMessage)this.getMessage();
			BagManager.getInstance().move((Player) this.getParameter(), msg.getItemId(), msg.getNum(), msg.getToGridId(), 0);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}