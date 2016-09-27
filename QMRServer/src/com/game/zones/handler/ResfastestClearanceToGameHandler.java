package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ResfastestClearanceToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;

public class ResfastestClearanceToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResfastestClearanceToGameHandler.class);

	public void action(){
		try{
			ResfastestClearanceToGameMessage msg = (ResfastestClearanceToGameMessage)this.getMessage();
			ManagerPool.zonesManager.stResfastestClearanceToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}