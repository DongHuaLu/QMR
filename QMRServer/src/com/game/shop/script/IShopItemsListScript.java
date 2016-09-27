package com.game.shop.script;

import java.util.List;

import com.game.data.bean.Q_shopBean;
import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IShopItemsListScript extends IScript {

	public List<Q_shopBean> getShopList(Player player, List<Q_shopBean> list);
}
