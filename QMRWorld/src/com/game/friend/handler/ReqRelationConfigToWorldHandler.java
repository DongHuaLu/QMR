package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.friend.message.ReqRelationConfigToWorldMessage;
import com.game.command.Handler;
import com.game.friend.manager.FriendManager;

public class ReqRelationConfigToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqRelationConfigToWorldHandler.class);

	public void action(){
		try{
			ReqRelationConfigToWorldMessage msg = (ReqRelationConfigToWorldMessage)this.getMessage();
			FriendManager.getInstance().relationConfigInWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}