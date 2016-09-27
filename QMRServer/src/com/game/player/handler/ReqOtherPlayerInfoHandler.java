package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqOtherPlayerInfoMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqOtherPlayerInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqOtherPlayerInfoHandler.class);

	public void action(){
		try{
			ReqOtherPlayerInfoMessage msg = (ReqOtherPlayerInfoMessage)this.getMessage();
			//获得他人详细信息
			ManagerPool.playerManager.sendPlayerDetails((Player)getParameter(), msg.getPersonId(),msg.getType());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}