package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildAddDiplomaticToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildAddDiplomaticToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildAddDiplomaticToServerHandler.class);

	public void action(){
		try{
			ReqGuildAddDiplomaticToServerMessage msg = (ReqGuildAddDiplomaticToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildAddDiplomaticToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}