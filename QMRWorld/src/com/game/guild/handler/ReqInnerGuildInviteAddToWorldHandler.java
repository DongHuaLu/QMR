package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildInviteAddToWorldMessage;

public class ReqInnerGuildInviteAddToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildInviteAddToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildInviteAddToWorldMessage msg = (ReqInnerGuildInviteAddToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildInviteAddToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}