package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqDivorceToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqDivorceToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqDivorceToGameHandler.class);

	public void action(){
		try{
			ReqDivorceToGameMessage msg = (ReqDivorceToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqDivorceToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}