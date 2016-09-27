package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqZoneOpenPanelMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZoneOpenPanelHandler extends Handler{

	Logger log = Logger.getLogger(ReqZoneOpenPanelHandler.class);

	public void action(){
		try{
			ReqZoneOpenPanelMessage msg = (ReqZoneOpenPanelMessage)this.getMessage();
			ManagerPool.zonesManager.stReqZoneOpenPanelMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}