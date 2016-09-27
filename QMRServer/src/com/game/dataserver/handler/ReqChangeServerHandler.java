package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.dataserver.manager.DataServerManager;
import com.game.languageres.manager.ResManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.utils.MessageUtil;

public class ReqChangeServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangeServerHandler.class);

	public void action(){
		try{
//			ReqChangeServerMessage msg = (ReqChangeServerMessage)this.getMessage();
			Player player = (Player)this.getParameter();
			//锁定包裹
			player.getGlobalBag().setLocked(true);
			//跨服消息
			boolean result = DataServerManager.getInstance().reqSyncPlayerToDataServer(player);
			if(!result){
				//提示未与跨服服务器连接
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("与公共服务器连接出错！"));
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}