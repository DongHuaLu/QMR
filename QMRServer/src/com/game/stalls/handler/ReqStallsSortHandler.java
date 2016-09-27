package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.stalls.message.ReqStallsSortMessage;
import com.game.command.Handler;

public class ReqStallsSortHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsSortHandler.class);

	public void action(){
		try{
			ReqStallsSortMessage msg = (ReqStallsSortMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsSortMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}