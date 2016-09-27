package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildDeleteGuildToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildDeleteGuildToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildDeleteGuildToServerHandler.class);

	public void action(){
		try{
			ReqGuildDeleteGuildToServerMessage msg = (ReqGuildDeleteGuildToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildDeleteGuildToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}