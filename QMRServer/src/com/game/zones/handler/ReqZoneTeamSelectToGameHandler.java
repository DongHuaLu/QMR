package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqZoneTeamSelectToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZoneTeamSelectToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneTeamSelectToGameHandler.class);

	public void action(){
		try{
			ReqZoneTeamSelectToGameMessage msg = (ReqZoneTeamSelectToGameMessage)this.getMessage();
			ManagerPool.zonesTeamManager.stReqZoneTeamSelectToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}