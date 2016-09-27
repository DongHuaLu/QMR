package com.game.backpack.structs;

import com.game.languageres.manager.ResManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.utils.MessageUtil;

public class Jewel extends Item {

	private static final long serialVersionUID = 55918837386036527L;

	/**
	 * 镶嵌宝石
	 */
	@Override
	public void use(Player player, String... parameters) {
		// TODO 镶嵌宝石
		MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("该物品不支持双击使用"));

	}

	/**
	 * 摘除宝石
	 */
	@Override
	public void unuse(Player player, String... parameters) {
		
	}

}