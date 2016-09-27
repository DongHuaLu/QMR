package com.game.ybcard.handler;

import org.apache.log4j.Logger;

import com.game.ybcard.message.ResYBCardReceiveToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;

public class ResYBCardReceiveToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResYBCardReceiveToGameHandler.class);

	public void action(){
		try{
			ResYBCardReceiveToGameMessage msg = (ResYBCardReceiveToGameMessage)this.getMessage();
			ManagerPool.ybcardManager.stResYBCardReceiveToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}