package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildGetGuildListToWorldMessage;

public class ReqInnerGuildGetGuildListToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildGetGuildListToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildGetGuildListToWorldMessage msg = (ReqInnerGuildGetGuildListToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildGetGuildListToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}