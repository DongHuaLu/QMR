package com.game.mail.handler;

import org.apache.log4j.Logger;

import com.game.mail.message.ReqMailQueryDetailToServerMessage;
import com.game.command.Handler;
import com.game.mail.manager.MailServerManager;
import com.game.player.structs.Player;

public class ReqMailQueryDetailToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqMailQueryDetailToServerHandler.class);

	public void action(){
		try{
			ReqMailQueryDetailToServerMessage msg = (ReqMailQueryDetailToServerMessage)this.getMessage();
			MailServerManager.getInstance().reqMailQueryDetailToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}