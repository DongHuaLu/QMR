package com.game.publogin.handler;

import org.apache.log4j.Logger;

import com.game.publogin.message.ReqKickPlayerForPublicMessage;
import com.game.command.Handler;

public class ReqKickPlayerForPublicHandler extends Handler{

	Logger log = Logger.getLogger(ReqKickPlayerForPublicHandler.class);

	public void action(){
		try{
			ReqKickPlayerForPublicMessage msg = (ReqKickPlayerForPublicMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}