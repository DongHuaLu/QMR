package com.game.friend.handler;

import org.apache.log4j.Logger;

import com.game.chat.manager.ChatManager;
import com.game.command.Handler;
import com.game.friend.message.ResInnerRelationAddNoticeMessage;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;

public class ResInnerRelationAddNoticeHandler extends Handler{

	Logger log = Logger.getLogger(ResInnerRelationAddNoticeHandler.class);

	public void action(){
		try{
			ResInnerRelationAddNoticeMessage msg = (ResInnerRelationAddNoticeMessage)this.getMessage();
			long playerId = msg.getPlayerId();
			Player player = PlayerManager.getInstance().getPlayer(playerId);
			if(msg.getBtListType()==4)
			ChatManager.getInstance().checkBlackList(player);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}