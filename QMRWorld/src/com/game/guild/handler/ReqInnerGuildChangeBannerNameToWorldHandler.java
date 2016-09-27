package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildChangeBannerNameToWorldMessage;

public class ReqInnerGuildChangeBannerNameToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildChangeBannerNameToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildChangeBannerNameToWorldMessage msg = (ReqInnerGuildChangeBannerNameToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildChangeBannerNameToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}