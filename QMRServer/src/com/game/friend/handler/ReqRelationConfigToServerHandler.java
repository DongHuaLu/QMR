package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.friend.message.ReqRelationConfigToServerMessage;
import com.game.command.Handler;
import com.game.friend.manager.FriendManager;
import com.game.player.structs.Player;

public class ReqRelationConfigToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqRelationConfigToServerHandler.class);

	public void action(){
		try{
			ReqRelationConfigToServerMessage msg = (ReqRelationConfigToServerMessage)this.getMessage();
			FriendManager.getInstance().RelationConfig((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}