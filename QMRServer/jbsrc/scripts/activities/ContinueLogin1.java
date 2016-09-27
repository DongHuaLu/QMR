package scripts.activities;

import java.text.SimpleDateFormat;
import java.util.*;
import com.game.activities.script.IActivityScript;
import com.game.backend.manager.BackendManager;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;

public class ContinueLogin1 implements IActivityScript{

	private static String KEY = "CONTINUELOGIN1";
	
	private static int day = 1;
	
	@Override
	public int getId() {
		return 2041;
	}

	@Override
	public void getReward(Player player) {
		if(getState(player)!=1){
			return;
		}
		if(BackpackManager.getInstance().getAbleAddNum(player, 1015, true, 0)>0){
			long actionId = Config.getId();
			List<Item> items = Item.createItems(1015, 1, true, 0);
			BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, actionId);
			BackpackManager.getInstance().changeBindGold(player, 10, Reasons.ACTIVITY_GIFT, actionId);
			//更改修改状态
			int btw = player.getLoginTimes() - day;
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -btw);
			String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			player.getActivitiesReward().put(KEY, date);
		}else{
			MessageUtil.notify_player(player, Notifys.ERROR, "包裹不足,领取奖励失败");
		}
	}

	@Override
	public String getDescribe(Player player) {
		return "";
	}

	@Override  //双倍收益丹1015（绑）*1、礼金*10
	public String getRewardInfo(Player player) {
		return "[{\"id\":-5, \"num\":" + 10 + "}, {\"id\":1015, \"num\":" + 1 + "}]";
	}

	@Override
	public int getState(Player player) {
		int btw = player.getLoginTimes() - day;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -btw);
		String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		if(player.getActivitiesReward().containsKey(KEY)){ //已经领过
			if(date.equals(player.getActivitiesReward().get(KEY))){
				return 0;
			}
		}
		
		return 1;
//			if(times>0){
//				return 1;
//			}else{
//				//player.getActivitiesReward().remove(KEY);
//				return 0;
//			}
//		}else{
//			return 2;
//		}
	}
	
	@Override
	public boolean isOpen(Player player){
		
		return true;
	}

	@Override
	public int getDuration(Player player){
		
		return 0;
	}
}
