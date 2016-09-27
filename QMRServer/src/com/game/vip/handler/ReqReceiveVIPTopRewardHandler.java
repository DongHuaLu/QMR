package com.game.vip.handler;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.vip.manager.VipManager;
import com.game.vip.message.ReqReceiveVIPTopRewardMessage;
import com.game.command.Handler;

public class ReqReceiveVIPTopRewardHandler extends Handler{

	Logger log = Logger.getLogger(ReqReceiveVIPTopRewardHandler.class);
	//领取至尊VIP奖励
	public void action(){
		try{
			Player player = (Player)this.getParameter();
			ReqReceiveVIPTopRewardMessage msg = (ReqReceiveVIPTopRewardMessage)this.getMessage();
			VipManager.getInstance().receiveVipTopReward(player);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}