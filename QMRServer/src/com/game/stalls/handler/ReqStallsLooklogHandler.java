package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqStallsLooklogHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsLooklogHandler.class);

	public void action(){
		try{
			//ReqStallsLooklogMessage msg = (ReqStallsLooklogMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsLooklogMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}