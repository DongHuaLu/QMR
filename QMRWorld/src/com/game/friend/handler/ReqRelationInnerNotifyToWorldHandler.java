package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.friend.manager.FriendManager;
import com.game.friend.message.ReqRelationInnerNotifyToWorldMessage;

public class ReqRelationInnerNotifyToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqRelationInnerNotifyToWorldHandler.class);

	public void action(){
		try{
			ReqRelationInnerNotifyToWorldMessage msg = (ReqRelationInnerNotifyToWorldMessage)this.getMessage();
			FriendManager.getInstance().relationInnerNotifyInWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}