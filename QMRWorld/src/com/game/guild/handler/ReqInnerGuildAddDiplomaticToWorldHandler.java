package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildAddDiplomaticToWorldMessage;

public class ReqInnerGuildAddDiplomaticToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildAddDiplomaticToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildAddDiplomaticToWorldMessage msg = (ReqInnerGuildAddDiplomaticToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildAddDiplomaticToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}