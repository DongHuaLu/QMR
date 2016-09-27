package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqZoneContinuousRaidsYBMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZoneContinuousRaidsYBHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneContinuousRaidsYBHandler.class);

	public void action(){
		try{
			ReqZoneContinuousRaidsYBMessage msg = (ReqZoneContinuousRaidsYBMessage)this.getMessage();
			ManagerPool.zonesManager.stReqZoneContinuousRaidsYBMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}