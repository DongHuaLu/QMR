package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.team.message.ReqAutoTeaminvitedGameMessage;
import com.game.command.Handler;

public class ReqAutoTeaminvitedGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqAutoTeaminvitedGameHandler.class);

	public void action(){
		try{
			ReqAutoTeaminvitedGameMessage msg = (ReqAutoTeaminvitedGameMessage)this.getMessage();

			ManagerPool.teamManager.AutoTeaminvited((Player)this.getParameter(), msg.getAutoTeaminvited());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}