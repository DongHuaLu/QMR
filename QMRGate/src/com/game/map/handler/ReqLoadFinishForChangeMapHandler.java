package com.game.map.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.map.message.ReqLoadFinishForChangeMapMessage;
import com.game.map.message.ReqLoadFinishForChangeMapToGameMessage;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.server.GateServer;
import com.game.utils.MessageUtil;

public class ReqLoadFinishForChangeMapHandler extends Handler{

	Logger log = Logger.getLogger(ReqLoadFinishForChangeMapHandler.class);

	public void action(){
		try{
			ReqLoadFinishForChangeMapMessage msg = (ReqLoadFinishForChangeMapMessage)this.getMessage();

			ReqLoadFinishForChangeMapToGameMessage sendmsg = new ReqLoadFinishForChangeMapToGameMessage();
			sendmsg.setGateId(GateServer.getInstance().getServerId());
			
			
			Object roleId = msg.getSession().getAttribute(PlayerManager.PLAYER_ID);
			if(roleId==null){
				log.error("session未绑定角色！");
				return;
			}
			long playerId = (Long)roleId;
			sendmsg.getRoleId().add(playerId);
			
			int isAdult = 0;
			if(msg.getSession().containsAttribute(PlayerManager.IS_ADULT)){
				isAdult = (Integer)msg.getSession().getAttribute(PlayerManager.IS_ADULT);
			}
			
			sendmsg.setIsadult((byte)isAdult);
			sendmsg.setWidth(msg.getWidth());
			sendmsg.setHeight(msg.getHeight());
			
			Player player = ManagerPool.playerManager.getPlayer(playerId);
			if(player==null){
				player = ManagerPool.publicPlayerManager.getPlayer(playerId);
				if(player==null){
					log.error("角色" + playerId + "未注册！");
					return;
				}
			}
			
			int sessionId = (Integer)msg.getSession().getAttribute(PlayerManager.SESSION_ID);
			MessageUtil.send_to_game(player.getServer(), sessionId, sendmsg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}