package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildGetMemberListToWorldMessage;

public class ReqInnerGuildGetMemberListToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildGetMemberListToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildGetMemberListToWorldMessage msg = (ReqInnerGuildGetMemberListToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildGetMemberListToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}