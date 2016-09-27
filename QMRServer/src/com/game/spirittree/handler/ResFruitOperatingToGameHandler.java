package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ResFruitOperatingToGameMessage;
import com.game.command.Handler;

public class ResFruitOperatingToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResFruitOperatingToGameHandler.class);

	public void action(){
		try{
			ResFruitOperatingToGameMessage msg = (ResFruitOperatingToGameMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stResFruitOperatingToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}