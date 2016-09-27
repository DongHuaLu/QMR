package com.game.protect.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.protect.message.ReqPasswordUnlockToGameMessage;
import com.game.command.Handler;

public class ReqPasswordUnlockToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqPasswordUnlockToGameHandler.class);

	public void action(){
		try{
			ReqPasswordUnlockToGameMessage msg = (ReqPasswordUnlockToGameMessage)this.getMessage();
			//ManagerPool.protectManager.stReqPasswordUnlockToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}