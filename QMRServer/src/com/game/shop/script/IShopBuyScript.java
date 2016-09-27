package com.game.shop.script;

import com.game.backpack.structs.Item;
import com.game.data.bean.Q_shopBean;
import com.game.player.structs.Player;
import com.game.script.IScript;
import java.util.List;

/**
 * 物品商店购买接口
 *
 * @author 杨鸿岚
 */
public interface IShopBuyScript extends IScript {

	void shopbuy(Player player, Q_shopBean shopBean, List<Item> buyItems,int num, int costType);
}
