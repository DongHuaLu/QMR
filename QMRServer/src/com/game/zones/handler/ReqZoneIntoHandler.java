package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqZoneIntoMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZoneIntoHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneIntoHandler.class);

	public void action(){
		try{
			ReqZoneIntoMessage msg = (ReqZoneIntoMessage)this.getMessage();
			ManagerPool.zonesManager.stResReqZoneIntoMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}