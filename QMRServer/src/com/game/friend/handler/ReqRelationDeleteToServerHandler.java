package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.friend.message.ReqRelationDeleteToServerMessage;
import com.game.command.Handler;
import com.game.friend.manager.FriendManager;
import com.game.player.structs.Player;

public class ReqRelationDeleteToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqRelationDeleteToServerHandler.class);

	public void action(){
		try{
			ReqRelationDeleteToServerMessage msg = (ReqRelationDeleteToServerMessage)this.getMessage();
			FriendManager.getInstance().RelationDelete((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}