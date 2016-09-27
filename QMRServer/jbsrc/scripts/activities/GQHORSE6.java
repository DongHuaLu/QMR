package scripts.activities;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.game.activities.script.IActivityScript;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.horse.manager.HorseManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;

//国庆 坐骑升阶
public class GQHORSE6 implements IActivityScript {
	private int itemid1=1023;
	private int itemnum1=1;
	
	private int itemid2=1011;
	private int itemnum2=10;

	private int itemid3=30201;
	private int itemnum3=10;
	
	private static String KEY = "GQHORSE6";
	@Override
	public int getId() {
		return ScriptEnum.GQHORSE6;
	}

	@Override 
	public void getReward(Player player) {
		if(getState(player)!=1){
			return;
		}
		
		//获得奖励
		if(ManagerPool.backpackManager.getEmptyGridNum(player) < 3){
			MessageUtil.notify_player(player, Notifys.ERROR, "包裹不足,领取奖励失败");
		}else{
			player.getActivitiesReward().put(KEY, "2");
			
			long actionId = Config.getId();
			long time = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
			List<Item> items = Item.createItems(itemid1, itemnum1, true, time);
			BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, actionId);
			items = Item.createItems(itemid2, itemnum2, true, time);
			BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, actionId);
			items = Item.createItems(itemid3, itemnum3, true, time);
			BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, actionId);
			String name1=ManagerPool.dataManager.q_itemContainer.getMap().get(itemid1).getQ_name();
			String name2=ManagerPool.dataManager.q_itemContainer.getMap().get(itemid2).getQ_name();
			String name3=ManagerPool.dataManager.q_itemContainer.getMap().get(itemid3).getQ_name();
			
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "恭喜您获得奖励：{1}({2}),{3}({4}),{5}({6})", name1,itemnum1+"", name2,itemnum2+"", name3,itemnum3+"");
		}
	}

	@Override
	public String getDescribe(Player player) {
		return "a";
	}
	
	//坐骑祝福丹（绑定）*1、坐骑进阶丹（绑定）*10、真气丹（绑定）*10
	@Override
	public String getRewardInfo(Player player) {
		return "[{\"id\":"+itemid1+", \"num\":" + itemnum1 + "},{\"id\":"+itemid2+", \"num\":" + itemnum2 + "},{\"id\":"+itemid3+", \"num\":" + itemnum3 + "}]";
	}

	@Override
	public int getState(Player player) {
		if(player.getActivitiesReward().containsKey(KEY)){ //未领过
			String k = player.getActivitiesReward().get(KEY);
			if("1".equals(k)){
				return 1;
			}else{
				return 0;
			}
		}else{
			int playerlayer = HorseManager.getInstance().getHorse(player).getLayer(); //玩家坐骑最高阶数 
			if(playerlayer>=6){ //没有活动期间升阶的标记 并且当前坐骑等级大于等于领取条件 表示玩家在活动之前达到目标等级，直接跳过
				return 0;
			}
		}
		return 2;
	}

	@Override
	public boolean isOpen(Player player) {
		int sid = WServer.getInstance().getServerId();
		if(sid<3) return true;
		else return false;
	}

	@Override
	public int getDuration(Player player) {
		Date opendate = WServer.getGameConfig().getServerTimeByPlayer(player); 
		long opentime = opendate.getTime(); //开服时间
		int opensec = (int)(opentime/1000); //开服时候的秒数
		Calendar c = Calendar.getInstance();
		c.set(2012, 9, 8, 0, 0, 0); //10月8日的凌晨
		long limit = c.getTimeInMillis(); 
		int limitsec = (int)(limit/1000); 
		//从开服到10月07日的秒数
		return limitsec-opensec;
	}

}
