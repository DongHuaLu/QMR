package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildDeleteGuildToWorldMessage;

public class ReqInnerGuildDeleteGuildToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildDeleteGuildToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildDeleteGuildToWorldMessage msg = (ReqInnerGuildDeleteGuildToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildDeleteGuildToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}