package com.game.protect.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.protect.message.ReqVerificationToGameMessage;
import com.game.command.Handler;

public class ReqVerificationToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqVerificationToGameHandler.class);

	public void action(){
		try{
			ReqVerificationToGameMessage msg = (ReqVerificationToGameMessage)this.getMessage();
			//ManagerPool.protectManager.stReqVerificationToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}