package com.game.mail.handler;

import org.apache.log4j.Logger;

import com.game.mail.message.ReqMailReturnToServerMessage;
import com.game.command.Handler;
import com.game.mail.manager.MailServerManager;
import com.game.player.structs.Player;

public class ReqMailReturnToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqMailReturnToServerHandler.class);

	public void action(){
		try{
			ReqMailReturnToServerMessage msg = (ReqMailReturnToServerMessage)this.getMessage();
			MailServerManager.getInstance().reqMailReturnToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}