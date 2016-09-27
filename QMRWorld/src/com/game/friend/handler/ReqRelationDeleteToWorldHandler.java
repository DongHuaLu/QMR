package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.friend.manager.FriendManager;
import com.game.friend.message.ReqRelationDeleteToWorldMessage;

public class ReqRelationDeleteToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqRelationDeleteToWorldHandler.class);

	public void action(){
		try{
			ReqRelationDeleteToWorldMessage msg = (ReqRelationDeleteToWorldMessage)this.getMessage();
			FriendManager.getInstance().relationDeleteInWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}