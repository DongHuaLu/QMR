package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.team.message.ReqMapSearchMemberNameGameMessage;
import com.game.command.Handler;

public class ReqMapSearchMemberNameGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqMapSearchMemberNameGameHandler.class);

	public void action(){
		try{
			ReqMapSearchMemberNameGameMessage msg = (ReqMapSearchMemberNameGameMessage)this.getMessage();
			ManagerPool.teamManager.stReqMapSearchMemberNameGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}