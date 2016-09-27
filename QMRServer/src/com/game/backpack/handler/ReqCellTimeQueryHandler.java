package com.game.backpack.handler;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.message.ReqCellTimeQueryMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqCellTimeQueryHandler extends Handler{

	Logger log = Logger.getLogger(ReqCellTimeQueryHandler.class);

	public void action(){
		try{
			ReqCellTimeQueryMessage msg = (ReqCellTimeQueryMessage)this.getMessage();
			BackpackManager.getInstance().dealCellTimeQueryMsg((Player)this.getParameter(), msg.getCellId());
			
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}