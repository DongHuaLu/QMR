package com.game.server.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.guild.manager.GuildWorldManager;
import com.game.server.WorldServer;
import com.game.server.message.ReqRegisterWorldMessage;
import com.game.server.message.ResRegisterWorldMessage;
import com.game.toplist.manager.TopListManager;

public class ReqRegisterWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqRegisterWorldHandler.class);

	public void action(){
		try{
			ReqRegisterWorldMessage msg = (ReqRegisterWorldMessage)this.getMessage();
			//注册游戏服务器
			WorldServer.getInstance().registerGameServer(msg.getServerId(), msg.getSession());
			
			log.info("游戏服务器" + msg.getServerName() + "注册到" + WorldServer.getInstance().getServerName() + "成功！");
			
			//返回成功消息
			ResRegisterWorldMessage returnMsg = new ResRegisterWorldMessage();
			returnMsg.setServerId(WorldServer.getInstance().getServerId());
			returnMsg.setServerName(WorldServer.getInstance().getServerName());
			msg.getSession().write(returnMsg);
			
			TopListManager.getInstance().syncDiTuZoneTopToGame();
			GuildWorldManager.getInstance().syncFriendGuildToGame();
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}