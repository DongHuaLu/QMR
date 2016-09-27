package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildGetMemberListToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildGetMemberListToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildGetMemberListToServerHandler.class);

	public void action(){
		try{
			ReqGuildGetMemberListToServerMessage msg = (ReqGuildGetMemberListToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildGetMemberListToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}