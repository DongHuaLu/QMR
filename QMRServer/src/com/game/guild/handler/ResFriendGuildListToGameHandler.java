package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.manager.GuildServerManager;
import com.game.guild.message.ResFriendGuildListToGameMessage;
import com.game.command.Handler;

public class ResFriendGuildListToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResFriendGuildListToGameHandler.class);

	public void action(){
		try{
			ResFriendGuildListToGameMessage msg = (ResFriendGuildListToGameMessage)this.getMessage();
			GuildServerManager.getInstance().syncGuildFriend(msg.getFriend());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}