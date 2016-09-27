package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ReqRipeningDecYBToGameMessage;
import com.game.command.Handler;

public class ReqRipeningDecYBToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqRipeningDecYBToGameHandler.class);

	public void action(){
		try{
			ReqRipeningDecYBToGameMessage msg = (ReqRipeningDecYBToGameMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stReqRipeningDecYBToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}