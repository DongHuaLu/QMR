package scripts.zone.meihua;

import com.alibaba.fastjson.JSON;
import com.game.data.bean.Q_clone_activityBean;
import com.game.data.bean.Q_meihuaxuanwuBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.fight.structs.Fighter;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.script.IEnterMapScript;
import com.game.map.structs.Map;
import com.game.monster.manager.MonsterManager;
import com.game.monster.script.IMonsterDieScript;
import com.game.monster.structs.Monster;
import com.game.pet.manager.PetInfoManager;
import com.game.pet.struts.Pet;
import com.game.player.manager.PlayerManager;
import com.game.player.message.ResScriptCommonPlayerToClientMessage;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.server.config.MapConfig;
import com.game.server.impl.WServer;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.team.bean.TeamInfo;
import com.game.team.manager.TeamManager;
import com.game.util.TimerUtil;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.zones.log.ZoneLog;
import com.game.zones.manager.ZonesManager;
import com.game.zones.script.ICreateZoneScript;
import com.game.zones.script.IZoneEventTimerScript;
import com.game.zones.structs.ColorValue;
import com.game.zones.structs.PlumZoneInfo;
import com.game.zones.structs.ZoneContext;
import com.game.zones.timer.ZoneEventTimer;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * 梅花玄武阵
 *
 * @author 杨鸿岚
 */
public class MeiHuaXuanWu implements ICreateZoneScript, IEnterMapScript, IMonsterDieScript, IZoneEventTimerScript {

	private Logger log = Logger.getLogger(MeiHuaXuanWu.class);
	public static int zoneId = 3004;			//zoneid
	public static int scriptId = 4011;			//梅花玄武阵副本scriptid
	public static int messageType = 42121;			//消息类型
	public static int mapModelId = 42121;			//副本地图模板id
	public static String ScriptKey = "梅花玄武阵副本";	//副本保存KEY
	////////////////////////////////////////////////
	private static final int RED = 1;		//红色
	private static final int BLUE = 2;		//蓝色
	private static final int GREEN = 3;		//绿色幸运
	private static final int YELLOW = 4;		//黄色爆破
	private static final int REDMONSTER = 15042;	//红色
	private static final int BLUEMONSTER = 15043;	//蓝色
	private static final int GREENMONSTER = 15044;	//绿色幸运
	private static final int YELLOWMONSTER = 15045;	//黄色爆破
	private static final int BOSS = 15001;		//BOSS
	private int[] midposxy = {70, 70};		//BOSS刷新坐标
	private static int[] serialNumbers = {12, 13, //梅花桩编号
		21, 22, 23, 24,
		31, 32, 33, 34,
		41, 42, 43, 44,
		51, 52, 53, 54,
		61, 62, 63, 64,
		72, 73};
	private static int[][] roadNumbers = {{12, 13},
		{21, 22}, {23, 24},
		{32, 33},
		{41, 42}, {43, 44},
		{52, 53},
		{61, 62}, {63, 64},
		{72, 73},
		{21, 31}, {31, 41}, {41, 51}, {51, 61},
		{12, 22}, {22, 32}, {32, 42}, {42, 52}, {52, 62}, {62, 72},
		{13, 23}, {23, 33}, {33, 43}, {43, 53}, {53, 63}, {63, 73},
		{24, 34}, {34, 44}, {44, 54}, {54, 64},};
	private static Integer[][] posxy = {{60, 28}, {79, 28},
		{31, 43}, {49, 42}, {90, 40}, {110, 39},
		{18, 56}, {57, 53}, {83, 52}, {124, 58},
		{28, 70}, {47, 70}, {94, 70}, {115, 69},
		{19, 87}, {60, 88}, {86, 87}, {127, 85},
		{25, 101}, {50, 102}, {95, 101}, {119, 99},
		{63, 115}, {87, 115}};
	private static HashSet<Integer> points = new HashSet<Integer>();
	private static HashMap<Integer, List<Integer>> roads = new HashMap<Integer, List<Integer>>();

	public MeiHuaXuanWu() {
		for (int i = 0; i < serialNumbers.length; i++) {
			points.add(serialNumbers[i]);
		}
		for (int i = 0; i < roadNumbers.length; i++) {
			List<Integer> posr = null;
			if (roads.containsKey(roadNumbers[i][0])) {
				posr = roads.get(roadNumbers[i][0]);
			} else {
				posr = new ArrayList<Integer>();
				roads.put(roadNumbers[i][0], posr);
			}
			posr.add(roadNumbers[i][1]);

			posr = null;
			if (roads.containsKey(roadNumbers[i][1])) {
				posr = roads.get(roadNumbers[i][1]);
			} else {
				posr = new ArrayList<Integer>();
				roads.put(roadNumbers[i][1], posr);
			}
			posr.add(roadNumbers[i][0]);
		}
	}

	@Override
	public int getId() {
		return scriptId;
	}

	@Override
	public ZoneContext onCreate(Player player, int zoneId) {
		//副本ID检查
		if (zoneId != MeiHuaXuanWu.zoneId) {
			return null;
		}
		if (DataManager.getInstance().q_meihuaxuanwuContainer.getList().isEmpty()) {
			return null;
		}
		Q_meihuaxuanwuBean bean = DataManager.getInstance().q_meihuaxuanwuContainer.getList().get(RandomUtils.random(DataManager.getInstance().q_meihuaxuanwuContainer.getList().size()));
		if (bean == null) {
			return null;
		}
		Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(zoneId);
		if (zonedata == null) {
			return null;
		}
		//判断组队信息
		if (player.getTeamid() != 0) {
			TeamInfo teamInfo = TeamManager.getInstance().getTeam(player.getTeamid());
			if (teamInfo.getMemberinfo().size() > zonedata.getQ_max_num()) {
				if (zonedata.getQ_max_num() != 1) {
					MessageUtil.notify_player(player, Notifys.ERROR, String.format("梅花玄武阵副本只能%d人进入。", zonedata.getQ_max_num()));
					return null;
				} else if (zonedata.getQ_max_num() == 1) {
					MessageUtil.notify_player(player, Notifys.ERROR, "梅花玄武阵副本只能单人进入。");
					return null;
				}
			}
		}
		Map map = MapManager.getInstance().getMap(player);
		if (map == null) {
			return null;
		}
		HashMap<String, Object> others = new HashMap<String, Object>();
		others.put("time", (int) (System.currentTimeMillis() / 1000));	//开始时间
		others.put("zonetype", zonedata.getQ_zone_type());			//副本类型 3
		others.put("teamtype", 0);		//队伍类型，0，单人，1组队
		List<Integer> mapidlist = JSON.parseArray(zonedata.getQ_mapid(), Integer.class);
		ZoneContext zone = ManagerPool.zonesManager.setZone(ScriptKey, others, mapidlist, zonedata.getQ_id());	//创建副本，返回副本消息
		if (zone != null) {
			zone.getOthers().put("bean", bean.getQ_id());
			Iterator<MapConfig> iterator = zone.getConfigs().iterator();
			while (iterator.hasNext()) {
				MapConfig mapConfig = iterator.next();
				if (mapConfig != null) {
					Map zoneMap = ManagerPool.mapManager.getMap(mapConfig.getServerId(), mapConfig.getLineId(), mapConfig.getMapId());
					if (zoneMap != null) {
						zoneMap.setRound(Math.max(zoneMap.getWidth(), zoneMap.getHeight()) * 2 + 1);
					}
				}
			}
		}
		return zone;
	}

	@Override
	public void onEnterMap(Player player, Map map) {
		if (map.getZoneModelId() != MeiHuaXuanWu.zoneId) {
			return;
		}
		ZoneContext zoneContext = ZonesManager.getInstance().getContexts().get(map.getZoneId());
		if (zoneContext == null) {
			return;
		}
		PlumZoneInfo zoneInfo = getZoneInfo(zoneContext);
		if (zoneInfo == null) {
			return;
		}
		Integer beanid = (Integer) zoneContext.getOthers().get("bean");
		if (beanid == null || beanid == 0) {
			return;
		}
		Q_meihuaxuanwuBean bean = DataManager.getInstance().q_meihuaxuanwuContainer.getMap().get(beanid);
		if (bean == null) {
			return;
		}
		if (!zoneContext.getOthers().containsKey("refreshMonster")) {
			zoneInfo.setZhenxingid(beanid);
			zoneContext.getOthers().put("beginzonetime", System.currentTimeMillis());
			HashMap<Integer, ColorValue> valueMap = new HashMap<Integer, ColorValue>();
			List<Integer> alllist = new ArrayList<Integer>();
			for (int i = 0; i < serialNumbers.length; i++) {
				alllist.add(serialNumbers[i]);
			}
			List<Integer[]> posList = new ArrayList<Integer[]>();
			for (int i = 0; i < posxy.length; i++) {
				Integer[] is = posxy[i];
				posList.add(is);
			}
			List<Integer> parseArray = JSON.parseArray(bean.getQ_zhenxing(), Integer.class);
			ListIterator<Integer> listIterator = parseArray.listIterator();
			while (listIterator.hasNext()) {
				Integer integer = listIterator.next();
				int type = listIterator.nextIndex();
				for (int i = 0; i < integer; i++) {
					int random = RandomUtils.random(alllist.size());
					Integer removeidx = alllist.remove(random);
					Integer[] removepos = posList.remove(random);
					ColorValue colorValue = new ColorValue();
					colorValue.setIdx(removeidx);
					colorValue.setColortype(type);
					colorValue.setPos(removepos);
					valueMap.put(removeidx, colorValue);
				}
			}
			if (!alllist.isEmpty()) {
				ListIterator<Integer> allIterator = alllist.listIterator();
				while (allIterator.hasNext()) {
					Integer idx = allIterator.next();
					Integer[] pos = posList.get(allIterator.nextIndex() - 1);
					ColorValue colorValue = new ColorValue();
					colorValue.setIdx(idx);
					colorValue.setColortype(RED);
					colorValue.setPos(pos);
					valueMap.put(idx, colorValue);
				}
				alllist.clear();
				posList.clear();
			}
			zoneContext.getOthers().put("valuemap", valueMap);
			Iterator<ColorValue> iterator = valueMap.values().iterator();
			while (iterator.hasNext()) {
				ColorValue next = iterator.next();
				refreshMonster(player, map, next);
			}
//			ScriptsUtils.delayCall(getId(), "startZone", 3000, player, map);
			zoneContext.getOthers().put("refreshMonster", "1");
			List<Object> limitparamList = new ArrayList<Object>();
			limitparamList.add(1);
			limitparamList.add(WServer.getInstance().getServerId());
			limitparamList.add(1);
			limitparamList.add(map.getId());
			limitparamList.add(player.getId());
			int limitTime = bean.getQ_limitneedtime() * 1000;
			ZoneEventTimer limitTimer = new ZoneEventTimer(1, scriptId, map.getZoneId(), map.getZoneModelId(), limitparamList, limitTime);
			TimerUtil.addTimerEvent(limitTimer);
//			击蓝桩会变红桩，击红桩会变蓝桩
//			破桩后，同色相连即可造成连击，并获得大量经验
//			连击数越高，获得的经验越多
//			破桩达到数量后，再战胜BOSS霸下即为破阵成功
//			使用右侧追踪面板操作将更加简单
			MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("击蓝桩会变红桩，击红桩会变蓝桩"));
			MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("破桩后，同色相连即可造成连击，并获得大量经验"));
			MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("连击数越高，获得的经验越多"));
			MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("破桩达到数量后，再战胜BOSS霸下即为破阵成功"));
			MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("使用右侧追踪面板操作将更加简单"));

			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("击蓝桩会变红桩，击红桩会变蓝桩"));
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("破桩后，同色相连即可造成连击，并获得大量经验"));
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("连击数越高，获得的经验越多"));
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("破桩达到数量后，再战胜BOSS霸下即为破阵成功"));
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("使用右侧追踪面板操作将更加简单"));
		}
		zoneInfo.calTime(zoneContext);
		sendZoneInfo(player, zoneInfo, null);
	}

	@Override
	public void onMonsterDie(Monster monster, Fighter killer) {
		if (killer instanceof Player || killer instanceof Pet) {
			Map map = MapManager.getInstance().getMap(monster);
			if (map == null) {
				return;
			}
			if (map.getZoneModelId() != MeiHuaXuanWu.zoneId) {
				return;
			}
			ZoneContext zoneContext = ZonesManager.getInstance().getContexts().get(map.getZoneId());
			if (zoneContext == null) {
				return;
			}
			Player player = null;
			if (killer instanceof Pet) {
				Pet pet = (Pet) killer;
				player = PetInfoManager.getInstance().getPetHost(pet);
			} else {
				player = (Player) killer;
			}
			if (player == null) {
				return;
			}
			if (monster.getModelId() == REDMONSTER || monster.getModelId() == BLUEMONSTER || monster.getModelId() == GREENMONSTER || monster.getModelId() == YELLOWMONSTER) {
				log.error(String.format("梅花玄武副本梅花桩击杀,怪物模板id[%d],怪物唯一id[%d],玩家[%d]", monster.getModelId(), monster.getId(), player.getId()));

				HashMap<Integer, ColorValue> valueMap = (HashMap<Integer, ColorValue>) zoneContext.getOthers().get("valuemap");
				if (valueMap == null) {
					return;
				}
				ColorValue colorValue = (ColorValue) monster.getParameters().get("colorvalue");
				if (colorValue == null) {
					return;
				}
				List<Integer> comboList = getComboList(valueMap, colorValue.getIdx(), colorValue.getColortype(), colorValue.getIdx(), null);
				PlumZoneInfo zoneInfo = getZoneInfo(zoneContext);
				if (zoneInfo == null) {
					return;
				}
				boolean boDouble = false;
				Iterator<Integer> comboiterator = comboList.iterator();
				while (comboiterator.hasNext()) {
					Integer idx = comboiterator.next();
					ColorValue combocolorValue = valueMap.get(idx);
					if (combocolorValue != null && combocolorValue.getColortype() == GREEN) {
						boDouble = true;
						break;
					}
				}
				zoneInfo.setHitnum(zoneInfo.getHitnum() + comboList.size());
				zoneInfo.calTime(zoneContext);
				zoneInfo.setCurcombo(comboList.size());
				if (zoneInfo.getCurcombo() > zoneInfo.getMaxcombo()) {
					zoneInfo.setMaxcombo(zoneInfo.getCurcombo());
				}
				int curexp = (comboList.size() - 1 <= 0 ? 0 : comboList.size() - 1) * player.getLevel() * player.getLevel();
				if (curexp != 0) {
					curexp = boDouble ? curexp * 2 : curexp;
					PlayerManager.getInstance().addExp(player, curexp, AttributeChangeReason.MEIHUAXUANWUCOMBO);
				}
				zoneInfo.setExp(zoneInfo.getExp() + curexp);
				Integer beanid = (Integer) zoneContext.getOthers().get("bean");
				if (beanid == null || beanid == 0) {
					return;
				}
				Q_meihuaxuanwuBean bean = DataManager.getInstance().q_meihuaxuanwuContainer.getMap().get(beanid);
				if (bean == null) {
					return;
				}
				int curhp = (comboList.size() - 1 <= 0 ? 0 : comboList.size() - 1) * bean.getQ_decbosshp();
				if (curhp != 0) {
					curhp = boDouble ? curhp * 2 : curhp;
					zoneInfo.setBosshp(zoneInfo.getBosshp() + curhp);
				}
				sendZoneInfo(player, zoneInfo, comboList);
				if (zoneInfo.getHitnum() >= bean.getQ_limitneedhit()) { //达到需要击破的梅花桩数量，移除所有梅花桩，刷新BOSS
					Iterator<ColorValue> iterator = valueMap.values().iterator();
					while (iterator.hasNext()) {
						ColorValue delcolorValue = iterator.next();
						if (delcolorValue != null) {
							Monster oldMonster = MonsterManager.getInstance().getMonster(map, delcolorValue.getMonid());
							if (oldMonster != null) {
								MonsterManager.getInstance().removeMonster(oldMonster);
							}
							delcolorValue.setMonid(0);
						}
					}
					if (zoneContext.getOthers().containsKey("refreshed_boss")) {
						return;
					}
					refreshBoss(player, map, zoneInfo);

					zoneContext.getOthers().put("refreshed_boss", "refreshed_boss");
				} else {
					if (colorValue.getColortype() == YELLOW) {//爆破，全部刷新梅花桩
						ListIterator<Integer> listIterator = comboList.listIterator();
						while (listIterator.hasNext()) {
							Integer idx = listIterator.next();
							ColorValue oldcolorValue = valueMap.get(idx);
							if (oldcolorValue != null) {
								oldcolorValue.setColortype(RandomUtils.random(RED, BLUE));
								Monster oldMonster = MonsterManager.getInstance().getMonster(map, oldcolorValue.getMonid());
								if (oldMonster != null) {
									MonsterManager.getInstance().removeMonster(oldMonster);
									refreshMonster(player, map, oldcolorValue);
								}
							}
						}
					} else {
						ListIterator<Integer> listIterator = comboList.listIterator();
						while (listIterator.hasNext()) {
							Integer idx = listIterator.next();
							ColorValue oldcolorValue = valueMap.get(idx);
							if (oldcolorValue != null) {
								if (oldcolorValue.getIdx() == colorValue.getIdx()) {
									oldcolorValue.setColortype(changeColorType(oldcolorValue.getColortype()));
								} else {
									oldcolorValue.setColortype(RandomUtils.random(RED, BLUE));
								}
								Monster oldMonster = MonsterManager.getInstance().getMonster(map, oldcolorValue.getMonid());
								if (oldMonster != null) {
									MonsterManager.getInstance().removeMonster(oldMonster);
									refreshMonster(player, map, oldcolorValue);
								}
							}
						}
						if (comboList.size() < 8) {//8连以下不处理
						} else if (comboList.size() >= 8 && comboList.size() < 14) {//8连以上到14连，刷新幸运
							int idx = serialNumbers[RandomUtils.random(serialNumbers.length)];
							ColorValue luckColorValue = valueMap.get(idx);
							if (luckColorValue != null) {
								luckColorValue.setColortype(GREEN);
								Monster oldMonster = MonsterManager.getInstance().getMonster(map, luckColorValue.getMonid());
								if (oldMonster != null) {
									MonsterManager.getInstance().removeMonster(oldMonster);
									refreshMonster(player, map, luckColorValue);
								}
							}
						} else {//14连以上，刷新暴击
							int idx = serialNumbers[RandomUtils.random(serialNumbers.length)];
							ColorValue critColorValue = valueMap.get(idx);
							if (critColorValue != null) {
								critColorValue.setColortype(YELLOW);
								Monster oldMonster = MonsterManager.getInstance().getMonster(map, critColorValue.getMonid());
								if (oldMonster != null) {
									MonsterManager.getInstance().removeMonster(oldMonster);
									refreshMonster(player, map, critColorValue);
								}
							}
						}
					}
				}
			} else {
				log.error(String.format("梅花玄武副本BOSS击杀,怪物模板id[%d],怪物唯一id[%d],玩家[%d]", monster.getModelId(), monster.getId(), player.getId()));
				if (monster.getParameters().containsKey("playerid")) {
					long saveplayerid = (Long) (monster.getParameters().get("playerid"));
					if (saveplayerid != player.getId()) {
						return;
					}
				}
				try {
					PlumZoneInfo zoneInfo = getZoneInfo(zoneContext);
					zoneInfo.setBosshp(10000);
					sendZoneInfo(player, zoneInfo, null);
				} catch (Exception e) {
					log.error(e, e);
				}
				String sendString = "";
				if (map.getMapModelid() == player.getMapModelId() && !zoneContext.getOthers().containsKey("rew" + player.getId())) {
					zoneContext.getOthers().put("rew" + player.getId(), MeiHuaXuanWu.zoneId);
					player.getZoneinfo().put(ZonesManager.ManualDeathnum + "_" + MeiHuaXuanWu.zoneId, 0);
					player.getZoneinfo().put(ZonesManager.killmonsternum + "_" + MeiHuaXuanWu.zoneId, 0);
					int time = (Integer) zoneContext.getOthers().get("time");
					int newtime = (int) (System.currentTimeMillis() / 1000 - time);
					player.getZoneinfo().put(ZonesManager.Manualendtime + "_" + MeiHuaXuanWu.zoneId, newtime);

					ZoneLog zlog = new ZoneLog();
					zlog.setPlayerid(player.getId());
					zlog.setType(3);
					zlog.setZonemodelid(MeiHuaXuanWu.zoneId);
					zlog.setSid(player.getCreateServerId());
					LogService.getInstance().execute(zlog);

					ManagerPool.zonesFlopManager.addZoneReward(player, MeiHuaXuanWu.zoneId, false);
					ManagerPool.zonesFlopManager.stZonePassShow(player, 3, MeiHuaXuanWu.zoneId);

					sendString = sendString + "/[" + player.getName() + "|" + player.getId() + "]";
				}
				log.error(String.format("梅花玄武副本BOSS击杀,并通关发放奖励成功,怪物模板id[%d],怪物唯一id[%d],玩家[%s],发送奖励玩家[%s]", monster.getModelId(), monster.getId(), String.valueOf(player.getId()), sendString));
			}
		}
	}

	@Override
	public void action(long zoneId, int zoneModelId, List<Object> parameters) {
		if (zoneModelId != MeiHuaXuanWu.zoneId) {
			return;
		}
		if (parameters.size() > 0) {
			int type = (Integer) parameters.get(0);
			switch (type) {
				case 1: {
					if (parameters.size() < 4) {
						return;
					}
					int serverId = (Integer) parameters.get(1);
					int lineId = (Integer) parameters.get(2);
					long mapId = (Long) parameters.get(3);
					long playerid = (Long) parameters.get(4);
					Player player = PlayerManager.getInstance().getPlayer(playerid);
					if (player == null) {
						return;
					}
					Map map = ManagerPool.mapManager.getMap(serverId, lineId, (int) mapId);
					if (map != null && map.getMapModelid() == mapModelId) {
						ZoneContext zoneContext = ZonesManager.getInstance().getContexts().get(map.getZoneId());
						if (zoneContext != null) {
							HashMap<Integer, ColorValue> valueMap = (HashMap<Integer, ColorValue>) zoneContext.getOthers().get("valuemap");
							if (valueMap == null) {
								return;
							}
							PlumZoneInfo zoneInfo = getZoneInfo(zoneContext);
							if (zoneInfo == null) {
								return;
							}
							Iterator<ColorValue> iterator = valueMap.values().iterator();
							while (iterator.hasNext()) {
								ColorValue delcolorValue = iterator.next();
								if (delcolorValue != null) {
									Monster oldMonster = MonsterManager.getInstance().getMonster(map, delcolorValue.getMonid());
									if (oldMonster != null) {
										MonsterManager.getInstance().removeMonster(oldMonster);
									}
									delcolorValue.setMonid(0);
								}
							}
							zoneInfo.calTime(zoneContext);
							if (zoneContext.getOthers().containsKey("refreshed_boss")) {
								sendZoneInfo(player, zoneInfo, null);
								return;
							}
							refreshBoss(player, map, zoneInfo);
							zoneContext.getOthers().put("refreshed_boss", "refreshed_boss");
							sendZoneInfo(player, zoneInfo, null);
						}
					}
				}
				break;
			}
		}
	}

	//======================================================================
//	private static class ColorValue {
//
//		private int idx;		//梅花桩编号
//		private long monid;		//梅花桩对应怪物唯一ID
//		private int colortype;		//颜色类型
//		private Integer[] pos;		//梅花桩坐标
//
//		public int getIdx() {
//			return idx;
//		}
//
//		public void setIdx(int idx) {
//			this.idx = idx;
//		}
//
//		public long getMonid() {
//			return monid;
//		}
//
//		public void setMonid(long monid) {
//			this.monid = monid;
//		}
//
//		public int getColortype() {
//			return colortype;
//		}
//
//		public void setColortype(int colortype) {
//			this.colortype = colortype;
//		}
//
//		public Integer[] getPos() {
//			return pos;
//		}

//		public void setPos(Integer[] pos) {
//			this.pos = pos;
//		}
//	}

//	private static class ZoneInfo {
//
//		private int zhenxingid;		//梅花桩阵型id
//		private int hitnum;		//总击破数
//		private int exp;		//累积经验
//		private int maxcombo;		//最大连击数
//		private int curcombo;		//当前连击数
//		private int bosshp;		//扣掉的bosshp
//		private int remaintime;		//梅花桩剩余时间
//		private int zonetime;		//副本剩余时间
//
//		public int getZhenxingid() {
//			return zhenxingid;
//		}
//
//		public void setZhenxingid(int zhenxingid) {
//			this.zhenxingid = zhenxingid;
//		}
//
//		public int getBosshp() {
//			return bosshp;
//		}
//
//		public void setBosshp(int bosshp) {
//			this.bosshp = bosshp;
//		}
//
//		public int getCurcombo() {
//			return curcombo;
//		}
//
//		public void setCurcombo(int curcombo) {
//			this.curcombo = curcombo;
//		}
//
//		public int getExp() {
//			return exp;
//		}
//
//		public void setExp(int exp) {
//			this.exp = exp;
//		}
//
//		public int getHitnum() {
//			return hitnum;
//		}
//
//		public void setHitnum(int hitnum) {
//			this.hitnum = hitnum;
//		}
//
//		public int getMaxcombo() {
//			return maxcombo;
//		}
//
//		public void setMaxcombo(int maxcombo) {
//			this.maxcombo = maxcombo;
//		}
//
//		public int getRemaintime() {
//			return remaintime;
//		}
//
//		public void setRemaintime(int remaintime) {
//			this.remaintime = remaintime;
//		}
//
//		public int getZonetime() {
//			return zonetime;
//		}
//
//		public void setZonetime(int zonetime) {
//			this.zonetime = zonetime;
//		}
//
//		/*
//		 * 计算剩余时间：=梅花桩时间=副本时间
//		 */
//		public void calTime(ZoneContext zoneContext) {
//			Integer beanid = (Integer) zoneContext.getOthers().get("bean");
//			if (beanid == null || beanid == 0) {
//				return;
//			}
//			Q_meihuaxuanwuBean bean = DataManager.getInstance().q_meihuaxuanwuContainer.getMap().get(beanid);
//			if (bean == null) {
//				return;
//			}
//			Long beginzonetime = (Long) zoneContext.getOthers().get("beginzonetime");
//			if (beginzonetime == null || beginzonetime == 0) {
//				return;
//			}
//			if (beginzonetime + bean.getQ_limitneedtime() * 1000 >= System.currentTimeMillis()) {
//				long time = beginzonetime + bean.getQ_limitneedtime() * 1000 - System.currentTimeMillis();
//				setRemaintime((int) (time / 1000));
//			} else {
//				setRemaintime(0);
//			}
//			if (getHitnum() >= bean.getQ_limitneedhit()) {
//				setRemaintime(0);
//			}
//			Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(MeiHuaXuanWu.zoneId);
//			if (zonedata == null) {
//				return;
//			}
//			if (beginzonetime + zonedata.getQ_exist_time() >= System.currentTimeMillis()) {
//				long time = beginzonetime + zonedata.getQ_exist_time() - System.currentTimeMillis();
//				setZonetime((int) (time / 1000));
//			} else {
//				setZonetime(0);
//			}
//		}
//	}

	/*
	 * 得到副本信息
	 */
	private PlumZoneInfo getZoneInfo(ZoneContext zoneContext) {
		if (zoneContext == null) {
			return null;
		}
		PlumZoneInfo zoneInfo = (PlumZoneInfo) zoneContext.getOthers().get("zoneinfo");
		if (zoneInfo == null) {
			zoneInfo = new PlumZoneInfo();
			zoneContext.getOthers().put("zoneinfo", zoneInfo);
		}
		return zoneInfo;
	}

	/*
	 * 设置副本信息
	 */
	private void setZoneInfo(ZoneContext zoneContext, PlumZoneInfo zoneInfo) {
		if (zoneContext == null) {
			return;
		}
		if (zoneInfo == null) {
			return;
		}
		zoneContext.getOthers().put("zoneinfo", zoneInfo);
	}

	/*
	 * 刷新怪物
	 */
	public void refreshMonster(Player player, Map map, ColorValue colorValue) {
		ZoneContext zoneContext = ZonesManager.getInstance().getContexts().get(map.getZoneId());
		if (zoneContext == null) {
			return;
		}//刷梅花桩
		log.error(String.format("梅花玄武副本刷梅花桩"));
		Integer beanid = (Integer) zoneContext.getOthers().get("bean");
		if (beanid == null || beanid == 0) {
			return;
		}
		Q_meihuaxuanwuBean bean = DataManager.getInstance().q_meihuaxuanwuContainer.getMap().get(beanid);
		if (bean == null) {
			return;
		}
		Long begintime = (Long) zoneContext.getOthers().get("beginzonetime");
		if (begintime == null || begintime == 0) {
			return;
		}
		int trueMonsterId = getTrueMonsterId(colorValue.getColortype());
		Grid grid = MapUtils.getGrid(colorValue.getPos()[0], colorValue.getPos()[1], map.getMapModelid());
		if (grid != null) {
			Monster monster = MonsterManager.getInstance().createMonster(trueMonsterId, map.getServerId(), map.getLineId(), (int) map.getId(), grid.getCenter());
			if (monster != null) {
				colorValue.setMonid(monster.getId());
				monster.getParameters().put("colorvalue", colorValue);
				monster.setLevel(player.getLevel());
				monster.setDisappearTime(begintime + bean.getQ_limitneedtime() * 1000);
				ManagerPool.mapManager.enterMap(monster);
				log.error(String.format("梅花玄武副本梅花桩刷新,怪物模板id[%d],怪物唯一id[%d],颜色[%d],位置[%s],玩家[%d]", monster.getModelId(), monster.getId(), colorValue.getColortype(), JSON.toJSONString(colorValue.getPos()), player.getId()));
			}
		}
	}

	/*
	 * 刷新BOSS
	 */
	public void refreshBoss(Player player, Map map, PlumZoneInfo zoneInfo) {
		ZoneContext zoneContext = ZonesManager.getInstance().getContexts().get(map.getZoneId());
		if (zoneContext == null) {
			return;
		}//刷BOSS
		log.error(String.format("梅花玄武副本BOSS刷新开始"));
		Position position = MapUtils.getRoundPosByGridRadius(midposxy[0], midposxy[1], 2, 4, false, false, map.getMapModelid());
		if (position != null) {
			int addlevel = (player.getLevel() > 100) ? (100 - 60) : (player.getLevel() - 60);
			addlevel = addlevel < 0 ? 0 : addlevel;
			Monster monster = MonsterManager.getInstance().createMonster(BOSS + addlevel, map.getServerId(), map.getLineId(), (int) map.getId(), position);
			if (monster != null) {
				monster.setHp((int) (monster.getMaxHp() * ((double) (10000 - zoneInfo.getBosshp()) / 10000)));
				monster.getParameters().put("playerid", player.getId());
				ManagerPool.mapManager.enterMap(monster);
				MessageUtil.notify_map(map, Notifys.CUTOUT, String.format(ResManager.getInstance().getString("恭喜您已成功击破所有梅花桩，击败%s即可通关。"), MonsterManager.getInstance().getName(BOSS + addlevel)));
				MessageUtil.notify_map(map, Notifys.CHAT_SYSTEM, String.format(ResManager.getInstance().getString("恭喜您已成功击破所有梅花桩，击败%s即可通关。"), MonsterManager.getInstance().getName(BOSS + addlevel)));
				log.error(String.format("梅花玄武副本BOSS刷新,怪物模板id[%d],怪物唯一id[%d],位置[%s],真实位置[%s],玩家[%d]", monster.getModelId(), monster.getId(), JSON.toJSONString(midposxy), JSON.toJSONString(monster.getPosition()), player.getId()));
			}
		}
	}

	/*
	 * 发送副本信息
	 */
	public void sendZoneInfo(Player player, PlumZoneInfo info, List<Integer> combolist) {
		if (player == null) {
			return;
		}
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("zonemodelid", MeiHuaXuanWu.zoneId);
		paramMap.put("zhenxing", info.getZhenxingid());
		paramMap.put("hitnum", info.getHitnum());
		paramMap.put("exp", info.getExp());
		paramMap.put("maxcombo", info.getMaxcombo());
		paramMap.put("curcombo", info.getCurcombo());
		paramMap.put("bosshp", info.getBosshp());
		paramMap.put("remaintime", info.getRemaintime());
		paramMap.put("zonetime", info.getZonetime());
		paramMap.put("combolist", combolist == null ? "" : combolist);

		ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
		sendMessage.setScriptid(getId());
		sendMessage.setType(1);
		sendMessage.setMessageData(JSON.toJSONString(paramMap));
		MessageUtil.tell_player_message(player, sendMessage);
	}

	/**
	 * 获得真正的怪物id
	 *
	 * @return int
	 */
	public int getTrueMonsterId(int colorType) {
		switch (colorType) {
			case RED:
				return REDMONSTER;
			case BLUE:
				return BLUEMONSTER;
			case GREEN:
				return GREENMONSTER;
			case YELLOW:
				return YELLOWMONSTER;
			default:
				return 0;
		}
	}

	/**
	 * 获得真正的颜色类型
	 *
	 * @return int
	 */
	public int getTrueColorType(int monsterId) {
		switch (monsterId) {
			case REDMONSTER:
				return RED;
			case BLUEMONSTER:
				return BLUE;
			case GREENMONSTER:
				return GREEN;
			case YELLOWMONSTER:
				return YELLOW;
			default:
				return 0;
		}
	}

	/**
	 * 改变颜色
	 *
	 * @return
	 */
	public int changeColorType(int colorType) {
		switch (colorType) {
			case RED:
				return BLUE;
			case BLUE:
				return RED;
			case GREEN:
				return RandomUtils.random(RED, BLUE);
			case YELLOW:
				return YELLOW;
			default:
				return 0;
		}
	}

	/**
	 * 连击算法，返回可以被连击的列表
	 *
	 * @return
	 */
	public List<Integer> getComboList(HashMap<Integer, ColorValue> valueMap, int mainidx, int maincolor, int compidx, List<Integer> resultlist) {
		if (resultlist == null) {
			resultlist = new ArrayList<Integer>();
			resultlist.add(mainidx);
		} else {
			if (valueMap.containsKey(mainidx)) {
				ColorValue getcolor = valueMap.get(mainidx);
				if (getcolor.getColortype() == maincolor || getcolor.getColortype() == GREEN || maincolor == YELLOW) {//GREEN=3是幸运，通用 YELLOW=4是爆破，全部刷新
					if (!resultlist.contains(mainidx)) {
						resultlist.add(mainidx);
					} else {
						return resultlist;
					}
				} else {
					return resultlist;
				}
			} else {
				return resultlist;
			}
		}
		if (maincolor == GREEN) {
			return resultlist;
		}
		if (!points.contains(mainidx)) {
			return resultlist;
		}
		List<Integer> round = getRound(mainidx);
		if (round.isEmpty()) {
			return resultlist;
		}
		ListIterator<Integer> listIterator = round.listIterator();
		while (listIterator.hasNext()) {
			Integer nextidx = listIterator.next();
			if (nextidx != compidx) {
				resultlist = getComboList(valueMap, nextidx, maincolor, compidx, resultlist);
			}
		}
//		int newrow = 0;
//		int newcol = 0;
//		int row = mainidx / 10;
//		int col = mainidx % 10;
//		if (row <= 0 || row >= 8) {
//			return resultlist;
//		}
//		if (col <= 0 || col >= 5) {
//			return resultlist;
//		}
//		if (row == 1 || row == 7) {
//			if (col <= 1 || col >= 4) {
//				return resultlist;
//			}
//		}
//		if (mainidx / 10 == compidx / 10) {
//			newrow = row - 1;
//			resultlist = getComboList(valueMap, newrow * 10 + col, maincolor, compidx, resultlist);
//			newrow = row + 1;
//			resultlist = getComboList(valueMap, newrow * 10 + col, maincolor, compidx, resultlist);
//
//		} else if (mainidx / 10 < compidx / 10) {
//			newrow = row - 1;
//			resultlist = getComboList(valueMap, newrow * 10 + col, maincolor, mainidx, resultlist);
//		} else if (mainidx / 10 > compidx / 10) {
//			newrow = row + 1;
//			resultlist = getComboList(valueMap, newrow * 10 + col, maincolor, mainidx, resultlist);
//		}
//		if (mainidx % 10 == compidx % 10) {
//			if (row % 2 != 0) {
//				switch (col) {
//					case 2:
//						newcol = col + 1;
//						resultlist = getComboList(valueMap, row * 10 + newcol, maincolor, mainidx, resultlist);
//						break;
//					case 3:
//						newcol = col - 1;
//						resultlist = getComboList(valueMap, row * 10 + newcol, maincolor, mainidx, resultlist);
//						break;
//				}
//			} else {
//				if (col % 2 != 0) {
//					newcol = col + 1;
//					resultlist = getComboList(valueMap, row * 10 + newcol, maincolor, mainidx, resultlist);
//				} else {
//					newcol = col - 1;
//					resultlist = getComboList(valueMap, row * 10 + newcol, maincolor, mainidx, resultlist);
//				}
//			}
//		} else if (mainidx % 10 < compidx % 10) {
//			if (row % 2 != 0) {
//				switch (col) {
//					case 3:
//						newcol = col - 1;
//						resultlist = getComboList(valueMap, row * 10 + newcol, maincolor, mainidx, resultlist);
//						break;
//				}
//			} else {
//				if (col % 2 == 0) {
//					newcol = col - 1;
//					resultlist = getComboList(valueMap, row * 10 + newcol, maincolor, mainidx, resultlist);
//				}
//			}
//		} else if (mainidx % 10 > compidx % 10) {
//			if (row % 2 != 0) {
//				switch (col) {
//					case 2:
//						newcol = col + 1;
//						resultlist = getComboList(valueMap, row * 10 + newcol, maincolor, mainidx, resultlist);
//						break;
//				}
//			} else {
//				if (col % 2 != 0) {
//					newcol = col + 1;
//					resultlist = getComboList(valueMap, row * 10 + newcol, maincolor, mainidx, resultlist);
//				}
//			}
//		}
		return resultlist;
	}

	/**
	 * 获取周围联通点
	 *
	 * @param mainidx
	 * @return
	 */
	public List<Integer> getRound(int mainidx) {
		if (roads.containsKey(mainidx)) {
			return roads.get(mainidx);
		}
		return new ArrayList<Integer>();
	}

	/**
	 * 获得真实的梅花桩编号
	 *
	 * @return
	 */
	public int getTrueSerialNumber(int serialNum) {
		int row = serialNum / 10;
		int col = serialNum % 10;
		int rownum = 0;
		int colnum = 0;
		if (row == 1) {
			rownum = 0;
			col--;
		} else if (row > 1 && row < 7) {
			rownum = 1;
		} else {
			rownum = 2;
			col--;
		}
		return (row - 1) * 4 + rownum * 2 + col;
	}
	//======================================================================
//	public static void main(String[] args) {
//		for (int i = 0; i < serialNumbers.length; i++) {
//			points.add(serialNumbers[i]);
//		}
//		for (int i = 0; i < roadNumbers.length; i++) {
//			List<Integer> posr = null;
//			if(roads.containsKey(roadNumbers[i][0])){
//				posr = roads.get(roadNumbers[i][0]);
//			}else{
//				posr = new ArrayList<Integer>();
//				roads.put(roadNumbers[i][0], posr);
//			}
//			posr.add(roadNumbers[i][1]);
//			
//			posr = null;
//			if(roads.containsKey(roadNumbers[i][1])){
//				posr = roads.get(roadNumbers[i][1]);
//			}else{
//				posr = new ArrayList<Integer>();
//				roads.put(roadNumbers[i][1], posr);
//			}
//			posr.add(roadNumbers[i][0]);
//		}
//		for (int j = 0; j < 10000; j++) {
//			HashMap<Integer, ColorValue> valueMap = new HashMap<Integer, ColorValue>();
//			System.out.println("serialNumbers=" + serialNumbers.length);
//			for (int i = 0; i < serialNumbers.length; i++) {
//				int num = serialNumbers[i];
//				ColorValue colorValue = new ColorValue();
//				colorValue.setIdx(num);
//				colorValue.setMonid(0);
//				colorValue.setColortype(RandomUtils.random(1, 2));
//				valueMap.put(num, colorValue);
//			}
////			int random = RandomUtils.random(12);
////			System.out.println("random=" + random);
////			for (int i = 0; i < random; i++) {
////				int randomvalue = serialNumbers[RandomUtils.random(serialNumbers.length)];
////				valueMap.put(randomvalue, 2);
////				System.out.println("randomvalue=" + randomvalue);
////			}
//			System.out.println(JSON.toJSONString(valueMap));
//			int putidx = serialNumbers[RandomUtils.random(serialNumbers.length)];
//			ColorValue getColorValue = valueMap.get(putidx);
//			System.out.println("putidx=" + putidx + "=" + getColorValue.getColortype());
//			long curtime = System.currentTimeMillis();
//			List<Integer> comboList = getComboList(valueMap, putidx, getColorValue.getColortype(), putidx, null);
//			curtime = System.currentTimeMillis() - curtime;
//			System.out.println("curtime=" + curtime);
//			Collections.sort(comboList);
//			System.out.println(JSON.toJSONString(comboList));
//			System.out.println("comboList=" + comboList.size());
//			System.out.println("=======================================================================");
//		}
//	}
}
