package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildAddMemberToWorldMessage;

public class ReqInnerGuildAddMemberToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildAddMemberToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildAddMemberToWorldMessage msg = (ReqInnerGuildAddMemberToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildAddMemberToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}