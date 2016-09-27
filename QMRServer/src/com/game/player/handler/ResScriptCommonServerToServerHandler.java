package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.manager.PlayerManager;
import com.game.player.message.ResScriptCommonServerToServerMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ResScriptCommonServerToServerHandler extends Handler{

	Logger log = Logger.getLogger(ResScriptCommonServerToServerHandler.class);

	public void action(){
		try{
			Player player = (Player)this.getParameter();
			ResScriptCommonServerToServerMessage msg = (ResScriptCommonServerToServerMessage)this.getMessage();
			PlayerManager.getInstance().ResScriptCommonServerToServer(player, msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}