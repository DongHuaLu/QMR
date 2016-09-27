package com.game.protect.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.protect.message.ReqPasswordSetToGameMessage;
import com.game.command.Handler;

public class ReqPasswordSetToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqPasswordSetToGameHandler.class);

	public void action(){
		try{
			ReqPasswordSetToGameMessage msg = (ReqPasswordSetToGameMessage)this.getMessage();
			//ManagerPool.protectManager.stReqPasswordSetToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}