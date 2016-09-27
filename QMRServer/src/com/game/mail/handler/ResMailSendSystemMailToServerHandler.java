package com.game.mail.handler;

import org.apache.log4j.Logger;

import com.game.mail.message.ResMailSendSystemMailToServerMessage;
import com.game.command.Handler;
import com.game.mail.manager.MailServerManager;
import com.game.player.structs.Player;

public class ResMailSendSystemMailToServerHandler extends Handler{

	Logger log = Logger.getLogger(ResMailSendSystemMailToServerHandler.class);

	public void action(){
		try{
			ResMailSendSystemMailToServerMessage msg = (ResMailSendSystemMailToServerMessage)this.getMessage();
			MailServerManager.getInstance().resMailSendSystemMailToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}