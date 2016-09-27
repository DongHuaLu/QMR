package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.manager.GuildServerManager;
import com.game.guild.message.ResFriendGuildToGameMessage;
import com.game.command.Handler;

public class ResFriendGuildToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResFriendGuildToGameHandler.class);

	public void action(){
		try{
			ResFriendGuildToGameMessage msg = (ResFriendGuildToGameMessage)this.getMessage();
			GuildServerManager.getInstance().syncGuildFriend(msg.getFriend());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}