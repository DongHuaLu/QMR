package com.game.spirittree.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.spirittree.message.ResGuildAccExpToWorldMessage;
import com.game.command.Handler;

public class ResGuildAccExpToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ResGuildAccExpToWorldHandler.class);

	public void action(){
		try{
			ResGuildAccExpToWorldMessage msg = (ResGuildAccExpToWorldMessage)this.getMessage();
			ManagerPool.spiritTreeManager.stResGuildAccExpToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}