package com.game.horse.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqOpenSkillUpPanelHandler extends Handler{

	Logger log = Logger.getLogger(ReqOpenSkillUpPanelHandler.class);

	public void action(){
		try{
			//ReqOpenSkillUpPanelMessage msg = (ReqOpenSkillUpPanelMessage)this.getMessage();
			ManagerPool.horseManager.stReqOpenSkillUpPanelMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}