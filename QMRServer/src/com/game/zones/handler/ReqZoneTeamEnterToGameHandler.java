package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqZoneTeamEnterToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZoneTeamEnterToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneTeamEnterToGameHandler.class);

	public void action(){
		try{
			ReqZoneTeamEnterToGameMessage msg = (ReqZoneTeamEnterToGameMessage)this.getMessage();
			ManagerPool.zonesTeamManager.stReqZoneTeamEnterToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}