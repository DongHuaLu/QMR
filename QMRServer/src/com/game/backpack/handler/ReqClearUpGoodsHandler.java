package com.game.backpack.handler;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqClearUpGoodsHandler extends Handler{

	Logger log = Logger.getLogger(ReqClearUpGoodsHandler.class);

	public void action(){
		try{
			BackpackManager.getInstance().bagClearUp((Player)this.getParameter(),false);
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}