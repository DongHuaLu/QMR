package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.login.message.ReqLoadFinishMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqLoadFinishHandler extends Handler{

	Logger log = Logger.getLogger(ReqLoadFinishHandler.class);

	public void action(){
		try{
			ReqLoadFinishMessage msg = (ReqLoadFinishMessage)this.getMessage();
			//登录完成
			ManagerPool.playerManager.login((Player)this.getParameter(), msg.getType(), msg.getWidth(), msg.getHeight());
		}catch(ClassCastException e){
			log.error(e, e);
		}
	}
}