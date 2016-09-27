package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqCreateateamGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqCreateateamGameHandler.class);

	public void action(){
		try{
			//ReqCreateateamGameMessage msg = (ReqCreateateamGameMessage)this.getMessage();
			ManagerPool.teamManager.stCreateateam((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}