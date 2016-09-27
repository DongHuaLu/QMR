package com.game.sceneobj.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Attribute;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemTypeConst;
import com.game.config.Config;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_mapBean;
import com.game.data.bean.Q_scene_objBean;
import com.game.data.manager.DataManager;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.structs.Map;
import com.game.monster.manager.MonsterManager;
import com.game.monster.structs.Monster;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.sceneobj.timer.SceneObjSpecialRefreshTimer;
import com.game.server.impl.MServer;
import com.game.server.impl.WServer;
import com.game.spirittree.structs.FruitReward;
import com.game.spirittree.structs.FruitRewardAttr;
import com.game.structs.Position;
import com.game.structs.Reasons;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;

public class SceneobjManager {

	protected Logger log = Logger.getLogger(SceneobjManager.class);
	//玩家管理类实例
	private static SceneobjManager manager;
	private static Object obj = new Object();

	private SceneobjManager() {
	}

	public static SceneobjManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new SceneobjManager();
			}
		}
		return manager;
	}
	
	
	
	public static String GOLDTHIEF = "GOLDTHIEF";
	public static int GOLDTHIEFBOSSID = 5009;
	public HashMap<String, Object> sceneobjmap = new HashMap<String, Object>();

	public List<Q_scene_objBean> getSceneobjdatalist() {
		return ManagerPool.dataManager.q_scene_objContainer.getList();
	}

	public HashMap<String, Object> getSceneobjmap() {
		return sceneobjmap;
	}

	public void setSceneobjmap(HashMap<String, Object> sceneobjmap) {
		this.sceneobjmap = sceneobjmap;
	}

	public boolean parseMapid(Map map, Q_scene_objBean q_scene_objBean) {
		if (q_scene_objBean.getQ_refresh_map() == -1) {
			return true;
		}
		if (map.getMapModelid() == q_scene_objBean.getQ_refresh_map()) {
			return true;
		}
		return false;
	}

	public List<Map> getRefreshMaps(Q_scene_objBean q_scene_objBean) {
		List<Map> maps = new ArrayList<Map>();
		Iterator<Map> iterator = MapManager.getMapping().values().iterator();
		while (iterator.hasNext()) {
			Map map = iterator.next();
			if (!map.isCopy()) {
				Q_mapBean q_mapBean = DataManager.getInstance().q_mapContainer.getMap().get(map.getMapModelid());
				if (q_mapBean != null && !q_mapBean.getQ_map_lines().equalsIgnoreCase("1") && q_mapBean.getQ_map_subsidiary() != 1 && q_mapBean.getQ_map_id() != 30002 && q_mapBean.getQ_map_id() != 30003) {
					if (MapManager.checkLineIsOpen(map.getMapModelid(), map.getLineId())) {
						maps.add(map);
					}
				}
			}
		}
		return maps;
	}

	public void refreshMonster(Q_scene_objBean q_scene_objBean) {
		List<Map> maps = getRefreshMaps(q_scene_objBean);
		Map map = maps.get(RandomUtils.random(maps.size()));
		if (map != null) {
			Position position = MapUtils.getMapRandPoint(map.getMapModelid());
			if (position != null) {
				Monster refreshMonster = MonsterManager.getInstance().createMonsterAndEnterMap(GOLDTHIEFBOSSID, map.getServerId(), map.getLineId(), map.getMapModelid(), position);
				if (refreshMonster != null) {
					getSceneobjmap().put(GOLDTHIEF, refreshMonster);
					log.info("mapid:" + map.getMapModelid() + ",line:" + map.getLineId() + ",x:" + position.getX() + ",y:" + position.getY() + ",monid:" + GOLDTHIEFBOSSID + ",num:" + 1);
				}
			}
		}
		ListIterator<Map> listIterator = maps.listIterator();
		while (listIterator.hasNext()) {
			Map refreshMap = listIterator.next();
			if (refreshMap != null) {
				MServer mServer = WServer.getInstance().getMServer(refreshMap.getLineId(), refreshMap.getMapModelid());
				if (mServer != null) {
					mServer.getMainThread().addTimerEvent(new SceneObjSpecialRefreshTimer(refreshMap.getServerId(), refreshMap.getLineId(), refreshMap.getMapModelid()));
					//break;
				}
			}
		}
	}

	public void loopRefreshSceneObjSpecial(String callStr) {
		boolean borefresh = false;
		Q_scene_objBean curQ_scene_objBean = null;
		ListIterator<Q_scene_objBean> listIterator = DataManager.getInstance().q_scene_objContainer.getList().listIterator();
		while (listIterator.hasNext()) {
			Q_scene_objBean q_scene_objBean = listIterator.next();
			if (q_scene_objBean != null && q_scene_objBean.getQ_class().equalsIgnoreCase(callStr)) {
				if (TimeUtil.checkRangeTime(q_scene_objBean.getQ_refresh_time())) {
					borefresh = true;
					curQ_scene_objBean = q_scene_objBean;
					break;
				}
			}
		}
		if (!borefresh) {
			return;
		}
		if (curQ_scene_objBean == null) {
			return;
		}
		if (getSceneobjmap().containsKey(GOLDTHIEF)) {
			Monster monster = (Monster) getSceneobjmap().get(GOLDTHIEF);
			if (monster != null) {
				if (monster.isDie()) {
					refreshMonster(curQ_scene_objBean);
				}
			} else {
				refreshMonster(curQ_scene_objBean);
			}
		} else {
			refreshMonster(curQ_scene_objBean);
		}
		MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("金钱鼠老大带领贼徒们又来祸害百姓，前往铲除恶贼的侠义之士必定有所回报。"));
	}

	
	
/**奖励计算
 * 
 * @param player
 * @param sceneid
 * @return
 */
	public HashMap<Integer, Integer> getSceneReward(Player player,int sceneid){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		Q_scene_objBean sceneBean = ManagerPool.dataManager.q_scene_objContainer.getMap().get(sceneid);
 		if (sceneBean != null) {
 			int[][] items=ManagerPool.zonesFlopManager.getZoneFixedReward(sceneBean.getQ_reward());
 			if (items == null) {
 				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("数据库奖励为空"));
				return null;
			}

 			for (int[] is : items) {
 				if (is.length == 3 && is[2] > 1) {	//奖励存在系数，而且大于1
 					Q_characterBean data = ManagerPool.dataManager.q_characterContainer.getMap().get(player.getLevel());
 					if (is[0] == -3) {	//真气
						map.put(is[0], (int) ( data.getQ_basis_zhenqi() * is[2]));
					}else if (is[0] == -4) {//经验
						map.put(is[0], (int) ( data.getQ_basis_exp() * is[2]));
					}else  {
						map.put(is[0], is[1]);
					}
				}else {
					if (is[0] > 0) {
						map.put(is[0], is[1]);
					}
				}
			}
 		}
 		return map;
	}
	
	
	
	/**给玩家奖励
	 * 
	 * @param player
	 * @param sceneid
	 */
	public void giveSceneFixedReward(Player player,int sceneid){
		long action = Config.getId();
		HashMap<Integer, Integer> map = getSceneReward( player, sceneid);
		Iterator<Entry<Integer, Integer>> items = map.entrySet().iterator();
		while (items.hasNext()) {
			Entry<Integer, Integer> entry = (Entry<Integer, Integer>) items.next();
			int id = entry.getKey();
			int num = entry.getValue();
			FruitReward  fruitreward = new FruitReward();
			fruitreward.setItemModelid(id);
			fruitreward.setNum(num);
			fruitreward.setBind(true);
			giveSceneRewarded(player,fruitreward,action);
		}
	}
 		
	

	
	
	/**场景物件采集奖励
	 * 
	 * @param player
	 * @param fruitReward
	 */
	public void giveSceneRewarded(Player player , FruitReward fruitReward,long action) {
		String rewardedname = ResManager.getInstance().getString("采集奖励：");
		int id = fruitReward.getItemModelid();
		
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
				ManagerPool.playerManager.addZhenqi(player, fruitReward.getNum(),AttributeChangeReason.SCENEOBJ);
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}
		}else if (id == -4) {
			itemname = ResManager.getInstance().getString("经验");
			if(player != null){
				ManagerPool.playerManager.addExp(player, fruitReward.getNum(),AttributeChangeReason.SCENEOBJ);
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}
		}else if (id == -5) {
			itemname = ResManager.getInstance().getString("礼金");
			if(player != null && ManagerPool.backpackManager.changeBindGold(player, fruitReward.getNum(), Reasons.RAID_BIND_YUANBAO, action)){
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
				}else if (id == -2 ) {	//元宝
					//ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,"系统邮件",rewardedname+":"+itemname,(byte) 2,fruitReward.getNum(),new ArrayList<Item>());
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
