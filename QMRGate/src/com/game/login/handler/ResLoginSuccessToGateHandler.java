package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.login.message.ResLoginSuccessMessage;
import com.game.login.message.ResLoginSuccessToGateMessage;
import com.game.manager.ManagerPool;
import com.game.utils.MessageUtil;

public class ResLoginSuccessToGateHandler extends Handler{

	Logger log = Logger.getLogger(ResLoginSuccessToGateHandler.class);

	public void action(){
		try{
			ResLoginSuccessToGateMessage msg = (ResLoginSuccessToGateMessage)this.getMessage();
			//注册角色
			ManagerPool.playerManager.registerPlayer(msg.getServerId(), msg.getCreateServerId(), msg.getUserId(), msg.getPlayerId());
			
			//通知用户登录成功
			ResLoginSuccessMessage return_msg = new ResLoginSuccessMessage();
			return_msg.setMapId(msg.getMapId());
			MessageUtil.tell_player_message(msg.getPlayerId(), return_msg);
		}catch(ClassCastException e){
			log.error(e, e);
		}
	}
}