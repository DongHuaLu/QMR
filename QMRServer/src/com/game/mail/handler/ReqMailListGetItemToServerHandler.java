package com.game.mail.handler;

import org.apache.log4j.Logger;

import com.game.mail.message.ReqMailListGetItemToServerMessage;
import com.game.command.Handler;
import com.game.mail.manager.MailServerManager;
import com.game.player.structs.Player;

public class ReqMailListGetItemToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqMailListGetItemToServerHandler.class);

	public void action(){
		try{
			ReqMailListGetItemToServerMessage msg = (ReqMailListGetItemToServerMessage)this.getMessage();
			MailServerManager.getInstance().reqMailListGetItemToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}