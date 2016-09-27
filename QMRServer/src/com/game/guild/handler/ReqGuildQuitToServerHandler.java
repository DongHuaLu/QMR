package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildQuitToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildQuitToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildQuitToServerHandler.class);

	public void action(){
		try{
			ReqGuildQuitToServerMessage msg = (ReqGuildQuitToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildQuitToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}