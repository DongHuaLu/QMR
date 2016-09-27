package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildBannerLevelUpToWorldMessage;

public class ReqInnerGuildBannerLevelUpToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildBannerLevelUpToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildBannerLevelUpToWorldMessage msg = (ReqInnerGuildBannerLevelUpToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildBannerLevelUpToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}