package com.game.publogin.handler;

import org.apache.log4j.Logger;

import com.game.publogin.message.ReqQuitForPublicMessage;
import com.game.command.Handler;

public class ReqQuitForPublicHandler extends Handler{

	Logger log = Logger.getLogger(ReqQuitForPublicHandler.class);

	public void action(){
		try{
			ReqQuitForPublicMessage msg = (ReqQuitForPublicMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}