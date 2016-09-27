package scripts.shop;

import com.alibaba.fastjson.JSON;
import com.game.backpack.structs.Item;
import com.game.data.bean.Q_shopBean;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.shop.script.IShopBuyScript;
import com.game.utils.ServerParamUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * 物品商店购买脚本
 *
 * @author 杨鸿岚
 */
public class ShopBuyScript implements IShopBuyScript {

	@Override
	public int getId() {
		return ScriptEnum.SHOPBUY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void shopbuy(Player player, Q_shopBean shopBean, List<Item> buyItems, int num, int costType) {
		if (costType == 2) {
			HashMap<String, List<Integer>> shopbuyMap = null;
			if (ServerParamUtil.getNormalParamMap().containsKey(ServerParamUtil.SHOPBUY + "_" + WServer.getInstance().getServerId())) {
				String shopbuyString = ServerParamUtil.getNormalParamMap().get(ServerParamUtil.SHOPBUY + "_" + WServer.getInstance().getServerId());
				shopbuyMap = JSON.parseObject(shopbuyString, HashMap.class);
			}
			if (shopbuyMap != null) {
				Calendar calendar = Calendar.getInstance();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				if (shopbuyMap.containsKey(date)) {
					List<Integer> shopbuyList = shopbuyMap.get(date);
					if (shopbuyList != null && shopbuyList.contains(shopBean.getQ_sell())) {
						if (num != 0) {//坐骑9折包 坐骑8.5折包
							if (player.getVariables().containsKey(shopBean.getQ_sell() + "_" + date)) {
								int buynum = Integer.valueOf(player.getVariables().get(shopBean.getQ_sell() + "_" + date));
								buynum = buynum + num;
								player.getVariables().put(shopBean.getQ_sell() + "_" + date, Integer.toString(buynum));
							} else {
								player.getVariables().put(shopBean.getQ_sell() + "_" + date, Integer.toString(num));
							}
						}
					}
				}
			}
		}
	}
}
