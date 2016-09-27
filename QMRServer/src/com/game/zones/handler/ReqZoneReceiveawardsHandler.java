package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqZoneReceiveawardsMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZoneReceiveawardsHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneReceiveawardsHandler.class);

	public void action(){
		try{
			ReqZoneReceiveawardsMessage msg = (ReqZoneReceiveawardsMessage)this.getMessage();
			ManagerPool.zonesFlopManager.stReqZoneReceiveawardsMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}