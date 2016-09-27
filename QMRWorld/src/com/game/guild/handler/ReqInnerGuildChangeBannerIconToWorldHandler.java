package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildChangeBannerIconToWorldMessage;

public class ReqInnerGuildChangeBannerIconToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildChangeBannerIconToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildChangeBannerIconToWorldMessage msg = (ReqInnerGuildChangeBannerIconToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildChangeBannerIconToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}