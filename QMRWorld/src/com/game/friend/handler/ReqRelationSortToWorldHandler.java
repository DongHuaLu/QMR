package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.friend.manager.FriendManager;
import com.game.friend.message.ReqRelationSortToWorldMessage;

public class ReqRelationSortToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqRelationSortToWorldHandler.class);

	public void action(){
		try{
			ReqRelationSortToWorldMessage msg = (ReqRelationSortToWorldMessage)this.getMessage();
			FriendManager.getInstance().relationSortInWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}