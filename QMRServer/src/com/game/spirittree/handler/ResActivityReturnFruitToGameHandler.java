package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ResActivityReturnFruitToGameMessage;
import com.game.command.Handler;

public class ResActivityReturnFruitToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResActivityReturnFruitToGameHandler.class);

	public void action(){
		try{
			ResActivityReturnFruitToGameMessage msg = (ResActivityReturnFruitToGameMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stResActivityReturnFruitToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}