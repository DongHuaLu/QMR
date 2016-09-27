package com.game.mail.handler;

import org.apache.log4j.Logger;

import com.game.mail.message.ReqMailQueryListToServerMessage;
import com.game.command.Handler;
import com.game.mail.manager.MailServerManager;
import com.game.player.structs.Player;

public class ReqMailQueryListToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqMailQueryListToServerHandler.class);

	public void action(){
		try{
			ReqMailQueryListToServerMessage msg = (ReqMailQueryListToServerMessage)this.getMessage();
			MailServerManager.getInstance().reqMailQueryListToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}