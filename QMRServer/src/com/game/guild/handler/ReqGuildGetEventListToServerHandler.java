package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildGetEventListToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildGetEventListToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildGetEventListToServerHandler.class);

	public void action(){
		try{
			ReqGuildGetEventListToServerMessage msg = (ReqGuildGetEventListToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildGetEventListToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}