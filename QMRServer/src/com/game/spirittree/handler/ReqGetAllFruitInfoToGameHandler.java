package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.spirittree.message.ReqGetAllFruitInfoToGameMessage;
import com.game.command.Handler;

public class ReqGetAllFruitInfoToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetAllFruitInfoToGameHandler.class);

	public void action(){
		try{
			ReqGetAllFruitInfoToGameMessage msg = (ReqGetAllFruitInfoToGameMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqGetAllFruitInfoToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}