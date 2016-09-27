package scripts.item;

import java.util.ArrayList;
import java.util.List;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.scripts.bean.PanelInfo;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;

public class YuandanGiftMin implements IItemScript {

	private int panelId = 6;
	
	@Override
	public int getId() {
		return 1009149;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		PanelInfo panel = NpcParamUtil.getPanelInfo(player, panelId);
		List<String> list = new ArrayList<String>();
		list.add("btn1#" + this.getId() + "#open#"+item.getId());
		panel.setButtoninfolist(NpcParamUtil.getbuttonInfo(player , list));
		NpcParamUtil.showPanel(player, panel);
		return false;
	}
	
	//200
	public void open(List<Object> para){
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
		//花费200元宝开启可获得：5倍*3  真气2W  坐骑进阶丹5个 元旦礼包（中）
		String itemstr = "1024,3,0,1;-3,20000;1011,5,0,1;9150,1,0,1";
		//创建奖励物品
		List<Item> items = new ArrayList<Item>();
		int num = ManagerPool.backpackManager.createItems(player, itemstr, items);
		++num;
		//包裹空间是否足够
		if(ManagerPool.backpackManager.getEmptyGridNum(player) >= num){
			long id = Config.getId();
			if (ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, id)
					&& ManagerPool.backpackManager.changeGold(player, -gold, Reasons.SHENGDAN, id)) {
				ManagerPool.backpackManager.addItems(player, items, id);
			}
		}
		else {
			MessageUtil.notify_player(player, Notifys.ERROR, "背包空间不足");
		}
		//关闭面板
		NpcParamUtil.setPanel(player, panelId, new ArrayList<String>(), 1);
	}
}
