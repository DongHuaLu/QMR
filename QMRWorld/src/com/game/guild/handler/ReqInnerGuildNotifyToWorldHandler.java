package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ReqInnerGuildNotifyToWorldMessage;
import com.game.command.Handler;

public class ReqInnerGuildNotifyToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerGuildNotifyToWorldHandler.class);

	public void action(){
		try{
			ReqInnerGuildNotifyToWorldMessage msg = (ReqInnerGuildNotifyToWorldMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}