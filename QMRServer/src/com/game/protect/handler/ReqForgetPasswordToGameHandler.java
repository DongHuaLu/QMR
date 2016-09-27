package com.game.protect.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.protect.message.ReqForgetPasswordToGameMessage;
import com.game.command.Handler;

public class ReqForgetPasswordToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqForgetPasswordToGameHandler.class);

	public void action(){
		try{
			ReqForgetPasswordToGameMessage msg = (ReqForgetPasswordToGameMessage)this.getMessage();
			//ManagerPool.protectManager.stReqForgetPasswordToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}