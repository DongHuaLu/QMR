package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqZoneAutoAwardToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZoneAutoAwardToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneAutoAwardToGameHandler.class);

	public void action(){
		try{
			ReqZoneAutoAwardToGameMessage msg = (ReqZoneAutoAwardToGameMessage)this.getMessage();
			ManagerPool.zonesFlopManager.autoAwardfor((Player)this.getParameter(),msg.getZonetype());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}