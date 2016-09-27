package com.game.protect.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.protect.message.ReqBackstageModifyToGameMessage;

public class ReqBackstageModifyToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqBackstageModifyToGameHandler.class);

	public void action(){
		try{
			ReqBackstageModifyToGameMessage msg = (ReqBackstageModifyToGameMessage)this.getMessage();
			//ManagerPool.protectManager.stReqBackstageModifyToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}