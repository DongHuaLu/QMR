package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.login.message.ResLoginSuccessToWorldMessage;
import com.game.manager.ManagerPool;

public class ResLoginSuccessToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ResLoginSuccessToWorldHandler.class);

	public void action(){
		try{
			ResLoginSuccessToWorldMessage msg = (ResLoginSuccessToWorldMessage)this.getMessage();
			//注册角色
			ManagerPool.playerManager.registerPlayer(msg.getGateId(), msg.getServerId(), msg.getUserId(), msg.getPlayerId(), msg.getIsAdult(), msg.getLogintype(),msg.getCreateServerId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}