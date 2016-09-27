package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildDeleteDiplomaticToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildDeleteDiplomaticToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildDeleteDiplomaticToServerHandler.class);

	public void action(){
		try{
			ReqGuildDeleteDiplomaticToServerMessage msg = (ReqGuildDeleteDiplomaticToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildDeleteDiplomaticToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}