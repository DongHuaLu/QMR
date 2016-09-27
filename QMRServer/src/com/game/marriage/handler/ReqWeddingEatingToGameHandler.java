package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqWeddingEatingToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqWeddingEatingToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqWeddingEatingToGameHandler.class);

	public void action(){
		try{
			ReqWeddingEatingToGameMessage msg = (ReqWeddingEatingToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqWeddingEatingToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}