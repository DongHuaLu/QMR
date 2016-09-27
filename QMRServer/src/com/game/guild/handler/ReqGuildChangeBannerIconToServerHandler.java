package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildChangeBannerIconToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildChangeBannerIconToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildChangeBannerIconToServerHandler.class);

	public void action(){
		try{
			ReqGuildChangeBannerIconToServerMessage msg = (ReqGuildChangeBannerIconToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildChangeBannerIconToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}