package com.game.buff.handler;

import org.apache.log4j.Logger;

import com.game.buff.message.ReqRemoveBuffMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqRemoveBuffHandler extends Handler{

	Logger log = Logger.getLogger(ReqRemoveBuffHandler.class);

	public void action(){
		try{
			ReqRemoveBuffMessage msg = (ReqRemoveBuffMessage)this.getMessage();
			//移除Buff
			ManagerPool.buffManager.removeById((Player)this.getParameter(), msg.getBuffId());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}