package com.game.gem.handler;

import org.apache.log4j.Logger;

import com.game.gem.message.ReqGemIntoMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqGemIntoHandler extends Handler{

	Logger log = Logger.getLogger(ReqGemIntoHandler.class);

	public void action(){
		try{
			ReqGemIntoMessage msg = (ReqGemIntoMessage)this.getMessage();
			ManagerPool.gemManager.stReqGemIntoMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}