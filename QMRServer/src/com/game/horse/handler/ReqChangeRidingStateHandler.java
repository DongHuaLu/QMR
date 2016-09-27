package com.game.horse.handler;

import org.apache.log4j.Logger;

import com.game.horse.message.ReqChangeRidingStateMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqChangeRidingStateHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangeRidingStateHandler.class);

	public void action(){
		try{
			ReqChangeRidingStateMessage msg = (ReqChangeRidingStateMessage)this.getMessage();
			ManagerPool.horseManager.stChangeRidingState((Player)this.getParameter(), msg.getStatus(),msg.getCurlayer());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}