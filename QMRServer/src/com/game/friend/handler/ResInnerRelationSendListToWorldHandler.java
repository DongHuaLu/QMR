package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.friend.message.ResInnerRelationSendListToWorldMessage;
import com.game.command.Handler;
import com.game.friend.manager.FriendManager;
import com.game.player.structs.Player;

public class ResInnerRelationSendListToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ResInnerRelationSendListToWorldHandler.class);

	public void action(){
		try{
			ResInnerRelationSendListToWorldMessage msg = (ResInnerRelationSendListToWorldMessage)this.getMessage();
			FriendManager.getInstance().relationInnerSendList((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}