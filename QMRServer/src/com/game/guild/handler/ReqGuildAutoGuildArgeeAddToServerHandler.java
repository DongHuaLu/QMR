package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildAutoGuildArgeeAddToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildAutoGuildArgeeAddToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildAutoGuildArgeeAddToServerHandler.class);

	public void action(){
		try{
			ReqGuildAutoGuildArgeeAddToServerMessage msg = (ReqGuildAutoGuildArgeeAddToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildAutoGuildArgeeAddToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}