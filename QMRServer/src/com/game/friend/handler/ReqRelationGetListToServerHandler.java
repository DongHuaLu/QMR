package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.friend.message.ReqRelationGetListToServerMessage;
import com.game.command.Handler;
import com.game.friend.manager.FriendManager;
import com.game.player.structs.Player;

public class ReqRelationGetListToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqRelationGetListToServerHandler.class);

	public void action(){
		try{
			ReqRelationGetListToServerMessage msg = (ReqRelationGetListToServerMessage)this.getMessage();
			FriendManager.getInstance().RelationGetList((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}