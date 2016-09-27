package com.game.ybcard.handler;

import org.apache.log4j.Logger;

import com.game.ybcard.message.ResYBCardAddYBPlayerToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;

public class ResYBCardAddYBPlayerToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResYBCardAddYBPlayerToGameHandler.class);

	public void action(){
		try{
			ResYBCardAddYBPlayerToGameMessage msg = (ResYBCardAddYBPlayerToGameMessage)this.getMessage();
			ManagerPool.ybcardManager.stResYBCardAddYBPlayerToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}