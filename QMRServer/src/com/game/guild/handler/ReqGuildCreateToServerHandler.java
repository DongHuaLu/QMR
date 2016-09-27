package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildCreateToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildCreateToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildCreateToServerHandler.class);

	public void action(){
		try{
			ReqGuildCreateToServerMessage msg = (ReqGuildCreateToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildCreateToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}