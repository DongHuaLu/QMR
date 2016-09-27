package scripts.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_activitiesBean;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.message.ResScriptCommonPlayerToClientMessage;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.IScript;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;

public class NewYearCouplet  implements IScript{

	@Override
	public int getId() {
		return ScriptEnum.COUPLET;
	}

	//配置
	HashMap<String, String> coupletmap = new HashMap<String, String>();
	//奖励表
	HashMap<String, String>  rewardmap  = new HashMap<String, String>();
	
	/**获得活动剩余时间
	 * 
	 * @return
	 */
	public long gettime(){
		String id = WServer.getInstance().getServerWeb();
		int aid = 120;
		if (id!= null && id.equals("twgmei")) {//台湾春联活动ID 122
			aid = 122;
		}
		Q_activitiesBean data = ManagerPool.dataManager.q_activitiesContainer.getMap().get(aid);
		if (data != null) {
			long  s = TimeUtil.getRangeTimeBeforeOrAfter(data.getQ_timingstart(), false, false);
			return s;
		}
		return -1;
	}
	
	
	
	public NewYearCouplet(){
		//春联配置
		coupletmap.put("1", "18143,18144,18145");
		coupletmap.put("2", "18146,18147,18148");
		coupletmap.put("3", "18149,18150,18151");
		coupletmap.put("4", "18152,18153,18154");
		coupletmap.put("5", "18155,18156,18157");
		
		//奖励设置
		rewardmap.put("1", "8571,1,0,1");
		rewardmap.put("2", "8572,1,0,1");
		rewardmap.put("3", "8573,1,0,1");
		rewardmap.put("4", "8574,1,0,1");
		rewardmap.put("5", "8575,1,0,1");
		
	}
	
	
	public void openNewYearCouplet(List<Object> plist){
		Player player = (Player)plist.get(0);
		if (player == null) {
			return;
		}
		long ms = gettime();
		if ( gettime() > 0) {
			HashMap<String, List<Integer>> duilian = player.getCoupletmap();
			ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
			sendMessage.setScriptid(ScriptEnum.COUPLET);
			sendMessage.setType(1);
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("time", ""+(ms /1000));
			for (Entry<String, String> map : coupletmap.entrySet()) {
				long num = ManagerPool.countManager.getCount(player, CountTypes.COUPLET,map.getKey());
				paramMap.put("activationnum_"+map.getKey(), num+"");	//次数
				paramMap.put("reward_"+ map.getKey() , rewardmap.get(map.getKey()));//奖励内容
				if (duilian.containsKey(map.getKey())) {
					List<Integer> list = duilian.get(map.getKey());
					String str= "";
					for (int i = 0; i < list.size(); i++) {
						if (i == 0) {
							str = list.get(i)+"";
						}else {
							str =str +"," + list.get(i);
						}
					}
					paramMap.put("activationstatus_"+map.getKey(), str);//激活状态 
				}
			}
			sendMessage.setMessageData(JSON.toJSONString(paramMap));
			MessageUtil.tell_player_message(player, sendMessage);	
		}
	}
	
	
	
	
	
	
	/**激活对联部位
	 * 
	 */
	public void coupletactivation(List<Object> plist){
		Player player = (Player)plist.get(0);
		if (player == null) {
			return;
		}
		
		String idxString = (String)plist.get(1);
		int itemid = Integer.valueOf((String)plist.get(2));
		long num = ManagerPool.countManager.getCount(player, CountTypes.COUPLET,idxString);
		if (num >= 2) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("每幅对联每天最多只能激活2次"));
			return;
		}
		String config = coupletmap.get(idxString);//当前春联配置
		if (config.contains(itemid+"")) {
			HashMap<String, List<Integer>> duilian = player.getCoupletmap();
			if (duilian.containsKey(idxString) && duilian.get(idxString).contains(itemid)) {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("已经激活这个部位"));
				return ;
			}else {
				long actid = Config.getId();
				String nameString = ManagerPool.backpackManager.getName(itemid);
				if (ManagerPool.backpackManager.getItemNum(player, itemid) <= 0 ) {
					MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("您没有【{1}】，不能激活"),nameString);
					return;
				}
				
				if (!BackpackManager.getInstance().checkGold(player, 5)) {
					MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("激活【{1}】，需要5元宝"),nameString);
					return;
				}
				
				if (ManagerPool.backpackManager.removeItem(player, itemid, 1, Reasons.def22, actid) && ManagerPool.backpackManager.changeGold(player, -5, Reasons.def22, actid)) {
					if (!duilian.containsKey(idxString)) {
						duilian.put(idxString, new ArrayList<Integer>() );
					}
					duilian.get(idxString).add(itemid);
					MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("您激活了【{1}】部位"),nameString);
					openNewYearCouplet(plist);
				}
			}
		}
	}
	
	
	
	
	/**领奖
	 * 
	 */
	public void receiveawards(List<Object> plist){
		Player player = (Player)plist.get(0);
		if (player == null) {
			return;
		}
		
		String idxString = (String)plist.get(1);
		HashMap<String, List<Integer>> duilian = player.getCoupletmap();
		if (duilian.containsKey(idxString)) {
			String config = coupletmap.get(idxString);
			List<Integer> list = duilian.get(idxString);
			if (list.size() < 3) {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("没有激活"));
				return;
			}
			
			for (Integer integer : list) {
				if(!config.contains(integer+"")){
					MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("没有激活"));
					return;
				}
			}
			
			long num = ManagerPool.countManager.getCount(player, CountTypes.COUPLET, idxString);
			if (num < 2) {
				list.clear();
				ManagerPool.countManager.addCount(player,  CountTypes.COUPLET, idxString, 1,1,0); //记录次数
				List<Item> itemmakes = new ArrayList<Item>();
				String itemstr =  rewardmap.get(idxString);
				ManagerPool.backpackManager.createItems(player, itemstr, itemmakes);
				ManagerPool.backpackManager.addItems(player, itemmakes, Config.getId());
				ManagerPool.backpackManager.notifyitemname(player, itemstr);
				openNewYearCouplet(plist);
				ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
				sendMessage.setScriptid(ScriptEnum.COUPLET);
				sendMessage.setType(2);
				MessageUtil.tell_player_message(player, sendMessage);	
			}
		}
	}
	
	

}
