package com.game.recharge.handler;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.command.Handler;
import com.game.config.Config;
import com.game.player.structs.Player;
import com.game.recharge.message.RechargeMessage;
import com.game.structs.Reasons;

public class RechargeHandler extends Handler{
	//gameserver接到的充值信息
	Logger log = Logger.getLogger("RECHARGE");
	public void action() {
		RechargeMessage msg = (RechargeMessage) this.getMessage();
		Player player = (Player)this.getParameter();
		log.info(msg.getOid()+"\t"+msg.getRechargeParam());
		if(msg.getRechargeParam()==0){
			return;
		}
		if(player!=null){
			BackpackManager.getInstance().addGold(player, msg.getRechargeParam(), Reasons.GOLD_RECHARGE, Config.getId());
		}
	}
}