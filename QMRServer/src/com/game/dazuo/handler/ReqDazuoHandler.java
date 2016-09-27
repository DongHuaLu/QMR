package com.game.dazuo.handler;

import org.apache.log4j.Logger;

import com.game.dazuo.manager.PlayerDaZuoManager;
import com.game.dazuo.message.ReqDazuoMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqDazuoHandler extends Handler{

	Logger log = Logger.getLogger(ReqDazuoHandler.class);

	public void action(){
		try{
//			ReqDazuoMessage msg = (ReqDazuoMessage)this.getMessage();
			PlayerDaZuoManager.getInstacne().startOrEndDaZuo((Player) getParameter());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}