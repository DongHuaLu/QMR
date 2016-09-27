package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.friend.manager.FriendManager;
import com.game.friend.message.ReqRelationGetListToWorldMessage;

public class ReqRelationGetListToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqRelationGetListToWorldHandler.class);

	public void action(){
		try{
			ReqRelationGetListToWorldMessage msg = (ReqRelationGetListToWorldMessage)this.getMessage();
			FriendManager.getInstance().relationGetListInWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}