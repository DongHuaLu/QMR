package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.friend.message.ReqRelationAddToServerMessage;
import com.game.command.Handler;
import com.game.friend.manager.FriendManager;
import com.game.player.structs.Player;

public class ReqRelationAddToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqRelationAddToServerHandler.class);

	public void action(){
		try{
			ReqRelationAddToServerMessage msg = (ReqRelationAddToServerMessage)this.getMessage();
			FriendManager.getInstance().RelationAdd((Player)this.getParameter(), msg);
			if (msg.getBtListType() == 0) {
				log.error("添加好友关系类型为0:"+msg.toString());
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}