package scripts.item;

import java.util.ArrayList;
import java.util.List;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.rank.structs.RankType;
import com.game.scripts.bean.PanelInfo;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;

public class Baifulibao implements IItemScript {

	@Override
	public int getId() {
		return 1009126;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
//		List<String> list = new ArrayList<String>();
//		list.add("btn1#1009126#shiyonglibao1#1");
//		list.add("btn2#1009126#shiyonglibao2#2");
//		list.add("btn3#1009126#shiyonglibao3#3");
//		list.add("btn4#1009126#shiyonglibao4#4");
		//NpcParamUtil.showPanel(player, 1, list);
		
		
		PanelInfo panel = NpcParamUtil.getPanelInfo(player, 1);
		List<String> list = new ArrayList<String>();
		list.add("btn1#1009126#shiyonglibao1#1");
		list.add("btn2#1009126#shiyonglibao2#2");
		list.add("btn3#1009126#shiyonglibao3#3");
		list.add("btn4#1009126#shiyonglibao4#4");
		panel.setButtoninfolist(NpcParamUtil.getbuttonInfo(player , list));
//		List<String> txtlist =new ArrayList<String>();
//		txtlist.add("ssss#0#替换内容");
//		NpcParamUtil.getPanelTxtInfo(txtlist);
		NpcParamUtil.showPanel(player , panel);
		
		
		return false;
	}

	public void shiyonglibao(List<Object> list) {
		Player player = (Player) list.get(0);
		Integer type = Integer.valueOf(list.get(1).toString());
		long id = Config.getId();
		if (player != null) {
			if(ManagerPool.backpackManager.getEmptyGridNum(player) < 2) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("包裹空格数量不足，至少需要2个空格"));
				return;
			}

			List<String> lista = new ArrayList<String>();
			NpcParamUtil.setPanel(player, 1, lista, 1);
//			免费开启可获得：大真气丹*1、50礼金、50军功、200战魂
//			花100元宝开启：大真气丹*5、100礼金、200军功、500战魂
//			花200元宝开启：大真气丹*15、300礼金、500军功、1500战魂
//			花500元宝开启：大真气丹*50、800礼金、1500军功、5000战魂
			String itemString = "";
			List<Item> items = new ArrayList<Item>();
			int dazhenqidan = 0;
			int lijin = 0;
			int jungong = 0;
			int zhanhun = 0;
			int costgold = 0;
			if (type == 1) {
				dazhenqidan = 1;
				List<Item> createItems = Item.createItems(30202, dazhenqidan, true, 0);
				if (!createItems.isEmpty()) {
					itemString = getItemString(itemString, 30202, dazhenqidan);
					items.addAll(createItems);
				}
				lijin = 50;
				itemString = getItemString(itemString, -5, lijin);
				jungong = 50;
				itemString = getItemString(itemString, -7, jungong);
				zhanhun = 1;
				List<Item> createItems1 = Item.createItems(8440, zhanhun, true, 0);
				if (!createItems1.isEmpty()) {
					itemString = getItemString(itemString, 8440, zhanhun);
					items.addAll(createItems1);
				}
				costgold = 0;
			} else if (type == 2) {
				dazhenqidan = 5;
				List<Item> createItems = Item.createItems(30202, dazhenqidan, true, 0);
				if (!createItems.isEmpty()) {
					itemString = getItemString(itemString, 30202, dazhenqidan);
					items.addAll(createItems);
				}
				lijin = 100;
				itemString = getItemString(itemString, -5, lijin);
				jungong = 200;
				itemString = getItemString(itemString, -7, jungong);
				zhanhun = 1;
				List<Item> createItems1 = Item.createItems(8441, zhanhun, true, 0);
				if (!createItems1.isEmpty()) {
					itemString = getItemString(itemString, 8441, zhanhun);
					items.addAll(createItems1);
				}
				costgold = 100;
			} else if (type == 3) {
				dazhenqidan = 15;
				List<Item> createItems = Item.createItems(30202, dazhenqidan, true, 0);
				if (!createItems.isEmpty()) {
					itemString = getItemString(itemString, 30202, dazhenqidan);
					items.addAll(createItems);
				}
				lijin = 300;
				itemString = getItemString(itemString, -5, lijin);
				jungong = 500;
				itemString = getItemString(itemString, -7, jungong);
				zhanhun = 1;
				List<Item> createItems1 = Item.createItems(8442, zhanhun, true, 0);
				if (!createItems1.isEmpty()) {
					itemString = getItemString(itemString, 8442, zhanhun);
					items.addAll(createItems1);
				}
				costgold = 200;
			} else if (type == 4) {
				dazhenqidan = 50;
				List<Item> createItems = Item.createItems(30202, dazhenqidan, true, 0);
				if (!createItems.isEmpty()) {
					itemString = getItemString(itemString, 30202, dazhenqidan);
					items.addAll(createItems);
				}
				lijin = 800;
				itemString = getItemString(itemString, -5, lijin);
				jungong = 1500;
				itemString = getItemString(itemString, -7, jungong);
				zhanhun = 1;
				List<Item> createItems1 = Item.createItems(8443, zhanhun, true, 0);
				if (!createItems1.isEmpty()) {
					itemString = getItemString(itemString, 8443, zhanhun);
					items.addAll(createItems1);
				}
				costgold = 500;
			}
			if (!BackpackManager.getInstance().checkGold(player, costgold)) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的元宝不足{1}，不能兑换百服礼包。"), "" + costgold);
				return;
			}
			
			
			if (BackpackManager.getInstance().changeGold(player, -costgold, Reasons.def10, id)) {
				if (BackpackManager.getInstance().removeItem(player, 9126, 1, Reasons.GOODUSE, id)) {
					if (!items.isEmpty()) {
						ManagerPool.backpackManager.addItems(player, items, Reasons.def10, id);
					}
					ManagerPool.backpackManager.changeBindGold(player, lijin, Reasons.def10, id);
					//ManagerPool.arrowManager.addFightSpiritNum(player, 1, jungong, true, ArrowReasonsType.OTHER);
					ManagerPool.rankManager.addranknum(player, jungong, RankType.OTHER);
					MessageUtil.notify_player(player, Notifys.SUCCESS, String.format(ResManager.getInstance().getString("您的百服礼包兑换成功，获得%s。"), itemString));
				}else{
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的百服礼包不存在。"));
				}
			}
		}
	}

	public void shiyonglibao1(List<Object> list) {
		shiyonglibao(list);
	}

	public void shiyonglibao2(List<Object> list) {
		shiyonglibao(list);
	}

	public void shiyonglibao3(List<Object> list) {
		shiyonglibao(list);
	}

	public void shiyonglibao4(List<Object> list) {
		shiyonglibao(list);
	}
	
	public String getItemString(String itemString, int itemid, int itemnum){
		Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(itemid);
		if (q_itemBean != null) {
			if (itemString.equalsIgnoreCase("")) {
				itemString = String.format("%s*%d", q_itemBean.getQ_name(),itemnum);
			}else{
				if (itemString.length() < 64) {
					itemString = itemString + String.format(",%s*%d", q_itemBean.getQ_name(),itemnum);
					if (itemString.length() >= 64) {
						itemString = itemString + ResManager.getInstance().getString("等物品");
					}
				}
			}
		}
		return itemString;
	}
}
