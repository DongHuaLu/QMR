package com.game.drop.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.message.ResTakeUpSuccessMessage;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.IAutoUseItem;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_boss_dropBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_mapBean;
import com.game.data.bean.Q_monster_dropgroupBean;
import com.game.data.bean.Q_monster_dropprobBean;
import com.game.data.manager.DataManager;
import com.game.drop.structs.DropItem;
import com.game.drop.structs.MapDropInfo;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.bean.DropGoodsInfo;
import com.game.map.manager.MapManager;
import com.game.map.message.ResRoundGoodsDisappearMessage;
import com.game.map.structs.Area;
import com.game.map.structs.Map;
import com.game.monster.script.IMonsterDieDropScript;
import com.game.monster.structs.Hatred;
import com.game.monster.structs.Monster;
import com.game.monster.structs.Morph;
import com.game.monster.structs.MorphType;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.structs.Position;
import com.game.structs.Reasons;
import com.game.team.manager.TeamManager;
import com.game.utils.BeanUtil;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.StringUtil;
import com.game.utils.Symbol;
/**
 * 掉落 管理类
 * @author 赵聪慧
 * 2012-2-29下午4:19:03
 */
public class DropManager {
	private static final Logger log=Logger.getLogger(DropManager.class);
	/**
	 * 最大掉落数
	 */
	private static final int MAXDROPGOODNUM=50;
	
	/**
	 * BOSS追加掉落最大配置数
	 */
	private static final int MAXBOSSDROPGOODNUM=20;
	
	/**
	 * 单个组包最大可配置数量
	 */
	private static final int MAXGROUPDROPGOODNUM=20;
	
	/**
	 * 最大拾取距离(像素)
	 */
	private static final int TAKEUPDISTANCE=50;
	
	/**
	 * 拾取后自动使用的最大检测距离
	 */
	private static final int AUTOUSEDISTANCE=100;
	/**
	 * 是否可丢弃
	 */
	private static final int DROPABLE=1;
//	/**
//	 * 掉落的所有物品
//	 */
//	private static  HashMap<Long,Item> items=new HashMap<Long, Item>();
	
	private static HashMap<Integer,Integer> groups=new HashMap<Integer, Integer>();
	
	/**
	 * 杀怪计数(KEY=怪物模型ID)用于一定数量必定掉一个的逻辑
	 */
	private static HashMap<Integer,Integer> counter=new HashMap<Integer, Integer>();
	
	public static int count(final int monsterModelId){
		Integer count=null;
		synchronized (counter) {
			 count= counter.get(monsterModelId);
			 count=count==null?1:++count;
			 if(count<0){
				 //防止溢出
				 count=Integer.MAX_VALUE;
			 }
			counter.put(monsterModelId,count);
			if(log.isDebugEnabled()){
				log.debug("kill count"+monsterModelId+"_"+count);	
			}
		}
		return count;
	}
	
	public static int getCount(int monsterModelId){
		synchronized (counter) {
			try{
				Integer integer = counter.get(monsterModelId);
				
				return integer==null?0:integer;	
			}catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			return 0;
		}
	}
	
	public static void resetCount(final int monsterModelId){
		synchronized (counter) {
			try {
				counter.remove(monsterModelId);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	}
	
	
	
	/**
	 * 怪物死亡掉落
	 * @param player
	 * @param monster
	 */
	public static void monsterDieDrop(Monster monster) {
		try {
			IMonsterDieDropScript script = (IMonsterDieDropScript) ManagerPool.scriptManager.getScript(ScriptEnum.MONSTER_DROP);
			if (script != null) {
				script.onMonsterDie(monster, monster.getKiller());
			} else {
				log.error("怪物掉落脚本不存在！");
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		
		Q_monster_dropprobBean dropModel = ManagerPool.dataManager.q_monster_dropprobContainer.getMap().get(monster.getModelId());
		if(dropModel==null){
			return ;
		}
		
		List<DropItem> dropConfigList = new ArrayList<DropItem>();
		try {
			// 将配置中的各项掉落解析出来
			for (int i = 1; i <= MAXDROPGOODNUM; i++) {
				Object value = BeanUtil.getMethodValue(dropModel, "Q_goods" + i);
				if (value != null) {
					String str = (String) value;
					DropItem item = buildItem(str);
					if (item != null) {
						dropConfigList.add(item);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		for (DropItem item : dropConfigList) {
			try {
				if (item instanceof CopperDrop) {
					// 计算机率
					boolean isDrop = RandomUtils.isGenerate(item.getProbability(), Global.MAX_PROBABILITY);
					// System.out.println(getProbability()+"-"+Global.MAX_PROBABILITY+"-"+isDrop);
					// 如果是计数类型
					if (item.getMaxNum() > 1) {
						int count = DropManager.getCount(monster.getModelId());
						if (item.getMaxNum() == count) {
							isDrop = true;
							DropManager.resetCount(monster.getModelId());
						} else {
							isDrop = false;
							DropManager.count(monster.getModelId());
						}
					}
					if (isDrop) {
						Morph morph = monster.getMorph().get(MorphType.MONEY.getValue());
						if (morph != null) {
							int value = morph.getValue()  / 10000;
							while (value > 0) {
								try {
									MapDropInfo buildGoodsInfo = ((CopperDrop) item).buildGoodsInfo(monster);
									if (buildGoodsInfo == null) {
										break;
									} else {
										item.drop(buildGoodsInfo);
									}
								} catch (Exception e) {
									log.error(e, e);
								}
								value--;
							}
						} else {
							MapDropInfo buildGoodsInfo = ((CopperDrop) item).buildGoodsInfo(monster);
							// MapDropInfo buildGoodsInfo =
							// buildGoodsInfo(monster);
							if (buildGoodsInfo == null) {
								// //// logger.error("这里不应该为空但有时候会报出来",new
								// Exception());
							} else {
								item.drop(buildGoodsInfo);
							}
						}
					}
				} else {
					MapDropInfo dropGoods = item.buildDropGoods(monster);
					if (dropGoods != null) {
						item.drop(dropGoods);
					}
				}
			} catch (Exception e) {
				log.error(e, e);
			}
		}
	}
	
	/**
	 * BOSS鞭尸掉落
	 * @param monster
	 */
	public static void bossCorpseDrop(Monster monster) {
		try {
			if (ManagerPool.monsterManager.isBoss(monster.getModelId()) || ManagerPool.monsterManager.isMapMonster(monster.getModelId())) {
				List<String> array=new ArrayList<String>();
				
				Q_boss_dropBean dropModel = DataManager.getInstance().q_boss_dropContainer.getMap().get(monster.getModelId());
				if(dropModel!=null){
					for (int i = 1; i <= MAXBOSSDROPGOODNUM; i++) {
						String propValue = (String) BeanUtil.getMethodValue(dropModel, "Q_goods" + i);
						if (!StringUtil.isBlank(propValue)) {
								array.add(propValue);
						}
					}	
				}
				
				if(array.size()>0){
					try{
						int random = RandomUtils.random(1,array.size()-1);
						String itemdefine = array.get(random);
						Item item =null;
						if(itemdefine.toLowerCase().startsWith("copper")){
							String[] couteTrs = itemdefine.split("[*]");
							if (couteTrs.length == 2) {
								item=Item.createMoney(Integer.parseInt(couteTrs[1]));
							}
						}else if(itemdefine.toLowerCase().startsWith("@")){
							itemdefine = itemdefine.replace("@","");
							Q_monster_dropgroupBean groupModel = getGroupModel(itemdefine);
							Q_itemBean next = DropManager.getNowGroupAndToNext(groupModel);
							List<Item> createItems = Item.createItems(next.getQ_id(),1, false, 0,DropItem.getGradeNum(monster),DropItem.getAppendCount(monster));
							if(createItems.size()<=0||createItems.get(0)==null){
								return;
							}
							item=createItems.get(0);
						}else{
							Q_itemBean q_itemBean = getItemModelByName(itemdefine);
							List<Item> list = Item.createItems(q_itemBean.getQ_id(),1, false, 0,DropItem.getGradeNum(monster),DropItem.getAppendCount(monster));
							if(list.size()>=0||list.get(0)==null){
								return;
							}
							item = list.get(0);
						}
						if(item==null){
							log.error("boss追加掉落 出错定义"+itemdefine);
							return;
						}
						DropGoodsInfo info=new DropGoodsInfo();
						info.setDropGoodsId(item.getId());
						info.setItemModelId(item.getItemModelId());
						info.setNum(item.getNum());
						info.setDropTime(System.currentTimeMillis());
						if(item instanceof Equip){
							Equip equip=(Equip) item;
							if(equip.getAttributes()!=null){
								info.setAttributs((byte) equip.getAttributes().size());	
							}else{
								info.setAttributs((byte) 0);
							}
							info.setIntensify((byte) equip.getGradeNum());
							info.setIsFullAppend((byte) (equip.isFullAppend()?1:0));
						}
						Hatred owner = monster.getMaxHatred();
						Map map = MapManager.getInstance().getMap(monster);
						Position ableDropPoint =DropItem.getAbleDropPoint(monster.getPosition(), map);
						info.setX(ableDropPoint.getX());
						info.setY(ableDropPoint.getY());
						if(owner!=null&&owner.getTarget()!=null){
							if(owner.getTarget() instanceof Player){
								//如果是宠物攻击 则需要另行处理
								Player player=(Player) owner.getTarget();
								info.setOwnerId(player.getId());	
							}			
						}
						MapDropInfo mapDropInfo = new MapDropInfo(info, item,map,0);
						Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());					
						if (q_itemBean != null&&monster.isShow()) {
							if (q_itemBean.getQ_notice() == 3 || q_itemBean.getQ_notice() == 4) {
								int mapModelid = mapDropInfo.getMapModelid();
								Q_mapBean mapModel = DataManager.getInstance().q_mapContainer.getMap().get(mapModelid);
								short x = mapDropInfo.getDropinfo().getX();
								short y = mapDropInfo.getDropinfo().getY();
								int lineId = mapDropInfo.getLine();
								int serverId = mapDropInfo.getServerId();
								String name = WServer.getGameConfig().getCountryNameByServer(serverId);
								MessageUtil.notify_All_player(Notifys.CUTOUT, ResManager.getInstance().getString("{1}掉落在{2}{3}{4}的[{5},{6}]"),BackpackManager.getInstance().getName(q_itemBean.getQ_id()), name, mapModel.getQ_map_name(), lineId + ResManager.getInstance().getString("线"), String.valueOf(x/25),
										String.valueOf(y/25));
							}
						}
						mapDropInfo.getHideSet().addAll(monster.getHideSet());
						mapDropInfo.getShowSet().addAll(monster.getShowSet());
						mapDropInfo.setShow(monster.isShow());
						MapManager.getInstance().enterMap(mapDropInfo);
					}catch (Exception e) {
						log.error(e,e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 人物丢弃物品
	 * @param roleId
	 * @param cellId
	 */
	public static void playerDiscard(Player player, int cellId){
		if(cellId<=0||cellId>Global.MAX_BAG_CELLS){
			return;
		}
		if(player.isDie()){//死亡不允许丢物品
			return ;
		}
		Item item = BackpackManager.getInstance().getItemByCellId(player, cellId);
		if(item!=null){
			Q_itemBean q_itemBean = ManagerPool.dataManager.q_itemContainer.getMap().get(item.getItemModelId());
			if(q_itemBean.getQ_drop()!=DROPABLE){
				MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，该物品无法丢弃"));
				return;
			}
			if(MapManager.getInstance().isSafe(player.getPosition(),player.getMapModelId())){
				MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，在安全区无法丢弃物品"));
				return;
			}
			if(item.isBind()){
				MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，绑定物品无法丢弃"));
				return;
			}
			if(item.isTrade()){
				MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，物品正在交易中"));
				return;
			}
			if(PlayerState.SWIM.compare(player.getState())){
				MessageUtil.notify_player(player, Notifys.NORMAL,ResManager.getInstance().getString("游泳时不可以丢弃物品"), player.getName());
				return;
			}
			Map map = MapManager.getInstance().getMap(player);
			Position ableDropPoint = DropItem.getAbleDropPointAndAxclude(player.getPosition(), map);
			if(ableDropPoint==null){
				MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，该区域不允许丢弃物品"));
				return;
			}
			ManagerPool.backpackManager.removeItemByCellId(player, cellId,Reasons.GOODSDISCARD,Config.getId());
			DropGoodsInfo info=new DropGoodsInfo();
			info.setDropGoodsId(item.getId());
			info.setItemModelId(item.getItemModelId());
			info.setNum(item.getNum());
			if(item instanceof Equip){
				Equip equip=(Equip) item;
				if(equip.getAttributes()!=null){
					info.setAttributs((byte) equip.getAttributes().size());	
				}else{
					info.setAttributs((byte) 0);
				}
				info.setIntensify((byte) equip.getGradeNum());
				info.setIsFullAppend((byte) (equip.isFullAppend()?1:0));
			}
			info.setX(ableDropPoint.getX());
			info.setY(ableDropPoint.getY());
			info.setDropTime(System.currentTimeMillis());
			item.setGridId(-1);
			MapDropInfo mapDropInfo = new MapDropInfo(info, item, map,0);
			if (q_itemBean != null) {
				if (q_itemBean.getQ_notice() == 3 || q_itemBean.getQ_notice() == 4) {
					int mapModelid = mapDropInfo.getMapModelid();
					Q_mapBean mapModel = DataManager.getInstance().q_mapContainer.getMap().get(mapModelid);
					short x = mapDropInfo.getDropinfo().getX();
					short y = mapDropInfo.getDropinfo().getY();
					int lineId = mapDropInfo.getLine();
					int serverId = mapDropInfo.getServerId();
					String name = WServer.getGameConfig().getCountryNameByServer(serverId);
					MessageUtil.notify_All_player(Notifys.CUTOUT, ResManager.getInstance().getString("{1}掉落在{2}{3}{4}的[{5},{6}]"), BackpackManager.getInstance().getName(q_itemBean.getQ_id()), name, mapModel.getQ_map_name(), lineId + ResManager.getInstance().getString("线"), String.valueOf(x/25),
							String.valueOf(y/25));
				}
			}
			MapManager.getInstance().enterMap(mapDropInfo);
		}
	}

	
	
	private static Pattern pattern = Pattern.compile("(\\d+)/(\\d+)(\\s+)(.*)");
	
	/**
	 * 解析掉落配置项
	 * @param str
	 * @return
	 */
	private static DropItem buildItem(String str) {
		DropItem item = null;
		try {
			if (str != null && str.equals("")) {
				return null;
			}
			
			String[] goodStr = null;
			//韩文平台 掉落单独解析因为奇葩韩文道具名有空格
			if(WServer.getInstance().getServerWeb().equals("hgpupugame")){
				Matcher matcher = pattern.matcher(str);
				if(matcher.find()){
					String fen = matcher.group(1)+"/"+matcher.group(2);
					String drop = matcher.group(4);
					goodStr=new String[]{fen,drop};
				}
			}else {
				//其他平台正常解析
				str=str.replace("  ", " ");
				str=str.replace("  ", " ");
				str=str.replace("  ", " ");
				str=str.replace("  ", " ");
				goodStr = str.split(" ");
			}

			
			if (goodStr == null) {
				log.error("掉落配置有错误:"+str);
				return null;
			}

			if (goodStr.length != 2) {
				// 数据错误
				if(goodStr.length>2)
				log.error("掉落配置有误:"+str);
				return null;
			}
			
			boolean gradeLimit = true;
			boolean bind = false;
			boolean isOwner = true;
			if (goodStr[1].contains("~")) {
				// 是否排除在爆率衰减规则之外
				gradeLimit = false;
				goodStr[1] = goodStr[1].replace("~", "");
			}
			if (goodStr[1].contains("$")) {
				// 掉落后将物品设为拾取绑定
				goodStr[1] = goodStr[1].replace("$", "");
				bind = true;
			}
			if (goodStr[1].contains("&")) {
				// 掉出后为无主物品
				goodStr[1] = goodStr[1].replace("&", "");
				isOwner = false;
			}

			if (goodStr[1].contains("@")) {
				// 组包掉落规则初始化
				item = new GroupDrop();
				goodStr[1] = goodStr[1].replace("@", "");
				String name = goodStr[1];
				Q_monster_dropgroupBean groupItemModel = getGroupModel(name);
				item.setGroupModel(groupItemModel.getQ_group_id());
			} else if (goodStr[1].contains("#")) {
				// 任务掉落
				item = new TaskDrop();
				TaskDrop taskDrop=(TaskDrop) item;
				goodStr[1] = goodStr[1].replace("#", "");
				String express = goodStr[1];
				String[] split = express.split(Symbol.XIAHUAXIAN_REG);
				int taskId = Integer.parseInt(split[0]);
				taskDrop.setTaskId(taskId);
				Q_itemBean itemModel = getItemModelByName(split[1]);
				String string = split[2];
				int maxNum = Integer.parseInt(string);
				taskDrop.setTaskMaxNum(maxNum);
				if(itemModel==null){
					log.debug(split[1]+"在模型表中找不到");
				}
				taskDrop.setItemModel(itemModel.getQ_id());
			} else if (goodStr[1].contains("!")) {
				// 脚本掉落
				item = new ScripDrop();
				goodStr[1] = goodStr[1].replace("!", "");
				Integer script=Integer.parseInt(goodStr[1]);
				ScripDrop drop=(ScripDrop) item;
				drop.setScriptId(script);
			} else if (goodStr[1].contains("copper")) {
				String[] couteTrs = goodStr[1].split("[*]");
				if (couteTrs.length == 2) {
					item = new CopperDrop(Integer.parseInt(couteTrs[1]));
					// 金币类型模型为-1
					item.setItemModel(-1);
				}
			} else {
				String name = goodStr[1];
				Q_itemBean itemModel = getItemModelByName(name);
				if(itemModel!=null){
					item = new CommonDrop();
					item.setItemModel(itemModel.getQ_id());
				}else{
					log.error(name+"物品模型找不到");
				}
			}
			String[] jilvStr = goodStr[0].split("/");
			if (jilvStr.length == 2) {
				float fenzi = Float.parseFloat(jilvStr[0]);
				float fenmu = Float.parseFloat(jilvStr[1]);
				if (fenzi > fenmu) {
					// 打多少怪物必定掉一个
					item.setMaxNum((int) (fenzi / fenmu));
				} else {
//					if(fenmu==2){
//						System.out.println();
//					}
					// 转换成以系统默认几率基数的几率形式
					item.setProbability((int) ((Global.MAX_PROBABILITY / fenmu) * fenzi));
					
				}
			}
			if(item!=null){
				item.setGradeLimit(gradeLimit);
				item.setBind(bind);
				item.setOwner(isOwner);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}
	
	private static Q_itemBean getItemModelByName(String name){
		List<Q_itemBean> list = ManagerPool.dataManager.q_itemContainer.getList();
		for (Q_itemBean q_itemBean : list) {
			if(name.equals(q_itemBean.getQ_name())){
				return q_itemBean;
			}
		}
		log.error(name+"在模型表中找不到");
		return null;
	}
	
	
	private static Q_monster_dropgroupBean getGroupModel(String name){
		List<Q_monster_dropgroupBean> list = ManagerPool.dataManager.q_monster_dropgroupContainer.getList();
		for (Q_monster_dropgroupBean q_monster_dropgroupBean : list) {
			if(name.equals(q_monster_dropgroupBean.getQ_groupname())){
				return q_monster_dropgroupBean;
			}
		}
		return null;
	}
	
	public static Q_itemBean getNowGroupAndToNext(Q_monster_dropgroupBean model) {
		synchronized (groups) {
			try {
				Integer index = groups.get(model.getQ_group_id());
				if (index == null || index == 0) {
					index = 1;
					groups.put(model.getQ_group_id(), index);
				}
				String value = (String)BeanUtil.getMethodValue(model, "Q_group" + index);
				if(StringUtil.isBlank(value)){
					index = 1;
					groups.put(model.getQ_group_id(), index);
					return null;
				}
				Q_itemBean itemModelByName = getItemModelByName((String) value);
				if(index>MAXGROUPDROPGOODNUM){
					index=MAXGROUPDROPGOODNUM;
					groups.put(model.getQ_group_id(), index);
				}
				if(index==MAXGROUPDROPGOODNUM){
					index=1;
				}else{
					index++;
					value = (String) BeanUtil.getMethodValue(model, "Q_group" + index);
					if(StringUtil.isBlank(value)){
						index=1;
					}
				}
				groups.put(model.getQ_group_id(), index);
				return itemModelByName;
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			return null;
		}

	}
	
//	public static void groupToNext(Q_monster_dropgroupBean model){
//		synchronized (groups) {
//			try{
//				Integer index = groups.get(model.getQ_group_id());
//				if(index==null||index==0||index==MAXDROPGOODNUM){
//					index=1;
//				}else{
//					
//				}
//				groups.put(model.getQ_group_id(),index);	
//			}catch (Exception e) {
//				log.error("组包指针下移出错",e);
//			}
//		}
//	}
	
	/**
	 * 拾取物品
	 * 
	 * @param roleId
	 * @param goodsId
	 */
	public static void takeUp(Player player, long goodsId) {
		if(player.isDie()){
			//死了不能捡东西
			return;
		}
		Position position = player.getPosition();
		Item item = null;
		try {

			Map map = MapManager.getInstance().getMap(player);
			
			List<Area> round = MapManager.getInstance().getRound(map, position);
			MapDropInfo mapDropInfo=null;
			Area areas=null;
			for (Area area : round) {
				MapDropInfo info = area.getDropGoods().get(goodsId);
				if(info!=null){
					mapDropInfo=info;
					areas=area;
					break;
				}		
			}			
			if (mapDropInfo == null) {
//				MessageUtil.notify_player(player, Notifys.NORMAL, "物品不存在");
				ResRoundGoodsDisappearMessage dismsg = new ResRoundGoodsDisappearMessage();
				dismsg.getGoodsIds().add(goodsId);
				MessageUtil.tell_player_message(player, dismsg);
				return;
			}
			item = mapDropInfo.getItem();
			DropGoodsInfo dropGoodsInfo = mapDropInfo.getDropinfo();
			if (item == null||dropGoodsInfo==null) {
//				MessageUtil.notify_player(player, Notifys.NORMAL, "物品不存在");
				ResRoundGoodsDisappearMessage dismsg = new ResRoundGoodsDisappearMessage();
				dismsg.getGoodsIds().add(goodsId);
				MessageUtil.tell_player_message(player, dismsg);
				return;
			}
		
			Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
			if(model.getQ_auto_use()==1){
				autoUseTakeUp(player, mapDropInfo, areas);
			}else{
				defaultTakeUp(player, mapDropInfo, areas);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	
	private static void autoUseTakeUp(Player player, MapDropInfo mapDropInfo, Area area) {
		DropGoodsInfo dropGoodsInfo = mapDropInfo.getDropinfo();
		Item item = mapDropInfo.getItem();
		if (MapUtils.countDistance(player.getPosition(), MapUtils.buildPosition((short) dropGoodsInfo.getX(), (short) dropGoodsInfo.getY())) > AUTOUSEDISTANCE) {
//			MessageUtil.notify_player(player, Notifys.NORMAL, "离要拾取的物品太远");
			return;
		}
		// 有归属
		if (dropGoodsInfo.getOwnerId() != 0
				&& dropGoodsInfo.getOwnerId() != player.getId()) {
			long time = System.currentTimeMillis()
					- dropGoodsInfo.getDropTime();
			if(TeamManager.getInstance().isSameTeam(player, dropGoodsInfo.getOwnerId())){
				//是队友
				if (time < 10 * 1000) {
//					 10秒之内只有本人可以拾取
					MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("有主物品,还有")
							+ (10 - time / 1000) + ResManager.getInstance().getString("秒才可拾取"));
					return;
				}			
			}else{
				//不是队友
				if (time < 20 * 1000) {
					MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("有主物品,还有")
							+ (20 - time / 1000) + ResManager.getInstance().getString("秒才可拾取"));
					return;
				}
			}
		}

		
		IAutoUseItem medicines = (IAutoUseItem) item;
		if(medicines.autoTakeUpUse(player)){
			area.getDropGoods().remove(item.getId());
			ResRoundGoodsDisappearMessage dismsg = new ResRoundGoodsDisappearMessage();
			dismsg.getGoodsIds().add(dropGoodsInfo.getDropGoodsId());
			MessageUtil.tell_round_message(mapDropInfo, dismsg);
		}
		return;
	}

	private static void defaultTakeUp(Player player,MapDropInfo mapDropInfo,Area area){
		DropGoodsInfo dropGoodsInfo = mapDropInfo.getDropinfo();
		Item item = mapDropInfo.getItem();
		// 有归属
		if (dropGoodsInfo.getOwnerId() != 0
				&& dropGoodsInfo.getOwnerId() != player.getId()) {
			long time = System.currentTimeMillis()
					- dropGoodsInfo.getDropTime();
			if(TeamManager.getInstance().isSameTeam(player, dropGoodsInfo.getOwnerId())){
				//是队友
				if (time < 10 * 1000) {
					// 10秒之内只有本人可以拾取
					MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("有主物品,还有")
							+ (10 - time / 1000) + ResManager.getInstance().getString("秒才可拾取"));
					return;
				}			
			}else{
				//不是队友
				if (time < 20 * 1000) {
					MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("有主物品,还有")
							+ (20 - time / 1000) + ResManager.getInstance().getString("秒才可拾取"));
					return;
				}
			}
		}
		

		long action = Config.getId();

		if (item.getItemModelId() == -1) {
			if (MapUtils.countDistance(player.getPosition(), MapUtils.buildPosition((short) dropGoodsInfo.getX(), (short) dropGoodsInfo.getY())) > 2000) {
				return;
			}
			int num = player.getMoney() + item.getNum();
			if (num <= 0 || num > Global.BAG_MAX_COPPER) {
				// MessageUtil.notify_player(player, Notifys.ERROR,
				// "背包金币数量已达上限，拾取失败");
				return;
			}
			// 金币拾取
			if (!BackpackManager.getInstance().changeMoney(player, item.getNum(), Reasons.TAKEUP, action)) {
				// 拾取失败
				return;
			}

		} else {
			if (MapUtils.countDistance(player.getPosition(), MapUtils.buildPosition((short) dropGoodsInfo.getX(), (short) dropGoodsInfo.getY())) > TAKEUPDISTANCE) {
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("离要拾取的物品太远"));
				return;
			}

			if (!BackpackManager.getInstance().hasAddSpace(player, item)) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("对不起，您的背包空格不足，请清理后再进行本操作"));
				return;
			}
			if (!BackpackManager.getInstance().addItem(player, item, Reasons.TAKEUP, action)) {
				// 拾取失败
				return;
			}
		}
		ResRoundGoodsDisappearMessage dismsg=new ResRoundGoodsDisappearMessage();
		dismsg.getGoodsIds().add(dropGoodsInfo.getDropGoodsId());
		area.getDropGoods().remove(item.getId());
		ResTakeUpSuccessMessage msg=new ResTakeUpSuccessMessage();
		msg.setGoodsId(item.getId());
		msg.setGoodModelId(dropGoodsInfo.getItemModelId());
		MessageUtil.tell_player_message(player, msg);
		MessageUtil.tell_round_message(mapDropInfo, dismsg);
		Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
//		if(item.getItemModelId()!=-1)
		if(item.getNum()>1){
			MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("捡到{1}({2})"),BackpackManager.getInstance().getName(model.getQ_id()),item.getNum()+"");
//			MessageUtil.notify_player(player,Notifys.NORMAL,"捡到{1}({2})",model.getQ_name(),item.getNum()+"");
		}else{
			MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("捡到{1}"),BackpackManager.getInstance().getName(model.getQ_id()));
//			MessageUtil.notify_player(player,Notifys.NORMAL,"捡到{1}",model.getQ_name());
		}
	}
	
}
