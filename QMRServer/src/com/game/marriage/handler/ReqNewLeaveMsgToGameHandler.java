package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqNewLeaveMsgToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqNewLeaveMsgToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqNewLeaveMsgToGameHandler.class);

	public void action(){
		try{
			ReqNewLeaveMsgToGameMessage msg = (ReqNewLeaveMsgToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqNewLeaveMsgToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}