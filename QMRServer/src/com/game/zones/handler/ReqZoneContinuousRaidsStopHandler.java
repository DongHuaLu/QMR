package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqZoneContinuousRaidsStopMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZoneContinuousRaidsStopHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneContinuousRaidsStopHandler.class);

	public void action(){
		try{
			ReqZoneContinuousRaidsStopMessage msg = (ReqZoneContinuousRaidsStopMessage)this.getMessage();
			ManagerPool.zonesManager.stReqZoneContinuousRaidsStopMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}