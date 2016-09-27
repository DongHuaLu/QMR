package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZoneTeamOpenToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneTeamOpenToGameHandler.class);

	public void action(){
		try{
			//ReqZoneTeamOpenToGameMessage msg = (ReqZoneTeamOpenToGameMessage)this.getMessage();
			ManagerPool.zonesTeamManager.stReqZoneTeamOpenToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}