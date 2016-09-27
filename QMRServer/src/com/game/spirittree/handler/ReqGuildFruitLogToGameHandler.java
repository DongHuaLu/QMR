package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqGuildFruitLogToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildFruitLogToGameHandler.class);

	public void action(){
		try{
			//ReqGuildFruitLogToGameMessage msg = (ReqGuildFruitLogToGameMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqGuildFruitLogToGameMessage((Player)this.getParameter());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}