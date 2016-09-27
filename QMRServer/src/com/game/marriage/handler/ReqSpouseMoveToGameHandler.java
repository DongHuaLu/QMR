package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqSpouseMoveToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqSpouseMoveToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqSpouseMoveToGameHandler.class);

	public void action(){
		try{
			ReqSpouseMoveToGameMessage msg = (ReqSpouseMoveToGameMessage)this.getMessage();
			//ManagerPool.marriageManager.stReqSpouseMoveToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}