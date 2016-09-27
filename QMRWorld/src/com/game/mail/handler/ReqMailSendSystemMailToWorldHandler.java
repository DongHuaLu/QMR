package com.game.mail.handler;

import org.apache.log4j.Logger;

import com.game.mail.message.ReqMailSendSystemMailToWorldMessage;
import com.game.command.Handler;
import com.game.mail.manager.MailWorldManager;

public class ReqMailSendSystemMailToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqMailSendSystemMailToWorldHandler.class);

	public void action(){
		try{
			ReqMailSendSystemMailToWorldMessage msg = (ReqMailSendSystemMailToWorldMessage)this.getMessage();
			MailWorldManager.getInstance().reqMailSendSystemMailToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}