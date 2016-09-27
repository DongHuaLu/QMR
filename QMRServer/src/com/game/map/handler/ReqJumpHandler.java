package com.game.map.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.map.message.ReqJumpMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqJumpHandler extends Handler{

	Logger log = Logger.getLogger(ReqJumpHandler.class);

	public void action(){
		try{
			ReqJumpMessage msg = (ReqJumpMessage)this.getMessage();
			//跳跃处理
			ManagerPool.mapManager.playerJump((Player)this.getParameter(), msg.getPositions(), msg.getType());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}