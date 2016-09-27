package com.game.publogin.handler;

import org.apache.log4j.Logger;

import com.game.publogin.message.ReqQuitToPublicGameMessage;
import com.game.command.Handler;

public class ReqQuitToPublicGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqQuitToPublicGameHandler.class);

	public void action(){
		try{
			ReqQuitToPublicGameMessage msg = (ReqQuitToPublicGameMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}