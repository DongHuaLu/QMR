package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.friend.manager.FriendManager;
import com.game.friend.message.ReqRelationInnerAddToWorldMessage;

public class ReqRelationInnerAddToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqRelationInnerAddToWorldHandler.class);

	public void action(){
		try{
			ReqRelationInnerAddToWorldMessage msg = (ReqRelationInnerAddToWorldMessage)this.getMessage();
			FriendManager.getInstance().relationInnerAddInWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}