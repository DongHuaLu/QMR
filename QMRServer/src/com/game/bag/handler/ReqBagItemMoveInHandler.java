package com.game.bag.handler;

import org.apache.log4j.Logger;
import com.game.player.structs.Player;
import com.game.bag.manager.BagManager;
import com.game.bag.message.ReqBagItemMoveInMessage;
import com.game.command.Handler;

public class ReqBagItemMoveInHandler extends Handler{

	Logger log = Logger.getLogger(ReqBagItemMoveInHandler.class);

	public void action(){
		try{
			ReqBagItemMoveInMessage msg = (ReqBagItemMoveInMessage)this.getMessage();
			BagManager.getInstance().addItem((Player) this.getParameter(), msg.getCellId(), 0);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}