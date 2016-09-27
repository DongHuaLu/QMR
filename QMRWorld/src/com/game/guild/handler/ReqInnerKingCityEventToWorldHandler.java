package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqInnerKingCityEventToWorldMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;

public class ReqInnerKingCityEventToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerKingCityEventToWorldHandler.class);

	public void action(){
		try{
			ReqInnerKingCityEventToWorldMessage msg = (ReqInnerKingCityEventToWorldMessage)this.getMessage();
			GuildWorldManager.getInstance().reqInnerKingCityEventToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}