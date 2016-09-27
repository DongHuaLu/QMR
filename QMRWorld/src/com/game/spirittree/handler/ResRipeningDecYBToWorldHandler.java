package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ResRipeningDecYBToWorldMessage;
import com.game.command.Handler;

public class ResRipeningDecYBToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ResRipeningDecYBToWorldHandler.class);

	public void action(){
		try{
			ResRipeningDecYBToWorldMessage msg = (ResRipeningDecYBToWorldMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stResRipeningDecYBToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}