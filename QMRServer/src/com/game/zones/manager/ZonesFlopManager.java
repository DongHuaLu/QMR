package com.game.zones.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.game.arrow.structs.ArrowReasonsType;
import com.game.backpack.bean.GoodsAttribute;
import com.game.backpack.bean.ItemInfo;
import com.game.backpack.bean.ItemReasonsInfo;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.message.ResGetItemReasonsMessage;
import com.game.backpack.structs.Attribute;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemTypeConst;
import com.game.config.Config;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_clone_activityBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_spirittree_kiwiBean;
import com.game.data.bean.Q_spirittree_pack_conBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.rank.structs.RankType;
import com.game.spirittree.manager.FruitAppendManager;
import com.game.spirittree.structs.FruitReward;
import com.game.spirittree.structs.FruitRewardAttr;
import com.game.structs.Reasons;
import com.game.task.struts.RankTaskEnum;
import com.game.task.struts.Task;
import com.game.team.manager.TeamManager;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;
import com.game.zones.bean.ZoneRewardNumInfo;
import com.game.zones.log.ZoneRewardLog;
import com.game.zones.message.ReqSelectAwardMessage;
import com.game.zones.message.ReqZoneReceiveawardsMessage;
import com.game.zones.message.ResAutoRaidInfoMessage;
import com.game.zones.message.ResZoneContinuousRaidsMessage;
import com.game.zones.message.ResZoneNoticeRewardMessage;
import com.game.zones.message.ResZonePassShowMessage;
import com.game.zones.message.ResZoneSelectAwardMessage;
import com.game.zones.structs.ContinuousRaidsInfo;
import com.game.zones.structs.Raid;


public class ZonesFlopManager {
	protected Logger log = Logger.getLogger(ZonesFlopManager.class);
	//玩家管理类实例
		private static ZonesFlopManager manager;
		private static Object obj = new Object();
		private ZonesFlopManager(){}
		
		public static ZonesFlopManager getInstance(){
			synchronized (obj) {
				if(manager == null){
					manager = new ZonesFlopManager();
				}
			}
			return manager;
		}
		
	//设置翻牌数量
	private int REWARDSUM = 10;
		
	/**奖励组包内容
	 * @return 
	 * 
	 */
	public HashMap<Integer, Q_spirittree_pack_conBean> getpackcondata(){
		 HashMap<Integer, Q_spirittree_pack_conBean> data = DataManager.getInstance().q_spirittree_pack_conContainer.getMap();
		return data;
	}
	
	
	/**发送通关奖励
	 * 
	 * @param player
	 * @param msg 表示多人副本
	 */
	public void stZonePassShow(Player player,int type,int zoneModelId) {
		Q_clone_activityBean zonedata = ManagerPool.zonesManager.getContainer(zoneModelId);
		List<FruitReward> rewardlist = null;
		if ( zonedata.getQ_zone_type() == 4) {	//如果没有临时储存的奖励选择，则重新随机一套奖励
			rewardlist = player.getRaidflop().getQiyaorewardlist();//奖励临时存放
			if (rewardlist.size() == 0) {
				List<FruitReward> fruitRewards= ManagerPool.zonesFlopManager.createFruitRewardList(player,1,zoneModelId);//筛选并设置奖励列表 
				rewardlist.clear();
				rewardlist.addAll(fruitRewards);
			}
		}else {
			rewardlist = player.getRaidflop().getManualrewardlist();//奖励临时存放
			if (rewardlist.size() == 0) {
				List<FruitReward> fruitRewards= ManagerPool.zonesFlopManager.createFruitRewardList(player,1,zoneModelId);//筛选并设置奖励列表 
				rewardlist.clear();
				rewardlist.addAll(fruitRewards);
			}
		}
		
		ResZonePassShowMessage smsg = new ResZonePassShowMessage();
		Raid raid=player.getRaidinfo();
		int ztime = 0;
		
		if (type <=1) {
			if (raid.getZoneraidtimes().containsKey(zoneModelId+"")) {
				ztime = raid.getZoneraidtimes().get(zoneModelId+"");
			}	
		}

		player.getRaidinfo().setRaidmanualzonemodelid(zoneModelId);// -------设定领取奖励的副本
		
		if (type == 0 || type == 3 || type == 4) { //手动
			try {
				if(player.getZoneinfo().containsKey(ZonesManager.ManualDeathnum+"_"+zoneModelId))
					smsg.setDeathnum( player.getZoneinfo().get(ZonesManager.ManualDeathnum+"_"+zoneModelId));
				if(player.getZoneinfo().containsKey(ZonesManager.killmonsternum+"_"+zoneModelId))
					smsg.setKillmonstrnum( player.getZoneinfo().get(ZonesManager.killmonsternum+"_"+zoneModelId));
				if(player.getZoneinfo().containsKey(ZonesManager.Manualendtime+"_"+zoneModelId))
					smsg.setTime(player.getZoneinfo().get(ZonesManager.Manualendtime+"_"+zoneModelId));
			} catch (Exception e) {
				log.error(e, e);
				smsg.setTime(ztime);			//完成时间
			}
		}else {
			smsg.setKillmonstrnum(0);	
			smsg.setTime(ztime);			//完成时间
		}
		
		smsg.setZoneid(zoneModelId);
		smsg.setThroughtime(ztime);
		smsg.setType((byte) type);
		for (FruitReward fruitReward : rewardlist) {
			smsg.getItemlist().add(getItemInfo(fruitReward));
		}
		
		if (smsg.getItemlist().size() > 0) {
			for (int i = 0; i < 25; i++) {
				int rnd = RandomUtils.random(1,smsg.getItemlist().size()) - 1;
				ItemInfo item = smsg.getItemlist().remove(rnd);
				smsg.getItemlist().add(item);
			}
		}
		if (type == 4) {
			if (player.getZoneinfo().containsKey(ZonesManager.Entrances+"_"+zoneModelId)) {
				int entrances = (Integer)player.getZoneinfo().get(ZonesManager.Entrances+"_"+zoneModelId);
				if (entrances == 1) {
					smsg.setIsfirst(1);
				}
			}
		}
		MessageUtil.tell_player_message(player, smsg);
	}
	
	
	
	
	/**生成ItemInfo
	 * 
	 * @param fruitReward
	 * @return
	 */
	public ItemInfo getItemInfo(FruitReward fruitReward){
		ItemInfo itemInfo = new ItemInfo();
		itemInfo.setIntensify((byte) fruitReward.getStrenglevel());
		if (fruitReward.isBind()) {
			itemInfo.setIsbind((byte) 1);
		}
		itemInfo.setItemModelId(fruitReward.getItemModelid());
		itemInfo.setNum(fruitReward.getNum());
		itemInfo.setLostTime((int) fruitReward.getLosttime());
		itemInfo.setAttributs((byte) fruitReward.getFruitRewardAttrslist().size());
		for (FruitRewardAttr fruitRewardAttr : fruitReward.getFruitRewardAttrslist()) {
			GoodsAttribute goodsatt=new GoodsAttribute();
			goodsatt.setType(fruitRewardAttr.getType());
			goodsatt.setValue(fruitRewardAttr.getValue());
			itemInfo.getGoodAttributes().add(goodsatt);
		}
		itemInfo.setGridId(-1);
		itemInfo.setItemId(fruitReward.getId());
		return itemInfo;
	}
	
	
	
	
	
	

	
	/**
	 * 筛选并设置奖励列表 
	 * @param player
	 * @param fruit
	 * @param rewarddata
	 * @return 
	 */
	public List<FruitReward> createFruitRewardList(Player player,int type,int zonemodelid){
		Q_clone_activityBean zonedata = ManagerPool.zonesManager.getContainer(zonemodelid);
		List<Integer> grouplist=null;
		if (zonemodelid > 0 && zonedata.getQ_zone_type() == 4) {
			 grouplist= randomGroup(player,5);//如果是七曜战将副本，奖励类型为5
		}else{
			 grouplist= randomGroup(player,3);//其他副本奖励类型为3
		}

		HashMap<Integer, Q_spirittree_pack_conBean> rewarddata = getpackcondata();
		List<FruitReward> rewlist = new ArrayList<FruitReward>();
		for (Integer gid :grouplist) {
			List<Integer> tmpidxs =new ArrayList<Integer>(); // 储存符合条件的道具奖励索引
			List<Integer> tmprnds =new ArrayList<Integer>(); //选中道具的几率值
			Iterator<Entry<Integer, Q_spirittree_pack_conBean>> it = rewarddata.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Integer, Q_spirittree_pack_conBean> pack = it.next();
				Q_spirittree_pack_conBean data = pack.getValue();
				if (gid ==data.getQ_packet_id() ) {
					if (player.getLevel() >= data.getQ_need_level() && player.getLevel() < data.getQ_exclude_level() ) {
						if (data.getQ_item_id() > 0) {
							 Q_itemBean itemdata = ManagerPool.dataManager.q_itemContainer.getMap().get(data.getQ_item_id());
							 if (itemdata != null) {
								if (itemdata.getQ_type() == 1) {	
									//检测装备性别
									if (itemdata.getQ_sex() == 0 || itemdata.getQ_sex() == player.getSex()) {
										tmpidxs.add(data.getQ_id());
										tmprnds.add(data.getQ_selected_rnd());
									}
								}else {
									tmpidxs.add(data.getQ_id());
									tmprnds.add(data.getQ_selected_rnd());
								}
							}
						}else {
							tmpidxs.add(data.getQ_id());
							tmprnds.add(data.getQ_selected_rnd());
						}
					}
				}
			}

			
			if (tmprnds.size() > 0) {
				int index= RandomUtils.randomIndexByProb(tmprnds);//在道具合集中随机一个道具
				int idx = tmpidxs.get(index);
				Q_spirittree_pack_conBean rewdata = rewarddata.get(idx);//获取选中道具信息
				FruitReward fruitreward = createFruitreward(rewdata);
				rewlist.add(fruitreward);
			}
		}
		return rewlist;
	}
	
	
	/**随机奖励组包 :
	 * @return 
	 * type=3  副本奖励
	 */
	public List<Integer> randomGroup(Player player ,int type){
		List<Integer > gidlist = new ArrayList<Integer>();  //选中的组包列表
		List<Integer > tmplist = new ArrayList<Integer>();	//临时补充列表
		HashMap<Integer, Integer> numMap= new HashMap<Integer, Integer>();
		List<Q_spirittree_kiwiBean> data = DataManager.getInstance().q_spirittree_kiwiContainer.getList();
		 for (Q_spirittree_kiwiBean kiwiBean : data) {
			 if  (kiwiBean.getQ_type() == type){
				 if (player.getLevel() >= kiwiBean.getQ_need_level()) {
					 int num = 0;
					 if(numMap.containsKey(kiwiBean.getQ_packet_id())){
						 num = numMap.get(kiwiBean.getQ_packet_id());
					 }
					 
					 for (int i = 0; i < kiwiBean.getQ_check_num(); i++) {
						 if(RandomUtils.isGenerate2(10000, kiwiBean.getQ_arise_rnd()) ){
							 if (num < kiwiBean.getQ_check_num() ) {	//复选
								if (gidlist.size() >= REWARDSUM) {
									 break;
								}
								 gidlist.add(kiwiBean.getQ_packet_id());
								 numMap.put(kiwiBean.getQ_packet_id(), num+1);
							}
						 } 
					}
				 }
				 tmplist.add(kiwiBean.getQ_packet_id());
			}

		}
		 
		if (gidlist.size() < REWARDSUM) {
			//组包不足10个，需要补充
			 if (tmplist.size() > 1) {
				 Collections.sort(tmplist, new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						if (o1< o2) {
							return 1;
						}
						return 0;
					}
				} );
				int num = REWARDSUM - gidlist.size();//补充数量
				for (int i = 0; i < num; i++) {
					gidlist.add(tmplist.get(0));
				}
			}
		}
		return gidlist;
	}
	
	/**
	 * 创建翻牌奖励
	 * @param rewdata
	 * @return
	 */

	public FruitReward createFruitreward(Q_spirittree_pack_conBean rewdata){
		if (rewdata != null ) {
			FruitReward  fruitreward = new FruitReward();
			fruitreward.setNum(rewdata.getQ_item_num());
			fruitreward.setSum(rewdata.getQ_item_num());
			fruitreward.setItemModelid(rewdata.getQ_item_id());
			fruitreward.setIdx(rewdata.getQ_id());
			fruitreward.setStrenglevel(rewdata.getQ_streng_level());	//产生道具属性
			fruitreward.setLosttime(rewdata.getQ_existtime());
			boolean isbidn = false;
			if (rewdata.getQ_is_binding() == 1) {
				isbidn=true;
			}
			fruitreward.setBind(isbidn);
			if (rewdata.getQ_addProperty_num() > 0 && rewdata.getQ_item_id() > 0) {
				FruitAppendManager.getInstance().buildAppend(fruitreward, rewdata.getQ_addProperty_num());
			}
			if (fruitreward.getItemModelid() == 0) {
				log.error("创建翻牌奖励=0");
			}
			return fruitreward;
		}
		return null;
	}
	

	
	/**选择奖励,0到9自选，不能重复选
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqSelectAwardMessage(Player player,ReqSelectAwardMessage msg) {
		if (player == null) {
			return;
		}
		
		int zoneModelId = player.getRaidinfo().getRaidmanualzonemodelid();	//
		Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(zoneModelId);
		if (zonedata == null) {
			return;
		}
		if (zonedata.getQ_zone_type() == 1 && msg.getType() > 1) {
			return;
		}
		if (zonedata.getQ_zone_type() > 1 && zonedata.getQ_zone_type() != msg.getType() ) {
			return;
		}
		
		List<Integer> idxlist = player.getRaidflop().getManualrewardidx();
		List<FruitReward> rewardlist = player.getRaidflop().getManualrewardlist();//战役奖励临时存放
		if (zonedata.getQ_zone_type() == 4) {
			rewardlist = player.getRaidflop().getQiyaorewardlist();//七曜战将奖励临时存放
		}
		
		
		if (rewardlist.size() == 0) {
			return;
		}

		if (idxlist.size() == 1) {	//现在只开放一次选择
			return;
		}
		
		int rnd= 0;
		List<Integer> rndlist = new ArrayList<Integer>();
		for (FruitReward fruitReward : rewardlist) {
			 Q_spirittree_pack_conBean fruitdata = getpackcondata().get(fruitReward.getIdx());
			 if (fruitdata == null) {
				 rndlist.add(100);	//数据库改变后，默认值
			}else{
				rndlist.add(fruitdata.getQ_selected_rnd());
			}
		}
		rnd = RandomUtils.randomIndexByProb(rndlist);

		idxlist.add(rnd);
		FruitReward fruitReward = rewardlist.remove(rnd);
		
		if (idxlist.size() == 1) {	//在这里进行传送，清除奖励数据
			HashMap<String, Integer> rewmap = player.getZonerewardmap();
			if (zonedata.getQ_zone_type() == 3) {//读多人副本奖励
				rewmap = player.getZoneteamrewardmap();
			}else if (zonedata.getQ_zone_type() == 4) {//读取七曜战将副本
				rewmap= player.getZoneqiyaorewardmap();
			}
			
//			Raid raid=player.getRaidinfo();
			if (zonedata.getQ_zone_type() == 4) {
				player.getRaidflop().getQiyaorewardlist().clear();	//清除奖励
			}else {
				player.getRaidflop().getManualrewardlist().clear();	//清除奖励
			}

			idxlist.clear();
			ZoneRewardLog zlog =new ZoneRewardLog();
			zlog.setBeforerewardlist(rewmap.toString());
			player.getRaidinfo().setRaidmanualzonemodelid(0);
			
			boolean is = false;	//标记当前地图次数是否存在
			if (rewmap.containsKey(zoneModelId+"")) {
				int num = rewmap.get(zoneModelId+"");
				if (num > 0) {
					num = num -1;
					rewmap.put(zoneModelId+"",num);
					is = true;	//次数已扣
				}
			}

			if (is==false) {	//如果上面没扣除次数，则在这里找一个副本次数扣除
				for (String strzid : rewmap.keySet()) {
					int tmpnum = rewmap.get(strzid);
					if(tmpnum > 0){
						tmpnum = tmpnum -1;
						rewmap.put(strzid, tmpnum);
						is = true;
						break;
					}
				}
			}
			if (is == false) {
				return;
			}
			if(msg.getType() == 0){
				giveZoneFixedReward(player, zoneModelId,0,false);//手动单人扫荡固定奖励
				zlog.setZonemodelid(zoneModelId);
			}else if (msg.getType() == 1) {
				giveZoneFixedReward(player, zoneModelId,0,true);// 自动单人扫荡固定奖励
				zlog.setZonemodelid(zoneModelId);
			}else if (msg.getType() == 3) {						
				giveZoneFixedReward(player, zoneModelId,0,false);//多人副本固定奖励
				zlog.setZonemodelid(zoneModelId);
			}else if (msg.getType() == 4) {
				giveZoneFixedReward(player, zoneModelId,0,false);//七曜副本固定奖励
				zlog.setZonemodelid(zoneModelId);
				if (player.getZoneinfo().containsKey(ZonesManager.Entrances+"_"+zoneModelId)) {
					int entrances = (Integer)player.getZoneinfo().get(ZonesManager.Entrances+"_"+zoneModelId);
					if (entrances == 1) {
						giveZoneFixedReward(player, zoneModelId,1,false);//七曜副本第一次额外奖励（参与奖）
					}
				}
			}
			
			LoginRaidRewarded(player);
			zlog.setPlayerid(player.getId());
			zlog.setRemainderrewardlist(rewmap.toString());
			LogService.getInstance().execute(zlog);		
			
			ResZoneSelectAwardMessage smsg = new ResZoneSelectAwardMessage();
			smsg.setType(msg.getType());
			smsg.setNum((byte) idxlist.size());
			smsg.setIteminfo(getItemInfo(fruitReward));
			MessageUtil.tell_player_message(player, smsg);

			giveRewarded(player, fruitReward,0);	//给副本奖励
			ManagerPool.playerManager.savePlayer(player);
			
		}
		

	}

	

	/**得到副本奖励 
	 * type = 0副本抽奖，1通关固定奖励,2翻牌
	 * 
	 * @param msg
	 */
	public void giveRewarded(Player player , FruitReward fruitReward,int type) {
		String rewardedname = ResManager.getInstance().getString("副本抽奖"); //默认0
		if  (type == 1) {
			rewardedname = ResManager.getInstance().getString("副本通关奖励");
		}else if (type == 2) {
			rewardedname = ResManager.getInstance().getString("翻牌奖励");
		}else if (type > 100) {
			rewardedname = "";
		}
		
		int id = fruitReward.getItemModelid();
		long action = Config.getId();
		//-1铜币，-2元宝，-3真气，-4经验  -5礼金
		if (fruitReward.getNum() == 0) {
			return ;
		}
		boolean issuccess = true;
		List<Item> createItems = new ArrayList<Item>();
		String itemname="";
		if (id == -1) {
			itemname = ResManager.getInstance().getString("铜币");
			if(player != null && ManagerPool.backpackManager.changeMoney(player, fruitReward.getNum(), Reasons.RAID_MONEY, action)){
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");	
			}else {
				issuccess =false;
			}
			
//		}else if (id== -2) {
//			itemname = "元宝";
//			if(player != null && ManagerPool.backpackManager.changeGold(player, fruitReward.getNum(), Reasons.RAID_YUANBAO, action)){
//				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, "恭喜！获得了{1}{2}({3})",rewardedname,itemname,fruitReward.getNum()+"");
//			}else {
//				issuccess =false;
//			}
		}else if ( id == -3) {
			itemname = ResManager.getInstance().getString("真气");
			if(player != null){
				ManagerPool.playerManager.addZhenqi(player, fruitReward.getNum(),AttributeChangeReason.FUBENG);
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}
		}else if (id == -4) {
			itemname = ResManager.getInstance().getString("经验");
			if(player != null){
				ManagerPool.playerManager.addExp(player, fruitReward.getNum(),AttributeChangeReason.FUBENG);
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}
		}else if (id == -5) {
			itemname = ResManager.getInstance().getString("礼金");
			if(player != null && ManagerPool.backpackManager.changeBindGold(player, fruitReward.getNum(), Reasons.RAID_BIND_YUANBAO, action)){
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}
		}else if (id == -6) {	//战魂
			itemname = ResManager.getInstance().getString("七曜战魂");
			if(player != null){
				ManagerPool.arrowManager.addFightSpiritNum(player, 1, fruitReward.getNum(), true, ArrowReasonsType.ZONES);
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}
		}else if (id == -7) {	//军功
			itemname = ResManager.getInstance().getString("军功值");
			if(player != null){
				ManagerPool.rankManager.addranknum(player, fruitReward.getNum(), RankType.OTHER);
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}
			
			
		}else if (id > 0) {
			Q_itemBean itemMode = ManagerPool.dataManager.q_itemContainer.getMap().get(fruitReward.getItemModelid());
			if (itemMode != null) {
				itemname = itemMode.getQ_name();
				createItems = Item.createItems(fruitReward.getItemModelid(), fruitReward.getNum(), fruitReward.isBind(),((fruitReward.getLosttime() == 0) ? fruitReward.getLosttime() : (System.currentTimeMillis() + fruitReward.getLosttime() * 1000)) , fruitReward.getStrenglevel(), null);
				List<FruitRewardAttr> attrs = fruitReward.getFruitRewardAttrslist();
				//写入属性
				if (itemMode.getQ_type()== ItemTypeConst.EQUIP) {
					if (attrs.size() > 0) {
						for (Item item : createItems) {
							Equip equip = (Equip)item;
							for (FruitRewardAttr attr : attrs) {
								Attribute attribute = new Attribute();
								attribute.setType(attr.getType());
								attribute.setValue(attr.getValue());
								equip.getAttributes().add(attribute);
							}
						}
					}
				}

				if(player != null && ManagerPool.backpackManager.getEmptyGridNum(player) >= createItems.size()) {
					BackpackManager.getInstance().addItems(player, createItems,Reasons.RAID_ITEM,action);
					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})。"),rewardedname,itemMode.getQ_name(),fruitReward.getNum()+"");
				}else {
					issuccess =false;
				}
			}else {
				log.error(rewardedname+"道具item不存在：["+id +"]");
			}
		}else{
			log.error(rewardedname+"ID类型未知：["+id+"]");
		}
		
		if(issuccess == false){
			itemname = itemname+"("+fruitReward.getNum()+")";
			if (id > 0) {
				ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0,createItems);
			}else {
				if (id == -1 ) {	//铜币
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createMoney(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0,createItems2);
				//}else if (id == -2 ) {	//元宝
				//	ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,"系统邮件",rewardedname+":"+itemname,(byte) 2,fruitReward.getNum(),new ArrayList<Item>());
				}else if (id == -3) {	//真气
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createZhenQi(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0,createItems2);
				}else if ( id == -4 ) {	//经验
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createExp(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0,createItems2);
				}else if ( id == -5) {	//礼金
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createBindGold(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0,createItems2);
				}
			}
			if (player != null) {
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("由于您的包裹已满，")+rewardedname+"："+itemname+ResManager.getInstance().getString(" 已经通过邮件发送给您。"));
			}
		}
	}

	
	/**登录发送扫荡奖励信息
	 * 
	 * @param player
	 */
	public void LoginRaidRewarded(Player player){
		Raid raidinfo = player.getRaidinfo();
		if (raidinfo != null && raidinfo.getRaidzonemodelid() > 0 && raidinfo.getRaidautoendtime() > 0) {
			ResAutoRaidInfoMessage reidmsg = new ResAutoRaidInfoMessage();
			reidmsg.setSurplustime(raidinfo.getRaidautoendtime() - (int)(System.currentTimeMillis()/1000));
			reidmsg.setZoneid(raidinfo.getRaidzonemodelid());
			MessageUtil.tell_player_message(player, reidmsg);
		}
		
		if (raidinfo != null && raidinfo.getQiyaoraidzonemodelid() > 0 && raidinfo.getQiyaoraidautoendtime() > 0) {
			ResAutoRaidInfoMessage reidmsg = new ResAutoRaidInfoMessage();
			reidmsg.setSurplustime(raidinfo.getQiyaoraidautoendtime() - (int)(System.currentTimeMillis()/1000));
			reidmsg.setZoneid(raidinfo.getQiyaoraidzonemodelid());
			MessageUtil.tell_player_message(player, reidmsg);
		}
		
		//---------------------------上面是单个自动扫荡，下面是连续自动扫荡 + 奖励信息
		//---------------------------------单人副本奖励数量信息
		ResZoneNoticeRewardMessage smsg = new ResZoneNoticeRewardMessage();
		int rewnum = 0;
		HashMap<String, Integer> rewardmap = player.getZonerewardmap();
		Iterator<Entry<String, Integer>> it = rewardmap.entrySet().iterator();
		while (it.hasNext()) {
			ZoneRewardNumInfo info = new ZoneRewardNumInfo();
			Entry<String, Integer> entry = it.next();
			
			int zid = Integer.parseInt(entry.getKey());
			info.setZoneid(zid);
			info.setNum(entry.getValue());
			smsg.getZoneRewardnuminfo().add(info);
			rewnum = rewnum + entry.getValue();
		}
		
		//七曜战将副本
		HashMap<String, Integer> qiyaorewardmap = player.getZoneqiyaorewardmap();
		Iterator<Entry<String, Integer>> qiyaoit = qiyaorewardmap.entrySet().iterator();
		while (qiyaoit.hasNext()) {
			ZoneRewardNumInfo info = new ZoneRewardNumInfo();
			Entry<String, Integer> entry = qiyaoit.next();
			
			int zid = Integer.parseInt(entry.getKey());
			info.setZoneid(zid);
			info.setNum(entry.getValue());
			smsg.getZoneRewardnuminfo().add(info);
			//rewnum = rewnum + entry.getValue();
		}
		
		
		
		//多人副本奖励数量
		HashMap<String, Integer> teamrewardmap = player.getZoneteamrewardmap();
		Iterator<Entry<String, Integer>> it2 = teamrewardmap.entrySet().iterator();
		while (it2.hasNext()) {
			ZoneRewardNumInfo info = new ZoneRewardNumInfo();
			Entry<String, Integer> entry = it2.next();
			
			int zid = Integer.parseInt(entry.getKey());
			info.setZoneid(zid);
			info.setNum(entry.getValue());
			smsg.getZoneRewardnuminfo().add(info);
			//rewnum = rewnum + entry.getValue();
		}
		
		MessageUtil.tell_player_message(player, smsg);
		
		
		//-------------------------------剧情副本-连续自动扫荡
		
		List<ContinuousRaidsInfo> Raidcontinuouslist = player.getRaidcontinuouslist();
		if (Raidcontinuouslist.size() > 0) {
			int zonenum = 0;
			for (int i = 0; i < Raidcontinuouslist.size(); i++) {
				ContinuousRaidsInfo zones= Raidcontinuouslist.get(i);
				if (zones.getStatus() == 0) {
					zonenum = zonenum+ 1;
				}
			}
			if (zonenum > 0) {
				ResZoneContinuousRaidsMessage conmsg = new ResZoneContinuousRaidsMessage();
				ContinuousRaidsInfo zuihou = Raidcontinuouslist.get(Raidcontinuouslist.size()-1);
				conmsg.setZonenum(zonenum);
				conmsg.setSumtime(zuihou.getTime() - player.getRaidcontinuoustime());
				conmsg.setPassedime((int)(System.currentTimeMillis()/1000 ) - player.getRaidcontinuoustime());
				conmsg.setRewardnum(rewnum);
				conmsg.setZonetype(1);
				MessageUtil.tell_player_message(player, conmsg);
			}else {
				Raidcontinuouslist.clear();
			}
		}	
		
		//-----------------------七曜连续扫荡
		List<ContinuousRaidsInfo> qiyaolist = player.getQiyaocontinuouslist();
		if (qiyaolist.size() > 0) {
			int zonenum = 0;
			for (int i = 0; i < qiyaolist.size(); i++) {
				ContinuousRaidsInfo zones= qiyaolist.get(i);
				if (zones.getStatus() == 0) {
					zonenum = zonenum+ 1;
				}
			}
			if (zonenum > 0) {
				ResZoneContinuousRaidsMessage conmsg = new ResZoneContinuousRaidsMessage();
				ContinuousRaidsInfo zuihou = qiyaolist.get(qiyaolist.size()-1);
				conmsg.setZonenum(zonenum);
				conmsg.setSumtime(zuihou.getTime() - player.getRaidcontinuoustime());
				conmsg.setPassedime((int)(System.currentTimeMillis()/1000 ) - player.getQiyaocontinuoustime());
				conmsg.setRewardnum(rewnum);
				conmsg.setZonetype(4);
				MessageUtil.tell_player_message(player, conmsg);
			}else {
				qiyaolist.clear();
			}
		}	
		
		
	}
	

	
	/**前端手动请求领取奖励消息  
	 * 
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqZoneReceiveawardsMessage(Player player,ReqZoneReceiveawardsMessage msg) {
		Map map=ManagerPool.mapManager.getMap(player);
		if (map.getZoneId() > 0) {
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("在副本中不能领取自动扫荡奖励,离开副本后再领取吧"));
			return;
		}
		
		
		if (msg.getZid() > 0) {
			stZonePassShow(player,msg.getType(),msg.getZid());//发送奖励列表到前端
			return;
		}

		if (msg.getType() == 3) {	//多人活动副本
			HashMap<String, Integer> rewmap = player.getZoneteamrewardmap();
			for (String strzid : rewmap.keySet()) {
				int zid= Integer.valueOf(strzid);
				if((int)rewmap.get(strzid) > 0){
					stZonePassShow(player,msg.getType(),zid);//发送奖励列表到前端
					break;
				}
			}
		}else if (msg.getType() == 4) {	//七曜战将
			HashMap<String, Integer> rewmap = player.getZoneqiyaorewardmap();
			for (String strzid : rewmap.keySet()) {
				int zid= Integer.valueOf(strzid);
				if((int)rewmap.get(strzid) > 0){
					stZonePassShow(player,msg.getType(),zid);//发送奖励列表到前端
					break;
				}
			}
		}else {	//单人
			HashMap<String, Integer> rewmap = player.getZonerewardmap();
			for (String strzid : rewmap.keySet()) {
				int zid= Integer.valueOf(strzid);
				if((int)rewmap.get(strzid) > 0){
					stZonePassShow(player,msg.getType(),zid);//发送奖励列表到前端
					break;
				}
			}
		}

		
	}
	
	
	
	/**解析副本奖励
	 * 
	 * @param str
	 * @return
	 */
	public int[][] getZoneFixedReward(String str) {
		if (str == null || str.equals("")) {
			return null;
		}
		
		String[] rewardlist = str.split(Symbol.FENHAO);
		int[][] items = new int[rewardlist.length][3];
		if (rewardlist.length > 0) {
			for (int i = 0; i < items.length; i++) {
				String[] tmp = rewardlist[i].split(Symbol.XIAHUAXIAN_REG);
				if (tmp.length >= 3) {
					items[i][0] = Integer.parseInt(tmp[0]);
					items[i][1] = Integer.parseInt(tmp[1]);
					items[i][2] = Integer.parseInt(tmp[2]);
				}else {
					items[i][0] = Integer.parseInt(tmp[0]);
					items[i][1] = Integer.parseInt(tmp[1]);
					items[i][2] = 1;
				}
			}
		}
		return items;
	}
	
	
	
	public HashMap<Integer, Integer> getZoneCountReward(Player player,int zoneModelid,int type ){
		return getZoneCountReward(player,zoneModelid,type,false);
		
	}
	
	
	
	/**副本奖励计算
	 * player
	 * zoneModelid  副本模版ID
	 * type = 0 通关固定奖励，1参与奖
	 *  isauto = fulse 手动扫荡，true 自动扫荡
	 */
	public HashMap<Integer, Integer> getZoneCountReward(Player player,int zoneModelid,int type,boolean isauto){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		Q_clone_activityBean zoneBean = ManagerPool.zonesManager.getContainer(zoneModelid);
 		if (zoneBean != null) {
 			int[][] items = null;
 			if (type == 1) {	//参与奖
 				items=getZoneFixedReward(zoneBean.getQ_Participation_Award());
			}else if (type == 0){
				items = getZoneFixedReward(zoneBean.getQ_reward());
			}
 			if (items == null) {
 				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("数据库奖励为空"));
				return null;
			}
 			//+++++++++++++世界地图副本，星星数量决定奖励
 			int starlevel = 0;
 			double xishu = 1;	//奖励系数
 			if (zoneBean.getQ_zone_type() == 1) {
	 			if (isauto) {//自动扫荡获取最高星记录
					HashMap<String, Integer> zonestar = player.getRaidinfo().getZonestar();
					if (zonestar.containsKey(""+zoneModelid)) {
						starlevel = zonestar.get(""+zoneModelid);
		 	 			if (starlevel == 2) {
		 	 				xishu = 0.7;
		 				}else if(starlevel <= 1){
		 					xishu = 0.5;
		 				}
					}else {
						xishu = 0.5;
					}
				}else {
	 				starlevel = ManagerPool.zonesManager.computeStar(player,zoneModelid);
	 	 			if (starlevel == 2) {
	 	 				xishu = 0.7;
	 				}else if(starlevel <= 1){
	 					xishu = 0.5;
	 				}
				}
 			}

 			//+++++++++++++++世界地图副本，星星数量决定奖励
 			
 			
 			for (int[] is : items) {
 				if (is.length == 3 && is[2] > 1) {	//第3个数值大于1， 奖励存在系数，而且大于1
 					Q_characterBean data = ManagerPool.dataManager.q_characterContainer.getMap().get(player.getLevel());
 					if (is[0] == -3) {	//真气
						map.put(is[0], (int) ( data.getQ_basis_zhenqi() * is[2] * xishu));
					}else if (is[0] == -4) {//经验
						map.put(is[0], (int) ( data.getQ_basis_exp() * is[2] * xishu));
					}else  {
						map.put(is[0], (int) ( is[1] * xishu));
					}
				}else {
					if (is[0] > 0) {
						map.put(is[0],(int) (is[1]));
					}else {
						map.put(is[0], (int) ( is[1] * xishu));
					}
				}
			}
 		}
 		return map;
	}
	
	
	/**给玩家副本固定奖励
	 * 
	 * @param player
	 * @param zoneModelid
	 * @param type
	 */
	public void giveZoneFixedReward(Player player,int zoneModelid,int type){
		giveZoneFixedReward(player,zoneModelid,type,false);
		
	}

	/**给玩家副本固定奖励
	 * player
	 * zoneModelid  副本模版ID
	 * type = 0 通关固定奖励，1参与奖  
	 * isauto = fulse 手动扫荡，true 自动扫荡
	 */
	public void giveZoneFixedReward(Player player,int zoneModelid,int type,boolean isauto){
		HashMap<Integer, Integer> map = getZoneCountReward( player, zoneModelid, type,isauto);
		Iterator<Entry<Integer, Integer>> items = map.entrySet().iterator();
		while (items.hasNext()) {
			Entry<Integer, Integer> entry = (Entry<Integer, Integer>) items.next();
			int id = entry.getKey();
			int num = entry.getValue();
			FruitReward  fruitreward = new FruitReward();
			fruitreward.setItemModelid(id);
			fruitreward.setNum(num);
			fruitreward.setBind(true);
			giveRewarded(player,fruitreward,1);
		}
	}
 		
	
	/**设置每次通关奖励
	 * 
	 * @param player
	 * @param zonemodelid 类型不一样，奖励次数保存的地方不一样
	 */	
	public void addZoneReward(Player player ,int zoneModelId, boolean imme) {
		try {
			Q_clone_activityBean db = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(zoneModelId);
			HashMap<String, Integer> rewardmap = player.getZonerewardmap();//默认读取单人副本奖励
			if (db.getQ_zone_type() == 3) {
				rewardmap = player.getZoneteamrewardmap();//多人副本奖励保存
				List<Player> mapSameTeam = TeamManager.getInstance().getZoneMapSameTeam(player);//获得同组队同副本的玩家列表
				if (!imme && mapSameTeam.size() >= 2) {
					//军衔任务加次数
					ManagerPool.taskManager.action(player, Task.ACTION_TYPE_RANK, RankTaskEnum.COMPLETETEAMZONE, 1);
				}
				try {
					log.error(String.format("设置每次通关奖励 player=[%s][%s],zoneModelId=[%d],imme=[%d],同副本地图组队人数=[%d]", player.getName(), String.valueOf(player.getId()), zoneModelId, imme ? 1 :0, mapSameTeam.size()));
					Iterator<Player> iterator = mapSameTeam.iterator();
					while(iterator.hasNext()) {
						Player curPlayer =  iterator.next();
						if (curPlayer != null) {
							log.error(String.format("设置每次通关奖励 同副本地图组队玩家=[%s][%s]", curPlayer.getName(), String.valueOf(curPlayer.getId())));
						}
					}
				} catch (Exception e) {
					log.error(e,e);
				}
			}else if (db.getQ_zone_type() == 1) {
				//军衔任务加次数
				ManagerPool.taskManager.action(player, Task.ACTION_TYPE_RANK, RankTaskEnum.COMPLETECLASSICBATTLE, 1);
			}else if (db.getQ_zone_type() ==4) {
				rewardmap = player.getZoneqiyaorewardmap();	//七曜战将副本
				
				//记录七曜副本通关次数（只是记录第一次领取）
				if (player.getZoneinfo().containsKey(ZonesManager.Entrances+"_"+zoneModelId)) {
					int entrances = (Integer)player.getZoneinfo().get(ZonesManager.Entrances+"_"+zoneModelId);
					player.getZoneinfo().put(ZonesManager.Entrances+"_"+zoneModelId,entrances+1);
				}else {
					player.getZoneinfo().put(ZonesManager.Entrances+"_"+zoneModelId,1);
				}
				
				List<FruitReward> rewardlist = player.getRaidflop().getQiyaorewardlist();
				if (rewardlist.size() == 0) { //生成七曜副本奖励
					List<FruitReward> fruitRewards= ManagerPool.zonesFlopManager.createFruitRewardList(player,1,zoneModelId);//筛选并设置奖励列表 
					player.getRaidflop().getQiyaorewardlist().addAll(fruitRewards);
				}
			}
			
			
			if (db.getQ_zone_type() !=4) {	//生成普通战役奖励
				List<FruitReward> rewardlist = player.getRaidflop().getManualrewardlist();
				if (rewardlist.size() == 0) {
					List<FruitReward> fruitRewards= ManagerPool.zonesFlopManager.createFruitRewardList(player,1,zoneModelId);//筛选并设置奖励列表 
					player.getRaidflop().getManualrewardlist().addAll(fruitRewards);
				}
			}

			
			if(!rewardmap.containsKey(zoneModelId+"")){
				rewardmap.put(zoneModelId+"", 1);
			}else {
				int num = rewardmap.get(zoneModelId+"") + 1;
				rewardmap.put(zoneModelId+"", num);
			}
			
			long status = ManagerPool.countManager.getCount(player, CountTypes.ZONE_TEAM_ST, ""+zoneModelId);
			if (status == 0) {
				ManagerPool.countManager.addCount(player, CountTypes.ZONE_TEAM_ST, ""+zoneModelId,1, 2, 0);
			}else {
				ManagerPool.countManager.addCount(player, CountTypes.ZONE_TEAM_ST, ""+zoneModelId,2);
			}
			
			ManagerPool.zonesManager.stReqZoneOpenPanelMessage(player,null);//刷新剩余次数
		} catch (Exception e) {
			log.error(e, e);
		}
	}
	
	
	
	
	/**循环领奖
	 * 
	 */
	public void autoAwardfor(Player player,int zonetype){
		HashMap<String, Integer> rewmap = null;
		if (zonetype == 4) {
			rewmap = player.getZoneqiyaorewardmap();	//七曜奖励次数保存
		}else {
			rewmap = player.getZonerewardmap();	//单人扫荡副本励次数保存
		}
		
		Iterator<Entry<String, Integer>> it = rewmap.entrySet().iterator();
		ResGetItemReasonsMessage cmsg = new ResGetItemReasonsMessage();
		while (it.hasNext()) {
			Entry<String, Integer> entry = it.next();
			int num =entry.getValue();
			for (int i = 0; i < num; i++) {//可领奖励次数
				FruitReward fruitReward = autoAward(player,Integer.parseInt(entry.getKey()));
				if (fruitReward != null) {
					ItemReasonsInfo info = new ItemReasonsInfo();
					info.setItemId(fruitReward.getItemModelid());
					info.setItemModelId(fruitReward.getItemModelid());
					info.setItemNum(fruitReward.getNum());
					info.setItemReasons(0);
					cmsg.getItemReasonsInfoList().add(info);
				}
			}
		}
		
//		HashMap<String, Integer> teamrewmap = player.getZoneteamrewardmap();//多人活动副本
//		Iterator<Entry<String, Integer>> teamit = teamrewmap.entrySet().iterator();
//		while (teamit.hasNext()) {
//			Entry<String, Integer> entry = teamit.next();
//			int num =entry.getValue();
//			for (int i = 0; i < num; i++) {
//				autoAward(player,Integer.parseInt(entry.getKey()));
//			}
//		}
		
		cmsg.setItemReasons(Reasons.RAID_ITEM.getValue());
		MessageUtil.tell_player_message(player, cmsg);
		LoginRaidRewarded(player);
	}
	
	
	
	
	/**自动领奖
	 * @return 
	 * 
	 */
	public FruitReward autoAward(Player player ,int zoneModelId){
		Q_clone_activityBean data = ManagerPool.zonesManager.getContainer(zoneModelId);
		if (data != null) {
			List<FruitReward> fruitRewards= ManagerPool.zonesFlopManager.createFruitRewardList(player,1,zoneModelId);//筛选并设置奖励列表 
			if (fruitRewards.size() > 0) {
				ZoneRewardLog zlog =new ZoneRewardLog();
				HashMap<String, Integer> rewmap = player.getZonerewardmap();
				if (data.getQ_zone_type() == 3) {//读多人副本奖励
					rewmap = player.getZoneteamrewardmap();
				}else if (data.getQ_zone_type() == 4) {
					rewmap = player.getZoneqiyaorewardmap();
				}
				
				zlog.setBeforerewardlist(rewmap.toString());
				
				boolean is = false;	//标记当前地图次数是否存在
				if (rewmap.containsKey(zoneModelId+"")) {
					int num = rewmap.get(zoneModelId+"");
					if (num > 0) {
						num = num -1;
						rewmap.put(zoneModelId+"",num);
						is = true;	//次数已扣
					}
				}

				if (is==false) {	//如果上面没扣除次数，则在这里找一个副本次数扣除
					for (String strzid : rewmap.keySet()) {
						int tmpnum = rewmap.get(strzid);
						if(tmpnum > 0){
							tmpnum = tmpnum -1;
							rewmap.put(strzid, tmpnum);
							is = true;
							break;
						}
					}
				}
				if (is == false) {
					return null;
				}
				
				if (data.getQ_zone_type() == 1) {//读战役单人副本奖励
					giveZoneFixedReward(player, zoneModelId,0,true);	// 自动扫荡固定奖励
					zlog.setZonemodelid(zoneModelId);
				}else if (data.getQ_zone_type() == 3) {//读多人副本奖励
					giveZoneFixedReward(player, zoneModelId,0,false);
					zlog.setZonemodelid(zoneModelId);
				}else if (data.getQ_zone_type() == 4) {
					giveZoneFixedReward(player, zoneModelId,0,false);
					zlog.setZonemodelid(zoneModelId);
					if (player.getZoneinfo().containsKey(ZonesManager.Entrances+"_"+zoneModelId)) {
						int entrances = (Integer)player.getZoneinfo().get(ZonesManager.Entrances+"_"+zoneModelId);
						if (entrances == 1) {
							giveZoneFixedReward(player, zoneModelId,1,false);//七曜副本第一次额外奖励（参与奖）
						}
					}
				}
				
				int rnd= 0;
				List<Integer> rndlist = new ArrayList<Integer>();
				for (FruitReward fruitReward : fruitRewards) {
					 Q_spirittree_pack_conBean fruitdata = getpackcondata().get(fruitReward.getIdx());
					 if (fruitdata == null) {
						 rndlist.add(100);	//数据库改变后，默认值
					}else{
						rndlist.add(fruitdata.getQ_selected_rnd());
					}
				}
				
				rnd = RandomUtils.randomIndexByProb(rndlist);
				FruitReward fruitReward = fruitRewards.get(rnd);
				giveRewarded(player, fruitReward,0);	//给副本奖励
				zlog.setPlayerid(player.getId());
				zlog.setRemainderrewardlist(rewmap.toString());
				LogService.getInstance().execute(zlog);
				return fruitReward;
			}
		}
		return null;
	}
	
	
}
