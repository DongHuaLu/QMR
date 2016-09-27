package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.spirittree.message.ReqGuildFruitOperatingToGameMessage;

public class ReqGuildFruitOperatingToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildFruitOperatingToGameHandler.class);

	public void action(){
		try{
			ReqGuildFruitOperatingToGameMessage msg = (ReqGuildFruitOperatingToGameMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqGuildFruitOperatingToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}