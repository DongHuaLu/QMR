package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ResGameMakeFruitInfoMessage;
import com.game.command.Handler;

public class ResGameMakeFruitInfoHandler extends Handler{

	Logger log = Logger.getLogger(ResGameMakeFruitInfoHandler.class);

	public void action(){
		try{
			ResGameMakeFruitInfoMessage msg = (ResGameMakeFruitInfoMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stResGameMakeFruitInfoMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}