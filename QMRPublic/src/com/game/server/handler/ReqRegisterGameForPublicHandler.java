package com.game.server.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.server.impl.PublicServer;
import com.game.server.message.ReqRegisterGameForPublicMessage;
import com.game.server.message.ResRegisterGameForPublicMessage;

public class ReqRegisterGameForPublicHandler extends Handler{

	Logger log = Logger.getLogger(ReqRegisterGameForPublicHandler.class);

	public void action(){
		try{
			ReqRegisterGameForPublicMessage msg = (ReqRegisterGameForPublicMessage)this.getMessage();
			//注册公共服务器
			PublicServer.getInstance().registerGame(msg.getSession(), msg.getServerId(), msg.getWebName(), msg.getVersion());
			
			log.error("平台" + msg.getWebName() + "游戏服务器" + msg.getServerId() + "(" + msg.getServerName() + ")" + "注册公共服务器成功");
			ResRegisterGameForPublicMessage msg2 = new ResRegisterGameForPublicMessage();
			msg2.setServerId(msg.getServerId());
			msg2.setServerName(msg.getServerName());
			msg2.setWebName(msg.getWebName());
			msg.getSession().write(msg2);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}