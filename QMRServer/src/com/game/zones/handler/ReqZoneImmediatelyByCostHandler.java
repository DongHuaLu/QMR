package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqZoneImmediatelyByCostMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZoneImmediatelyByCostHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneImmediatelyByCostHandler.class);

	public void action(){
		try{
			ReqZoneImmediatelyByCostMessage msg = (ReqZoneImmediatelyByCostMessage)this.getMessage();
			//立刻完成副本
			ManagerPool.zonesManager.stReqZoneImmediatelyByCostMessage((Player)this.getParameter(), msg.getZoneid());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}