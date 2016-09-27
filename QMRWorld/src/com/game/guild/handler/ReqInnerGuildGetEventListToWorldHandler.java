package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildGetEventListToWorldMessage;

public class ReqInnerGuildGetEventListToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildGetEventListToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildGetEventListToWorldMessage msg = (ReqInnerGuildGetEventListToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildGetEventListToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}