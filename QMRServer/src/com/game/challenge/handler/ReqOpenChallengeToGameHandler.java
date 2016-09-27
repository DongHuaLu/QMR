package com.game.challenge.handler;

import org.apache.log4j.Logger;

import com.game.challenge.message.ReqOpenChallengeToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqOpenChallengeToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqOpenChallengeToGameHandler.class);

	public void action(){
		try{
			ReqOpenChallengeToGameMessage msg = (ReqOpenChallengeToGameMessage)this.getMessage();
			ManagerPool.challengeManager.stReqOpenChallengeToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}