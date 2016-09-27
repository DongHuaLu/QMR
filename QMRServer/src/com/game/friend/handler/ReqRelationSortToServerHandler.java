package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.friend.message.ReqRelationSortToServerMessage;
import com.game.command.Handler;
import com.game.friend.manager.FriendManager;
import com.game.player.structs.Player;

public class ReqRelationSortToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqRelationSortToServerHandler.class);

	public void action(){
		try{
			ReqRelationSortToServerMessage msg = (ReqRelationSortToServerMessage)this.getMessage();
			FriendManager.getInstance().RelationSort((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}