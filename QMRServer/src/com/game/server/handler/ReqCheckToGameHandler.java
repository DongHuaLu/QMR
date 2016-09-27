package com.game.server.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.script.PlayerCheckType;
import com.game.player.structs.Player;
import com.game.server.message.ReqCheckToGameMessage;

public class ReqCheckToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqCheckToGameHandler.class);

	public void action(){
		try{
			ReqCheckToGameMessage msg = (ReqCheckToGameMessage)this.getMessage();
			//心跳信息检测
			Player player = (Player)this.getParameter();
			if(player!=null){
				ManagerPool.playerManager.playercheck(player, PlayerCheckType.HEART, msg.getCheck(), msg.getPara());
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}