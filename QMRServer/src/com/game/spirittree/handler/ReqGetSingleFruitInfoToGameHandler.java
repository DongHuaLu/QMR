package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.spirittree.message.ReqGetSingleFruitInfoToGameMessage;
import com.game.command.Handler;

public class ReqGetSingleFruitInfoToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetSingleFruitInfoToGameHandler.class);

	public void action(){
		try{
			ReqGetSingleFruitInfoToGameMessage msg = (ReqGetSingleFruitInfoToGameMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqGetSingleFruitInfoToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}