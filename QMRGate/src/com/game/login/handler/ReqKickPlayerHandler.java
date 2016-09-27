package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.login.message.ReqKickPlayerMessage;
import com.game.manager.ManagerPool;
import com.game.command.Handler;

public class ReqKickPlayerHandler extends Handler{

	Logger log = Logger.getLogger(ReqKickPlayerHandler.class);

	public void action(){
		try{
			ReqKickPlayerMessage msg = (ReqKickPlayerMessage)this.getMessage();
			//踢人处理
			ManagerPool.playerManager.kickPlayer(msg.getPlayerId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}