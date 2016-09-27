package com.game.gift.handler;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.gift.bean.GiftInfo;
import com.game.gift.manager.GiftManager;
import com.game.gift.message.ReqPlatformGiftListMessage;
import com.game.gift.message.ResPlatformGiftListMessage;
import com.game.player.structs.Player;
import com.game.utils.MessageUtil;
import com.game.command.Handler;

public class ReqPlatformGiftListHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlatformGiftListHandler.class);

	public void action(){
		try{
			ReqPlatformGiftListMessage msg = (ReqPlatformGiftListMessage)this.getMessage();
			Player player = (Player)this.getParameter();
			String platform = msg.getPlatform();
			String tag = msg.getTag();
			List<GiftInfo> giftinfolist = GiftManager.getInstance().getPlatformGiftList(player, platform);
			
			ResPlatformGiftListMessage resmsg = new ResPlatformGiftListMessage();
			resmsg.setGifts(giftinfolist);
			resmsg.setTag(tag);
			MessageUtil.tell_player_message(player, resmsg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}