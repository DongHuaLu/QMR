package com.game.map.asyn;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.login.message.ResLoginSuccessToGateMessage;
import com.game.login.message.ResLoginSuccessToWorldMessage;
import com.game.manager.ManagerPool;
import com.game.map.message.ResChangeMapMessage;
import com.game.player.structs.Player;
import com.game.utils.MessageUtil;

public class ReqChangeServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangeServerHandler.class);

	public void action(){
		try{
			Player player = (Player)this.getParameter();
			/**
			 * 普通地图切换到公共地图 公共地图切换到普通地图 把本服务器缓存数据移除并保存
			 */
			ManagerPool.playerManager.updatePlayer(player);
			ManagerPool.playerManager.removePlayer(player);


			// 通知网关服务器玩家切换服务器
			ResLoginSuccessToGateMessage gate_msg = new ResLoginSuccessToGateMessage();
			gate_msg.setCreateServerId(player.getCreateServerId());
			gate_msg.setServerId(player.getServerId());
			gate_msg.setUserId(player.getUserId());
			gate_msg.setPlayerId(player.getId());
			MessageUtil.send_to_gate(player.getGateId(), player.getId(),
				gate_msg);

			// 通知世界服务器玩家切换服务器
			ResLoginSuccessToWorldMessage world_msg = new ResLoginSuccessToWorldMessage();
			world_msg.setGateId(player.getGateId());
			world_msg.setServerId(player.getServerId());
			world_msg.setUserId(player.getUserId());
			world_msg.setPlayerId(player.getId());
			world_msg.setIsAdult((byte) 1);
			world_msg.setLoginIp(player.getLoginIp());
			MessageUtil.send_to_world(world_msg);
			
			ResChangeMapMessage msg = new ResChangeMapMessage();
			msg.setMapId(player.getMapModelId());
			MessageUtil.tell_player_message(player, msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}
