package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildDeleteDiplomaticToWorldMessage;

public class ReqInnerGuildDeleteDiplomaticToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildDeleteDiplomaticToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildDeleteDiplomaticToWorldMessage msg = (ReqInnerGuildDeleteDiplomaticToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildDeleteDiplomaticToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}