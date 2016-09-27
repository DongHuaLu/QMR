package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.friend.message.ReqRelationMoodToServerMessage;
import com.game.command.Handler;
import com.game.friend.manager.FriendManager;
import com.game.player.structs.Player;

public class ReqRelationMoodToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqRelationMoodToServerHandler.class);

	public void action(){
		try{
			ReqRelationMoodToServerMessage msg = (ReqRelationMoodToServerMessage)this.getMessage();
			FriendManager.getInstance().RelationMood((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}