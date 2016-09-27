package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqChangeRingToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqChangeRingToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangeRingToGameHandler.class);

	public void action(){
		try{
			ReqChangeRingToGameMessage msg = (ReqChangeRingToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqChangeRingToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}