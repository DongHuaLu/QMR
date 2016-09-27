package scripts.item;

import java.util.ArrayList;
import java.util.List;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.scripts.bean.PanelInfo;
import com.game.structs.Reasons;
import com.game.utils.NpcParamUtil;

/**
 * 变身卡
 */
public class ChristmasHat implements IItemScript {
	
	//对话面板ID
	private int panelId = 5;
	
	@Override
	public int getId() {
		return 1009147;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		//打开对话
		PanelInfo panel = NpcParamUtil.getPanelInfo(player, panelId);
		List<String> list = new ArrayList<String>();
		list.add("btn1#" + this.getId() + "#open1#"+item.getId());
		list.add("btn2#" + this.getId() + "#open2#"+item.getId());
		list.add("btn3#" + this.getId() + "#open3#"+item.getId());
		list.add("btn4#" + this.getId() + "#open4#"+item.getId());
		panel.setButtoninfolist(NpcParamUtil.getbuttonInfo(player , list));
		NpcParamUtil.showPanel(player, panel);
		//弹出兑换框
		return false;
	}
	
	//免费兑换
	public void open1(List<Object> para){
		Player player = (Player) para.get(0);
		if (player == null ) {
			return;
		}
		long itemId = Long.valueOf((String)para.get(1));
		Item item = ManagerPool.backpackManager.getItemById(player, itemId);
		if(item == null){
			return;
		}
		
		//物品模板ID，物品数量，性别，是否绑定（0或1)，过期时间，升级级数，附加条数
		//免费开启可获得：大真气丹*1、50礼金、50军功
		String itemstr = "30202,1,0,1;-5,50;-7,50";
		//创建奖励物品
		List<Item> items = new ArrayList<Item>();
		int num = ManagerPool.backpackManager.createItems(player, itemstr, items);
		//包裹空间是否足够
		if(ManagerPool.backpackManager.getEmptyGridNum(player) >= num){
			long id = Config.getId();
			ManagerPool.backpackManager.addItems(player, items, id);
			ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, id);
		}
		//关闭面板
		NpcParamUtil.setPanel(player, panelId, new ArrayList<String>(), 1);
	}
	
	//100元宝开启
	public void open2(List<Object> para){
		Player player = (Player) para.get(0);
		if (player == null ) {
			return;
		}
		long itemId = Long.valueOf((String)para.get(1));
		Item item = ManagerPool.backpackManager.getItemById(player, itemId);
		if(item == null){
			return;
		}
		
		int gold = 100;
		if(!ManagerPool.backpackManager.checkGold(player, gold)){
			return;
		}
		
		//物品模板ID，物品数量，性别，是否绑定（0或1)，过期时间，升级级数，附加条数
		//花费100元宝开启可获得：大真气丹*5、100礼金、200军功、战魂丹（200）
		String itemstr = "30202,5,0,1;-5,100;-7,200;8440,1,0,1";
		//创建奖励物品
		List<Item> items = new ArrayList<Item>();
		int num = ManagerPool.backpackManager.createItems(player, itemstr, items);
		//包裹空间是否足够
		if(ManagerPool.backpackManager.getEmptyGridNum(player) >= num){
			long id = Config.getId();
			ManagerPool.backpackManager.changeGold(player, -gold, Reasons.SHENGDAN, id);
			ManagerPool.backpackManager.addItems(player, items, id);
			ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, id);
		}
		//关闭面板
		NpcParamUtil.setPanel(player, panelId, new ArrayList<String>(), 1);
	}
	
	//200元宝开启
	public void open3(List<Object> para){
		Player player = (Player) para.get(0);
		if (player == null ) {
			return;
		}
		long itemId = Long.valueOf((String)para.get(1));
		Item item = ManagerPool.backpackManager.getItemById(player, itemId);
		if(item == null){
			return;
		}
		
		int gold = 200;
		if(!ManagerPool.backpackManager.checkGold(player, gold)){
			return;
		}
		
		//物品模板ID，物品数量，性别，是否绑定（0或1)，过期时间，升级级数，附加条数
		//花费200元宝开启可获得：大真气丹*15、300礼金、500军功、大战魂丹（500）
		String itemstr = "30202,15,0,1;-5,300;-7,500;8441,1,0,1";
		//创建奖励物品
		List<Item> items = new ArrayList<Item>();
		int num = ManagerPool.backpackManager.createItems(player, itemstr, items);
		//包裹空间是否足够
		if(ManagerPool.backpackManager.getEmptyGridNum(player) >= num){
			long id = Config.getId();
			ManagerPool.backpackManager.changeGold(player, -gold, Reasons.SHENGDAN, id);
			ManagerPool.backpackManager.addItems(player, items, id);
			ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, id);
		}
		//关闭面板
		NpcParamUtil.setPanel(player, panelId, new ArrayList<String>(), 1);
	}
	
	//500元宝开启
	public void open4(List<Object> para){
		Player player = (Player) para.get(0);
		if (player == null ) {
			return;
		}
		long itemId = Long.valueOf((String)para.get(1));
		Item item = ManagerPool.backpackManager.getItemById(player, itemId);
		if(item == null){
			return;
		}
		
		int gold = 500;
		if(!ManagerPool.backpackManager.checkGold(player, gold)){
			return;
		}
		
		//物品模板ID，物品数量，性别，是否绑定（0或1)，过期时间，升级级数，附加条数
		//花费500元宝开启可获得：大真气丹*50、800礼金、1500军功、宗师战魂丹（1500）
		String itemstr = "30202,50,0,1;-5,800;-7,1500;8442,1,0,1";
		//创建奖励物品
		List<Item> items = new ArrayList<Item>();
		int num = ManagerPool.backpackManager.createItems(player, itemstr, items);
		//包裹空间是否足够
		if(ManagerPool.backpackManager.getEmptyGridNum(player) >= num){
			long id = Config.getId();
			ManagerPool.backpackManager.changeGold(player, -gold, Reasons.SHENGDAN, id);
			ManagerPool.backpackManager.addItems(player, items, id);
			ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, id);
		}
		//关闭面板
		NpcParamUtil.setPanel(player, panelId, new ArrayList<String>(), 1);
	}
}
