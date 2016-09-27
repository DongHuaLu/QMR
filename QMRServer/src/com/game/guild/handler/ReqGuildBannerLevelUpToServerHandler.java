package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildBannerLevelUpToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildBannerLevelUpToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildBannerLevelUpToServerHandler.class);

	public void action(){
		try{
			ReqGuildBannerLevelUpToServerMessage msg = (ReqGuildBannerLevelUpToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildBannerLevelUpToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}