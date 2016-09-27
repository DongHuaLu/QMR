package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.message.ReqSyncPlayerAttributeMessage;
import com.game.command.Handler;
import com.game.player.manager.PlayerManager;

public class ReqSyncPlayerAttributeHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerAttributeHandler.class);

	public void action(){
		try{
			ReqSyncPlayerAttributeMessage msg = (ReqSyncPlayerAttributeMessage)this.getMessage();
			PlayerManager.getInstance().syncPlayerAttribute(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}