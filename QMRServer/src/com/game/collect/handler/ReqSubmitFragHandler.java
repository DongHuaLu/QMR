package com.game.collect.handler;

import org.apache.log4j.Logger;

import com.game.collect.message.ReqSubmitFragMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqSubmitFragHandler extends Handler{

	Logger log = Logger.getLogger(ReqSubmitFragHandler.class);

	public void action(){
		try{
			ReqSubmitFragMessage msg = (ReqSubmitFragMessage)this.getMessage();
			ManagerPool.collectManager.submitFrag((Player)getParameter(), msg.getItemmodel(), msg.getNum());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}