package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildChangeBannerNameToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildChangeBannerNameToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildChangeBannerNameToServerHandler.class);

	public void action(){
		try{
			ReqGuildChangeBannerNameToServerMessage msg = (ReqGuildChangeBannerNameToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildChangeBannerNameToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}