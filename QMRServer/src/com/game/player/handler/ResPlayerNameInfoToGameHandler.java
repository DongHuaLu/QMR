package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ResPlayerNameInfoToClientMessage;
import com.game.player.message.ResPlayerNameInfoToGameMessage;
import com.game.player.structs.Player;
import com.game.utils.MessageUtil;
import com.game.command.Handler;

public class ResPlayerNameInfoToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResPlayerNameInfoToGameHandler.class);

	public void action(){
		try{
			ResPlayerNameInfoToGameMessage msg = (ResPlayerNameInfoToGameMessage)this.getMessage();
			//更新改名信息
			Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerId());
			if(player!=null){
				player.setChangeName(msg.getChangeName());
				player.setChangeUser(msg.getChangeUser());
				ResPlayerNameInfoToClientMessage cmsg = new ResPlayerNameInfoToClientMessage();
				cmsg.setChangeName(msg.getChangeName());
				cmsg.setChangeUser(msg.getChangeUser());
				MessageUtil.tell_player_message(player, cmsg);
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}