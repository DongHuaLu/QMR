package com.game.npc.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.npc.message.ReqServiceMessage;
import com.game.player.structs.Player;

public class ReqServiceHandler extends Handler{

	Logger log = Logger.getLogger(ReqServiceHandler.class);

	public void action(){
		try{
			ReqServiceMessage msg = (ReqServiceMessage)this.getMessage();
			//请求npc服务
			ManagerPool.npcManager.reqNpcService((Player)this.getParameter(), msg.getNpcId(), msg.getServiceParameter());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}