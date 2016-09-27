package com.game.server.handler;

import org.apache.log4j.Logger;

import com.game.server.impl.WServer;
import com.game.server.message.ResRegisterWorldMessage;
import com.game.command.Handler;

public class ResRegisterWorldHandler extends Handler{

	Logger log = Logger.getLogger(ResRegisterWorldHandler.class);

	public void action(){
		try{
			ResRegisterWorldMessage msg = (ResRegisterWorldMessage)this.getMessage();
			log.info("游戏服务器" + WServer.getInstance().getServerName() + "注册到" + msg.getServerName() + "返回成功！");
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}