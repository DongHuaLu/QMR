package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.spirittree.message.ReqContinuousRipeningToGameMessage;
import com.game.command.Handler;

public class ReqContinuousRipeningToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqContinuousRipeningToGameHandler.class);

	public void action(){
		try{
			ReqContinuousRipeningToGameMessage msg = (ReqContinuousRipeningToGameMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqContinuousRipeningToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}