package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.friend.manager.FriendManager;
import com.game.friend.message.ReqRelationAddToWorldMessage;

public class ReqRelationAddToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqRelationAddToWorldHandler.class);

	public void action(){
		try{
			ReqRelationAddToWorldMessage msg = (ReqRelationAddToWorldMessage)this.getMessage();
			FriendManager.getInstance().relationAddInWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}