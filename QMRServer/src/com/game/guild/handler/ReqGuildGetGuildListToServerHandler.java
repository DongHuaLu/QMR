package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildGetGuildListToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildGetGuildListToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildGetGuildListToServerHandler.class);

	public void action(){
		try{
			ReqGuildGetGuildListToServerMessage msg = (ReqGuildGetGuildListToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildGetGuildListToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}