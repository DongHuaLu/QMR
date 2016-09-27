package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ReqInnerGuildChangeBulletinToWorldMessage;

public class ReqInnerGuildChangeBulletinToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildChangeBulletinToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildChangeBulletinToWorldMessage msg = (ReqInnerGuildChangeBulletinToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerGuildChangeBulletinToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}