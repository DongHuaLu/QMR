package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqWeddingParticipateToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqWeddingParticipateToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqWeddingParticipateToGameHandler.class);

	public void action(){
		try{
			ReqWeddingParticipateToGameMessage msg = (ReqWeddingParticipateToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqWeddingParticipateToGameMessage((Player)this.getParameter(),msg );
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}