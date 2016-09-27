package scripts.item;

import java.util.ArrayList;
import java.util.List;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.scripts.bean.PanelInfo;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;
import com.game.utils.Symbol;


//佳人礼包+100元宝 →战魂*1000，海心铁精*2，攻击属性百宝箱*20，江山礼包*1
//山河礼包+500元宝 →战魂*5000，骑兵祝福丹*1，装备进阶石*10，王城礼包*1
//王城礼包+2000元宝→10倍收益丹*2 战魂*2w，帝王礼包*1
//帝王礼包+10000元宝→帮会令牌*20  锻骨草*1


/**300服大礼包
 * 
 * @author zhangrong
 *
 */
public class SpreeBox_300Server implements IItemScript {

	@Override
	public int getId() {
		return 1009153;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		PanelInfo panel = null;
		List<String> list = new ArrayList<String>();
		int panelid= 0;
		if (item.getItemModelId() == 9153) {
			 panelid = 9;
			 panel = NpcParamUtil.getPanelInfo(player, panelid);
			 list.add("btn3#" + this.getId() + "#open1#"+item.getId()+","+panelid);
		}else if (item.getItemModelId() == 9154) {
			 panelid = 10;
			 panel = NpcParamUtil.getPanelInfo(player, panelid);
			 list.add("btn3#" + this.getId() + "#open2#"+item.getId()+","+panelid);
		}else if (item.getItemModelId() == 9155) {
			 panelid = 11;
			 panel = NpcParamUtil.getPanelInfo(player, panelid);
			 list.add("btn3#" + this.getId() + "#open3#"+item.getId()+","+panelid);
		}else if (item.getItemModelId() == 9156) {
			 panelid = 12;
			 panel = NpcParamUtil.getPanelInfo(player, panelid);
			 list.add("btn3#" + this.getId() + "#open4#"+item.getId()+","+panelid);
		}
		panel.setButtoninfolist(NpcParamUtil.getbuttonInfo(player , list));
		NpcParamUtil.showPanel(player, panel);
		return false;
	}

	
	
	
	
	
	//美人礼包+100元宝 →战魂*1000，海心铁精*2，攻击属性百宝箱*20，江山礼包*1
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
		int gold = 100;
		if(!ManagerPool.backpackManager.checkGold(player, gold)){
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "您的元宝不足{1}", gold+"");
			return;
		}
		int panelid = Integer.valueOf((String)para.get(2));
		//格式(物品模板ID，物品数量，性别，是否绑定（0或1)，过期时间，升级级数，附加条数；...)
		String itemstr = "8440,5,0,1;3019,2,0,1;16030,20,0,1;9154,1,0,1";
		//创建奖励物品
		List<Item> items = new ArrayList<Item>();
		int num = ManagerPool.backpackManager.createItems(player, itemstr, items);
		//包裹空间是否足够
		if(ManagerPool.backpackManager.getEmptyGridNum(player) >= num){
			long id = Config.getId();
			if (ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.def16, id) && ManagerPool.backpackManager.changeGold(player, -gold, Reasons.def16, id)) {
				ManagerPool.backpackManager.addItems(player, items, id);
				notifyitemname(player,itemstr);
			}
		}else {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "您的包裹空位不足{1}个，请先清理一下。", num+"");
		}
		//关闭面板
		NpcParamUtil.setPanel(player, panelid, new ArrayList<String>(), 1);
	}
	
	
	
	
	//江山礼包+500元宝 →战魂*5000，骑兵祝福丹*1，装备进阶石*10，王城礼包*1
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
		int gold = 500;
		if(!ManagerPool.backpackManager.checkGold(player, gold)){
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "您的元宝不足{1}", gold+"");
			return;
		}
		int panelid = Integer.valueOf((String)para.get(2));
		//格式(物品模板ID，物品数量，性别，是否绑定（0或1)，过期时间，升级级数，附加条数；...)
		String itemstr = "8441,10,0,1;9121,10,0,1;1004,10,0,1;9155,1,0,1";
		//创建奖励物品
		List<Item> items = new ArrayList<Item>();
		int num = ManagerPool.backpackManager.createItems(player, itemstr, items);
		//包裹空间是否足够
		if(ManagerPool.backpackManager.getEmptyGridNum(player) >= num){
			long id = Config.getId();
			if (ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.def16, id) && ManagerPool.backpackManager.changeGold(player, -gold, Reasons.def16, id)) {
				ManagerPool.backpackManager.addItems(player, items, id);
				notifyitemname(player,itemstr);
			}
		}else {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "您的包裹空位不足{1}个，请先清理一下。", num+"");
		}
		//关闭面板
		NpcParamUtil.setPanel(player, panelid, new ArrayList<String>(), 1);
	}
	
	
	
	
	//王城礼包+2000元宝→10倍收益丹*2 战魂*2w，帝王礼包*1
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
		int gold = 2000;
		if(!ManagerPool.backpackManager.checkGold(player, gold)){
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "您的元宝不足{1}", gold+"");
			return;
		}
		int panelid = Integer.valueOf((String)para.get(2));
		//格式(物品模板ID，物品数量，性别，是否绑定（0或1)，过期时间，升级级数，附加条数；...)
		String itemstr = "8443,4,0,1;1019,2,0,1;9156,1,0,1";
		//创建奖励物品
		List<Item> items = new ArrayList<Item>();
		int num = ManagerPool.backpackManager.createItems(player, itemstr, items);
		//包裹空间是否足够
		if(ManagerPool.backpackManager.getEmptyGridNum(player) >= num){
			long id = Config.getId();
			if (ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.def16, id) && ManagerPool.backpackManager.changeGold(player, -gold, Reasons.def16, id)) {
				ManagerPool.backpackManager.addItems(player, items, id);
				notifyitemname(player,itemstr);
			}
		}else {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "您的包裹空位不足{1}个，请先清理一下。", num+"");
		}
		//关闭面板
		NpcParamUtil.setPanel(player, panelid, new ArrayList<String>(), 1);
	}
	
	
	//帝王礼包+10000元宝→帮会令牌*20  锻骨草*1
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
		int gold = 10000;
		if(!ManagerPool.backpackManager.checkGold(player, gold)){
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "您的元宝不足{1}", gold+"");
			return;
		}
		int panelid = Integer.valueOf((String)para.get(2));
		//格式(物品模板ID，物品数量，性别，是否绑定（0或1)，过期时间，升级级数，附加条数；...)
		String itemstr = "3011,5,0,1;3012,5,0,1;3013,5,0,1;3014,5,0,1;9148,1,0,1";
		//创建奖励物品
		List<Item> items = new ArrayList<Item>();
		int num = ManagerPool.backpackManager.createItems(player, itemstr, items);
		//包裹空间是否足够
		if(ManagerPool.backpackManager.getEmptyGridNum(player) >= num){
			long id = Config.getId();
			if (ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.def16, id) && ManagerPool.backpackManager.changeGold(player, -gold, Reasons.def16, id)) {
				ManagerPool.backpackManager.addItems(player, items, id);
				notifyitemname(player,itemstr);
			}
		}else {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "您的包裹空位不足{1}个，请先清理一下。", num+"");
		}
		//关闭面板
		NpcParamUtil.setPanel(player, panelid, new ArrayList<String>(), 1);
	}
	
	
	/**玩家得到道具提示
	 * 
	 * @param player
	 * @param itemstr
	 */
	public void notifyitemname(Player player ,String itemstr){
		String[] itemstrs = itemstr.split(Symbol.FENHAO_REG);
		for (int i = 0; i < itemstrs.length; i++) {
			String[] itemdata = itemstrs[i].split(Symbol.DOUHAO_REG);
			if (itemdata.length >= 2) {
				Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(Integer.valueOf(itemdata[0]));
				if (model != null) {
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "获得：{1}（{2}）", model.getQ_name(),itemdata[1]);
				}
			}
		}
	}
	
	
	
	
}
