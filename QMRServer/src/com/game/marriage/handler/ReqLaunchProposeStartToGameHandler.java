package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqLaunchProposeStartToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqLaunchProposeStartToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqLaunchProposeStartToGameHandler.class);

	public void action(){
		try{
			ReqLaunchProposeStartToGameMessage msg = (ReqLaunchProposeStartToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqLaunchProposeStartToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}