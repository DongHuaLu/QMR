package com.game.vip.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.vip.manager.VipManager;
import com.game.vip.message.ReqReceiveVIPRewardMessage;
import com.game.command.Handler;

public class ReqReceiveVIPRewardHandler extends Handler{

	Logger log = Logger.getLogger(ReqReceiveVIPRewardHandler.class);

	public void action(){
		try{
			Player player = (Player)this.getParameter();
			ReqReceiveVIPRewardMessage msg = (ReqReceiveVIPRewardMessage)this.getMessage();
			int vipid = ManagerPool.vipManager.getPlayerVipId(player);
			if(vipid>0){
				if(VipManager.getInstance().canReceiveReward(player) &&
						VipManager.getInstance().receiveVIPReward(player)){  //领取成功  领取失败是否发送？
					VipManager.getInstance().sendVipInfoToClient(player); //发送VIP信息
				}
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}