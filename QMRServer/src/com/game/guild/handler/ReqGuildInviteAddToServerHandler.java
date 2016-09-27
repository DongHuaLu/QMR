package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildInviteAddToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildInviteAddToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildInviteAddToServerHandler.class);

	public void action(){
		try{
			ReqGuildInviteAddToServerMessage msg = (ReqGuildInviteAddToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildInviteAddToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}