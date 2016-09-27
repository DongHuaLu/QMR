package com.game.map.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.map.message.ReqNpcTransMessage;
import com.game.npc.manager.NpcManager;
import com.game.player.structs.Player;

public class ReqNpcTransHandler extends Handler{

	Logger log = Logger.getLogger(ReqNpcTransHandler.class);

	public void action(){
		try{
			ReqNpcTransMessage msg = (ReqNpcTransMessage)this.getMessage();
			NpcManager.npcTrans((Player) getParameter(), msg.getNpcId(),msg.getMapId(),msg.getPos());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}