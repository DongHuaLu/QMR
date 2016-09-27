package com.game.npc.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.npc.message.ReqNpcServicesMessage;
import com.game.player.structs.Player;

public class ReqNpcServicesHandler extends Handler{

	Logger log = Logger.getLogger(ReqNpcServicesHandler.class);

	public void action(){
		try{
			ReqNpcServicesMessage msg = (ReqNpcServicesMessage)this.getMessage();
			//请求npc服务列表
			ManagerPool.npcManager.getNpcServices((Player)this.getParameter(), msg.getNpcId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}