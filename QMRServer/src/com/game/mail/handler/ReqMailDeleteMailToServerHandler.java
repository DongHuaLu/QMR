package com.game.mail.handler;

import org.apache.log4j.Logger;

import com.game.mail.message.ReqMailDeleteMailToServerMessage;
import com.game.command.Handler;
import com.game.mail.manager.MailServerManager;
import com.game.player.structs.Player;

public class ReqMailDeleteMailToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqMailDeleteMailToServerHandler.class);

	public void action(){
		try{
			ReqMailDeleteMailToServerMessage msg = (ReqMailDeleteMailToServerMessage)this.getMessage();
			MailServerManager.getInstance().reqMailDeleteMailToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}