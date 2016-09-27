package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildApplyAddToWorldMessage;

public class ReqInnerGuildApplyAddToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildApplyAddToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildApplyAddToWorldMessage msg = (ReqInnerGuildApplyAddToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildApplyAddToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}