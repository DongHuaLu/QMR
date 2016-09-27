package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqChangePKStateMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqChangePKStateHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangePKStateHandler.class);

	public void action(){
		try{
			ReqChangePKStateMessage msg = (ReqChangePKStateMessage)this.getMessage();
			//更改攻击模式
			ManagerPool.playerManager.changePkState((Player)getParameter(), msg.getPkState(), msg.getAuto());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}