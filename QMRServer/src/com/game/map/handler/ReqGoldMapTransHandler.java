package com.game.map.handler;

import org.apache.log4j.Logger;

import com.game.map.manager.MapManager;
import com.game.map.message.ReqGoldMapTransMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqGoldMapTransHandler extends Handler{

	Logger log = Logger.getLogger(ReqGoldMapTransHandler.class);

	public void action(){
		try{
			ReqGoldMapTransMessage msg = (ReqGoldMapTransMessage)this.getMessage();
			MapManager.getInstance().yuanBaoChangeMap((Player) getParameter(),msg.getMapId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}