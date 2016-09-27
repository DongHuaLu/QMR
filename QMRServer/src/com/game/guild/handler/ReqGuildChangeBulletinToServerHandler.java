package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqGuildChangeBulletinToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.structs.Player;

public class ReqGuildChangeBulletinToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGuildChangeBulletinToServerHandler.class);

	public void action(){
		try{
			ReqGuildChangeBulletinToServerMessage msg = (ReqGuildChangeBulletinToServerMessage)this.getMessage();
			GuildServerManager.getInstance().reqGuildChangeBulletinToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}