package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.message.ReqVipPlayerChangeMapToServerMessage;
import com.game.command.Handler;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;

public class ReqVipPlayerChangeMapToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqVipPlayerChangeMapToServerHandler.class);

	public void action(){
		try{
			ReqVipPlayerChangeMapToServerMessage msg = (ReqVipPlayerChangeMapToServerMessage)this.getMessage();
			PlayerManager.getInstance().reqVipPlayerChangeMap((Player) this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}