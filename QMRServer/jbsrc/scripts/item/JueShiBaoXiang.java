package scripts.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;
import com.game.vip.manager.VipManager;
/**绝世宝箱
 * 
 * @author zhangrong
 *
 */
public class JueShiBaoXiang implements IItemScript{

	@Override
	public int getId() {
		return 1009162;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		if (item.getItemModelId() != 9162) {
			return false;
		}
		
		String itemname= item.acqItemModel().getQ_name();
		
		if (ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.def18, Config.getId())) {
			List<Integer[]> items = new ArrayList<Integer[]>();
			List<Integer> ranList = new ArrayList<Integer>();
			HashMap<String, String> rewarddata = player.getActivitiesReward();
			int curday  = TimeUtil.GetSeriesDay();;
			int day = 0 ;
			if (rewarddata.containsKey("JSBX_DAY")) {
				 day = Integer.valueOf(rewarddata.get("JSBX_DAY"));
			}
			
			if (curday != day) {
				rewarddata.put("JSBX_DAY",curday+"");
				for (Integer[] data : itemlist) {
					rewarddata.put("JSBX_"+data[0],0+"");
				}
			}

			for (Integer[] data : itemlist) {//筛选符合条件的道具
				int curnum = 0;
				if (!rewarddata.containsKey("JSBX_"+data[0])) {
					items.add(data);
					ranList.add(data[2]);
				}else {
					if (rewarddata.containsKey("JSBX_"+data[0])) {
						curnum = Integer.valueOf(rewarddata.get("JSBX_"+data[0]));
						if (curnum < data[3]) {
							items.add(data);
							ranList.add(data[2]);
						}
					}else {
						items.add(data);
						ranList.add(data[2]);
					}
				}
			}

			int idx = RandomUtils.randomIndexByProb(ranList);
			Integer[] itemdata = items.get(idx);	//选中的道具
			
			List<Item> itemmakes = new ArrayList<Item>();
			String itemstr =  itemdata[0]+","+itemdata[1]+",0,1";
			ManagerPool.backpackManager.createItems(player, itemstr, itemmakes);
			ManagerPool.backpackManager.addItems(player, itemmakes, Config.getId());
			int max = 0;
			if (rewarddata.containsKey("JSBX_"+itemdata[0])) {
				max = Integer.valueOf(rewarddata.get("JSBX_"+itemdata[0]));
			}
			rewarddata.put("JSBX_"+itemdata[0],(max+1)+"");
			String rewname = ManagerPool.backpackManager.getName(itemdata[0]);
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("打开{1}，恭喜获得:{2}({3})"), itemname,rewname,itemdata[1]+"");
			
			if (itemdata[4] > 0) {
				ParseUtil parseUtil = new ParseUtil();
				parseUtil.setValue(String.format(ResManager.getInstance().getString("【%s】开启【%s】，获得:%s(%d)!{@}"), player.getName(),itemname,rewname,itemdata[1]), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),35));
				MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),35);
			}
			return true;
		}
		return false;
	}

	
	private Integer[][] itemlist = {
			//{id,数量，几率，最大开出数量,是否公告}
			{1007,5,1500,20,1},
			{3019,1,2000,5,1},
			//{1011,1,2000,5,1},
			{1001,1,1500,5,0},
			{9132,1,2000,5,0},
			{1004,1,1500,5,1},
			{1039,1,1500,2,1},
			{-1,150000,100,999999,0},	
	};
			
	
	
	
}
