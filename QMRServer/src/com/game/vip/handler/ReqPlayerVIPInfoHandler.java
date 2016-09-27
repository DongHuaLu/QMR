package com.game.vip.handler;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.utils.MessageUtil;
import com.game.vip.bean.VipInfo;
import com.game.vip.manager.VipManager;
import com.game.vip.message.ReqPlayerVIPInfoMessage;
import com.game.vip.message.ResPlayerVIPInfoMessage;
import com.game.command.Handler;

public class ReqPlayerVIPInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlayerVIPInfoHandler.class);

	public void action(){
		try{
			Player player = (Player)this.getParameter();
			ReqPlayerVIPInfoMessage msg = (ReqPlayerVIPInfoMessage)this.getMessage();
			VipInfo info = VipManager.getInstance().getPlayerVipInfo(player);
			ResPlayerVIPInfoMessage resmsg = new ResPlayerVIPInfoMessage();
			resmsg.setVipinfo(info);
			MessageUtil.tell_player_message(player, resmsg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}