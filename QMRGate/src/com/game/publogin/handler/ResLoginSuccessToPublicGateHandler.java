package com.game.publogin.handler;

import org.apache.log4j.Logger;

import com.game.login.message.ResLoginSuccessMessage;
import com.game.manager.ManagerPool;
import com.game.publogin.message.ResLoginSuccessToPublicGateMessage;
import com.game.utils.MessageUtil;
import com.game.command.Handler;

public class ResLoginSuccessToPublicGateHandler extends Handler{

	Logger log = Logger.getLogger(ResLoginSuccessToPublicGateHandler.class);

	public void action(){
		try{
			ResLoginSuccessToPublicGateMessage msg = (ResLoginSuccessToPublicGateMessage)this.getMessage();
			//注册角色
			ManagerPool.publicPlayerManager.registerPlayer(msg.getServerId(), msg.getWeb(), msg.getUserId(), msg.getPlayerId());
			
			//通知用户登录成功
			ResLoginSuccessMessage return_msg = new ResLoginSuccessMessage();
			return_msg.setMapId(msg.getMapId());
			MessageUtil.tell_player_message(msg.getPlayerId(), return_msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}