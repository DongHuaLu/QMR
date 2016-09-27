package com.game.zones.handler;

import org.apache.log4j.Logger;

import com.game.zones.message.ReqSelectAwardMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqSelectAwardHandler extends Handler{

	Logger log = Logger.getLogger(ReqSelectAwardHandler.class);

	public void action(){
		try{
			ReqSelectAwardMessage msg = (ReqSelectAwardMessage)this.getMessage();
			ManagerPool.zonesFlopManager.stReqSelectAwardMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}