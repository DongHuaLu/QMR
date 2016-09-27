package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.manager.PlayerManager;
import com.game.player.message.ReqPlayerAvatarChangeMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqPlayerAvatarChangeHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlayerAvatarChangeHandler.class);

	public void action(){
		try{
			ReqPlayerAvatarChangeMessage msg = (ReqPlayerAvatarChangeMessage)this.getMessage();
			PlayerManager.getInstance().changeAvatar((Player)this.getParameter(), msg.getAvatarid());			
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}