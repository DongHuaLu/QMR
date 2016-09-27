package com.game.mail.handler;

import org.apache.log4j.Logger;

import com.game.mail.message.ReqMailQueryUserToServerMessage;
import com.game.command.Handler;
import com.game.mail.manager.MailServerManager;
import com.game.player.structs.Player;

public class ReqMailQueryUserToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqMailQueryUserToServerHandler.class);

	public void action(){
		try{
			ReqMailQueryUserToServerMessage msg = (ReqMailQueryUserToServerMessage)this.getMessage();
			MailServerManager.getInstance().reqMailQueryUserToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}