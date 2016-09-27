package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqCockRedenvelopeToGameMessage;
import com.game.player.structs.Player;

public class ReqCockRedenvelopeToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqCockRedenvelopeToGameHandler.class);

	public void action(){
		try{
			ReqCockRedenvelopeToGameMessage msg = (ReqCockRedenvelopeToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqCockRedenvelopeToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}