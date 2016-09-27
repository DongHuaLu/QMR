package com.game.login.handler;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.game.login.message.ReqQuitMessage;
import com.game.manager.ManagerPool;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqQuitHandler extends Handler{

	Logger log = Logger.getLogger(ReqQuitHandler.class);

	public void action(){
		try{
			ReqQuitMessage msg = (ReqQuitMessage)this.getMessage();
			
			IoSession iosession = msg.getSession();
			
			if(iosession.containsAttribute(PlayerManager.PLAYER_ID)){
				long roleId = (Long)iosession.getAttribute(PlayerManager.PLAYER_ID);
				
				Player player = ManagerPool.playerManager.getPlayer(roleId);
				if(player==null){
					log.error("角色" + roleId + "未注册！");
					return;
				}
				
				ManagerPool.playerManager.quit(player, false);
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}