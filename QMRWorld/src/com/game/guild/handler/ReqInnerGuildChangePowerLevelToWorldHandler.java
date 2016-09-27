package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildChangePowerLevelToWorldMessage;

public class ReqInnerGuildChangePowerLevelToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildChangePowerLevelToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildChangePowerLevelToWorldMessage msg = (ReqInnerGuildChangePowerLevelToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildChangePowerLevelToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}