package com.game.mail.handler;

import org.apache.log4j.Logger;

import com.game.mail.message.ReqMailGetItemToServerMessage;
import com.game.command.Handler;
import com.game.mail.manager.MailServerManager;
import com.game.player.structs.Player;

public class ReqMailGetItemToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqMailGetItemToServerHandler.class);

	public void action(){
		try{
			ReqMailGetItemToServerMessage msg = (ReqMailGetItemToServerMessage)this.getMessage();
			MailServerManager.getInstance().reqMailGetItemToServer((Player)this.getParameter(), msg, true);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}