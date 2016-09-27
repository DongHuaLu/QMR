package scripts.player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.game.arrow.structs.ArrowReasonsType;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemChangeAction;
import com.game.buff.manager.BuffManager;
import com.game.buff.structs.Buff;
import com.game.config.Config;
import com.game.data.bean.Q_buffBean;
import com.game.data.bean.Q_collectBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.gem.struts.Gem;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.mail.manager.MailServerManager;
import com.game.manager.ManagerPool;
import com.game.marriage.structs.Marriage;
import com.game.message.Message;
import com.game.player.manager.PlayerManager;
import com.game.player.script.IPlayerLoginEndScript;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.rank.structs.RankType;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.store.log.StoreItemChangeLog;
import com.game.store.message.ResStoreItemChangeMessage;
import com.game.store.message.ResStoreItemRemoveMessage;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.ScriptsUtils;
import com.game.utils.TimeUtil;

/**
 * 登录完成后执行脚本
 *
 * @author zhangrong
 *
 */
public class PlayerLoginEndScript implements IPlayerLoginEndScript {

	private Logger log = Logger.getLogger(IPlayerLoginEndScript.class);
	private Map<String, Map<String, Integer>> gift360map = new HashMap<String, Map<String,Integer>>();
	private Map<String, Map<String, Integer>> giftitem360map = new HashMap<String, Map<String,Integer>>();
	private Map<String, Map<String, Integer>> gift360plusmap = new HashMap<String, Map<String,Integer>>();
	
	public Map<String, Map<String, Integer>> getGift360map() {
		return gift360map;
	}
	public Map<String, Map<String, Integer>> getGiftitem360map() {
		return giftitem360map;
	}

	private Date startdate = null;
	
	public PlayerLoginEndScript() {
		try{
			startdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-4-28 22:18:00");
		}catch(Exception e){
			log.error(e, e);
		}
		 
//		File file = new File("collect.txt");
//		if (!file.exists()) {
//			return;
//		}
//		try {
//			FileInputStream in = new FileInputStream(file);
//			BufferedReader br = new BufferedReader(new InputStreamReader(in));
//			String line = null;
//			while ((line = br.readLine()) != null) {
//				String[] strs = line.split(",");
//				daqindianchang.add(strs);
//			}
//		} catch (Exception e) {
//			log.error(e, e);
//		}
		
		File gift360file = new File("gift360.txt");
		if (!gift360file.exists()) {
			return;
		}
		try {
			FileInputStream in = new FileInputStream(gift360file);
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] strs = line.split("\t");
				String username = strs[0]; String rolename = strs[1];
				String key = username+rolename;
				int gemdownto = Integer.parseInt(strs[2]);
				int equipdownto = Integer.parseInt(strs[3]);
				Map<String, Integer> dealmap = new HashMap<String, Integer>();
				dealmap.put("gemdownto", gemdownto); dealmap.put("equipdownto", equipdownto);
				gift360map.put(key, dealmap);
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		//gift360plusmap
		File gift360plusfile = new File("gift360plus.txt");
		if (!gift360plusfile.exists()) {
			return;
		}
		try {
			FileInputStream in = new FileInputStream(gift360plusfile);
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] strs = line.split("\t");
				String username = strs[0]; String rolename = strs[1];
				String key = username+rolename;
				int gemdownto = Integer.parseInt(strs[2]);
				int equipdownto = Integer.parseInt(strs[3]);
				Map<String, Integer> dealmap = new HashMap<String, Integer>();
				dealmap.put("gemdownto", gemdownto); dealmap.put("equipdownto", equipdownto);
				gift360plusmap.put(key, dealmap);
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		//
		File giftitem360file = new File("giftitem360.txt");
		if (!giftitem360file.exists()) {
			return;
		}
		try {
			FileInputStream in = new FileInputStream(giftitem360file);
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] strs = line.split("\t");
				String username = strs[0]; String rolename = strs[1];
				String key = username+rolename;
				int keepbackgem = Integer.parseInt(strs[2]);//背包保留宝石精华  4  
				int keepbackstreng = Integer.parseInt(strs[3]);//背包保留装备强化石  5
				int keepbackspeaker = Integer.parseInt(strs[4]);//背包保留小喇叭  6
				int keepstoregem = Integer.parseInt(strs[5]);//仓库保留宝石精华  7
				int keepstorestreng = Integer.parseInt(strs[6]);//仓库保留装备强化石  8
				int keepstorespeaker = Integer.parseInt(strs[7]);//仓库保留小喇叭  9
				Map<String, Integer> dealmap = new HashMap<String, Integer>();
				dealmap.put("keepbackgem", keepbackgem);
				dealmap.put("keepbackstreng", keepbackstreng);
				dealmap.put("keepbackspeaker", keepbackspeaker);
				dealmap.put("keepstoregem", keepstoregem);
				dealmap.put("keepstorestreng", keepstorestreng);
				dealmap.put("keepstorespeaker", keepstorespeaker);
				giftitem360map.put(key, dealmap);
			}
			//log.info("giftitem360map.size="+giftitem360map.size());
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	@Override
	public int getId() {
		return ScriptEnum.PLAYER_LOGIN_END;
	}

	@Override
	public void onLogin(Player player, int type) {
		//不是帮旗战期间，上线检查指定BUFF，如果有就删除BUFF
		IPlayerLoginEndScript script = (IPlayerLoginEndScript) ScriptManager.getInstance().getScript(ScriptEnum.GUILDFLAG);
		if (script != null) {
			try {
				script.onLogin(player, type);
			} catch (Exception e) {
				log.error(e);
			}
		}
		//登录就发送比武岛活动倒计时
		script = (IPlayerLoginEndScript) ScriptManager.getInstance().getScript(ScriptEnum.BIWUDAO);
		if (script != null) {
			try {
				script.onLogin(player, type);
			} catch (Exception e) {
				log.error(e);
			}
		}
		
		//登录检测聚宝盆活动
		ScriptsUtils.call(105,"loginRetbindgold", player);
		
		//上线后清除大秦典藏的buff
		clearCollectBuff(player);

		//玩家上线后给自定义补偿
		compensatory(player);
		//玩家合区后给自定义补偿
		hequcompensatory(player);
		
		playerbuchang(player);
		
		//360刷礼包账号处理1-22
		deal360gift(player);
		//360刷礼包账号删除道具处理 1-22
		deal360item(player);
		//360刷礼包二次处理
		deal360plusgift(player);
		//上线给春节大礼包
		TotheNewYeargifts(player);
		//500服大礼包
		to500serverGifts(player);
		//百度100服大礼包
		toBaidu100ServerGift(player);
		
		//多玩回档补偿
		String platform = WServer.getInstance().getServerWeb();
		if (platform!= null && platform.equals("duowan")) {
			ScriptsUtils.call(2200, "duowanbuchang", player);
		}
		
		//检查并替换骑兵隐藏属性BUFF
		ckForgingBuff(player);
		
		cleanupmarriagedata(player);
		
		//台湾G妹 秦美人全服禮包發放
		if("twgmei".equals(platform)){
			try{
				sendtwgmei0316gift(player);
			}catch (Exception e) {
				log.error(e, e);
			}
		}
		
		//百度104-106  3月16日服务器故障补偿
		if("baidu".equals(platform)){
			try{
				sendbaidu0316gift(player);
			}catch (Exception e) {
				log.error(e, e);
			}
		}
		
		//手机  4月28日服务器故障补偿
		if("haowan123".equals(platform)){
			try{
				shouji428(player);
			}catch (Exception e) {
				log.error(e, e);
			}
		}
		
		setSkillLevelSum(player);
	}
	
	//台湾G妹 秦美人全服禮包發放
	public void sendtwgmei0316gift(Player player){
		//玩家已经发放过 则返回
		if(player.getActivitiesReward().containsKey("TWGMEI20130316GIFT")){ return; }
		long now = System.currentTimeMillis();
		//时间范围是 3月16日0點開始，到3月17日23點59分結束
		long fromtime = 1363363200000L, totime = 1363535999000L;
		if(fromtime <= now && now <= totime){
			String title = "天下英雄會全服獎勵";
			String content = "您所在的伺服器有玩家獲得天下英雄會戰力排行前三，得到全服獎勵。";
			int serverid = player.getCreateServerId(); //注意是创建服
			if(serverid == 7){
				List<Item> items = Item.createItems(8628, 1, true, 0L);  //聖者禮包
				if(items!=null && items.size()>0){
					MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), title, content, (byte)0, 0, items);
					player.getActivitiesReward().put("TWGMEI20130316GIFT", "1_"+now);
				}else{
					log.error("items[8628]为空 检查道具表");
				}
			}else if(serverid == 10){
				List<Item> items = Item.createItems(8629, 1, true, 0L);  //尊者禮包
				if(items!=null && items.size()>0){
					MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), title, content, (byte)0, 0, items);
					player.getActivitiesReward().put("TWGMEI20130316GIFT", "2_"+now);
				}else{
					log.error("items[8629]为空 检查道具表");
				}
			}else if(serverid == 2){
				List<Item> items = Item.createItems(8630, 1, true, 0L);  //英者禮包
				if(items!=null && items.size()>0){
					MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), title, content, (byte)0, 0, items);
					player.getActivitiesReward().put("TWGMEI20130316GIFT", "3_"+now);
				}else{
					log.error("items[8630]为空 检查道具表");
				}
			}
		}
	}
	
	//百度104-106 3月16日服务器故障补偿
	public void sendbaidu0316gift(Player player){
		//服务器范围 104 - 106
		int serverid = player.getCreateServerId(); //注意是创建服
		long now = System.currentTimeMillis();
		long fromtime = 1363402800000L, totime = 1363575600000L;//时间范围是 3月16日11點開始，到3月18日11點結束
		if(serverid<104 || serverid>106){ return; } //服务器不满足要求
		if(player.getActivitiesReward().containsKey("BAIDU20130316GIFT")){ return; } //玩家已经领取过
		if(now < fromtime || totime < now){ return; } //超过发放时间
		if(player.getCreateTime()>=1363402800000L){ return; }  ////角色创建时间不满足要求，  需要在 3月16日11点之前创建
		//验证通过  发放   2000礼金  5个双倍收益丹
		boolean bindgoldresult = BackpackManager.getInstance().changeBindGold(player, 2000, Reasons.def7, Config.getId());
		List<Item> items = Item.createItems(1015, 5, true, 0L);
		boolean itemsresult = BackpackManager.getInstance().addItems(player, items, Reasons.def7, Config.getId());
		//发放完成 标记
		player.getActivitiesReward().put("BAIDU20130316GIFT", String.valueOf(now));
		log.error("发放 百度104-106服 3月16日 服务器机器故障补偿 "+bindgoldresult+"|"+itemsresult);
	}
	
	private void toBaidu100ServerGift(Player player) {
		// 只是给百度1-100服礼包
		if (WServer.getInstance().getServerWeb().compareToIgnoreCase("baidu") != 0) {
			return ;
		}
		
		if (WServer.getInstance().getServerId() > 100) {
			return ;
		}
		
		int day = TimeUtil.GetSeriesDay();
		if (day < 20130309 || day > 20130320) {
			return ;
		}
		
		String key = "toBaidu100ServerGift_2013";
		if (!player.getActivitiesReward().containsKey(key)) {
			 List<Item> items = Item.createItems(9202, 1, true, 0);
			 if (!items.isEmpty()) {
				long action = Config.getId();
				if (!BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_RNDITEM, action)) {
					MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), "未领取道具：","百度大礼包", (byte) 0, 0, items);
				}
				player.getActivitiesReward().put(key, "9202");
				String str = ManagerPool.backpackManager.getName(9202);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您获得了: {1}"), str);
			 }
		}
	}
	
	private void to500serverGifts(Player player) {
		// 只是给500服礼包
		if (WServer.getInstance().getServerId() != 500) {
			return ;
		}
		
		int day = TimeUtil.GetSeriesDay();
		if (day < 20130302 || day > 20130322) {
			return ;
		}
		
		String key = "to500serverGifts_2013";
		if (!player.getActivitiesReward().containsKey(key)) {
			 List<Item> items = Item.createItems(9192, 1, true, 0);
			 if (!items.isEmpty()) {
				long action = Config.getId();
				if (!BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_RNDITEM, action)) {
					MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), "未领取道具：","500服大礼包", (byte) 0, 0, items);
				}
				player.getActivitiesReward().put(key, "9192");
				String str = ManagerPool.backpackManager.getName(9192);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您获得了: {1}"), str);
			 }
		}
	}
	private void clearCollectBuff(Player player) {
		try{
			List<Q_collectBean> list = DataManager.getInstance().q_collectContainer.getList();
			for (Q_collectBean q_collectBean : list) {
				Q_buffBean buffBean = ManagerPool.dataManager.q_buffContainer.getMap().get(q_collectBean.getQ_buff_id());
				if (buffBean != null) {
					BuffManager.getInstance().removeByBuffId(player, buffBean.getQ_buff_id());
				}
			}
		}catch (Exception e) {
			log.error(e, e);
		}
	}


	//----------------------------------------------上线补偿内容--------------------------------------------------
	//	{"平台名字_区ID","开始时间","结束时间","奖励内容（数值）","邮件内容（可选）"},
	//   奖励内容（数值 ）-1铜币，-2元宝，-3真气，-4经验，-5礼金，-6战魂，-7军功，大于0的ID是物品，现在不能支持复杂物品配置	
	public String[][] BUCHANG_TAB = {//补偿 区ID是计算的服务器区id
				{"twgmei_1","2013-1-22 00:01:00","2013-1-22 18:00:00","8552_1"},	//台湾22日补偿礼包
				{"twgmei_2","2013-1-22 00:01:00","2013-1-22 18:00:00","8552_1"},	
				{"twgmei_3","2013-1-22 00:01:00","2013-1-22 18:00:00","8552_1"},	
				{"twgmei_4","2013-1-22 00:01:00","2013-1-22 18:00:00","8552_1"},	
				{"twgmei_5","2013-1-22 00:01:00","2013-1-22 18:00:00","8552_1"},	
				{"twgmei_6","2013-1-22 00:01:00","2013-1-22 18:00:00","8552_1"},
				{"twgmei_7","2013-1-22 00:01:00","2013-1-22 18:00:00","8552_1"},
				{"twgmei_8","2013-1-22 00:01:00","2013-1-22 18:00:00","8552_1"},
				{"twgmei_9","2013-1-22 00:01:00","2013-1-22 18:00:00","8552_1"},
				{"twgmei_6","2013-1-27 00:01:00","2013-1-27 23:59:59","-5_500;9121_2"},
				{"twgmei_1","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
				{"twgmei_2","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
				{"twgmei_3","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
				{"twgmei_4","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
				{"twgmei_5","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
				{"twgmei_6","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
				{"twgmei_7","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
				{"twgmei_8","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
				{"twgmei_9","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
				{"twgmei_10","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
				{"twgmei_11","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
				{"twgmei_12","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
				{"twgmei_13","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
				{"twgmei_14","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
				{"twgmei_15","2013-2-21 00:01:00","2013-2-21 23:59:59","8599_1"},
	};
	
	
	
	public String[][] HEQUBUCHANG_TAB = {//合区补偿 区ID是计算的每个玩家的创建区id
//		{"360_2", "2012-12-14 00:01:00", "2012-12-21 00:01:00", "8468_1", "合区补偿礼包1个"},
//		{"360_3", "2012-12-14 00:01:00", "2012-12-21 00:01:00", "8468_3", "合区补偿礼包3个"},
//		{"baidu_2", "2012-12-18 00:01:00", "2012-12-25 00:01:00", "8468_9", "合区补偿礼包9个"},
//		{"pps_5", "2012-12-18 00:01:00", "2012-12-25 00:01:00", "8468_1", "合区补偿礼包1个"},
//		{"pps_6", "2012-12-18 00:01:00", "2012-12-25 00:01:00", "8468_2", "合区补偿礼包2个"},
//		{"7k7k_2", "2012-12-21 00:01:00", "2012-12-28 00:01:00", "8468_5", "合区补偿礼包5个"},
//		{"duowan_9", "2012-12-21 00:01:00", "2012-12-28 00:01:00", "8468_3", "合区补偿礼包3个"},
//		{"duowan_10", "2012-12-21 00:01:00", "2012-12-28 00:01:00", "8468_7", "合区补偿礼包7个"},
//		{"kuaiwan_9", "2012-12-21 00:01:00", "2012-12-28 00:01:00", "8468_4", "合区补偿礼包4个"},
//		{"swkedou_2", "2012-12-21 00:01:00", "2012-12-28 00:01:00", "8468_7", "合区补偿礼包7个"},
//		{"kuaiwan_8", "2012-12-26 00:01:00", "2013-01-02 00:01:00", "8468_6", "合区补偿礼包6个"},
//		{"360_5", "2012-12-27 00:01:00", "2013-01-03 00:01:00", "8468_2", "合区补偿礼包2个"},
//		{"360_6", "2012-12-27 00:01:00", "2013-01-03 00:01:00", "8468_4", "合区补偿礼包4个"},
//		{"baidu_4", "2012-12-28 00:01:00", "2013-01-04 00:01:00", "8468_5", "合区补偿礼包5个"},  //百度 4服 礼包5
//		{"pps_8", "2012-12-28 00:01:00", "2013-01-04 00:01:00", "8468_2", "合区补偿礼包2个"},    //PPS 8服 礼包2
//		{"pps_9", "2012-12-28 00:01:00", "2013-01-04 00:01:00", "8468_4", "合区补偿礼包4个"},	//PPS 9服 礼包4
//		{"xunlei_2", "2012-12-28 00:01:00", "2013-01-04 00:01:00", "8468_5", "合区补偿礼包5个"},	//迅雷2 礼包5
//		{"52xiyou_2", "2012-12-28 00:01:00", "2013-01-04 00:01:00", "8468_4", "合区补偿礼包4个"},	//西游2 礼包4
//		{"52xiyou_4", "2012-12-28 00:01:00", "2013-01-04 00:01:00", "8468_1", "合区补偿礼包1个"}, 	//西游4礼包1
//		{"51com_2", "2012-12-29 00:01:00", "2013-01-05 00:01:00", "8468_6", "合区补偿礼包6个"},		//51com2 礼包6
//		{"kuaiwan_13", "2012-12-28 00:01:00", "2013-01-04 00:01:00", "8468_4", "合区补偿礼包4个"}		//快玩13 礼包4
//		{"7k7k_4", "2013-01-05 00:01:00", "2013-01-12 00:01:00", "8468_4", "合区补偿礼包4个"},   //7k7k 4服 4个
//		{"7k7k_6", "2013-01-05 00:01:00", "2013-01-12 00:01:00", "8468_2", "合区补偿礼包2个"},	//7k7k 6服 2个
//		{"pps_11", "2013-01-09 00:01:00", "2013-01-16 00:01:00", "8468_2", "合区补偿礼包2个"},   //pps 11 2
//		{"pps_12", "2013-01-09 00:01:00", "2013-01-16 00:01:00", "8468_4", "合区补偿礼包4个"},	//pps 12 4		
//		{"pps_14", "2013-01-09 00:01:00", "2013-01-16 00:01:00", "8468_2", "合区补偿礼包2个"},	//pps 14 2
//		{"pps_15", "2013-01-09 00:01:00", "2013-01-16 00:01:00", "8468_4", "合区补偿礼包4个"},	//pps 15 4
//		{"xunlei_4", "2013-01-07 00:01:00", "2013-01-14 00:01:00", "8468_4", "合区补偿礼包4个"},	//xunlei 4 4
//		{"kaixin_2", "2013-01-07 00:01:00", "2013-01-14 00:01:00", "8468_12", "合区补偿礼包12个"},	//kaixin 2 12
//		{"51com_4", "2013-01-11 00:01:00", "2013-01-18 00:01:00", "8468_3", "合区补偿礼包3个"},	//51com 4 3
//		{"51com_5", "2013-01-11 00:01:00", "2013-01-18 00:01:00", "8468_5", "合区补偿礼包5个"},	//51com 5 5
//		{"52xiyou_5", "2013-01-11 00:01:00", "2013-01-18 00:01:00", "8468_7", "合区补偿礼包7个"},	//xiyou 5 7
//		{"52xiyou_7", "2013-01-11 00:01:00", "2013-01-18 00:01:00", "8468_2", "合区补偿礼包2个"},	//xiyou 7 2
//		{"52xiyou_8", "2013-01-11 00:01:00", "2013-01-18 00:01:00", "8468_6", "合区补偿礼包6个"},	//xiyou 8 6
//		{"52xiyou_2", "2013-01-11 00:01:00", "2013-01-18 00:01:00", "8468_4", "合区补偿礼包4个"},	//sougou 2 4
//		{"kuaiwan_11", "2013-01-12 00:01:00", "2013-01-19 00:01:00", "8468_7", "合区补偿礼包7个"},    //合11/13服补偿7个合服礼包
//		{"kuaiwan_13", "2013-01-12 00:01:00", "2013-01-19 00:01:00", "8468_7", "合区补偿礼包7个"},
//		{"kuaiwan_17", "2013-01-12 00:01:00", "2013-01-19 00:01:00", "8468_4", "合区补偿礼包4个"},	//17服服补偿4个合服礼包
//		{"kuaiwan_4", "2013-01-15 00:01:00", "2013-01-22 00:01:00", "8468_3", "合区补偿礼包3个"},	//kuaiwan 4 3
//		{"kuaiwan_8", "2013-01-15 00:01:00", "2013-01-22 00:01:00", "8468_3", "合区补偿礼包3个"},	//kuaiwan 8 3
//		{"xunlei_6", "2013-01-16 00:01:00", "2013-01-23 00:01:00", "8468_4", "合区补偿礼包4个"},		//xunlei 6 4
//		{"duowan_17", "2013-01-18 00:01:00", "2013-01-25 00:01:00", "8468_3", "合区补偿礼包3个"},   //duowan 17 3
//		{"duowan_18", "2013-01-18 00:01:00", "2013-01-25 00:01:00", "8468_8", "合区补偿礼包8个"},   //duowan 18 8
//		{"duowan_20", "2013-01-18 00:01:00", "2013-01-25 00:01:00", "8468_4", "合区补偿礼包4个"},   //duowan 20 4
//		{"22kk_2", "2013-01-21 00:01:00", "2013-01-28 00:01:00", "8468_7", "合区补偿礼包7个"},   //22kk 2 7
//		{"22kk_3", "2013-01-21 00:01:00", "2013-01-28 00:01:00", "8468_10", "合区补偿礼包10个"},   //22kk 3 10
//		{"gtv_2", "2013-01-21 00:01:00", "2013-01-28 00:01:00", "8468_41", "合区补偿礼包41个"},   //gtv 2 41
//		{"duowan_15", "2013-01-21 00:01:00", "2013-01-28 00:01:00", "8468_6", "合区补偿礼包6个"},   //duowan 15 6
//		{"duowan_22", "2013-01-21 00:01:00", "2013-01-28 00:01:00", "8468_3", "合区补偿礼包3个"},   //duowan 22 3
//		{"xunlei_8", "2013-01-22 00:01:00", "2013-01-29 00:01:00", "8468_5", "合区补偿礼包5个"}, // xunlei 8 5
//		{"xunlei_10", "2013-01-22 00:01:00", "2013-01-29 00:01:00", "8468_5", "合区补偿礼包5个"}, // xunlei 10 5
//		{"baidu_6", "2013-01-22 00:01:00", "2013-01-29 00:01:00", "8468_6", "合区补偿礼包6个"}, // baidu 6 6
//		{"baidu_8", "2013-01-22 00:01:00", "2013-01-29 00:01:00", "8468_5", "合区补偿礼包5个"}, // baidu 8 5
//		{"kuaiwan_21", "2013-01-22 00:01:00", "2013-01-29 00:01:00", "8468_4", "合区补偿礼包4个"}, // kuaiwan 21 4
//		{"kuaiwan_5", "2013-01-24 00:01:00", "2013-01-31 00:01:00", "8468_14", "合区补偿礼包14个"}, //kuaiwan 5 14
//		{"kuaiwan_10", "2013-01-24 00:01:00", "2013-01-31 00:01:00", "8468_4", "合区补偿礼包4个"}, //kuaiwan 10 4
//		{"kuaiwan_28", "2013-01-24 00:01:00", "2013-01-31 00:01:00", "8468_7", "合区补偿礼包7个"}, //kuaiwan 28 7
//		{"kuaiwan_20", "2013-01-24 00:01:00", "2013-01-31 00:01:00", "8468_7", "合区补偿礼包7个"}, //kuaiwan 20 7
//		{"kuaiwan_19", "2013-01-24 00:01:00", "2013-01-31 00:01:00", "8468_7", "合区补偿礼包7个"}, //kuaiwan 19 7
//		{"kuaiwan_22", "2013-01-24 00:01:00", "2013-01-31 00:01:00", "8468_7", "合区补偿礼包7个"}, //kuaiwan 22 7
//		{"fengxing_3", "2013-01-28 00:01:00", "2013-02-05 00:01:00", "8468_15", "合区补偿礼包15个"}, //fengxing 3 15
//		{"fengxing_4", "2013-01-28 00:01:00", "2013-02-05 00:01:00", "8468_15", "合区补偿礼包15个"}, //fengxing 4 15
		//
//		{"52xiyou_3", "2013-01-30 00:01:00", "2013-02-07 00:01:00", "8468_5", "合区补偿礼包5个"}, //xiyou 3 5
//		{"52xiyou_4", "2013-01-30 00:01:00", "2013-02-07 00:01:00", "8468_5", "合区补偿礼包5个"}, //xiyou 4 5
//		{"52xiyou_5", "2013-01-30 00:01:00", "2013-02-07 00:01:00", "8468_5", "合区补偿礼包5个"}, //xiyou 5 5
//		{"52xiyou_9", "2013-01-30 00:01:00", "2013-02-07 00:01:00", "8468_10", "合区补偿礼包10个"}, //xiyou 9 10
//		{"91wan_2", "2013-01-30 00:01:00", "2013-02-07 00:01:00", "8468_6", "合区补偿礼包6个"}, //91wan 2 6
//		{"91wan_3", "2013-01-30 00:01:00", "2013-02-07 00:01:00", "8468_10", "合区补偿礼包10个"}, //91wan 3 10
//		{"37ww_2", "2013-01-30 00:01:00", "2013-02-07 00:01:00", "8468_11", "合区补偿礼包11个"}, //37ww  2 11
		//1月31日
//		{"360_8", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_3", "合区补偿礼包3个"},//360 8 3
//		{"360_9", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_6", "合区补偿礼包6个"},//360 9 6
//		{"360_11", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_3", "合区补偿礼包3个"},//360 11 3
//		{"360_12", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_6", "合区补偿礼包6个"},//360 12 6
//		{"360_14", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_3", "合区补偿礼包3个"},//360 14 3
//		{"360_15", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_6", "合区补偿礼包6个"},//360 15 6 
//		{"360_17", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_2", "合区补偿礼包2个"},//360 17 2
//		{"360_18", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_4", "合区补偿礼包4个"},//360 18 4
//		{"baidu_10", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_5", "合区补偿礼包5个"},//baidu 10 5
//		{"baidu_11", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_9", "合区补偿礼包9个"},//baidu 11 9
//		{"baidu_13", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_4", "合区补偿礼包4个"},//baidu 13 4
//		{"baidu_15", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_2", "合区补偿礼包2个"},//baidu 15 2
//		{"baidu_16", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_4", "合区补偿礼包4个"},//baidu 16 4
//		{"duowan_2", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_2", "合区补偿礼包2个"},//duowan 2 2
//		{"duowan_3", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_2", "合区补偿礼包2个"},//duowan 3 2
//		{"duowan_6", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_8", "合区补偿礼包8个"},//duowan 6 8
//		{"duowan_7", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_8", "合区补偿礼包8个"},//duowan 7 8
//		{"duowan_11", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_11", "合区补偿礼包11个"},//duowan 11 11
//		{"duowan_12", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_11", "合区补偿礼包11个"},//duowan 12 11
//		{"sougou_6", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_4", "合区补偿礼包4个"},//sougou 6 4
//		{"sougou_12", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_2", "合区补偿礼包2个"},//sougou 12 2
//		{"sougou_13", "2013-01-31 00:01:00", "2013-02-08 00:01:00", "8468_4", "合区补偿礼包4个"},//sougou 13 4
		//2月1日
//		{"360_20", "2013-02-01 00:01:00", "2013-02-09 00:01:00", "8468_3", "合区补偿礼包3个"},//360 20 3
//		{"360_21", "2013-02-01 00:01:00", "2013-02-09 00:01:00", "8468_6", "合区补偿礼包6个"},//360 21 6
//		{"360_24", "2013-02-01 00:01:00", "2013-02-09 00:01:00", "8468_3", "合区补偿礼包3个"},//360 23 3
//		{"360_24", "2013-02-01 00:01:00", "2013-02-09 00:01:00", "8468_6", "合区补偿礼包6个"},//360 24 6
//		{"pps_17", "2013-02-01 00:01:00", "2013-02-09 00:01:00", "8468_7", "合区补偿礼包7个"},//pps 17 7
//		{"pps_19", "2013-02-01 00:01:00", "2013-02-09 00:01:00", "8468_7", "合区补偿礼包7个"},//pps 19 7
//		{"pps_21", "2013-02-01 00:01:00", "2013-02-09 00:01:00", "8468_4", "合区补偿礼包4个"},//pps 21 4
//		{"xunlei_12", "2013-02-01 00:01:00", "2013-02-09 00:01:00", "8468_2", "合区补偿礼包2个"},//xunlei 12 2
//		{"7k7k_3", "2013-02-01 00:01:00", "2013-02-09 00:01:00", "8468_8", "合区补偿礼包8个"},//7k7k 3 8
//		{"7k7k_4", "2013-02-01 00:01:00", "2013-02-09 00:01:00", "8468_8", "合区补偿礼包8个"},//7k7k 4 8
//		{"7k7k_8", "2013-02-01 00:01:00", "2013-02-09 00:01:00", "8468_2", "合区补偿礼包2个"},//7k7k 8 2
//		{"7k7k_10", "2013-02-01 00:01:00", "2013-02-09 00:01:00", "8468_2", "合区补偿礼包2个"},//7k7k 10 2
//		//2月2日
//		{"49you_2", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_5", "合区补偿礼包5个"},//49you 2 5
//		{"49you_4", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_3", "合区补偿礼包3个"},//49you 4 3
//		{"49you_5", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_5", "合区补偿礼包5个"},//49you 5 5
//		{"49you_11", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_1", "合区补偿礼包1个"},//49you 11 1
//		{"49you_7", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_1", "合区补偿礼包1个"},//49you 7 1
//		{"51wan_2", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_11", "合区补偿礼包11个"},//51wan 2 11
//		{"51wan_3", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_18", "合区补偿礼包18个"},//51wan 3 18
//		{"51wan_5", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_3", "合区补偿礼包3个"},//51wan 5 3
//		{"51wan_6", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_7", "合区补偿礼包7个"},//51wan 6 7
//		{"51wan_8", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_5", "合区补偿礼包5个"},//51wan 8 5
//		{"51wan_9", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_8", "合区补偿礼包8个"},//51wan 9 8
//		{"weiyou_4", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_7", "合区补偿礼包7个"},//weiyou 4 7
//		{"weiyou_5", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_14", "合区补偿礼包14个"},//weiyou 5 14
//		{"sjjy_2", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_11", "合区补偿礼包11个"},//sjjy 2 11
//		{"7k7k_12", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_3", "合区补偿礼包3个"},//7k7k 12 3
//		{"7k7k_14", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_2", "合区补偿礼包2个"},//7k7k 14 2
//		{"swkedou_4", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_11", "合区补偿礼包11个"},//swkedou 4 11
//		{"swkedou_6", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_9", "合区补偿礼包9个"},//swkedou 6 9
//		{"kugou_2", "2013-02-02 00:01:00", "2013-02-10 00:01:00", "8468_5", "合区补偿礼包5个"},//kugou 2 5
//		//2月3日
//		//2月4日
//		{"fengxing_6", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_6", "合区补偿礼包6个"},//fengxing 6 6
//		{"fengxing_8", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_7", "合区补偿礼包7个"},//fengxing 8 7
//		{"fengxing_2", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_15", "合区补偿礼包15个"},//fengxing 2 15
//		{"jinshan_2", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_15", "合区补偿礼包15个"},//jinshan 2 15
//		{"xunlei_14", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_3", "合区补偿礼包3个"},//xunlei 14 3
//		{"xunlei_15", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_5", "合区补偿礼包5个"},//xunlei 15 5
//		{"xunlei_16", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_7", "合区补偿礼包7个"},//xunlei 16 7
//		{"kuaiwan_3", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_14", "合区补偿礼包14个"},//kuaiwan 3 14
//		{"kuaiwan_4", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_14", "合区补偿礼包14个"},//kuaiwan 4 14
//		{"kuaiwan_8", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_14", "合区补偿礼包14个"},//kuaiwan 8 14
//		{"kuaiwan_6", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_23", "合区补偿礼包23个"},//kuaiwan 6 23
//		{"kuaiwan_9", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_23", "合区补偿礼包23个"},//kuaiwan 9 23
//		{"kuaiwan_11", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_23", "合区补偿礼包23个"},//kuaiwan 11 23
//		{"kuaiwan_13", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_23", "合区补偿礼包23个"},//kuaiwan 13 23
//		{"kuaiwan_14", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_4", "合区补偿礼包4个"},//kuaiwan 14 4
//		{"kuaiwan_21", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_6", "合区补偿礼包6个"},//kuaiwan 21 6
//		{"kuaiwan_25", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_5", "合区补偿礼包5个"},//kuaiwan 25 5
//		{"kuaiwan_28", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_5", "合区补偿礼包5个"},//kuaiwan 28 5
//		{"kuaiwan_27", "2013-02-04 00:01:00", "2013-02-12 00:01:00", "8468_3", "合区补偿礼包3个"},//kuaiwan 27 3
//		//2月5日
//		{"duowan_13", "2013-02-05 00:01:00", "2013-02-13 00:01:00", "8468_7", "合区补偿礼包7个"},//duowan 13 7
//		{"duowan_14", "2013-02-05 00:01:00", "2013-02-13 00:01:00", "8468_7", "合区补偿礼包7个"},//duowan 14 7
//		{"duowan_15", "2013-02-05 00:01:00", "2013-02-13 00:01:00", "8468_7", "合区补偿礼包7个"},//duowan 15 7
//		{"duowan_19", "2013-02-05 00:01:00", "2013-02-13 00:01:00", "8468_11", "合区补偿礼包11个"},//duowan 19 11
//		{"duowan_20", "2013-02-05 00:01:00", "2013-02-13 00:01:00", "8468_11", "合区补偿礼包11个"},//duowan 20 11
//		{"duowan_23", "2013-02-05 00:01:00", "2013-02-13 00:01:00", "8468_7", "合区补偿礼包7个"},//duowan 23 7
//		{"2133_2", "2013-02-20 00:01:00", "2013-02-28 00:01:00", "8468_10", "合区补偿礼包10个"},//2133 2 10
//		{"pps_23", "2013-02-20 00:01:00", "2013-02-28 00:01:00", "8468_7", "合区补偿礼包7个"},//pps 23 7
//		{"pps_24", "2013-02-20 00:01:00", "2013-02-28 00:01:00", "8468_13", "合区补偿礼包13个"},//pps 24 13
//		{"pps_26", "2013-02-20 00:01:00", "2013-02-28 00:01:00", "8468_3", "合区补偿礼包3个"},//pps 26 3
		//2月21日
		{"jinshan_4", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_16", "合区补偿礼包16个"},//jinshan 4 16
		{"jinshan_5", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_21", "合区补偿礼包21个"},//jinshan 5 21
		{"zixia_2", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_7", "合区补偿礼包7个"},//zixia 2 7
		{"51wan_7", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_11", "合区补偿礼包11个"},//51wan 7 11
		{"51wan_8", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_11", "合区补偿礼包11个"},//51wan 8 11
		{"51wan_9", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_11", "合区补偿礼包11个"},//51wan 9 11
		{"51wan_11", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_3", "合区补偿礼包3个"},//51wan 11 3
		{"51wan_12", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_6", "合区补偿礼包6个"},//51wan 12 6
		{"51wan_14", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_2", "合区补偿礼包2个"},//51wan 14 2
		{"51wan_15", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_3", "合区补偿礼包3个"},//51wan 15 3
		{"49you_8", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_2", "合区补偿礼包2个"},//49you 8 2
		{"49you_9", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_3", "合区补偿礼包3个"},//49you 9 3 
		{"49you_12", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_2", "合区补偿礼包2个"},//49you 12 2
		{"49you_14", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_1", "合区补偿礼包1个"},//49you 14 1
		{"49you_15", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_2", "合区补偿礼包2个"},//49you 15 2
		{"49you_17", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_1", "合区补偿礼包1个"},//49you 17 1
		{"49you_18", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_3", "合区补偿礼包2个"},//49you 18 2
		{"49you_20", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_1", "合区补偿礼包1个"},//49you 20 1
		{"49you_21", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_2", "合区补偿礼包2个"},//49you 21 2
		{"49you_23", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_1", "合区补偿礼包1个"},//49you 23 1
		{"49you_24", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_2", "合区补偿礼包2个"},//49you 24 2
		{"49you_27", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_1", "合区补偿礼包1个"},//49you 27 1
		{"49you_29", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_1", "合区补偿礼包1个"},//49you 29 1
		{"49you_30", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_2", "合区补偿礼包2个"},//49you 30 2
		{"49you_31", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_4", "合区补偿礼包4个"},//49you 31 4
		{"49you_33", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_1", "合区补偿礼包1个"},//49you 33 1
		{"49you_34", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_1", "合区补偿礼包1个"},//49you 34 1
		{"49you_35", "2013-02-21 00:01:00", "2013-02-29 00:01:00", "8468_2", "合区补偿礼包2个"},//49you 35 2
		//2月22日
		{"tiexue_2", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_7", "合区补偿礼包7个"},//tiexue 2 7
		{"tiexue_3", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_21", "合区补偿礼包21个"},//tiexue 3 21
		{"game168_2", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_4", "合区补偿礼包4个"},//game168 2 4
		{"game168_3", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_6", "合区补偿礼包6个"},//game168 3 6
		{"game168_4", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_7", "合区补偿礼包7个"},//game168 4 7
		{"game168_6", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_2", "合区补偿礼包2个"},//game168 6 2
		{"game168_7", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_4", "合区补偿礼包4个"},//game168 7 4
		{"game168_8", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_5", "合区补偿礼包5个"},//game168 8 5
		{"7k7k_5", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_16", "合区补偿礼包16个"},//7k7k 5 16
		{"7k7k_6", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_16", "合区补偿礼包16个"},//7k7k 6 16
		{"7k7k_9", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_5", "合区补偿礼包5个"},//7k7k 9 5
		{"7k7k_10", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_5", "合区补偿礼包5个"},//7k7k 10 5
		{"7k7k_13", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_5", "合区补偿礼包5个"},//7k7k 13 5
		{"7k7k_14", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_5", "合区补偿礼包5个"},//7k7k 14 5
		{"7k7k_16", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_7", "合区补偿礼包7个"},//7k7k 16 7
		{"7k7k_18", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_7", "合区补偿礼包7个"},//7k7k 18 7
		{"kuaiwan_16", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_16", "合区补偿礼包16个"},//kuaiwan 16
		{"kuaiwan_20", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_20", "合区补偿礼包20个"},//kuaiwan 20
		{"kuaiwan_29", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_29", "合区补偿礼包29个"},//kuaiwan 29
		{"kuaiwan_34", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_34", "合区补偿礼包34个"},//kuaiwan 34
		{"xunlei_18", "2013-02-22 00:01:00", "2013-02-30 00:01:00", "8468_18", "合区补偿礼包18个"},//xunlei 18
		//2月23日合服礼包
		{"360_29", "2013-02-23 00:01:00", "2013-02-30 00:01:00", "8468_2", "合区补偿礼包2个"},//360 29 2
		{"360_30", "2013-02-23 00:01:00", "2013-02-30 00:01:00", "8468_4", "合区补偿礼包4个"},//360 30 4
		{"360_32", "2013-02-23 00:01:00", "2013-02-30 00:01:00", "8468_2", "合区补偿礼包2个"},//360 32 2
		{"360_33", "2013-02-23 00:01:00", "2013-02-30 00:01:00", "8468_4", "合区补偿礼包4个"},//360 33 4
		{"360_35", "2013-02-23 00:01:00", "2013-02-30 00:01:00", "8468_2", "合区补偿礼包2个"},//360 35 2
		{"360_36", "2013-02-23 00:01:00", "2013-02-30 00:01:00", "8468_4", "合区补偿礼包4个"},//360 36 4
		{"baidu_18", "2013-02-23 00:01:00", "2013-02-30 00:01:00", "8468_2", "合区补偿礼包2个"},//baidu 18 2
		{"baidu_19", "2013-02-23 00:01:00", "2013-02-30 00:01:00", "8468_4", "合区补偿礼包4个"},//baidu 19 4
		{"baidu_21", "2013-02-23 00:01:00", "2013-02-30 00:01:00", "8468_1", "合区补偿礼包1个"},//baidu 21 1
		{"baidu_22", "2013-02-23 00:01:00", "2013-02-30 00:01:00", "8468_2", "合区补偿礼包2个"},//baidu 22 2
		{"baidu_24", "2013-02-23 00:01:00", "2013-02-30 00:01:00", "8468_1", "合区补偿礼包1个"},//baidu 24 1
		{"baidu_25", "2013-02-23 00:01:00", "2013-02-30 00:01:00", "8468_2", "合区补偿礼包2个"},//baidu 25 2
		//2月26日合服礼包
		{"baidu_27", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_1", "合区补偿礼包1个"},//baidu 27 1
		{"baidu_28", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_2", "合区补偿礼包2个"},//baidu 28 2
		{"baidu_30", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_1", "合区补偿礼包1个"},//baidu 30 1
		{"baidu_31", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_2", "合区补偿礼包2个"},//baidu 31 2
		{"baidu_33", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_1", "合区补偿礼包1个"},//baidu 33 1
		{"baidu_34", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_1", "合区补偿礼包1个"},//baidu 34 1
		{"baidu_37", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_1", "合区补偿礼包1个"},//baidu 37 1
		{"baidu_39", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_1", "合区补偿礼包1个"},//baidu 39 1
		{"baidu_40", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_2", "合区补偿礼包2个"},//baidu 40 2
		{"baidu_41", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_2", "合区补偿礼包2个"},//baidu 41 2
		{"kugou_4", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_7", "合区补偿礼包7个"},//kugou 4 7
		{"pps_4", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_2", "合区补偿礼包2个"},//pps 4 2
		{"pps_5", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_2", "合区补偿礼包2个"},//pps 5 2
		{"pps_6", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_2", "合区补偿礼包2个"},//pps 6 2
		{"pps_7", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_5", "合区补偿礼包5个"},//pps 7 5
		{"pps_8", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_5", "合区补偿礼包5个"},//pps 8 5
		{"pps_9", "2013-02-26 00:01:00", "2013-03-06 00:01:00", "8468_5", "合区补偿礼包5个"},//pps 9 5
		//27日
		{"360_26", "2013-02-27 00:01:00", "2013-03-07 00:01:00", "8468_2", "合区补偿礼包2个"},//360 26 2
		{"360_27", "2013-02-27 00:01:00", "2013-03-07 00:01:00", "8468_4", "合区补偿礼包4个"},//360 27 4 
		{"kuaiwan_32", "2013-02-27 00:01:00", "2013-03-07 00:01:00", "8468_2", "合区补偿礼包2个"},//kuaiwan 32 2
		{"kuaiwan_39", "2013-02-27 00:01:00", "2013-03-07 00:01:00", "8468_4", "合区补偿礼包4个"},//kuaiwan 39 4
		{"kuaiwan_41", "2013-02-27 00:01:00", "2013-03-07 00:01:00", "8468_2", "合区补偿礼包2个"},//
		//3月1日合服礼包
		{"duowan_25", "2013-03-01 00:01:00", "2013-03-08 00:01:00", "8468_4", "合区补偿礼包4个"},//duowan 25 4
		{"duowan_26", "2013-03-01 00:01:00", "2013-03-08 00:01:00", "8468_7", "合区补偿礼包7个"},//duowan 26 7
		{"duowan_28", "2013-03-01 00:01:00", "2013-03-08 00:01:00", "8468_2", "合区补偿礼包2个"},//duowan 28 2
		{"duowan_29", "2013-03-01 00:01:00", "2013-03-08 00:01:00", "8468_3", "合区补偿礼包3个"},//duowan 29 3
		{"xunlei_20", "2013-03-01 00:01:00", "2013-03-08 00:01:00", "8468_4", "合区补偿礼包4个"},//xunlei 20 4
		//3月4日合服礼包
		{"baidu_43", "2013-03-04 00:01:00", "2013-03-12 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 43 1
		{"baidu_44", "2013-03-04 00:01:00", "2013-03-12 00:01:00", "8468_2", "合区补偿礼包2个"},// baidu 44 2
		{"baidu_47", "2013-03-04 00:01:00", "2013-03-12 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 47 1
		{"baidu_49", "2013-03-04 00:01:00", "2013-03-12 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 49 1
		{"baidu_50", "2013-03-04 00:01:00", "2013-03-12 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 50 1
		{"youyang_2", "2013-03-04 00:01:00", "2013-03-12 00:01:00", "8468_2", "合区补偿礼包2个"},// youyang 2 2
		{"youyang_3", "2013-03-04 00:01:00", "2013-03-12 00:01:00", "8468_4", "合区补偿礼包4个"},// youyang 3 4
		{"youyang_5", "2013-03-04 00:01:00", "2013-03-12 00:01:00", "8468_1", "合区补偿礼包1个"},// youyang 5 1
		{"youyang_6", "2013-03-04 00:01:00", "2013-03-12 00:01:00", "8468_2", "合区补偿礼包2个"},// youyang 6 2
		{"youyang_8", "2013-03-04 00:01:00", "2013-03-12 00:01:00", "8468_1", "合区补偿礼包1个"},// youyang 8 1
		{"youyang_9", "2013-03-04 00:01:00", "2013-03-12 00:01:00", "8468_2", "合区补偿礼包2个"},// youyang 9 2
		{"swkedou_6", "2013-03-04 00:01:00", "2013-03-12 00:01:00", "8468_9", "合区补偿礼包9个"},// swkedou 6 9
		{"swkedou_8", "2013-03-04 00:01:00", "2013-03-12 00:01:00", "8468_11", "合区补偿礼包11个"},// swkedou 8 11
		{"gtv_4", "2013-03-04 00:01:00", "2013-03-12 00:01:00", "8468_22", "合区补偿礼包22个"},// gtv 4 22
		//3月6日合服礼包
		{"kuaiwan_12", "2013-03-06 00:01:00", "2013-03-14 00:01:00", "8468_7", "合区补偿礼包7个"},// kuaiwan 12 7
		{"kuaiwan_14", "2013-03-06 00:01:00", "2013-03-14 00:01:00", "8468_7", "合区补偿礼包7个"},// kuaiwan 14 7
		{"kuaiwan_38", "2013-03-06 00:01:00", "2013-03-14 00:01:00", "8468_4", "合区补偿礼包4个"},// kuaiwan 38 4
		{"kuaiwan_43", "2013-03-06 00:01:00", "2013-03-14 00:01:00", "8468_2", "合区补偿礼包2个"},// kuaiwan 43 2
		{"weiyou_7", "2013-03-06 00:01:00", "2013-03-14 00:01:00", "8468_5", "合区补偿礼包5个"},// weiyou 7 5
		{"weiyou_8", "2013-03-06 00:01:00", "2013-03-14 00:01:00", "8468_8", "合区补偿礼包8个"},// weiyou 8 8
		{"51com_11", "2013-03-06 00:01:00", "2013-03-14 00:01:00", "8468_4", "合区补偿礼包4个"},// 51com 11 4
		{"51com_12", "2013-03-06 00:01:00", "2013-03-14 00:01:00", "8468_6", "合区补偿礼包6个"},// 51com 12 6
		{"51com_13", "2013-03-06 00:01:00", "2013-03-14 00:01:00", "8468_10", "合区补偿礼包10个"},// 51com 13 10
		{"jinshan_7", "2013-03-06 00:01:00", "2013-03-14 00:01:00", "8468_7", "合区补偿礼包7个"},// jinshan 7 7
		{"jinshan_8", "2013-03-06 00:01:00", "2013-03-14 00:01:00", "8468_10", "合区补偿礼包10个"},// jinshan 8 10
		{"sjjy_4", "2013-03-06 00:01:00", "2013-03-14 00:01:00", "8468_8", "合区补偿礼包8个"},// sjjy 4 8
		{"sjjy_5", "2013-03-06 00:01:00", "2013-03-14 00:01:00", "8468_15", "合区补偿礼包15个"},// sjjy 5 15
		//3月7日合服礼包
		{"360_40", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_6", "合区补偿礼包6个"},// 360 40 6
		{"360_41", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_8", "合区补偿礼包8个"},// 360 41 8
		{"360_39", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_2", "合区补偿礼包2个"},// 360 39 2
		{"360_42", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_8", "合区补偿礼包8个"},// 360 42 8
		{"360_44", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_2", "合区补偿礼包2个"},// 360 44 2
		{"360_45", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_4", "合区补偿礼包4个"},// 360 45 4
		{"swkedou_2", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_7", "合区补偿礼包7个"},// swkedou 2 7
		{"swkedou_3", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_15", "合区补偿礼包15个"},// swkedou 3 15
		{"swkedou_4", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_26", "合区补偿礼包26个"},// swkedou 4 26
		{"22kk_4", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_14", "合区补偿礼包14个"},// 22kk 4 14
		{"22kk_5", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_21", "合区补偿礼包21个"},// 22kk 5 21
		{"22kk_6", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_28", "合区补偿礼包28个"},// 22kk 6 28
		{"22kk_7", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_35", "合区补偿礼包35个"},// 22kk 7 35
		{"22kk_8", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_41", "合区补偿礼包41个"},// 22kk 8 41
		{"22kk_9", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_49", "合区补偿礼包49个"},// 22kk 9 49
		{"baofeng_2", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_14", "合区补偿礼包14个"},// baofeng 2 14
		{"37ww_4", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_5", "合区补偿礼包5个"},// 37ww 4 5
		{"7k7k_20", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_7", "合区补偿礼包7个"},// 7k7k 20 7
		{"7k7k_21", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_10", "合区补偿礼包10个"},// 7k7k 21 10
		{"7k7k_22", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_12", "合区补偿礼包12个"},// 7k7k 22 12
		{"ruixing_2", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_9", "合区补偿礼包9个"},// ruixing 2 9
		{"ruixing_4", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_15", "合区补偿礼包15个"},// ruixing 4 15
		{"ruixing_5", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_35", "合区补偿礼包35个"},// ruixing 5 35
		{"91wan_4", "2013-03-07 00:01:00", "2013-03-15 00:01:00", "8468_13", "合区补偿礼包13个"},// 91wan 4 13
		//3月8日合服礼包
		{"duowan_31", "2013-03-08 00:01:00", "2013-03-16 00:01:00", "8468_3", "合区补偿礼包3个"},// duowan 31 3
		{"duowan_34", "2013-03-08 00:01:00", "2013-03-16 00:01:00", "8468_4", "合区补偿礼包4个"},// duowan 34 4
		{"duowan_35", "2013-03-08 00:01:00", "2013-03-16 00:01:00", "8468_7", "合区补偿礼包7个"},// duowan 35 7
		{"sougou_4", "2013-03-08 00:01:00", "2013-03-16 00:01:00", "8468_4", "合区补偿礼包4个"},// sougou 4 4
		{"sougou_8", "2013-03-08 00:01:00", "2013-03-16 00:01:00", "8468_3", "合区补偿礼包3个"},// sougou 8 3
		{"sougou_10", "2013-03-08 00:01:00", "2013-03-16 00:01:00", "8468_2", "合区补偿礼包2个"},// sougou 10 2
		{"sougou_15", "2013-03-08 00:01:00", "2013-03-16 00:01:00", "8468_4", "合区补偿礼包4个"},// sougou 15 4
		{"sougou_16", "2013-03-08 00:01:00", "2013-03-16 00:01:00", "8468_7", "合区补偿礼包7个"},// sougou 16 7
		{"aoyou_2", "2013-03-08 00:01:00", "2013-03-16 00:01:00", "8468_10", "合区补偿礼包10个"},// aoyou 2 10
		{"aoyou_4", "2013-03-08 00:01:00", "2013-03-16 00:01:00", "8468_3", "合区补偿礼包3个"},// aoyou 4 3
		{"aoyou_6", "2013-03-08 00:01:00", "2013-03-16 00:01:00", "8468_5", "合区补偿礼包5个"},// aoyou 6 5
		{"kaixin_4", "2013-03-08 00:01:00", "2013-03-16 00:01:00", "8468_17", "合区补偿礼包17个"},// kaixin 4 17
		{"zixia_4", "2013-03-08 00:01:00", "2013-03-16 00:01:00", "8468_15", "合区补偿礼包15个"},// zixia 4 15
		//3月9日合服礼包
		{"kuaiwan_15", "2013-03-09 00:01:00", "2013-03-16 00:01:00", "8468_30", "合区补偿礼包30个"},// kuaiwan 15 30
		{"kuaiwan_16", "2013-03-09 00:01:00", "2013-03-16 00:01:00", "8468_30", "合区补偿礼包30个"},// kuaiwan 16 30
		{"kuaiwan_17", "2013-03-09 00:01:00", "2013-03-16 00:01:00", "8468_30", "合区补偿礼包30个"},// kuaiwan 17 30
		{"kuaiwan_19", "2013-03-09 00:01:00", "2013-03-16 00:01:00", "8468_30", "合区补偿礼包30个"},// kuaiwan 19 30
		{"kuaiwan_20", "2013-03-09 00:01:00", "2013-03-16 00:01:00", "8468_30", "合区补偿礼包30个"},// kuaiwan 20 30
		{"kuaiwan_22", "2013-03-09 00:01:00", "2013-03-16 00:01:00", "8468_30", "合区补偿礼包30个"},// kuaiwan 22 30
		{"kuaiwan_23", "2013-03-09 00:01:00", "2013-03-16 00:01:00", "8468_11", "合区补偿礼包11个"},// kuaiwan 23 11
		{"kuaiwan_25", "2013-03-09 00:01:00", "2013-03-16 00:01:00", "8468_11", "合区补偿礼包11个"},// kuaiwan 25 11
		{"kuaiwan_28", "2013-03-09 00:01:00", "2013-03-16 00:01:00", "8468_11", "合区补偿礼包11个"},// kuaiwan 28 11
		{"kuaiwan_49", "2013-03-09 00:01:00", "2013-03-16 00:01:00", "8468_1", "合区补偿礼包1个"},// kuaiwan 49 1
		//3月11日合服礼包
		{"51com_15", "2013-03-11 00:01:00", "2013-03-18 00:01:00", "8468_4", "合区补偿礼包4个"},// 51com 15 4
		{"51com_16", "2013-03-11 00:01:00", "2013-03-18 00:01:00", "8468_10", "合区补偿礼包10个"},// 51com 16 10
		{"51com_17", "2013-03-11 00:01:00", "2013-03-18 00:01:00", "8468_17", "合区补偿礼包17个"},// 51com 17 17
		//3月12日合服礼包
		{"game168_10", "2013-03-12 00:01:00", "2013-03-19 00:01:00", "8468_1", "合区补偿礼包1个"},// game168 10 1
		{"game168_12", "2013-03-12 00:01:00", "2013-03-19 00:01:00", "8468_1", "合区补偿礼包1个"},// game168 12 1
		{"game168_13", "2013-03-12 00:01:00", "2013-03-19 00:01:00", "8468_2", "合区补偿礼包2个"},// game168 13 2
		//3月14日合服礼包
		{"pps_13", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_8", "合区补偿礼包8个"},// pps 13 8
		{"pps_14", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_8", "合区补偿礼包8个"},// pps 14 8
		{"pps_15", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_8", "合区补偿礼包8个"},// pps 15 8
		{"pps_28", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_5", "合区补偿礼包5个"},// pps 28 5
		{"pps_30", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_4", "合区补偿礼包4个"},// pps 30 4
		{"xunlei_22", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_6", "合区补偿礼包6个"},// xunlei 22 6
		{"kuaiwan_30", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_10", "合区补偿礼包10个"},// kuaiwan 30 10
		{"kuaiwan_40", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_5", "合区补偿礼包5个"},// kuaiwan 40 5
		{"kuaiwan_41", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_5", "合区补偿礼包5个"},// kuaiwan 41 5
		{"kuaiwan_45", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_2", "合区补偿礼包2个"},// kuaiwan 45 2
		{"kuaiwan_47", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_2", "合区补偿礼包2个"},// kuaiwan 47 2
		{"49you_38", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_2", "合区补偿礼包2个"},// 49you 38 2
		{"49you_42", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_4", "合区补偿礼包4个"},// 49you 42 4
		{"49you_40", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_2", "合区补偿礼包2个"},// 49you 40 2
		{"49you_45", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_5", "合区补偿礼包5个"},// 49you 45 5
		{"49you_47", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_7", "合区补偿礼包7个"},// 49you 47 7
		{"49you_41", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_1", "合区补偿礼包1个"},// 49you 41 1
		{"49you_50", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_4", "合区补偿礼包4个"},// 49you 50 4
		{"49you_46", "2013-03-14 00:01:00", "2013-03-21 00:01:00", "8468_1", "合区补偿礼包1个"},// 49you 46 1
		//3月15日合服礼包
		{"49you_49", "2013-03-15 00:01:00", "2013-03-22 00:01:00", "8468_1", "合区补偿礼包1个"},// 49you 49 1
		{"49you_53", "2013-03-15 00:01:00", "2013-03-22 00:01:00", "8468_1", "合区补偿礼包1个"},// 49you 53 1
		{"49you_54", "2013-03-15 00:01:00", "2013-03-22 00:01:00", "8468_1", "合区补偿礼包1个"},// 49you 54 1
		{"49you_58", "2013-03-15 00:01:00", "2013-03-22 00:01:00", "8468_1", "合区补偿礼包1个"},// 49you 58 1
		{"49you_59", "2013-03-15 00:01:00", "2013-03-22 00:01:00", "8468_2", "合区补偿礼包2个"},// 49you 59 2
		{"49you_61", "2013-03-15 00:01:00", "2013-03-22 00:01:00", "8468_1", "合区补偿礼包1个"},// 49you 61 1
		{"49you_64", "2013-03-15 00:01:00", "2013-03-22 00:01:00", "8468_4", "合区补偿礼包4个"},// 49you 64 4
		{"51com_3", "2013-03-15 00:01:00", "2013-03-22 00:01:00", "8468_11", "合区补偿礼包11个"},// 51com 3 11
		{"51com_4", "2013-03-15 00:01:00", "2013-03-22 00:01:00", "8468_11", "合区补偿礼包11个"},// 51com 4 11
		{"51com_5", "2013-03-15 00:01:00", "2013-03-22 00:01:00", "8468_11", "合区补偿礼包11个"},// 51com 5 11
		//3月16日合服礼包
		{"kugou_6", "2013-03-16 00:01:00", "2013-03-23 00:01:00", "8468_7", "合区补偿礼包7个"},// kugou 6 7
		{"kugou_8", "2013-03-16 00:01:00", "2013-03-23 00:01:00", "8468_4", "合区补偿礼包4个"},// kugou 8 4
		{"pptv_2", "2013-03-16 00:01:00", "2013-03-23 00:01:00", "8468_60", "合区补偿礼包60个"},// pptv 2 60
		{"pptv_3", "2013-03-16 00:01:00", "2013-03-23 00:01:00", "8468_73", "合区补偿礼包73个"},// pptv 3 73
		//3月16日合服礼包
		{"kugou_6", "2013-03-16 00:01:00", "2013-03-23 00:01:00", "8468_7", "合区补偿礼包7个"},// kugou 6 7
		{"kugou_8", "2013-03-16 00:01:00", "2013-03-23 00:01:00", "8468_4", "合区补偿礼包4个"},// kugou 8 4
		{"pptv_2", "2013-03-16 00:01:00", "2013-03-23 00:01:00", "8468_60", "合区补偿礼包60个"},// pptv 2 60
		{"pptv_3", "2013-03-16 00:01:00", "2013-03-23 00:01:00", "8468_73", "合区补偿礼包73个"},// pptv 3 73
		//3月18日合服礼包
		{"baidu_53", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 53 1
		{"baidu_55", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 55 1
		{"baidu_56", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 56 1
		{"baidu_59", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 59 1
		{"baidu_61", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 61 1
		{"baidu_62", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 62 1
		{"baidu_65", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 65 1
		{"youyang_12", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_1", "合区补偿礼包1个"},// youyang 12 1
		{"youyang_13", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_1", "合区补偿礼包1个"},// youyang 13 1
		{"youyang_15", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_1", "合区补偿礼包1个"},// youyang 15 1
		{"youyang_16", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_1", "合区补偿礼包1个"},// youyang 16 1
		{"youyang_19", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_1", "合区补偿礼包1个"},// youyang 19 1
		{"youyang_21", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_1", "合区补偿礼包1个"},// youyang 21 1
		{"youyang_23", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_2", "合区补偿礼包2个"},// youyang 23 2
		{"youyang_24", "2013-03-18 00:01:00", "2013-03-25 00:01:00", "8468_4", "合区补偿礼包4个"},// youyang 24 4
		//3月19日合服礼包
		{"baidu_67", "2013-03-19 00:01:00", "2013-03-26 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 67 1
		{"baidu_68", "2013-03-19 00:01:00", "2013-03-26 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 68 1
		{"baidu_71", "2013-03-19 00:01:00", "2013-03-26 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 71 1
		{"baidu_73", "2013-03-19 00:01:00", "2013-03-26 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 73 1
		{"baidu_74", "2013-03-19 00:01:00", "2013-03-26 00:01:00", "8468_2", "合区补偿礼包2个"},// baidu 74 2
		{"baidu_76", "2013-03-19 00:01:00", "2013-03-26 00:01:00", "8468_1", "合区补偿礼包1个"},// baidu 76 1

	};

	/**
	 * 通用礼金补偿
	 *
	 */
	public void compensatory(Player player) {
		if (player.getLevel() < 20) {
			return;
		}
		
		for (String[] data : BUCHANG_TAB) {
			String platform = WServer.getInstance().getServerWeb();
			long curtime = System.currentTimeMillis();
			int sid = WServer.getInstance().getServerId();
			if (data[0].equals(platform + "_" + sid)) {
				Date beginDate = TimeUtil.getDateByString(data[1]);
				Date endDate = TimeUtil.getDateByString(data[2]);
				if (beginDate.getTime() < curtime && endDate.getTime() > curtime) {
					String key = data[0] + "_" + beginDate.getTime();
					if (!player.getActivitiesReward().containsKey(key)) {
						player.getActivitiesReward().put(key, data[3]);
						long action = Config.getId();
						int[][] rewtab = ManagerPool.zonesFlopManager.getZoneFixedReward(data[3]);
						String str = "";
						for (int[] rewdb : rewtab) {
							//-1铜币，-2元宝，-3真气，-4经验，-5礼金，-6战魂，-7军功
							if (rewdb[0] == -1) {
								ManagerPool.backpackManager.changeMoney(player, rewdb[1], Reasons.def7, action);
								str = str + ResManager.getInstance().getString(" 铜币:") + rewdb[1];
							} else if (rewdb[0] == -2) {
								//元宝为特殊，暂不加
							} else if (rewdb[0] == -3) {
								ManagerPool.playerManager.addZhenqi(player, rewdb[1], AttributeChangeReason.BUCHANG);
								str = str + ResManager.getInstance().getString(" 真气:") + rewdb[1];
							} else if (rewdb[0] == -4) {
								ManagerPool.playerManager.addExp(player, rewdb[1], AttributeChangeReason.BUCHANG);
								str = str + ResManager.getInstance().getString(" 经验:") + rewdb[1];
							} else if (rewdb[0] == -5) {
								ManagerPool.backpackManager.changeBindGold(player, rewdb[1], Reasons.def7, action);
								str = str + ResManager.getInstance().getString(" 礼金:") + rewdb[1];
							} else if (rewdb[0] == -6) {
								ManagerPool.arrowManager.addFightSpiritNum(player, 1, rewdb[1], true, ArrowReasonsType.BUCHANG);
								str = str + ResManager.getInstance().getString(" 战魂:") + rewdb[1];
							} else if (rewdb[0] == -7) {
								ManagerPool.rankManager.addranknum(player, rewdb[1], RankType.BUCHANG);
								str = str + ResManager.getInstance().getString(" 军功:") + rewdb[1];
							} else if (rewdb[0] > 0) {
								List<Item> createItems = Item.createItems(rewdb[0], rewdb[1], true, 0);
								if (!createItems.isEmpty()) {
									if (ManagerPool.backpackManager.getEmptyGridNum(player) >= createItems.size()) {
										if (!BackpackManager.getInstance().addItems(player, createItems, Reasons.def7, action)) {
											MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), "上線補償", data.length > 4 ? data[4] : "補償物品", (byte) 0, 0, createItems);
										} else {
											str = str + String.format(" %s:%d", BackpackManager.getInstance().getName(rewdb[0]), rewdb[1]);
										}
									} else {
										MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), "上線補償", data.length > 4 ? data[4] : "補償物品", (byte) 0, 0, createItems);
									}
								}
							}
						}
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您獲得:{1}"), str);
					}
				}
			}
		}
	}

	/**
	 * 合区补偿
	 *
	 */
	public void hequcompensatory(Player player) {
		for (String[] data : HEQUBUCHANG_TAB) {
			String platform = WServer.getInstance().getServerWeb();
			long curtime = System.currentTimeMillis();
			int sid = player.getCreateServerId();
			if (data[0].equals(platform + "_" + sid)) {
				Date beginDate = TimeUtil.getDateByString(data[1]);
				Date endDate = TimeUtil.getDateByString(data[2]);
				if (beginDate.getTime() < curtime && endDate.getTime() > curtime) {
					String key = data[0] + "_" + beginDate.getTime();
					if (!player.getActivitiesReward().containsKey(key)) {
						player.getActivitiesReward().put(key, data[3]);
						long action = Config.getId();
						int[][] rewtab = ManagerPool.zonesFlopManager.getZoneFixedReward(data[3]);
						String str = "";
						for (int[] rewdb : rewtab) {
							//-1铜币，-2元宝，-3真气，-4经验，-5礼金，-6战魂，-7军功
							if (rewdb[0] == -1) {
								ManagerPool.backpackManager.changeMoney(player, rewdb[1], Reasons.def7, action);
								str = str + ResManager.getInstance().getString(" 铜币:") + rewdb[1];
							} else if (rewdb[0] == -2) {
								//元宝为特殊，暂不加
							} else if (rewdb[0] == -3) {
								ManagerPool.playerManager.addZhenqi(player, rewdb[1], AttributeChangeReason.BUCHANG);
								str = str + ResManager.getInstance().getString(" 真气:") + rewdb[1];
							} else if (rewdb[0] == -4) {
								ManagerPool.playerManager.addExp(player, rewdb[1], AttributeChangeReason.BUCHANG);
								str = str + ResManager.getInstance().getString(" 经验:") + rewdb[1];
							} else if (rewdb[0] == -5) {
								ManagerPool.backpackManager.changeBindGold(player, rewdb[1], Reasons.def7, action);
								str = str + ResManager.getInstance().getString(" 礼金:") + rewdb[1];
							} else if (rewdb[0] == -6) {
								ManagerPool.arrowManager.addFightSpiritNum(player, 1, rewdb[1], true, ArrowReasonsType.BUCHANG);
								str = str + ResManager.getInstance().getString(" 战魂:") + rewdb[1];
							} else if (rewdb[0] == -7) {
								ManagerPool.rankManager.addranknum(player, rewdb[1], RankType.BUCHANG);
								str = str + ResManager.getInstance().getString(" 军功:") + rewdb[1];
							} else if (rewdb[0] > 0) {
								List<Item> createItems = Item.createItems(rewdb[0], rewdb[1], true, 0);
								if (!createItems.isEmpty()) {
									if (ManagerPool.backpackManager.getEmptyGridNum(player) >= createItems.size()) {
										if (!BackpackManager.getInstance().addItems(player, createItems, Reasons.def7, action)) {
											MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), "合区补偿", data.length > 4 ? data[4] : "补偿物品", (byte) 0, 0, createItems);
										} else {
											str = str + String.format(" %s:%d", BackpackManager.getInstance().getName(rewdb[0]), rewdb[1]);
										}
									} else {
										MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), "合区补偿", data.length > 4 ? data[4] : "补偿物品", (byte) 0, 0, createItems);
									}
								}
							}
						}
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您获得补偿:{1}"), str);
					}
				}
			}
		}
	}

	/**
	 * ##海量经验丹和海量真气丹补偿 ##大秦典藏补偿 合服补偿
	 */
	public void playerbuchang(Player player) {
//		String key = "BUCANG_20121214_HEFU_360";
//		if (!player.getActivitiesReward().containsKey(key)) {
//			String platform = WServer.getInstance().getServerWeb();
//			if ((player.getCreateServerId() == 1 || player.getCreateServerId() == 2 || player.getCreateServerId() == 3) && "360".equals(platform)) {
//				int modelId = 8468;
//				int num = 1;
//				if (ManagerPool.backpackManager.getEmptyGridNum(player) >= 1) {
//					List<Item> item = Item.createItems(modelId, num, true, 0);
//					ManagerPool.backpackManager.addItems(player, item, Reasons.SYSTEM_GIFT, Config.getId());
//
//					player.getActivitiesReward().put(key, key);
//				}
//
//			}
//		}
//		String key = "BUCANG_20121204_DAQIN";
//		if( !player.getActivitiesReward().containsKey(key)){
//			String platform = WServer.getInstance().getServerWeb();
//			for (int i = 0; i < daqindianchang.size(); i++) {
//				String[] data = daqindianchang.get(i);
//				if(data[0].equals(String.valueOf(player.getId())) && data[1].equals(platform)){
//					int modelId = Integer.parseInt(data[2]);
//					int num = Integer.parseInt(data[3]);
//					List<Q_collectBean> list = DataManager.getInstance().q_collectContainer.getList();
//					for (Q_collectBean model : list) {
//						if(isActivity(player, model)){
//							continue;
//						}
//						HashMap<Integer,Integer> itemModels = CollectModelManager.getInstance().getItemModels(model);
//						if(!itemModels.containsKey(modelId)){
//							continue;
//						}
//						CollectItem collectItem = player.getCollect().getInfos().get(String.valueOf(model.getQ_coll_id()));
//						if(collectItem==null){
//							collectItem = new CollectItem();
//							collectItem.setModelId(model.getQ_coll_id());
//							player.getCollect().getInfos().put(String.valueOf(model.getQ_coll_id()), collectItem);
//						}
//						
//						HashMap<String,Integer> collectCount = collectItem.getCollectCount();
//						Integer oldsubmit = collectCount.get(String.valueOf(modelId));
//						if(oldsubmit==null || oldsubmit < num){
//							collectCount.put(String.valueOf(modelId), num);
//						}
//					}
//				}
//			}
//			player.getActivitiesReward().put(key, key);
//		}
//		if (jingyanSet.contains(player.getId() + "_" + platform)) {
//			String key = "BUCANG_20121125_JINGYANDAN";
//			int itemModelId = 16023;
//			if( !player.getActivitiesReward().containsKey(key)){
//				//增加player物品
//				List<Item> item = Item.createItems(itemModelId, 1, true, 0);
//				if(ManagerPool.mailServerManager.sendSystemMail(player.getId(), player.getName(), "您获得海量经验丹", "您获得海量经验丹补偿，祝你游戏愉快！", (byte)0, 0, item)){
//					player.getActivitiesReward().put(key, key);
//				}
//			}
//		}
//		if (zhenqiSet.contains(player.getId() + "_" + platform)) {
//			String key = "BUCANG_20121125_ZHENQIDAN";
//			int itemModelId = 16024;
//			if( !player.getActivitiesReward().containsKey(key)){
//				//增加player物品
//				List<Item> item = Item.createItems(itemModelId, 1, true, 0);
//				if(ManagerPool.mailServerManager.sendSystemMail(player.getId(), player.getName(), "您获得海量真气丹", "您获得海量真气丹补偿，祝你游戏愉快！", (byte)0, 0, item)){
//					player.getActivitiesReward().put(key, key);
//				}
//			}
//		}
	}

	/**
	 * 处理360刷礼包账号 1-22服
	 * @param player
	 */
	private String DEAL360GIFTTAG20121220 = "DEAL360GIFTTAG20121220";
	private String DEAL360GIFTPLUSTAG20121224 = "DEAL360GIFTTAG201212240PLUS";
	private String DEAL360GIFTITEMTAG20121221 = "DEAL360GIFTITEMTAG20121221";
	public void deal360gift(Player player) {
		String platform = WServer.getInstance().getServerWeb();
		if(!platform.equals("360")){ return; }
		if(player.getCreateServerId()<1 || player.getCreateServerId()>22){ return; } 
		if(player.getActivitiesReward().containsKey(DEAL360GIFTTAG20121220)){ return; }
		String userName = player.getUserName(); String rolename = player.getName();
		String key = userName+rolename; //key
		if(!gift360map.containsKey(key)){ return; }
		Map<String, Integer> dealmap = gift360map.get(key);
		int gemlv = dealmap.get("gemdownto");//宝石降级到 
		int gradenum = dealmap.get("equipdownto");//装备降级到
		// 记录处理前的状态
		log.info(player.getName()+"("+player.getId()+") 处理前:"+JSONserializable.toString(player));
		//  在 3.设置宝石强化等级和装备强化等级
		Gem[][] gems = player.getGems();
		for(int pos = 0; pos<10; pos++){ //全身部位
			for(int idx = 0; idx<3; idx++){ //前三颗
				if(gems[pos][idx]!=null && gems[pos][idx].getLevel()>gemlv) gems[pos][idx].setLevel(gemlv); //设置等级
			}
			for(int idx = 3; idx<5; idx++){ //后两颗 设置为3级别并且不激活
				if(gems[pos][idx]!=null){
					gems[pos][idx].setLevel(3); //设置等级
					gems[pos][idx].setIsact((byte) 0);
				}
			}
		}
		for(Equip equip: player.getEquips()){ //全身强化等级设为x
			if(equip!=null && equip.getGradeNum()>gradenum){
				equip.setGradeNum(gradenum);
				equip.setGradefailnum((short)0);
				equip.setHighestgrade((byte)0);
				equip.setAdvfailnum((short)0);
			}
		}
		PlayerManager.getInstance().sendMyPlayerDetails(player);
		//记录处理后的状态
		log.error("处理["+userName+":"+rolename+"] 设置宝石为:"+gemlv+" 设置装备为:"+gradenum);
		log.info(player.getName()+"("+player.getId()+") 处理后:"+JSONserializable.toString(player));
		//4.设置处理标志位
		player.getActivitiesReward().put(DEAL360GIFTTAG20121220, String.valueOf(System.currentTimeMillis()));
	}
	//
	public void deal360plusgift(Player player) {
//		if(!platform.equals("360")){ return; }
		if(player.getCreateServerId()<1 || player.getCreateServerId()>22){ return; } 
		if(player.getActivitiesReward().containsKey(DEAL360GIFTPLUSTAG20121224)){ return; }
		String userName = player.getUserName(); String rolename = player.getName();
		String key = userName+rolename; //key
		if(!gift360plusmap.containsKey(key)){ return; }
		Map<String, Integer> dealmap = gift360plusmap.get(key);
		int gemlv = dealmap.get("gemdownto");//宝石降级到 
		int gradenum = dealmap.get("equipdownto");//装备降级到
		// 记录处理前的状态
		log.info(player.getName()+"("+player.getId()+") 处理前:"+JSONserializable.toString(player));
		//  在 3.设置宝石强化等级和装备强化等级
		Gem[][] gems = player.getGems();
		for(int pos = 0; pos<10; pos++){ //全身部位
			for(int idx = 0; idx<3; idx++){ //前三颗
				if(gems[pos][idx]!=null && gems[pos][idx].getLevel()>gemlv) gems[pos][idx].setLevel(gemlv); //设置等级
			}
			for(int idx = 3; idx<5; idx++){ //后两颗 设置为3级别并且不激活
				if(gems[pos][idx]!=null){
					gems[pos][idx].setLevel(3); //设置等级
					gems[pos][idx].setIsact((byte) 0);
				}
			}
		}
		for(Equip equip: player.getEquips()){ //全身强化等级设为x
			if(equip!=null && equip.getGradeNum()>gradenum){
				equip.setGradeNum(gradenum);
				equip.setGradefailnum((short)0);
				equip.setHighestgrade((byte)0);
				equip.setAdvfailnum((short)0);
			}
		}
		PlayerManager.getInstance().sendMyPlayerDetails(player);
		//记录处理后的状态
		log.error("处理["+userName+":"+rolename+"] 设置宝石为:"+gemlv+" 设置装备为:"+gradenum);
		log.info(player.getName()+"("+player.getId()+") 处理后:"+JSONserializable.toString(player));
		//4.设置处理标志位
		player.getActivitiesReward().put(DEAL360GIFTPLUSTAG20121224, String.valueOf(System.currentTimeMillis()));
	}
	//
	public void deal360item(Player player) {
		String platform = WServer.getInstance().getServerWeb();
		if(!platform.equals("360")){ return; }
		if(player.getCreateServerId()<1 || player.getCreateServerId()>22){ return; } 
		if(player.getActivitiesReward().containsKey(DEAL360GIFTITEMTAG20121221)){ return; }
		String userName = player.getUserName(); String rolename = player.getName();
		String key = userName+rolename; //key
		if(!giftitem360map.containsKey(key)){ return; }
		Map<String, Integer> dealmap = giftitem360map.get(key);
		int keepbackgem = dealmap.get("keepbackgem");			//背包保留宝石精华  4  
		int keepbackstreng = dealmap.get("keepbackstreng");		//背包保留装备强化石  5
		int keepbackspeaker=dealmap.get("keepbackspeaker");		//背包保留小喇叭  6
		int keepstoregem=dealmap.get("keepstoregem");			//仓库保留宝石精华  7
		int keepstorestreng=dealmap.get("keepstorestreng");		//仓库保留装备强化石  8
		int keepstorespeaker=dealmap.get("keepstorespeaker");	//仓库保留小喇叭  9
		//遍历背包
		int backgem = BackpackManager.getInstance().getItemNum(player, 1007); //宝石精华
		int backstreng = BackpackManager.getInstance().getItemNum(player, 1001);//装备强化石1001
		int backspeaker = BackpackManager.getInstance().getItemNum(player, 1014);//小喇叭 
		//遍历仓库
		int storegem = getStoreItemNum(player, 1007);//宝石精华
		int storestreng = getStoreItemNum(player, 1001);//装备强化石
		int storespeaker = getStoreItemNum(player, 1014);//小喇叭
		//
		log.info(player.getName()+"("+player.getId()+")背包和仓库道具处理前: "+JSONserializable.toString(player));
		long config = Config.getId();
		//处理背包
		if(backgem>keepbackgem){ //背包宝石精华
			BackpackManager.getInstance().removeItem(player, 1007, backgem-keepbackgem, Reasons.def13, config);
		}
		if(backstreng>keepbackstreng){ //背包装备强化石
			BackpackManager.getInstance().removeItem(player, 1001, backstreng-keepbackstreng, Reasons.def13, config);
		}
		if(backspeaker>keepbackspeaker){ //背包小喇叭
			BackpackManager.getInstance().removeItem(player, 1014, backspeaker-keepbackspeaker, Reasons.def13, config);
		}
		//处理仓库
		if(storegem>keepstoregem){ //仓库宝石精华
			removeStoreItem(player, 1007, storegem-keepstoregem);
		}
		if(storestreng>keepstorestreng){ //仓库装备强化石
			removeStoreItem(player, 1001, storestreng-keepstorestreng);
		}
		if(storespeaker>keepstorespeaker){ //仓库小喇叭
			removeStoreItem(player, 1014, storespeaker-keepstorespeaker);
		}
		//
		log.info(player.getName()+"("+player.getId()+")处理后: "+JSONserializable.toString(player));
		player.getActivitiesReward().put(DEAL360GIFTITEMTAG20121221, String.valueOf(System.currentTimeMillis()));
	}
	
	@SuppressWarnings("deprecation")
	public void removeStoreItem(Player player, int itemModelId, int delnum) {
		long actionid = Config.getId();
		int count = delnum;
		Iterator<Item> it = player.getStoreItems().values().iterator();
		while(it.hasNext()){
			Item item = it.next();
			if(item.getItemModelId()==itemModelId){
				int itemnum = item.getNum();    //道具数量
				if(count>=itemnum){ 			//剩余要删除的道具数
					item.setNum(0); 			//完全删除
					String iteminfo = JSONserializable.toString(item);
					StoreItemChangeLog storelog = StoreItemChangeLog.createLog(player.getId(), item.getId(), item.getItemModelId(), item.getNum(), iteminfo, "", Reasons.def16.getValue(), "REMOVE360", actionid, ItemChangeAction.REMOVE.toString(), player.getServerId());
					LogService.getInstance().execute(storelog);
					it.remove();
					count = count-itemnum;
					Message msg = getItemRemoveMessage(item);
					MessageUtil.tell_player_message(player, msg);
				}else{
					String biteminfo = JSONserializable.toString(item);
					item.setNum(itemnum-count); //部分删除
					String aiteminfo = JSONserializable.toString(item);
					StoreItemChangeLog storelog = StoreItemChangeLog.createLog(player.getId(), item.getId(), item.getItemModelId(), count, biteminfo, aiteminfo, Reasons.def16.getValue(), "REMOVE360", actionid, ItemChangeAction.REMOVE.toString(), player.getServerId());
					LogService.getInstance().execute(storelog);
					count = 0;
					Message msg = getItemChangeMessage(item);
					MessageUtil.tell_player_message(player, msg);
				}
				if(count==0) break; //删除完成
			}
		}
	}
	private Message getItemChangeMessage(Item item) {
		ResStoreItemChangeMessage msg=new ResStoreItemChangeMessage();
		msg.setItem(item.buildItemInfo());
		return msg;
	}
	private Message getItemRemoveMessage(Item item) {
		ResStoreItemRemoveMessage resp=new ResStoreItemRemoveMessage();
		resp.setItemId(item.getId());
		return resp;
	}
	
	@SuppressWarnings("deprecation")
	public int getStoreItemNum(Player player, int itemModelId) {
		int num = 0;
		Iterator<Item> iter = player.getStoreItems().values().iterator();
		// 遍历物品
		while (iter.hasNext()) {
			Item item = (Item) iter.next();
			if (item.getItemModelId() == itemModelId && !item.isLost() && !item.isTrade()) {
				num += item.getNum();
			}
		}
		return num;
	}
	
	protected boolean isActivity(Player player, Q_collectBean model) {
		List<Buff> buffs = BuffManager.getInstance().getBuffByModelId(player, model.getQ_buff_id());
		if (buffs != null && buffs.size() > 0) {
			return true;
		}
		return false;
	}
	
	
	
	/**给春节大礼包
	 * 
	 * @param player
	 */
	public void TotheNewYeargifts(Player player){
		int day = TimeUtil.GetSeriesDay();
		if (day >= 20130204 && day < 20130220) {
			String key = "NewYeargifts_2013";
			if (!player.getActivitiesReward().containsKey(key)) {
				 List<Item> items = Item.createItems(9174, 1, true, 0);
				 if (!items.isEmpty()) {
					long action = Config.getId();
					if (!BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_RNDITEM, action)) {
						MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), "未领取道具：","春节大礼包", (byte) 0, 0, items);
					}
					player.getActivitiesReward().put(key, "9174");
					String str = ManagerPool.backpackManager.getName(9174);
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您获得了: {1}"), str);
				 }
			}
		}
	}
	
	
	/**检查并替换骑兵隐藏属性BUFF
	 * 
	 * @param player
	 */
	public void ckForgingBuff(Player player){
		try {
			List<Buff> ybuffs = ManagerPool.buffManager.getBuffByModelId(player, 9157);//版本更新前，骑兵神锻符BUFFID
			if (ybuffs.size() > 0) {
				ManagerPool.buffManager.removeByBuffId(player, 9157);	//移除有属性BUFF
				ManagerPool.buffManager.addBuff(player, player, 9158, 0,0, 0);//添加没有属性BUFF
				ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE_WEAPON);
			}
		} catch (Exception e) {
			log.error(e,e);
		}
	}
	
	
	
	
	
	/**清理特定区的出错结婚数据
	 * //49you,  43区
	 */
	public void cleanupmarriagedata(Player player){
		try {
			String platform = WServer.getInstance().getServerWeb();
			if(!platform.equals("49you"))return; 
			int sid = player.getCreateServerId();
			if (sid != 43) return;	
			long mid = player.getMarriageid();
			if (player.getMarriageid() > 0) {
				Marriage marriage = ManagerPool.marriageManager.getMarriage(mid);
				if (marriage == null) {
					player.setMarriageid(0);
					log.error("玩家="+player.getId()+",结婚ID被清理：="+mid);
				}
			}
		} catch (Exception e) {
			log.error(e,e);
		}

	}
	
	
	/**去掉结婚技能对排行榜的影响，版本更新后有用（预计3月28号以后）
	 * 
	 * @param player
	 */
	public void setSkillLevelSum(Player player ){
		int day = TimeUtil.GetSeriesDay();
		if (day >= 20130328 && day < 20130410) {	
			if (player.getTotalSkillLevel() > 1704) {
				player.setTotalSkillLevel(ManagerPool.skillManager.getSkillLevelSum(player));
			}
		}
	}
	

//	4.28更新错误补偿方案
//	补偿对象：1、2、3、4服4月28日22:00前登录并创建角色的玩家
//	补偿内容：
//	礼金*3000，ID：-5
//	坐骑丹*50，ID：1011
//	40倍收益丹*2，ID：1024
//	生命池*5，ID：30301
//	内力池*5，ID：30302
//	玫瑰花*3，ID：1100
//	领取方式：登录即可领取，在背包内查看
//	领取时间：2013/4/30  18：00——2013/5/7 18:00
	
	public String[][] SHOUJI_TAB = {//补偿 区ID是计算的服务器区id
			{"haowan123_1","2013-4-30 18:00:00","2013-5-7 18:00:00","-5_3000;1011_50;1024_2;30301_5;30302_5;1100_3"},
			{"haowan123_2","2013-4-30 18:00:00","2013-5-7 18:00:00","-5_3000;1011_50;1024_2;30301_5;30302_5;1100_3"},	
			{"haowan123_3","2013-4-30 18:00:00","2013-5-7 18:00:00","-5_3000;1011_50;1024_2;30301_5;30302_5;1100_3"},	
			{"haowan123_4","2013-4-30 18:00:00","2013-5-7 18:00:00","-5_3000;1011_50;1024_2;30301_5;30302_5;1100_3"},	
	};
	
	/**
	 * 通用礼金补偿
	 *
	 */
	public void shouji428(Player player) {
		if(startdate==null){
			log.error("日期初始化失败!");
			return;
		}
		if (player.getCreateTime() > startdate.getTime()) {
			log.error("玩家创建时间靠后！");
			return;
		}
		
		for (String[] data : SHOUJI_TAB) {
			String platform = WServer.getInstance().getServerWeb();
			long curtime = System.currentTimeMillis();
			int sid = WServer.getInstance().getServerId();
			log.error(platform + "_" + sid);
			if (data[0].equals(platform + "_" + sid)) {
				Date beginDate = TimeUtil.getDateByString(data[1]);
				Date endDate = TimeUtil.getDateByString(data[2]);
				if (beginDate.getTime() < curtime && endDate.getTime() > curtime) {
					String key = data[0] + "_" + beginDate.getTime();
					if (!player.getActivitiesReward().containsKey(key)) {
						player.getActivitiesReward().put(key, data[3]);
						long action = Config.getId();
						int[][] rewtab = ManagerPool.zonesFlopManager.getZoneFixedReward(data[3]);
						String str = "";
						for (int[] rewdb : rewtab) {
							//-1铜币，-2元宝，-3真气，-4经验，-5礼金，-6战魂，-7军功
							if (rewdb[0] == -1) {
								ManagerPool.backpackManager.changeMoney(player, rewdb[1], Reasons.def7, action);
								str = str + ResManager.getInstance().getString(" 铜币:") + rewdb[1];
							} else if (rewdb[0] == -2) {
								//元宝为特殊，暂不加
							} else if (rewdb[0] == -3) {
								ManagerPool.playerManager.addZhenqi(player, rewdb[1], AttributeChangeReason.BUCHANG);
								str = str + ResManager.getInstance().getString(" 真气:") + rewdb[1];
							} else if (rewdb[0] == -4) {
								ManagerPool.playerManager.addExp(player, rewdb[1], AttributeChangeReason.BUCHANG);
								str = str + ResManager.getInstance().getString(" 经验:") + rewdb[1];
							} else if (rewdb[0] == -5) {
								ManagerPool.backpackManager.changeBindGold(player, rewdb[1], Reasons.def7, action);
								str = str + ResManager.getInstance().getString(" 礼金:") + rewdb[1];
							} else if (rewdb[0] == -6) {
								ManagerPool.arrowManager.addFightSpiritNum(player, 1, rewdb[1], true, ArrowReasonsType.BUCHANG);
								str = str + ResManager.getInstance().getString(" 战魂:") + rewdb[1];
							} else if (rewdb[0] == -7) {
								ManagerPool.rankManager.addranknum(player, rewdb[1], RankType.BUCHANG);
								str = str + ResManager.getInstance().getString(" 军功:") + rewdb[1];
							} else if (rewdb[0] > 0) {
								List<Item> createItems = Item.createItems(rewdb[0], rewdb[1], true, 0);
								if (!createItems.isEmpty()) {
									if (ManagerPool.backpackManager.getEmptyGridNum(player) >= createItems.size()) {
										if (!BackpackManager.getInstance().addItems(player, createItems, Reasons.def7, action)) {
											MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), "上線補償", data.length > 4 ? data[4] : "補償物品", (byte) 0, 0, createItems);
										} else {
											str = str + String.format(" %s:%d", BackpackManager.getInstance().getName(rewdb[0]), rewdb[1]);
										}
									} else {
										MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), "上線補償", data.length > 4 ? data[4] : "補償物品", (byte) 0, 0, createItems);
									}
								}
							}
						}
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您獲得:{1}"), str);
					}
				}
			}
		}
	}
}
