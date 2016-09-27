package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.manager.PlayerManager;
import com.game.player.message.ReqScriptCommonPlayerToServerMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqScriptCommonPlayerToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqScriptCommonPlayerToServerHandler.class);

	public void action(){
		try{
			Player player = (Player)this.getParameter();
			ReqScriptCommonPlayerToServerMessage msg = (ReqScriptCommonPlayerToServerMessage)this.getMessage();
			PlayerManager.getInstance().ReqScriptCommonPlayerToServer(player, msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}