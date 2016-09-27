package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqSpouseInfoToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqSpouseInfoToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqSpouseInfoToGameHandler.class);

	public void action(){
		try{
			ReqSpouseInfoToGameMessage msg = (ReqSpouseInfoToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqSpouseInfoToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}