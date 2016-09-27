package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqApplyWeddingToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqApplyWeddingToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqApplyWeddingToGameHandler.class);

	public void action(){
		try{
			ReqApplyWeddingToGameMessage msg = (ReqApplyWeddingToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqApplyWeddingToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}