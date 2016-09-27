package com.game.gift.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.gift.manager.GiftManager;
import com.game.gift.message.ReqGetPlatformGiftMessage;
import com.game.gift.message.ResGetPlatformGiftMessage;
import com.game.player.structs.Player;
import com.game.utils.MessageUtil;

public class ReqGetPlatformGiftHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetPlatformGiftHandler.class);

	public void action(){
		try{ //请求领取平台礼包
			ReqGetPlatformGiftMessage msg = (ReqGetPlatformGiftMessage)this.getMessage();
			Player player = (Player)this.getParameter();
			String platform = msg.getPlatform();
			int giftid = msg.getGiftid();
			int result = GiftManager.getInstance().getPlatformGift(player, platform, giftid);
			
			ResGetPlatformGiftMessage resmsg = new ResGetPlatformGiftMessage();
			resmsg.setGiftid(giftid);
			resmsg.setResult(result<0?0:result); 
			MessageUtil.tell_player_message(player, resmsg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}