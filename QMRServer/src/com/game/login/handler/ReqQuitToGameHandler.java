package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.login.message.ReqQuitToGameMessage;
import com.game.login.message.ResRemoveCharacterToGateMessage;
import com.game.login.message.ResRemoveCharacterToWorldMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.utils.MessageUtil;

public class ReqQuitToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqQuitToGameHandler.class);

	public void action(){
		try{
			ReqQuitToGameMessage msg = (ReqQuitToGameMessage)this.getMessage();
			
			Player player = (Player)this.getParameter();
			if(player!=null){
				//退出游戏
				ManagerPool.playerManager.quiting((Player)this.getParameter(), msg.getForce());
			} else if(msg.getForce()==1) {
				ResRemoveCharacterToGateMessage gatemsg = new ResRemoveCharacterToGateMessage();
				gatemsg.setPlayerId(msg.getRoleId().get(0));
				MessageUtil.write(msg.getSession(), gatemsg);
				//msg.getSession().write(gatemsg);
	
				ResRemoveCharacterToWorldMessage worldmsg = new ResRemoveCharacterToWorldMessage();
				worldmsg.setPlayerId(msg.getRoleId().get(0));
				MessageUtil.send_to_world(worldmsg);
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}