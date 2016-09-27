package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildAutoArgeeAddGuildToWorldMessage;

public class ReqInnerGuildAutoArgeeAddGuildToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildAutoArgeeAddGuildToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildAutoArgeeAddGuildToWorldMessage msg = (ReqInnerGuildAutoArgeeAddGuildToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildAutoArgeeAddGuildToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}