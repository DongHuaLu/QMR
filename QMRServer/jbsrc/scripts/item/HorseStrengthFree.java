package scripts.item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Attribute;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemTypeConst;
import com.game.config.Config;
import com.game.data.bean.Q_equip_appendBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.scripts.bean.ButtonInfo;
import com.game.scripts.bean.PanelInfo;
import com.game.structs.Attributes;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;
import com.game.utils.RandomUtils;

/*
 * 坐骑加点自定义
 */
public class HorseStrengthFree implements IItemScript {
	
	private int panelId = 13;

	@Override
	public int getId() {
		return 1009157;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		PanelInfo panel = NpcParamUtil.getPanelInfo(player, panelId);
		List<ButtonInfo> buttonInfos = new ArrayList<ButtonInfo>();
		ButtonInfo button = new ButtonInfo();
		button.setName("btnOk");
		button.setScriptId(Integer.valueOf(this.getId()));
		button.setMethod("open");
		button.getParas().add(String.valueOf(item.getId()));
		button.getParas().add("@");
		button.getParas().add("ListSel1");
		button.getParas().add("ListSel2");
		button.getParas().add("ListSel3");
		button.getParas().add("ListSel4");
		button.getParas().add("ListSel5");
		button.getParas().add("ListSel6");
		button.getParas().add("ListSel7");
		buttonInfos.add(button);
		player.getPanelverify().put(this.getId()+"_open", "@");
		panel.setButtoninfolist(buttonInfos);
		NpcParamUtil.showPanel(player, panel);
		return false;
	}
	
	public void open(List<Object> para){
		if (para.size() != 10) return ;
		
		Player player = (Player) para.get(0);
		if (player == null) return ;
		
		long itemId = Long.valueOf((String)para.get(1));
		Item item = ManagerPool.backpackManager.getItemById(player, itemId);
		if(item == null) return ;
		
		//包裹空间是否足够
		if(ManagerPool.backpackManager.getEmptyGridNum(player) < 1) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "背包空间不足");
			return ;
		}
		
		if (para.get(9) == null) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "请选择坐骑装备位置");
			return ;
		}
		int itemModel = getItemModel(player, (String) para.get(9));
		List<Item> items = Item.createItems(itemModel, 1, true, 0);
		if (items == null || items.size() != 1) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "不能生成道具,未知的道具[" + itemModel + "]");
			return ;
		}
		Equip equip = (Equip) items.get(0);
		equip.setGradeNum(6);
		
		Q_equip_appendBean appendModel = DataManager.getInstance().q_equip_appendContainer.getMap().get(equip.getItemModelId());
		if(appendModel==null) return ;
		
		for (int i = 3; i < 9; ++i) {
			Attribute attribute=new Attribute();
			if (para.get(i) == null) {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, "请选择所有的生成项");
				return ;
			}
			attribute.setType(Integer.valueOf((String) para.get(i)).intValue());
			int value = generateAttribute(attribute.getType(), appendModel);
			if (value < 1) {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, "请选择所有的生成项");
				return ;
			}
			attribute.setValue(value);
			equip.getAttributes().add(attribute);
		}
		
		if (ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, Config.getId())) {
			BackpackManager.getInstance().addItem(player, equip, Reasons.SHENGDAN, Config.getId());
		}
		NpcParamUtil.setPanel(player, panelId, new ArrayList<String>(), 1);
	}

	private int getItemModel(Player player, String kind) {
		List<Q_itemBean> itemBeans = new ArrayList<Q_itemBean>();
		
		int equipLevel = player.getLevel() / 20;
		if (equipLevel < 1) {
			equipLevel = 1;
		}
		else {
			equipLevel = equipLevel * 20;
		}
		Iterator<Q_itemBean> i = DataManager.getInstance().q_itemContainer.getList().iterator();
		while (i.hasNext()) {
			Q_itemBean item = i.next();
			if (item.getQ_type() != ItemTypeConst.HORSEEQUIP) continue;
			if (item.getQ_kind() != Integer.parseInt(kind)) continue;
			if (item.getQ_level() != equipLevel) continue;
			itemBeans.add(item);
		}
		
		if (itemBeans.size() > 0) {
			int index = RandomUtils.random(itemBeans.size());
			Q_itemBean itemBean = itemBeans.get(index);
			return itemBean.getQ_id();
		}
		return 0;
	}
	

	public int generateAttribute(int type, Q_equip_appendBean appendModel) {
		if (type == Attributes.ATTACK.getValue())
			return RandomUtils.random(appendModel.getQ_attack_min(),appendModel.getQ_attack_max());
		if (type == Attributes.ATTACKSPEED.getValue())
			return RandomUtils.random(appendModel.getQ_attackspeed_min(),appendModel.getQ_attackspeed_max());
		if (type == Attributes.DEFENSE.getValue())
			return RandomUtils.random(appendModel.getQ_defence_min(),appendModel.getQ_defence_max());
		if (type == Attributes.CRIT.getValue())
			return RandomUtils.random(appendModel.getQ_crit_min(),appendModel.getQ_crit_max());
		if (type == Attributes.DODGE.getValue())
			return RandomUtils.random(appendModel.getQ_dodge_min(),appendModel.getQ_dodge_max());
		if (type == Attributes.SPEED.getValue())
			return RandomUtils.random(appendModel.getQ_speed_min(),appendModel.getQ_speed_max());
		if (type == Attributes.MAXHP.getValue())
			return RandomUtils.random(appendModel.getQ_hp_min(),appendModel.getQ_hp_max());
		if (type == Attributes.MAXMP.getValue())
			return RandomUtils.random(appendModel.getQ_mp_min(),appendModel.getQ_mp_max());
		if (type == Attributes.MAXSP.getValue())
			return RandomUtils.random(appendModel.getQ_sp_min(),appendModel.getQ_sp_max());
		if (type == Attributes.LUCK.getValue())
			return RandomUtils.random(appendModel.getQ_luck_min(),appendModel.getQ_luck_max());
		return 0;
	}
}
