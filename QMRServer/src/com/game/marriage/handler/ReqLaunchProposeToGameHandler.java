package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqLaunchProposeToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqLaunchProposeToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqLaunchProposeToGameHandler.class);

	public void action(){
		try{
			ReqLaunchProposeToGameMessage msg = (ReqLaunchProposeToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqLaunchProposeToGameMessage((Player)this.getParameter(),msg.getSuitorobjid());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}