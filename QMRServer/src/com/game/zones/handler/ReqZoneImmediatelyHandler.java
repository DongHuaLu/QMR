package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqZoneImmediatelyMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZoneImmediatelyHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneImmediatelyHandler.class);

	public void action(){
		try{
			ReqZoneImmediatelyMessage msg = (ReqZoneImmediatelyMessage)this.getMessage();
			ManagerPool.zonesManager.stReqZoneImmediatelyMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}