package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqProposeSelectToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqProposeSelectToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqProposeSelectToGameHandler.class);

	public void action(){
		try{
			ReqProposeSelectToGameMessage msg = (ReqProposeSelectToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqProposeSelectToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}