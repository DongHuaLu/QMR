package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqZoneOutMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZoneOutHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneOutHandler.class);

	public void action(){
		try{
			ReqZoneOutMessage msg = (ReqZoneOutMessage)this.getMessage();
			ManagerPool.zonesManager.stReqZoneOutMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}