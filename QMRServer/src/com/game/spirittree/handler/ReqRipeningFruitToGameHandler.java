package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.spirittree.message.ReqRipeningFruitToGameMessage;
import com.game.command.Handler;

public class ReqRipeningFruitToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqRipeningFruitToGameHandler.class);

	public void action(){
		try{
			ReqRipeningFruitToGameMessage msg = (ReqRipeningFruitToGameMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqRipeningFruitToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}