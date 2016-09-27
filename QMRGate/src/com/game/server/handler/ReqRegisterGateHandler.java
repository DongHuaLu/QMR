package com.game.server.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.server.GateServer;
import com.game.server.message.ReqRegisterGateMessage;
import com.game.server.message.ResRegisterGateMessage;

public class ReqRegisterGateHandler extends Handler{

	Logger log = Logger.getLogger(ReqRegisterGateHandler.class);

	public void action(){
		try{
			ReqRegisterGateMessage msg = (ReqRegisterGateMessage)this.getMessage();
			//注册游戏服务器
			GateServer.getInstance().registerGameServer(msg.getServerId(), msg.getSession());
			log.info("游戏服务器" + msg.getServerName() + "注册到" + GateServer.getInstance().getServerName() + "成功！");
			
			//返回成功消息
			ResRegisterGateMessage returnMsg = new ResRegisterGateMessage();
			returnMsg.setServerId(GateServer.getInstance().getServerId());
			returnMsg.setServerName(GateServer.getInstance().getServerName());
			msg.getSession().write(returnMsg);
		}catch(ClassCastException e){
			log.error(e, e);
		}
	}
}