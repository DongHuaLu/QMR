package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqDivorceSelectToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqDivorceSelectToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqDivorceSelectToGameHandler.class);

	public void action(){
		try{
			ReqDivorceSelectToGameMessage msg = (ReqDivorceSelectToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqDivorceSelectToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}