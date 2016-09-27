package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildDeleteMemberToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildDeleteMemberToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildDeleteMemberToServerHandler.class);

	public void action(){
		try{
			ReqGuildDeleteMemberToServerMessage msg = (ReqGuildDeleteMemberToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildDeleteMemberToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}