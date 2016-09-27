package com.game.signwage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.signwage.message.ReqReceiveAwardsToClientMessage;
import com.game.command.Handler;

public class ReqReceiveAwardsToClientHandler extends Handler{

	Logger log = Logger.getLogger(ReqReceiveAwardsToClientHandler.class);

	public void action(){
		try{
			ReqReceiveAwardsToClientMessage msg = (ReqReceiveAwardsToClientMessage)this.getMessage();
			ManagerPool.signWageManager.receiveSignReward((Player)this.getParameter(),msg );
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}