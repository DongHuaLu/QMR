package com.game.gem.handler;

import org.apache.log4j.Logger;

import com.game.gem.message.ReqOpenGemPanelMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqOpenGemPanelHandler extends Handler{

	Logger log = Logger.getLogger(ReqOpenGemPanelHandler.class);

	public void action(){
		try{
			ReqOpenGemPanelMessage msg = (ReqOpenGemPanelMessage)this.getMessage();
			ManagerPool.gemManager.stReqOpenGemPanelMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}