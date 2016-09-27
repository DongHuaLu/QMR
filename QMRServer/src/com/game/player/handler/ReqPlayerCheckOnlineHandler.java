package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqPlayerCheckOnlineMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqPlayerCheckOnlineHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlayerCheckOnlineHandler.class);

	public void action(){
		try{
			ReqPlayerCheckOnlineMessage msg = (ReqPlayerCheckOnlineMessage)this.getMessage();
ManagerPool.playerManager.stReqPlayerCheckOnlineMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}