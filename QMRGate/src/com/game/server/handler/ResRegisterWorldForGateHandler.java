package com.game.server.handler;

import org.apache.log4j.Logger;

import com.game.server.GateServer;
import com.game.server.message.ResRegisterWorldForGateMessage;
import com.game.command.Handler;

public class ResRegisterWorldForGateHandler extends Handler{

	Logger log = Logger.getLogger(ResRegisterWorldForGateHandler.class);

	public void action(){
		try{
			ResRegisterWorldForGateMessage msg = (ResRegisterWorldForGateMessage)this.getMessage();
			log.info("网关服务器" + GateServer.getInstance().getServerName() + "注册到" + msg.getServerName() + "返回成功！");
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}