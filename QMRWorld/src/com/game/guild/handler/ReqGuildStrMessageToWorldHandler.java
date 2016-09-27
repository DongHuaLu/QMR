package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildStrMessageToWorldMessage;
import com.game.manager.ManagerPool;
import com.game.command.Handler;

public class ReqGuildStrMessageToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildStrMessageToWorldHandler.class);

	public void action(){
		try{
			ReqGuildStrMessageToWorldMessage msg = (ReqGuildStrMessageToWorldMessage)this.getMessage();
			ManagerPool.guildWorldManager.notify_guild_all_player(msg.getGuildId(), msg.getContent());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}