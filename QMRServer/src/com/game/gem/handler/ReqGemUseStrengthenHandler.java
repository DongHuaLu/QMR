package com.game.gem.handler;

import org.apache.log4j.Logger;

import com.game.gem.message.ReqGemUseStrengthenMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqGemUseStrengthenHandler extends Handler{

	Logger log = Logger.getLogger(ReqGemUseStrengthenHandler.class);

	public void action(){
		try{
			ReqGemUseStrengthenMessage msg = (ReqGemUseStrengthenMessage)this.getMessage();
		//	ManagerPool.gemManager.stReqGemUseStrengthenMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}