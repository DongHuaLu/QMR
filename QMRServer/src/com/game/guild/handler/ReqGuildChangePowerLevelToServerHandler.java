package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildChangePowerLevelToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildChangePowerLevelToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildChangePowerLevelToServerHandler.class);

	public void action(){
		try{
			ReqGuildChangePowerLevelToServerMessage msg = (ReqGuildChangePowerLevelToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildChangePowerLevelToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}