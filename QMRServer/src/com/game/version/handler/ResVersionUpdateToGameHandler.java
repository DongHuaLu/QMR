package com.game.version.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;

public class ResVersionUpdateToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResVersionUpdateToGameHandler.class);

	public void action(){
		try{
			//ResVersionUpdateToGameMessage msg = (ResVersionUpdateToGameMessage)this.getMessage();
			ManagerPool.versionManager.stResVersionUpdateToGameMessage();
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}