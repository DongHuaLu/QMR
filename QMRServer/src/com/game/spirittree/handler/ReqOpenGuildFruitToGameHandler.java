package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqOpenGuildFruitToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqOpenGuildFruitToGameHandler.class);

	public void action(){
		try{
			//ReqOpenGuildFruitToGameMessage msg = (ReqOpenGuildFruitToGameMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqOpenGuildFruitToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}