package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqWeddingListToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqWeddingListToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqWeddingListToGameHandler.class);

	public void action(){
		try{
			ReqWeddingListToGameMessage msg = (ReqWeddingListToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqWeddingListToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}