package scripts.activities;

import java.util.*;
import com.game.activities.script.IActivityScript;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;

//悬赏首任秦王帮派
public class BETALordGuild implements IActivityScript {

	private static String KEY="BETALORDGUILD";
	
	 //礼金*2000、红玫瑰*10、生命池（绑）*4、内力池（绑）*4
	private int itemid1=-5;
	private int itemnum1=2000;
	private int itemid2=1100;
	private int itemnum2=10;
	private int itemid3=30301;
	private int itemnum3=4;
	private int itemid4=30302;
	private int itemnum4=4;
	
	@Override
	public int getId() {
		return ScriptEnum.BETA_LORDGUILD;
	}

	@Override  
	public void getReward(Player player) {
		if(getState(player)!=1){
			if(getState(player)==2){
				MessageUtil.notify_player(player, Notifys.ERROR, "您不是王帮成员");
			}else{
				MessageUtil.notify_player(player, Notifys.ERROR, "您已经领取");
			}
			return ;
		}
		List<Item> items = new ArrayList<Item>();
		items.addAll(Item.createItems(itemid2, itemnum2, true, 0));
		items.addAll(Item.createItems(itemid3, itemnum3, true, 0));
		items.addAll(Item.createItems(itemid4, itemnum4, true, 0));
		if(BackpackManager.getInstance().hasAddSpace(player, items)){
			long actionid = Config.getId();
			BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, actionid);
			BackpackManager.getInstance().changeBindGold(player, itemnum1, Reasons.ACTIVITY_GIFT, actionid);
			MessageUtil.notify_player(player, Notifys.ERROR, "领取成功");
			player.getActivitiesReward().put(KEY, "1");
		}else{
			MessageUtil.notify_player(player, Notifys.ERROR, "包裹不足,活动奖励领取失败");
		}
	}

	@Override
	public String getDescribe(Player player) {
		return "第一次王城攻城战结束后获得王城帮派的成员可以领取";
	}

	@Override 
	public String getRewardInfo(Player player) {
		return "[{\"id\":"+itemid1+", \"num\":" +itemnum1+ "},{\"id\":"+itemid2+", \"num\":" + itemnum2 + "},{\"id\":"+itemid3+", \"num\":" + itemnum3 + "},{\"id\":"+itemid4+", \"num\":" + itemnum4 + "}]";
	}

	@Override  
	public int getState(Player player) {
		int klv = player.gKingLevel();
		if(player.getActivitiesReward().containsKey(KEY)){ //已经领过
			return 0;
		}else if(klv<=0){ //不是王帮成员 不能领
			return 2;
		}else{  //klv>0 当前是王帮成员
			return 1;
		}
	}

	@Override
	public boolean isOpen(Player player) {
		return true;
	}

	@Override
	public int getDuration(Player player){
		
		return 0;
	}
}
