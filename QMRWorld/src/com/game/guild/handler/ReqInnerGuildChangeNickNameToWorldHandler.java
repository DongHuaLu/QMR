package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildChangeNickNameToWorldMessage;

public class ReqInnerGuildChangeNickNameToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildChangeNickNameToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildChangeNickNameToWorldMessage msg = (ReqInnerGuildChangeNickNameToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildChangeNickNameToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}