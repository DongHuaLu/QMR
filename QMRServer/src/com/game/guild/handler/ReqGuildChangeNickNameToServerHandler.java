package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildChangeNickNameToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildChangeNickNameToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildChangeNickNameToServerHandler.class);

	public void action(){
		try{
			ReqGuildChangeNickNameToServerMessage msg = (ReqGuildChangeNickNameToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildChangeNickNameToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}