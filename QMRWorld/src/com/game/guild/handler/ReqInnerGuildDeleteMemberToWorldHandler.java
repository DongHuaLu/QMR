package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildDeleteMemberToWorldMessage;

public class ReqInnerGuildDeleteMemberToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildDeleteMemberToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildDeleteMemberToWorldMessage msg = (ReqInnerGuildDeleteMemberToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildDeleteMemberToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}