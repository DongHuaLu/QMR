package com.game.backpack.handler;

import org.apache.log4j.Logger;

import com.game.backpack.message.ReqTakeUpMessage;
import com.game.command.Handler;
import com.game.drop.manager.DropManager;
import com.game.player.structs.Player;

public class ReqTakeUpHandler extends Handler{

	Logger log = Logger.getLogger(ReqTakeUpHandler.class);

	public void action(){
		try{
			ReqTakeUpMessage msg = (ReqTakeUpMessage)this.getMessage();
			DropManager.takeUp((Player)this.getParameter(), msg.getGoodsId());	
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}