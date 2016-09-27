package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildQuitToWorldMessage;

public class ReqInnerGuildQuitToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildQuitToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildQuitToWorldMessage msg = (ReqInnerGuildQuitToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildQuitToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}