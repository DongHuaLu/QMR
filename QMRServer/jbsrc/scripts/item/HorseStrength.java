package scripts.item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.HorseEquip;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.horse.manager.HorseManager;
import com.game.horse.struts.Horse;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.scripts.bean.ButtonInfo;
import com.game.scripts.bean.PanelInfo;
import com.game.scripts.bean.PanelItemInfo;
import com.game.scripts.bean.PanelTxtInfo;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;

/**
 * 坐骑装备强化
 */
public class HorseStrength implements IItemScript {
	
	private int panelId = 15;

	@Override
	public int getId() {
		return 1009168;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		showPanel(player, -1);
		return false;
	}
	
	private void showPanel(Player player, int index) {
		PanelInfo panel = NpcParamUtil.getPanelInfo(player, panelId);
		List<PanelItemInfo> itemInfo = new ArrayList<PanelItemInfo>();
		Horse horse = HorseManager.getInstance().getHorse(player);
		String name = null;
		if (horse != null) {
			if (horse.getHorseequips()[0] != null) {
				itemInfo.add(createItem("GridAnJu", horse.getHorseequips()[0]));
			}
			if (horse.getHorseequips()[1] != null) {
				itemInfo.add(createItem("GridDengju", horse.getHorseequips()[1]));
			}
			if (horse.getHorseequips()[2] != null) {
				itemInfo.add(createItem("GridShengju", horse.getHorseequips()[2]));
			}
			if (horse.getHorseequips()[3] != null) {
				itemInfo.add(createItem("GridTiju", horse.getHorseequips()[3]));
			}
			if (index > -1) {
				HorseEquip equip = horse.getHorseequips()[index];
				if (equip.getGradeNum() > 9) {
					MessageUtil.notify_player(player, Notifys.MOUSEPOS, "已达最高等级,无法强化");
				}
				else if (equip != null && equip.getGradeNum() < 10) {
					itemInfo.add(createItem("danluGrid", equip));
					name = BackpackManager.getInstance().getName(equip.getItemModelId());
					if (name != null) name = name + "+" + equip.getGradeNum();
				}
			}
		}
		panel.setPaneliteminfolist(itemInfo);
		
		List<ButtonInfo> buttonInfos = new ArrayList<ButtonInfo>();
		buttonInfos.add(createButton("btnBeggin", "open", String.valueOf(getItem(player))));
		buttonInfos.add(createButton("sbtnAnJu", "show", String.valueOf(getItem(player))));
		buttonInfos.add(createButton("sbtnDengJu", "show", String.valueOf(getItem(player))));
		buttonInfos.add(createButton("sbtnShengJu", "show", String.valueOf(getItem(player))));
		buttonInfos.add(createButton("sbtnTiJu", "show", String.valueOf(getItem(player))));
		panel.setButtoninfolist(buttonInfos);
		
		PanelTxtInfo txtInfo = new PanelTxtInfo();
		txtInfo.setType((byte) 0);
		txtInfo.setName("labChooseNameItem");
		if (name != null) txtInfo.setContent(name);
		panel.getPaneltxtinfolist().add(txtInfo);
		
		NpcParamUtil.showPanel(player, panel);
		
		player.getPanelverify().put(this.getId()+"_open", "@");
		player.getPanelverify().put(this.getId()+"_show", "@");
	}

	private PanelItemInfo createItem(String name, HorseEquip horseEquip) {
		if (horseEquip == null) return null;
		PanelItemInfo item = new PanelItemInfo();
		item.setName(name);
		item.setIteminfo(horseEquip.buildItemInfo());
		return item;
	}

	private ButtonInfo createButton(String btn, String fun, String item) {
		ButtonInfo button = new ButtonInfo();
		button.setName(btn);
		button.setScriptId(Integer.valueOf(this.getId()));
		button.setMethod(fun);
		button.getParas().add(item);
		button.getParas().add("@");
		button.getParas().add("sbtnAnJu");
		button.getParas().add("sbtnDengJu");
		button.getParas().add("sbtnShengJu");
		button.getParas().add("sbtnTiJu");
		button.getParas().add("danluGrid");
		return button;
	}

	private long getItem(Player player) {
		Iterator<Item> it = player.getBackpackItems().values().iterator();
		while (it.hasNext()) {
			Item temp = it.next();
			if (temp.getItemModelId() == 9168) {
				return temp.getId();
			}
		}
		return 0;
	}

	public void open(List<Object> para){
		if (para.size() != 8) return ;
		
		Player player = (Player) para.get(0);
		if (player == null) return ;
		
		String itemId = (String)para.get(1);
		if (itemId == null) {
			return ;
		}
		if (Long.parseLong(itemId) == 0) {
			itemId = String.valueOf(getItem(player));
		}
		Item item = ManagerPool.backpackManager.getItemById(player, Long.parseLong(itemId));
		
		if(item == null || item.getNum() < 1) {
			String name = BackpackManager.getInstance().getName(9168);
			if (name != null) MessageUtil.notify_player(player, Notifys.MOUSEPOS, name +"不足,无法强化");
			return ;
		}
		
		String horseEquipItem = (String)para.get(7);
		if (horseEquipItem == null) {
			return ;
		}
		
		Horse horse = HorseManager.getInstance().getHorse(player);
		if (horse == null) {
			return ;
		}
		
		HorseEquip horseEquip = null;
		int pos = -1;
		for (int i = 0; i < 4; ++i) {
			if (horse.getHorseequips()[i] != null && horse.getHorseequips()[i].getId() == Long.valueOf(horseEquipItem)) {
				horseEquip = horse.getHorseequips()[i];
				pos = i;
				break;
			}
		}
		
		if (horseEquip == null) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "请选择强化装备");
			return;
		}
		
		if (horseEquip.getGradeNum() > 9) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "已经达到最高强化等级");
			return ;
		}
		
		if (!ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, Config.getId())) {
			return ;
		}
		
		horseEquip.setGradeNum(horseEquip.getGradeNum() + 1);
		MessageUtil.notify_player(player, Notifys.MOUSEPOS, "恭喜您,坐骑装备强化成功到" + horseEquip.getGradeNum());
		ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE_EQUIP, 0);
		HorseManager.getInstance().stResHorseInfo(player);
		showPanel(player, pos);
		
		try {
			if (horseEquip.getGradeNum() > 5) MessageUtil.notify_All_player(Notifys.CHAT_BULL, "恭喜玩家" + player.getName() + "将" + BackpackManager.getInstance().getName(horseEquip.getItemModelId()) + "强化到" + horseEquip.getGradeNum());
		}
		catch (Exception e) {
		}
	}
	
	public void show(List<Object> para){
		if (para.size() != 8) return ;
		
		Player player = (Player) para.get(0);
		if (player == null) return ;
		
		for (int i = 0; i < 4; ++i){
			String choice = (String)para.get(3 + i);
			if (choice != null && Integer.parseInt(choice) > 0) {
				showPanel(player, i);
				break;
			}
		}
		
	}
}
