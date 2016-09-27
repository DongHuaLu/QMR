package com.game.signwage.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.signwage.message.ReqWageERNIEToClientMessage;

public class ReqWageERNIEToClientHandler extends Handler{

	Logger log = Logger.getLogger(ReqWageERNIEToClientHandler.class);

	public void action(){
		try{
			ReqWageERNIEToClientMessage msg = (ReqWageERNIEToClientMessage)this.getMessage();
			ManagerPool.signWageManager.onlineERNIE((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}