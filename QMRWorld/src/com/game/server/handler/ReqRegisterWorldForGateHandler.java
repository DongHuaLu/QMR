package com.game.server.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.server.WorldServer;
import com.game.server.message.ReqRegisterWorldForGateMessage;
import com.game.server.message.ResRegisterWorldForGateMessage;

public class ReqRegisterWorldForGateHandler extends Handler{

	Logger log = Logger.getLogger(ReqRegisterWorldForGateHandler.class);

	public void action(){
		try{
			ReqRegisterWorldForGateMessage msg = (ReqRegisterWorldForGateMessage)this.getMessage();
			//注册网关服务器
			WorldServer.getInstance().registerGateServer(msg.getServerId(), msg.getSession());
			
			log.info("网关服务器" + msg.getServerName() + "注册到" + WorldServer.getInstance().getServerName() + "成功！");
			
			//返回成功消息
			ResRegisterWorldForGateMessage returnMsg = new ResRegisterWorldForGateMessage();
			returnMsg.setServerId(WorldServer.getInstance().getServerId());
			returnMsg.setServerName(WorldServer.getInstance().getServerName());
			msg.getSession().write(returnMsg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}