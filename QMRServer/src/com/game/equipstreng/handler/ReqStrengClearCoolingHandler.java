package com.game.equipstreng.handler;

import org.apache.log4j.Logger;

import com.game.equipstreng.message.ReqStrengClearCoolingMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqStrengClearCoolingHandler extends Handler{

	Logger log = Logger.getLogger(ReqStrengClearCoolingHandler.class);

	public void action(){
		try{
			ReqStrengClearCoolingMessage msg = (ReqStrengClearCoolingMessage)this.getMessage();
			ManagerPool.equipstrengManager.stReqStrengClearCoolingMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}