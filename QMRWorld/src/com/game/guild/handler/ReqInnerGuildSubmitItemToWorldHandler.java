package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildSubmitItemToWorldMessage;

public class ReqInnerGuildSubmitItemToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildSubmitItemToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildSubmitItemToWorldMessage msg = (ReqInnerGuildSubmitItemToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildSubmitItemToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}