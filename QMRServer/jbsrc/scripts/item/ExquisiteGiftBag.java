package scripts.item;

import java.util.ArrayList;
import java.util.List;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.db.bean.Gold;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.vip.manager.VipManager;
/**精美礼包
 * 
 * @author zhangrong
 *
 */
public class ExquisiteGiftBag implements IItemScript{


	@Override
	public int getId() {
		return 1009167;	
	}
	
	
	//玩家累计充值大于2W
	private int[][] GIFT_TAB1 = {
			//{道具ID，数量，几率}
			{30204,1,1500},
			{30105,2,3000},
			{30701,1,500},
			{30702,1,500},
			{30301,1,1000},
			{30302,1,500},
			{1100,1,500},
			{1014,2,1000},
			{9121,2,500},
			{-5,600,1000},
	};
	
	//玩家累计充值<2W
	private int[][] GIFT_TAB2 = {
			{30204,1,500},
			{30105,2,700},
			{30701,1,500},
			{30702,1,500},
			{30301,1,200},
			{30302,1,200},
			{1100,1,200},
			{1014,2,200},
			{9121,2,3000},
			{-5,600,4000},	
			
	};
	
	

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		String numstr = parameters[0];
		int num = Integer.valueOf(numstr);
		String name = item.acqItemModel().getQ_name();
		Gold gold = player.getGold();
		int[][] itemrnd={};
		if (gold != null && gold.getTotalGold() > 200000) {
			itemrnd = GIFT_TAB1;
		}else {
			itemrnd = GIFT_TAB2;
		}
		int opennum=0;
		if(player.getActivitiesReward().containsKey("USE_9167_NUM") ){
			opennum = Integer.valueOf(player.getActivitiesReward().get("USE_9167_NUM"));	
		}
		
		long aid = Config.getId();

		for (int i = 0; i < num; i++) {
			if(ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.def20,aid)){
				String addname="";
				opennum = opennum + 1;
				String itemstr = "";
				//玩家使用宝箱数量大于150个后，有5%几率开出，一旦开出，则重新计时，从此刻开始，继续达到第150个后，再有5%几率开出。依次类推。		
				if (opennum > 150 && RandomUtils.random(10000) <= 500) {
					opennum = 1;
					itemstr = "9168,1,0,1";
					addname = ManagerPool.backpackManager.getName(9168) + "(1)";
					
					ParseUtil parseUtil = new ParseUtil();
					parseUtil.setValue(String.format(ResManager.getInstance().getString("【%s】开启【%s】，获得:%s!{@}"), player.getName(),name,addname), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),37));
					MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),37);
						
					
				//玩家使用该宝箱数量大于15个后，有20%几率开出。当开出一个后，将不会再开出。		
				}else if (opennum > 15 && !player.getActivitiesReward().containsKey("USE_9167_SP")) {
					if (RandomUtils.random(10000) <= 2000) {
						player.getActivitiesReward().put("USE_9167_SP", "1022");
						itemstr = "1022,1,0,1";
						addname = ManagerPool.backpackManager.getName(1022) + "(1)";
						ParseUtil parseUtil = new ParseUtil();
						parseUtil.setValue(String.format(ResManager.getInstance().getString("【%s】开启【%s】，获得:%s!{@}"), player.getName(),name,addname), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),37));
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),37);
					}
				}
				
				if (itemstr.equals("")) {
					List< Integer > rndList = new ArrayList<Integer>();
					for (int j = 0; j < itemrnd.length; j++) {
						rndList.add(itemrnd[j][2]);
					}
					int idx = RandomUtils.randomIndexByProb(rndList);
					itemstr = itemrnd[idx][0] + "," + itemrnd[idx][1]  + ",0,1";
					addname = ManagerPool.backpackManager.getName(itemrnd[idx][0]) + "("+itemrnd[idx][1]+")";
				}
				
				player.getActivitiesReward().put("USE_9167_NUM",opennum+"") ;
				List<Item> itemmakes = new ArrayList<Item>();
				ManagerPool.backpackManager.createItems(player, itemstr, itemmakes);
				ManagerPool.backpackManager.addItems(player, itemmakes, Config.getId());
				MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString(("打开{1}，获得:{2}")), name,addname);
			}
		}
		return false;
	}

	
}
