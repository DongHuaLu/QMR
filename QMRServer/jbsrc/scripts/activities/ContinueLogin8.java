package scripts.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.game.activities.script.IActivityScript;
import com.game.backend.manager.BackendManager;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;

public class ContinueLogin8 implements IActivityScript{

	private static String KEY = "CONTINUELOGIN8";
	
	private static int day = 8;

	private static int gold = 2000*day;
	
	@Override
	public int getId() {
		return 2048;
	}

	@Override
	public void getReward(Player player) {
		if(getState(player)!=1){
			return;
		}
		long actionId = Config.getId();
		if(BackpackManager.getInstance().getEmptyGridNum(player)>=3){
			List<Item> items = Item.createItems(1015, 1, true, 0);
			items.addAll(Item.createItems(1100, 3, true, 0));
			items.addAll(Item.createItems(9001, 9, true, 0));
			items.addAll(Item.createItems(30201, 3, true, 0));
			BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, actionId);
			BackpackManager.getInstance().changeBindGold(player, 80, Reasons.ACTIVITY_GIFT, actionId);
			
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
		return "连续8天登录游戏可领取二测礼金奖励：16000,（若中断，登录天数将重置为1）";
	}

	@Override  //红玫瑰（绑）*3、双倍收益丹（绑）*1、礼金*80、回城卷（绑）*9、   真气丹(绑）*3
	public String getRewardInfo(Player player) {
		return "[{\"id\":-5, \"num\":" + 80 + "}, {\"id\":1015, \"num\":" + 1 + "},{\"id\":1100, \"num\":"+3+"},{\"id\":9001, \"num\":"+9+"},{\"id\":30201, \"num\":"+3+"}]";
	}

	@Override
	public int getState(Player player) {
		int btw = player.getLoginTimes() - day;
		if(btw >= 0){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -btw);
			String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		
			if(player.getActivitiesReward().containsKey(KEY)){ //已经领过
				if(date.equals(player.getActivitiesReward().get(KEY))){
					return 0;
				}
			}
			return 1;
		}else{
			return 2;
		}
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
