package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildAddMemberToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildAddMemberToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildAddMemberToServerHandler.class);

	public void action(){
		try{
			ReqGuildAddMemberToServerMessage msg = (ReqGuildAddMemberToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildAddMemberToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}