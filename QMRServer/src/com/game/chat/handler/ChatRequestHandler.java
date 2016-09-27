package com.game.chat.handler;

import org.apache.log4j.Logger;

import com.game.chat.manager.ChatManager;
import com.game.chat.message.ChatRequestMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ChatRequestHandler extends Handler{

	Logger log = Logger.getLogger(ChatRequestHandler.class);

	public void action(){
		try{
			ChatRequestMessage msg = (ChatRequestMessage)this.getMessage();
			ChatManager.getInstance().chat((Player)this.getParameter(), msg.getRoleName(), msg.getChattype(), msg.getOther(), msg.getCondition());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}