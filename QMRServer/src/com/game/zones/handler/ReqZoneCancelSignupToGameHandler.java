package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqZoneCancelSignupToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZoneCancelSignupToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneCancelSignupToGameHandler.class);

	public void action(){
		try{
			ReqZoneCancelSignupToGameMessage msg = (ReqZoneCancelSignupToGameMessage)this.getMessage();
			ManagerPool.zonesTeamManager.stReqZoneCancelSignupToGame((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}