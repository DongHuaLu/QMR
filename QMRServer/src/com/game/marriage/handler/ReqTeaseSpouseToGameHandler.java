package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqTeaseSpouseToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqTeaseSpouseToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqTeaseSpouseToGameHandler.class);

	public void action(){
		try{
			ReqTeaseSpouseToGameMessage msg = (ReqTeaseSpouseToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqTeaseSpouseToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}