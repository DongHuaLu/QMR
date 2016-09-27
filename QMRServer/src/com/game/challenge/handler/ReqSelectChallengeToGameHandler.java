package com.game.challenge.handler;

import org.apache.log4j.Logger;

import com.game.challenge.message.ReqSelectChallengeToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqSelectChallengeToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqSelectChallengeToGameHandler.class);

	public void action(){
		try{
			ReqSelectChallengeToGameMessage msg = (ReqSelectChallengeToGameMessage)this.getMessage();
			ManagerPool.challengeManager.stReqSelectChallengeToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}