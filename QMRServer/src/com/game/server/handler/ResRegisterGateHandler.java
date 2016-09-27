package com.game.server.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.server.impl.WServer;
import com.game.server.message.ResRegisterGateMessage;

public class ResRegisterGateHandler extends Handler{

	Logger log = Logger.getLogger(ResRegisterGateHandler.class);

	public void action(){
		try{
			ResRegisterGateMessage msg = (ResRegisterGateMessage)this.getMessage();
			log.error("游戏服务器" + WServer.getInstance().getServerName() + "注册到" + msg.getServerName() + "返回成功！");
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}