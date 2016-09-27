package com.game.bag.handler;

import org.apache.log4j.Logger;
import com.game.player.structs.Player;
import com.game.bag.manager.BagManager;
import com.game.command.Handler;

public class ReqBagSynHandler extends Handler{

	Logger log = Logger.getLogger(ReqBagSynHandler.class);

	public void action(){
		try{
			BagManager.getInstance().syn((Player) this.getParameter(), 0);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}