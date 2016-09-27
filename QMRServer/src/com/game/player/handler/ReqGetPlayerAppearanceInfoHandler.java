package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqGetPlayerAppearanceInfoMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqGetPlayerAppearanceInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetPlayerAppearanceInfoHandler.class);

	public void action(){
		try{
			ReqGetPlayerAppearanceInfoMessage msg = (ReqGetPlayerAppearanceInfoMessage)this.getMessage();
			ManagerPool.playerManager.stReqGetPlayerAppearanceInfoMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}