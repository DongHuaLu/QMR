package com.game.gem.handler;

import org.apache.log4j.Logger;

import com.game.gem.message.ReqGemActivationORUpMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqGemActivationORUpHandler extends Handler{

	Logger log = Logger.getLogger(ReqGemActivationORUpHandler.class);

	public void action(){
		try{
			ReqGemActivationORUpMessage msg = (ReqGemActivationORUpMessage)this.getMessage();
			ManagerPool.gemManager.stReqGemActivationORUpMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}