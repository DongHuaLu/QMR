package com.game.chat.handler;

import org.apache.log4j.Logger;

import com.game.chat.manager.WorldChatManager;
import com.game.chat.message.ChatResponseSWMessage;
import com.game.command.Handler;

public class ChatResponseSWHandler extends Handler{

	Logger log = Logger.getLogger(ChatResponseSWHandler.class);

	public void action(){
		try{
			ChatResponseSWMessage msg = (ChatResponseSWMessage)this.getMessage();
			WorldChatManager.getInstance().chat(msg.getChater(),msg.getChatername(), msg.getChaterkinglv(), msg.getCountry(),msg.getReceivername(),msg.getChattype(),msg.getCondition(),msg.getOther(),msg.getIsprohibit()==1,msg.getIsgm()==1,msg.getSenderviptype(), msg.getWebvip(), msg.getReceiverviptype(), msg.getReceiverwebvip());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}