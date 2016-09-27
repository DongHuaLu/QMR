package com.game.horse.handler;

import org.apache.log4j.Logger;

import com.game.horse.message.ReqhorseStageUpPanelMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqhorseStageUpPanelHandler extends Handler{

	Logger log = Logger.getLogger(ReqhorseStageUpPanelHandler.class);

	public void action(){
		try{
			ReqhorseStageUpPanelMessage msg = (ReqhorseStageUpPanelMessage)this.getMessage();
			ManagerPool.horseManager.stReqhorseStageUpPanelMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}