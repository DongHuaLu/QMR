package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.friend.manager.FriendManager;
import com.game.friend.message.ReqRelationMoodToWorldMessage;

public class ReqRelationMoodToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqRelationMoodToWorldHandler.class);

	public void action(){
		try{
			ReqRelationMoodToWorldMessage msg = (ReqRelationMoodToWorldMessage)this.getMessage();
			FriendManager.getInstance().relationMoodInWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}