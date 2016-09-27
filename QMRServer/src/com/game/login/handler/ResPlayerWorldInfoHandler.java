package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.login.message.ResPlayerWorldInfoMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ResPlayerWorldInfoHandler extends Handler{

	Logger log = Logger.getLogger(ResPlayerWorldInfoHandler.class);

	public void action(){
		try{
			ResPlayerWorldInfoMessage msg = (ResPlayerWorldInfoMessage)this.getMessage();
			//更新队伍和帮会信息
			Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerId());
			if(player!=null){
				player.setTeamid(msg.getTeamId());
				player.setGuildId(msg.getGuildId());
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}