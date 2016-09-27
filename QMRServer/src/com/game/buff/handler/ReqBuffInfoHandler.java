package com.game.buff.handler;

import org.apache.log4j.Logger;

import com.game.buff.message.ReqBuffInfoMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqBuffInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqBuffInfoHandler.class);

	public void action(){
		try{
			ReqBuffInfoMessage msg = (ReqBuffInfoMessage)this.getMessage();
			//查看buff数值
			ManagerPool.buffManager.sendBuffInfoMessage((Player)this.getParameter(), msg.getBuffId());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}