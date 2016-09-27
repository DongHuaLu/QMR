package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildAutoArgeeAddGuildToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildAutoArgeeAddGuildToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildAutoArgeeAddGuildToServerHandler.class);

	public void action(){
		try{
			ReqGuildAutoArgeeAddGuildToServerMessage msg = (ReqGuildAutoArgeeAddGuildToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildAutoArgeeAddGuildToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}