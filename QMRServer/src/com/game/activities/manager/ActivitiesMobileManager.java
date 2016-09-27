package com.game.activities.manager;

import java.util.ArrayList;
import java.util.List;

import com.game.activities.message.ResReceiveMobileGiftInfoMessage;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_phone_updateBean;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.utils.MessageUtil;

public class ActivitiesMobileManager {
	private static ActivitiesMobileManager me;
	private ActivitiesMobileManager() {
	}
	public static synchronized ActivitiesMobileManager getInstance() {
		if (me == null) {
			me = new ActivitiesMobileManager();
		}
		return me;
	}
	
	/**
	 * 领取版本更新礼包
	 * @param player
	 * @param index
	 */
	public void getVersionGift(Player player, int index) {
		if (index <= player.getMobileGiftIndex()) {
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "无法重复领取");
			return ;
		}
		for (int i = player.getMobileGiftIndex() + 1; i < index + 1; ++i) {
			Q_phone_updateBean bean = ManagerPool.dataManager.q_phone_updateContainer.getMap().get(i);
			if (bean == null) {
				continue;
			}
			
			player.setMobileGiftIndex(index);
			List<Item> itemmakes = new ArrayList<Item>();
			ManagerPool.backpackManager.createItems(player, bean.getQ_reward(), itemmakes);
			ManagerPool.backpackManager.addItems(player, itemmakes, Config.getId());
			for (int ii = 0; ii < itemmakes.size(); ++ii) {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "获得:{1}{2}", ManagerPool.backpackManager.getName(itemmakes.get(ii).getItemModelId()), itemmakes.get(ii).getNum() + "");
			}
			
			sendVersionGift(player);
		}
	}
	
	/**
	 * 发送给玩家已经领取的版本更新礼包信息
	 * @param player
	 */
	public void sendVersionGift(Player player) {
		ResReceiveMobileGiftInfoMessage msg = new ResReceiveMobileGiftInfoMessage();
		msg.setIndex(player.getMobileGiftIndex());
		MessageUtil.tell_player_message(player, msg);
	}
}
