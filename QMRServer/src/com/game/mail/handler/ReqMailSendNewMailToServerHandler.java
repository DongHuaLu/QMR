package com.game.mail.handler;

import org.apache.log4j.Logger;

import com.game.mail.message.ReqMailSendNewMailToServerMessage;
import com.game.command.Handler;
import com.game.mail.manager.MailServerManager;
import com.game.player.structs.Player;

public class ReqMailSendNewMailToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqMailSendNewMailToServerHandler.class);

	public void action(){
		try{
			ReqMailSendNewMailToServerMessage msg = (ReqMailSendNewMailToServerMessage)this.getMessage();
			MailServerManager.getInstance().reqMailSendNewMailToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}