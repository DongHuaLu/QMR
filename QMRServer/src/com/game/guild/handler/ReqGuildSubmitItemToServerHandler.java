package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildSubmitItemToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildSubmitItemToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildSubmitItemToServerHandler.class);

	public void action(){
		try{
			ReqGuildSubmitItemToServerMessage msg = (ReqGuildSubmitItemToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildSubmitItemToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}