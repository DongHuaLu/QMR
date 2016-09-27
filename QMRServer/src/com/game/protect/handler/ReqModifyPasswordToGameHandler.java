package com.game.protect.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.protect.message.ReqModifyPasswordToGameMessage;

public class ReqModifyPasswordToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqModifyPasswordToGameHandler.class);

	public void action(){
		try{
			ReqModifyPasswordToGameMessage msg = (ReqModifyPasswordToGameMessage)this.getMessage();
			//ManagerPool.protectManager.stReqModifyPasswordToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}