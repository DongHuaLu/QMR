package com.game.backpack.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqGetItemsHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetItemsHandler.class);

	public void action(){
		try{
			ManagerPool.backpackManager.sendItemInfos((Player)this.getParameter());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}