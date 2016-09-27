package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildCreateToWorldMessage;

public class ReqInnerGuildCreateToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildCreateToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildCreateToWorldMessage msg = (ReqInnerGuildCreateToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildCreateToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}