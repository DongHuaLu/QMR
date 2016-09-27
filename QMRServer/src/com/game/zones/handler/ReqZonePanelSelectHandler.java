package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqZonePanelSelectMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqZonePanelSelectHandler extends Handler{

	Logger log = Logger.getLogger(ReqZonePanelSelectHandler.class);

	public void action(){
		try{
			ReqZonePanelSelectMessage msg = (ReqZonePanelSelectMessage)this.getMessage();
			//选中副本
			ManagerPool.zonesManager.stReqZonePanelSelectMessage((Player)this.getParameter(),msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}