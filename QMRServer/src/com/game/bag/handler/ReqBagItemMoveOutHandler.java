package com.game.bag.handler;

import org.apache.log4j.Logger;
import com.game.player.structs.Player;
import com.game.bag.manager.BagManager;
import com.game.bag.message.ReqBagItemMoveOutMessage;
import com.game.command.Handler;

public class ReqBagItemMoveOutHandler extends Handler{

	Logger log = Logger.getLogger(ReqBagItemMoveOutHandler.class);

	public void action(){
		try{
			ReqBagItemMoveOutMessage msg = (ReqBagItemMoveOutMessage)this.getMessage();
			BagManager.getInstance().removeItem((Player) this.getParameter(), msg.getCellId(), 0);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}