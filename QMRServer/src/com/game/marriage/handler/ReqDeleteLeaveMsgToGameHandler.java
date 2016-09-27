package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqDeleteLeaveMsgToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqDeleteLeaveMsgToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqDeleteLeaveMsgToGameHandler.class);

	public void action(){
		try{
			ReqDeleteLeaveMsgToGameMessage msg = (ReqDeleteLeaveMsgToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqDeleteLeaveMsgToGameHandler((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}