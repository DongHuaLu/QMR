package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqZoneContinuousRaidsMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZoneContinuousRaidsHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneContinuousRaidsHandler.class);

	public void action(){
		try{
			ReqZoneContinuousRaidsMessage msg = (ReqZoneContinuousRaidsMessage)this.getMessage();
			ManagerPool.zonesManager.ContinuousRaids((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}