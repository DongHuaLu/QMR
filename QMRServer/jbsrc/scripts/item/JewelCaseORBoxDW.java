package scripts.item;

import java.util.ArrayList;
import java.util.List;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemDefine;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.count.manager.ServerCountManager;
import com.game.count.structs.ServerCountConst;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.languageres.manager.ResManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.utils.ScriptsUtils;
import com.game.utils.StringUtil;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;

/**
 * 帝王百宝袋
 * @author 赵聪慧
 * @2012-9-24 下午3:09:13
 */
public class JewelCaseORBoxDW implements IItemScript {
	
	
//	
//	数值配置需求：数值配置需要支持热加载（逻辑最好用脚本实现，不排除经常更改）
	@Override
	public int getId() {
		return 1009111;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		int num=0;
		if(parameters!=null){
			num=Integer.parseInt(parameters[0]);
		}
		
		ScriptsUtils.callWorld(35, "queryDayRecharge", ""+1009111, "queryCallBack", ""+player.getId(),""+num,""+item.getId());
		return true;
	}
	
	
	public void queryCallBack(List<String> paras){
		long roleid = Long.parseLong(paras.get(0));
		int recharge = Integer.parseInt(paras.get(1));
		int successtag= Integer.parseInt(paras.get(2));
		int num= Integer.parseInt(paras.get(3));
		long itemid=Long.parseLong(paras.get(4));
		Player player = PlayerManager.getInstance().getPlayer(roleid);
		Item item = BackpackManager.getInstance().getItemById(player, itemid);
//		ResourceBundle gameres = ResourceBundle.getBundle("gameres");
		if (item == null) {
			return;
		}
		Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
		if (model == null) {
			return;
		}
		if (num < 1) {
			num = 1;
		}
		if (num > 1 && model.getQ_max() <= 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该位置物品数量不足，该物品不支持批量使用"));
			return;
		}
		if (num > item.getNum()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该位置物品数量不足，请求数量{1}当前数量{2}"), num + "", item.getNum() + "");
			//请求非法
			return;
		}

		if (num > 1 && model.getQ_whether_batch() != 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该物品不支持批量使用"));
			return;
		}

		if (player.isDie()) {
//			log.error("玩家己死亡不允许使用物品" + player.getId());
			return;
		}
		//未在包裹中
		if (item.getGridId() == -1) {
			return;
		}

		// 装备条件检查
		if (model.getQ_sex() != 0 && model.getQ_sex() != player.getSex()) {
			// 装备性别不符合
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该物品限{1}性使用"), model.getQ_sex() == 1 ? ResManager.getInstance().getString("男") : ResManager.getInstance().getString("女"));
			return;
		}

		if (player.getLevel() < model.getQ_level()) {
			// 装备等级不符合
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该物品需要人物等级达到{1}后才可以使用"), String.valueOf(model.getQ_level()));
			return;
		}
		if (item.isLost()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该物品已过期"), String.valueOf(model.getQ_level()));
			return;
		}
		if (item.isTrade()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("物品正在交易中"));
			return;
		}
		
		
		
		
		long action = Config.getId();
//		（1）在线人数控制的8888元宝奖励开出条件：服务器在线人数超过1000人时，第一个使用美人百宝袋时直接开出8888元宝奖励，在线控制的8888元宝仅此一次
//		（2）开启数量条件控制的8888元宝奖励开出规则：服务器需要记录美人百宝袋开启数量达到200个时，会放出2个8888元宝奖励。开启数量达到400个时，会放出第3个8888元宝奖励，以此类推。
//		（3）玩家获得8888元宝奖励应优先计算，若玩家获得了8888元宝奖励，则不会获得其他奖励
		//服务器级计数
		
		String lasttime = player.getActivitiesReward().get("mrbbdlasttime");
		long time=0;
		if(!StringUtil.isBlank(lasttime)){
			time=Long.parseLong(lasttime);
		}
		long ago=System.currentTimeMillis()-time;
		
//		int num=0;
//		if(parameters!=null){
//			num=Integer.parseInt(parameters[0]);
//		}
		
		for(int i=0;i<num;i++){
			long count = ServerCountManager.getInstance().getCount(ServerCountConst.OPENBBXCOUNT);
//			System.out.println(count);
			if(count>0&&count%500==0&&recharge<=999
//					&&player.getGold().getIsinner()==0
					&&ago>7*24*60*60*1000){
				//千人奖励
				if(!BackpackManager.getInstance().removeItem(player,item,1,Reasons.GOODUSE,action)){
					MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("移除物品失败"));
					return;
				}
				BackpackManager.getInstance().addGold(player, 8888, Reasons.ACTIVITY_MRBBX, action);
				//MessageUtil.notify_All_player(Notifys.SROLL, "{1}玩家开启帝王百宝袋获得8888元宝大奖",player.getName());
				//MessageUtil.notify_All_Activity(Notifys.CHAT_SYSTEM, 21, "{1}玩家开启帝王百宝袋获得8888元宝大奖",player.getName());
				player.getActivitiesReward().put("mrbbdlasttime", System.currentTimeMillis()+"");
				MessageUtil.notify_player(player, Notifys.CHAT_BULL, ResManager.getInstance().getString("恭喜您开启帝王百宝袋获得8888元宝大奖"));
				ParseUtil parseUtil = new ParseUtil();
				parseUtil.setValue(String.format(ResManager.getInstance().getString("玩家【%s】开启【帝王百宝袋】获得8888元宝大奖，真是羡煞旁人！{@}"), player.getName()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.BAIBAODAI.getValue()));
				MessageUtil.notify_All_player(Notifys.CHAT_SYSTEM, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.BAIBAODAI.getValue());
				MessageUtil.notify_All_player(Notifys.SROLL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.BAIBAODAI.getValue());
				
			}else{
				ArrayList<ItemDefine> itemlist=new ArrayList<ItemDefine>();
				boolean bind=true;
				long lostTime=0;
//				初等修为丹：1000、中等修为丹：3000。高等修为丹：3000，玫瑰：600，双倍收益丹：600，自动补血丹：600，自动聚气丹：600，自动续体丹：600
				List<Integer> prob=new ArrayList<Integer>();
//				30101	初等修为丹	1000
				itemlist.add(new ItemDefine(30101, lostTime, 1, bind));
				prob.add(1000);
//				30102	中等修为丹	3000
				itemlist.add(new ItemDefine(30102, lostTime, 1, bind));
				prob.add(3000);
//				30103	高等修为丹	3000
				itemlist.add(new ItemDefine(30103, lostTime, 1, bind));
				prob.add(3000);
				
//				30101	初等真气丹	1000
				itemlist.add(new ItemDefine(30201, lostTime, 1, bind));
				prob.add(1000);
//				30102	中等真气丹	3000
				itemlist.add(new ItemDefine(30202, lostTime, 1, bind));
				prob.add(3000);
//				30103	高等真气丹	3000
				itemlist.add(new ItemDefine(30203, lostTime, 1, bind));
				prob.add(3000);
				
				
//				30103	玫瑰	3000
				itemlist.add(new ItemDefine(1100, lostTime, 1, bind));	
				prob.add(600);
//				1015	双倍收益丹	600
				itemlist.add(new ItemDefine(1015, lostTime, 1, bind));
				prob.add(600);
//				30301	生命池	600
				itemlist.add(new ItemDefine(30301, lostTime, 1, bind));
				prob.add(600);
//				30302	内力池	600
				itemlist.add(new ItemDefine(30302, lostTime, 1, bind));
				prob.add(600);
//				30303	体力池	600
				itemlist.add(new ItemDefine(30303, lostTime, 1, bind));
				prob.add(600);
				
				int randomIndexByProb = RandomUtils.randomIndexByProb(prob);
				ItemDefine randomItem = itemlist.get(randomIndexByProb);
				java.util.List<Item> createItems = Item.createItems(randomItem.getModelId(), randomItem.getNum(), randomItem.isBind(), randomItem.getLostTime());
				if(!BackpackManager.getInstance().hasAddSpace(player, createItems)){
					MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("包裹空间不足，请先清理包裹"));
					return;
				}
				if(!BackpackManager.getInstance().removeItem(player,item,1,Reasons.GOODUSE,action)){
					MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("移除物品失败"));
					return;
				}
				BackpackManager.getInstance().addItems(player, createItems, Reasons.ACTIVITY_MRBBX, action);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("获得物品{1}*{2}"),BackpackManager.getInstance().getName(randomItem.getModelId()),""+randomItem.getNum());
			}
			ServerCountManager.getInstance().addCount(ServerCountConst.OPENBBXCOUNT, 1);	
		}
	}
}
