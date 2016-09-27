package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.horse.message.ResOthersHorseInfoMessage;
import com.game.manager.ManagerPool;
import com.game.player.message.ReqOtherPlayerInfoToGameMessage;
import com.game.player.message.ResOtherPlayerInfoMessage;
import com.game.player.structs.Player;
import com.game.utils.MessageUtil;

public class ReqOtherPlayerInfoToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqOtherPlayerInfoToGameHandler.class);

	public void action(){
		try{
			ReqOtherPlayerInfoToGameMessage msg = (ReqOtherPlayerInfoToGameMessage)this.getMessage();
			
			Player me = ManagerPool.playerManager.getOnLinePlayer(msg.getOtherPlayerId());
			if(me==null){
				//TODO 提示该玩家找不到
			}else{
				if (msg.getType() == 0) {	//查看他人信息
					ResOtherPlayerInfoMessage othermsg = new ResOtherPlayerInfoMessage();
					othermsg.setOtherPlayerInfo(ManagerPool.playerManager.getOtherPlayerInfo(me));
					othermsg.getRoleId().add(msg.getPlayerId());
					MessageUtil.send_to_gate(othermsg);
				}else if (msg.getType() == 1) {	//查看他人坐骑信息
					ResOthersHorseInfoMessage message = ManagerPool.horseManager.getOthersHorseInfo(me);
					message.getRoleId().add(msg.getPlayerId());
					MessageUtil.send_to_gate(message);
				}
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}