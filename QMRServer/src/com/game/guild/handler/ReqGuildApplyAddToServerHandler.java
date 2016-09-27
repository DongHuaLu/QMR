package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildApplyAddToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildApplyAddToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildApplyAddToServerHandler.class);

	public void action(){
		try{
			ReqGuildApplyAddToServerMessage msg = (ReqGuildApplyAddToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildApplyAddToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}