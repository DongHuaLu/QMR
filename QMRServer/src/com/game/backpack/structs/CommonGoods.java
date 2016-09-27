package com.game.backpack.structs;

import com.game.languageres.manager.ResManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.utils.MessageUtil;

/**
 * 普通物品
 * @author 赵聪慧
 *
 */
public class CommonGoods extends Item {

	private static final long serialVersionUID = -2579158237907732200L;

	@Override
	public void use(Player player, String... parameters) {
		MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("该物品不支持双击使用"));

	}

	@Override
	public void unuse(Player player, String... parameters) {
		
	}
}
