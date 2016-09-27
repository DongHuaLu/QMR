package com.game.map.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.game.backpack.manager.BackpackManager;
import com.game.buff.structs.Buff;
import com.game.buff.structs.TriggerType;
import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_buffBean;
import com.game.data.bean.Q_mapBean;
import com.game.data.bean.Q_map_blockBean;
import com.game.data.bean.Q_monsterBean;
import com.game.data.bean.Q_transferBean;
import com.game.data.manager.DataManager;
import com.game.dazuo.manager.PlayerDaZuoManager;
import com.game.drop.structs.MapDropInfo;
import com.game.fight.structs.FighterState;
import com.game.hiddenweapon.structs.HiddenWeapon;
import com.game.horse.struts.Horse;
import com.game.horseweapon.structs.HorseWeapon;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.asyn.ReqChangeServerHandler;
import com.game.map.bean.EffectInfo;
import com.game.map.bean.MonsterInfo;
import com.game.map.bean.NpcInfo;
import com.game.map.bean.PetInfo;
import com.game.map.bean.PlayerInfo;
import com.game.map.message.ReqGetLinesMessage;
import com.game.map.message.ReqSelectLineMessage;
import com.game.map.message.ResChangeDirectMessage;
import com.game.map.message.ResChangeMapFailedMessage;
import com.game.map.message.ResChangeMapMessage;
import com.game.map.message.ResChangePositionMessage;
import com.game.map.message.ResEnterMapMessage;
import com.game.map.message.ResJumpSetPositionMessage;
import com.game.map.message.ResMonsterRunPositionsMessage;
import com.game.map.message.ResMonsterStopMessage;
import com.game.map.message.ResPetRunPositionsMessage;
import com.game.map.message.ResPetStopMessage;
import com.game.map.message.ResPetTranMoveMessage;
import com.game.map.message.ResPlayerJumpPositionsMessage;
import com.game.map.message.ResPlayerRunEndMessage;
import com.game.map.message.ResPlayerStopMessage;
import com.game.map.message.ResRoundEffectDisappearMessage;
import com.game.map.message.ResRoundEffectMessage;
import com.game.map.message.ResRoundGoodsDisappearMessage;
import com.game.map.message.ResRoundGoodsMessage;
import com.game.map.message.ResRoundMonsterDisappearMessage;
import com.game.map.message.ResRoundMonsterMessage;
import com.game.map.message.ResRoundNpcDisappearMessage;
import com.game.map.message.ResRoundNpcMessage;
import com.game.map.message.ResRoundObjectsMessage;
import com.game.map.message.ResRoundPetDisappearMessage;
import com.game.map.message.ResRoundPetMessage;
import com.game.map.message.ResRoundPlayerDisappearMessage;
import com.game.map.message.ResRoundPlayerMessage;
import com.game.map.message.ResRunPositionsMessage;
import com.game.map.message.ResSendLinesMessage;
import com.game.map.message.ResSetPositionMessage;
import com.game.map.message.ResStartBlockMessage;
import com.game.map.message.ResStopBlockMessage;
import com.game.map.script.IBeforeChangeMapScript;
import com.game.map.script.IChangeMapScript;
import com.game.map.script.IEnterMapScript;
import com.game.map.script.IEnterTeleporterScript;
import com.game.map.script.IMapCreateScript;
import com.game.map.script.IQuitMapScript;
import com.game.map.structs.Area;
import com.game.map.structs.Effect;
import com.game.map.structs.Jump;
import com.game.map.structs.Map;
import com.game.monster.message.ReqMonsterSyncMessage;
import com.game.monster.structs.Monster;
import com.game.monster.structs.MonsterState;
import com.game.monster.structs.Morph;
import com.game.npc.struts.NPC;
import com.game.pet.manager.PetInfoManager;
import com.game.pet.manager.PetOptManager;
import com.game.pet.struts.Pet;
import com.game.pet.struts.PetJumpState;
import com.game.player.manager.PlayerManager;
import com.game.player.message.ReqSyncPlayerMapMessage;
import com.game.player.script.IPlayerJumpScript;
import com.game.player.script.PlayerCheckType;
import com.game.player.structs.Person;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.server.config.MapConfig;
import com.game.server.config.ServerType;
import com.game.server.impl.MServer;
import com.game.server.impl.WServer;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.structs.Reasons;
import com.game.utils.CommonConfig;
import com.game.utils.Global;
import com.game.utils.MapBlockUtils;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;
import com.game.vip.manager.VipManager;

public class MapManager {

	private Logger log = Logger.getLogger(MapManager.class);
	
	private Logger monsterlog = Logger.getLogger("MONSTER");
	
	private static Object obj = new Object();
	private static final int AREA_WIDTH = 225;
	private static final int AREA_HEIGHT = 175;
	//
	
//	private static final long MIN_STEP_TIME = 500;
	// 玩家管理类实例
	private static MapManager manager;
	// 地图数据缓存（key为服务器编号+"_"+线编号+"_"+地图编号）
	private static ConcurrentHashMap<String, Map> mapping = new ConcurrentHashMap<String, Map>();
	// 地图阻挡数据
	private static ConcurrentHashMap<Integer, Grid[][]> mapblocks = new ConcurrentHashMap<Integer, Grid[][]>();
	// 地图线路分布
	private static ConcurrentHashMap<Integer, Vector<Integer>> mapLines = new ConcurrentHashMap<Integer, Vector<Integer>>();

	private AtomicInteger mapSendId = new AtomicInteger(0);
	
	private int prison_map = 42122;
	/**
	 * 检查线路是否开放
	 *
	 * @param mapmodelid	地图模型ID
	 * @param lineid	线ID
	 * @return
	 */
	public static boolean checkLineIsOpen(int mapmodelid, int lineid) {
		if (!mapLines.containsKey(mapmodelid)) {
			return false;
		}
		Vector<Integer> vector = mapLines.get(mapmodelid);
		if (vector == null) {
			return false;
		}
		return vector.contains(lineid);
	}

	/**
	 * 物品掉落信息KEY=(物品ID)
	 */
	// private static ConcurrentHashMap<Long,DropGoodsInfo> dropList=new
	// ConcurrentHashMap<Long, DropGoodsInfo>();
	private MapManager() {
		initMapBlocks();
	}

	public static MapManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new MapManager();
			}
		}
		return manager;
	}

	public synchronized void initMapBlocks() {
		// 获取map列表
		List<Q_mapBean> q_mapBeans = ManagerPool.dataManager.q_mapContainer.getList();

		for (int i = 0; i < q_mapBeans.size(); i++) {
			Q_mapBean q_mapBean = q_mapBeans.get(i);
			if (!mapblocks.containsKey(q_mapBean.getQ_map_id())) {
				mapblocks.put(q_mapBean.getQ_map_id(), initMapBlock(q_mapBean));
			}
		}
	}

	/**
	 * 初始化地图
	 *
	 * @param lineId
	 */
	public int initMaps(int serverId, int lineId, int mapModelId, long zoneId, int zoneModelId) {
		// 获取map列表
		Q_mapBean q_mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(mapModelId);
		// 初始化地图
		Map map = new Map(q_mapBean.getQ_map_id());
		//副本地图
		if (q_mapBean.getQ_map_zones() == 1) {
			map.setId(MapUtils.getMapId());
			map.setZoneId(zoneId);
			map.setZoneModelId(zoneModelId);
			map.setCopy(true);
		} else {
			map.setId(q_mapBean.getQ_map_id());
			map.setCopy(false);
		}
		map.setCreate(System.currentTimeMillis());
		map.setMapModelid(q_mapBean.getQ_map_id());
		map.setServerId(serverId);
		map.setLineId(lineId);
		map.setSendId(mapSendId.getAndIncrement());

		int width_num = (q_mapBean.getQ_map_width() % AREA_WIDTH == 0) ? (q_mapBean.getQ_map_width() / AREA_WIDTH) : (q_mapBean.getQ_map_width()
			/ AREA_WIDTH + 1);
		int height_num = (q_mapBean.getQ_map_height() % AREA_HEIGHT == 0) ? (q_mapBean.getQ_map_height() / AREA_HEIGHT) : (q_mapBean.getQ_map_height() / AREA_HEIGHT + 1);

		map.setWidth(width_num);
		map.setHeight(height_num);

		for (int i = 0; i < width_num; i++) {
			for (int j = 0; j < height_num; j++) {
				Area area = new Area();
				area.setId(i * 1000 + j);
				map.getAreas().put((int) area.getId(), area);
			}
		}

		if (lineId <= q_mapBean.getQ_default_line()) {
			synchronized (mapLines) {
				// 获取地图已经开放线路列表
				Vector<Integer> openLines = mapLines.get(map.getMapModelid());
				if (openLines == null) {
					openLines = new Vector<Integer>();
					mapLines.put(map.getMapModelid(), openLines);
				}
				openLines.add(lineId);
			}

		}

		mapping.put(serverId + "_" + lineId + "_" + (int) map.getId(), map);

		IMapCreateScript script = (IMapCreateScript) ManagerPool.scriptManager.getScript(ScriptEnum.MAP_CREATE);
		if (script != null) {
			try {
				script.onCreate(map);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("地图创建脚本不存在！");
		}

		log.debug("创建地图" + map.getId() + "，地图数量：" + mapping.size());
		return (int) map.getId();
	}

	/**
	 * 初始化地图阻挡
	 *
	 * @param q_mapBean
	 * @return
	 */
	public Grid[][] initMapBlock(Q_mapBean q_mapBean) {
		int width_num = q_mapBean.getQ_map_width() / MapUtils.GRID_BORDER;
		int height_num = q_mapBean.getQ_map_height() / MapUtils.GRID_BORDER;
		Q_map_blockBean block = ManagerPool.dataManager.q_map_blockContainer.getMap().get(q_mapBean.getQ_map_id());

		Grid[][] blocks = new Grid[height_num][width_num];
		if (block == null) {
			log.error("地图" + q_mapBean.getQ_map_id() + "没有阻挡数据", new NullPointerException("地图" + q_mapBean.getQ_map_id() + "没有阻挡数据"));
			return blocks;
		}
		log.debug("map " + q_mapBean.getQ_map_id() + " width " + width_num + " height " + height_num + " block " + block.getQ_block().length);
		log.error("开始加载地图："+q_mapBean.getQ_map_id());
		for (int i = 0; i < width_num; i++) {
			for (int j = 0; j < height_num; j++) {
				blocks[j][i] = new Grid(i, j);
				blocks[j][i].setCenter(new Position(
					(short) (blocks[j][i].getX()
					* MapUtils.GRID_BORDER + MapUtils.GRID_BORDER / 2),
					(short) (blocks[j][i].getY()
					* MapUtils.GRID_BORDER + MapUtils.GRID_BORDER / 2)));
				if (block != null) {
					int site = j * width_num + i;
					blocks[j][i].setBlock((block.getQ_block()[site / 8] & (0x01 << (7 - site % 8))) >> (7 - site % 8));
					blocks[j][i].setSafe((block.getQ_safe()[site / 8] & (0x01 << (7 - site % 8))) >> (7 - site % 8));
					blocks[j][i].setJump((block.getQ_jump()[site / 8] & (0x01 << (7 - site % 8))) >> (7 - site % 8));
					if (block.getQ_effect() != null) {
						blocks[j][i].setEffect(block.getQ_effect()[site]);
					} else {
						blocks[j][i].setEffect(0);
					}
					if (block.getQ_ban_monster() != null) {
						blocks[j][i].setForbid((block.getQ_ban_monster()[site / 8] & (0x01 << (7 - site % 8))) >> (7 - site % 8));
					} else {
						blocks[j][i].setForbid(0);
					}
				} else {
					blocks[j][i].setBlock(1);
					blocks[j][i].setSafe(0);
					blocks[j][i].setJump(0);
					blocks[j][i].setEffect(0);
					blocks[j][i].setForbid(0);
				}
				
			}
		}
		if (block != null) {
			MapBlockUtils.countBlock(blocks);
		}
		return blocks;
	}

	public void removeMap(int serverId, int lineId, int mapId) {
		mapping.remove(serverId + "_" + lineId + "_" + mapId);
		log.debug("移除地图" + mapId + "，地图数量：" + mapping.size());
	}

	/**
	 * 获取地图
	 *
	 * @param lineId
	 * @param mapId
	 */
	public Map getMap(int serverId, int lineId, int mapId) {
		return mapping.get(serverId + "_" + lineId + "_" + mapId);
	}

	public Map getMap(Person player) {
		if (player == null) {
			return null;
		}
		return getMap(player.getServerId(), player.getLine(), player.getMap());
	}
	
//	public Map getMap(Monster monster) {
//		return getMap(monster.getServerId(), monster.getLine(),
//				monster.getMap());
//	}
//	/**
//	 * 获取地图所有玩家
//	 * 
//	 * @param map
//	 * @return
//	 */
//	public HashSet<Player> getAllPlayer(Map map) {
//		HashMap<Integer, Area> areas = map.getAreas();
//		Iterator<Area> iterator = areas.values().iterator();
//		HashSet<Player> allPlayers = new HashSet<Player>();
//		while (iterator.hasNext()) {
//			Area area = (Area) iterator.next();
//			HashSet<Player> players = area.getPlayers();
//			allPlayers.addAll(players);
//		}
//		return allPlayers;
//	}
//
//	/**
//	 * 获取地图所有玩家
//	 * 
//	 * @param player
//	 * @return
//	 */
//	public HashSet<Player> getTongDiTu(Player player) {
//		Map map = getMap(player);
//		return getAllPlayer(map);
//	}
	/**
	 * 获取地图阻挡信息
	 *
	 * @param mapModelId
	 * @return
	 */
	public Grid[][] getMapBlocks(int mapModelId) {
		return mapblocks.get(mapModelId);
	}

	/**
	 * 根据玩家地图自动选择线路
	 *
	 * @param player
	 */
	public void selectLine(Player player) {
		//已有线路
		if (player.getLine() != 0) {
			return;
		}

		int mapModelId = player.getMapModelId();

		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(mapModelId);

		if (mapBean == null) {
			log.error("server " + player.getServerId() + " line " + player.getLine() + " map " + player.getMap() + " mapBean is null!");
			return;
		} else if (mapBean.getQ_map_zones() == 1) {
			player.setLine(1);
			return;
		}

		// 获取地图已经开放线路列表
		Vector<Integer> openLines = mapLines.get(mapModelId);
		if (openLines == null) {
			openLines = new Vector<Integer>();
			mapLines.put(mapModelId, openLines);
		}

		int max = mapBean.getQ_max_online();

		for (int i = 0; i < openLines.size(); i++) {
			int lineId = openLines.get(i);
			// 获取地图
			Map openMap = getMap(WServer.getInstance().getServerId(), lineId,
				mapModelId);
			if (openMap.getPlayerNumber() < max) {
				// 地图人数是否少于最大人数
				player.setLine(lineId);
				return;
			}
		}

//		// 是否已经选中地图
//		if (map != null) {
//			if (map.getPlayerNumber() < mapBean.getQ_max_online()) {
//				// 选线成功
//				player.setLine(line);
//				return;
//			}
//		}

		// 拥有该地图的线(是否开新的线路)
		if (mapBean.getQ_map_lines() != null && !"".equals(mapBean.getQ_map_lines())) {
			String[] lines = mapBean.getQ_map_lines().split(Symbol.SHUXIAN_REG);
			for (int i = 0; i < lines.length; i++) {
				int lineId = Integer.parseInt(lines[i]);
				// 该线是否已经开放
				if (openLines.contains(lineId)) {
					continue;
				} else {
					openLines.add(lineId);

					// 查询所有地图，该地图的子地图与本地图保持线路一致
					List<Q_mapBean> mapbeans = ManagerPool.dataManager.q_mapContainer.getList();
					Iterator<Q_mapBean> iter = mapbeans.iterator();
					while (iter.hasNext()) {
						Q_mapBean q_mapBean = (Q_mapBean) iter.next();
						if (q_mapBean.getQ_map_subsidiary() == 1 && q_mapBean.getQ_subsidiary_main() == mapModelId) {
							// 获取地图已经开放线路列表
							Vector<Integer> subOpenLines = mapLines.get(q_mapBean.getQ_map_id());
							if (subOpenLines != null) {
								subOpenLines.add(lineId);
							}
						}
					}

					// 选线成功
					player.setLine(lineId);
					return;
				}
			}
		}

//		// 是否已经选中地图
//		if (map != null) {
//			// 选线成功
//			player.setLine(line);
//			return;
//		}

		while (true) {
			max += mapBean.getQ_poll_num();
			for (int i = 0; i < openLines.size(); i++) {
				int lineId = openLines.get(i);
				// 获取地图
				Map openMap = getMap(WServer.getInstance().getServerId(), lineId,
					mapModelId);
				if (openMap.getPlayerNumber() < max) {
					// 地图人数是否少于最大人数
					player.setLine(lineId);
					return;
				}
			}
		}
	}

	/**
	 * 是否在传送点范围内
	 *
	 * @return
	 */
	public int isOnTransPoint(Player player) {
		Map map = getMap(player);
		if (map == null) {
			return -1;
		}
		List<Q_transferBean> trans = ManagerPool.dataManager.q_transferContainer.getList();
		for (Q_transferBean tran : trans) {
			if (map.getMapModelid() != tran.getQ_tran_from_map()) {
				continue;
			}
			String frompoint = tran.getQ_tran_from_range();
			frompoint = frompoint.replaceAll("\\{|\\}", "");
			String[] str = frompoint.split(Symbol.JINGHAO_REG);
			String[] pos = str[0].split(Symbol.DOUHAO_REG);
			short fromx = (short) (Short.parseShort(pos[0]) * MapUtils.GRID_BORDER);
			short fromy = (short) (Short.parseShort(pos[1]) * MapUtils.GRID_BORDER);
			short radius = (short) (Short.parseShort(str[1]) * MapUtils.GRID_BORDER);
			Position center = new Position(fromx, fromy);
			double dis = MapUtils.countDistance(player.getPosition(), center);
			// 不在传送范围内
			if (dis <= radius) {
				return tran.getQ_tran_id();
			}
		}
		return -1;
	}

	/**
	 * 传送点切换地图
	 *
	 * @param player
	 * @param line 切换线路
	 * @param tranId 切换传送点Id
	 */
	public boolean changeMapByMove(Player player, int line, int tranId) {
		Q_transferBean tran = ManagerPool.dataManager.q_transferContainer.getMap().get(tranId);
		// 判断现在玩家位置是否正确 {X,Y}#半径
		Map map = getMap(player);

		if (map == null) {
			return false;
		}
		// 非本地图传送点
		if (map.getMapModelid() != tran.getQ_tran_from_map()) {
			return false;
		}
		// 线路判断
		Vector<Integer> lines = mapLines.get(tran.getQ_tran_to_map());
		if (lines == null || !lines.contains(line)) {
			line = 0;
		}
		
		if(!ischangMap(player)){
			return false;
		}
//		if (PlayerState.AUTOFIGHT.compare(player.getState())) {
//			MessageUtil.notify_player(player, Notifys.NORMAL, "挂机状态不能切换地图。");
//			changeMapFailed(player);
//			return false;
//		}
//		//战斗状态不允许切换地图
//		if (PlayerState.FIGHT.compare(player.getState())) {
//			MessageUtil.notify_player(player, Notifys.ERROR, "很抱歉，您尚未脱离战斗状态，无法进行传送");
//			changeMapFailed(player);
//			return false;
//		}
//		if (PlayerManager.getInstance().hasAbleReceive(player)) {
//			MessageUtil.notify_player(player, Notifys.ERROR, "操作失败，传送前请先领走您的物品奖励");
//			changeMapFailed(player);
//			return false;
//		}
//		if (PlayerState.LONGYUANSTART.compare(player.getState())) {
//			MessageUtil.notify_player(player, Notifys.ERROR, "很抱歉，龙元心法激活倒计时中，暂时不能切换地图。");
//			changeMapFailed(player);
//			return false;
//		}
		String frompoint = tran.getQ_tran_from_range();
		frompoint = frompoint.replaceAll("\\{|\\}", "");
		String[] str = frompoint.split(Symbol.JINGHAO_REG);
		String[] pos = str[0].split(Symbol.DOUHAO_REG);
		short fromx = (short) (Short.parseShort(pos[0]) * MapUtils.GRID_BORDER);
		short fromy = (short) (Short.parseShort(pos[1]) * MapUtils.GRID_BORDER);
		short radius = (short) (Short.parseShort(str[1]) * MapUtils.GRID_BORDER);
		Position center = new Position(fromx, fromy);
		double dis = MapUtils.countDistance(player.getPosition(), center);
		// 不在传送范围内
		if (dis > radius) {
			//changeMapFailed(player);
			return false;
		}
		if (PlayerState.JUMP.compare(player.getState())) {
			//changeMapFailed(player);
			return false;
		}
		if (PlayerState.DOUBLEJUMP.compare(player.getState())) {
			//changeMapFailed(player);
			return false;
		}
//		// 传送地点 {X,Y}#半径@判断等级
//		String targetpoint = tran.getQ_tran_to_range();
//		targetpoint = targetpoint.replaceAll("\\{|\\}", "");
//		String[] strs = targetpoint.split(Symbol.AT_REG);
//		int needgrade = Integer.parseInt(strs[1]);
//		// 等级不足
//		if (player.getLevel() < needgrade) {
//			MessageUtil.notify_player(player, Notifys.ERROR, "等级限制，需要达到" + needgrade + "级才可进入");
//			changeMapFailed(player);
//			return false;
//		}
//
//		String targetexp[] = strs[0].split(Symbol.JINGHAO_REG);
//		String point[] = targetexp[0].split(Symbol.DOUHAO_REG);
//		radius = (short) (Short.parseShort(targetexp[1]) * MapUtils.GRID_BORDER);
//		short targetx = (short) (Short.parseShort(point[0]) * MapUtils.GRID_BORDER);
//		short targety = (short) (Short.parseShort(point[1]) * MapUtils.GRID_BORDER);
//		center = new Position(targetx, targety);
//		Grid[][] mapInfos = getMapBlocks(tran.getQ_tran_to_map());
//		List<Grid> grids = MapUtils.getRoundGrid(center, radius, mapInfos);
//		// 随即传送位置
//		Position position = null;
//		while (grids.size() > 0) {
//			Grid grid = grids.get(RandomUtils.random(grids.size()));
//			grids.remove(grid);
//			if (grid.getBlock() != 0) {
//				position = grid.getCenter();
//				break;
//			}
//		}

		IEnterTeleporterScript script = (IEnterTeleporterScript) ManagerPool.scriptManager.getScript(ScriptEnum.ON_TELEPORTERS);
		if (script != null) {
			try {
				script.onTeleporter(player, line, tranId, tran.getQ_scriptid());
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("地图传送脚本不存在！");
		}
//		// 开始传送
//		if (position != null) {
//			changeMap(player, tran.getQ_tran_to_map(), tran.getQ_tran_to_map(), line, position);
//		}
		return true;
	}

	/**
	 * 小地图传送
	 */
	public void yuanBaoChangeMap(Player player, int mapModelId) {
		if (!ischangMap(player)) {
			return;
		}
		List<Integer> transpoint = new ArrayList<Integer>();
		int countMapCost = countMapCost(player.getMapModelId(), mapModelId, transpoint);
		//同一地图
		if (countMapCost == -10) {
			return;
		}
		if (transpoint.size() <= 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该地图暂未开放"), countMapCost + "");
			return;
		}
//		Gold gold = BackpackManager.getInstance().getGold(player);
		
		Integer transid = transpoint.get(0);
		Q_transferBean tran = DataManager.getInstance().q_transferContainer.getMap().get(transid);
		String targetpoint = tran.getQ_tran_to_range();
		targetpoint = targetpoint.replaceAll("\\{|\\}", "");
		String[] strs = targetpoint.split(Symbol.AT_REG);
		int needgrade = Integer.parseInt(strs[1]);
		// 等级不足
		if (player.getLevel() < needgrade) {
			MessageUtil.notify_player(player, Notifys.ERROR, java.text.MessageFormat.format(ResManager.getInstance().getString("等级限制，需要达到{0}级才可进入"), new Object[] {needgrade}));
			return;
		}
		int vipid = VipManager.getInstance().getPlayerVipId(player);
		boolean vipFree = false;
		if(vipid>0){
			//VipManager.getInstance().resetFreeFly(player);//过天了则重置免费次数 移动到打开面板时重置
			if((vipid==1 || vipid==2) && player.getVipright().getFreeflytime()>0){ //VIP 1 2级 需要免费次数
				vipFree = true;
				player.getVipright().setFreeflytime(player.getVipright().getFreeflytime()-1); //扣一次免费次数 
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("VIP免费传送,今天剩余")+player.getVipright().getFreeflytime()+ResManager.getInstance().getString("次"));
				player.getVipright().setLastFreeFlyTime(System.currentTimeMillis()); //记录上次免费元宝传送时间
			}
			if(vipid==3){
				vipFree = true;  //VIP 3 级 直接免费
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("至尊VIP免费传送"));
			}
			PlayerManager.getInstance().reqVipPlayerChangeMap(player);
		}
		if(!vipFree){ // 非 VIP免费
			if (!BackpackManager.getInstance().checkGold(player, countMapCost)) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，元宝不足传送需要{1}元宝"), countMapCost + "");
				return;
			}
			if (!BackpackManager.getInstance().changeGold(player, -countMapCost, Reasons.YBTRANS, Config.getId())) {
				return;
			}
		}
		String targetexp[] = strs[0].split(Symbol.JINGHAO_REG);
		String point[] = targetexp[0].split(Symbol.DOUHAO_REG);
		int radius = (short) (Short.parseShort(targetexp[1]) * MapUtils.GRID_BORDER);
		short targetx = (short) (Short.parseShort(point[0]) * MapUtils.GRID_BORDER);
		short targety = (short) (Short.parseShort(point[1]) * MapUtils.GRID_BORDER);
		Position center = new Position(targetx, targety);
		List<Grid> roundNoBlockGrid = MapUtils.getRoundNoBlockGrid(center, radius, mapModelId);
		Grid grid = (Grid) RandomUtils.randomItem(roundNoBlockGrid);
		changeMap(player, mapModelId, mapModelId, 0, grid.getCenter(), this.getClass().getName() + ".yuanBaoChangeMap");
		
		
	}

	public void changeMapFailed(Player player) {
		ResChangeMapFailedMessage msg = new ResChangeMapFailedMessage();
		MessageUtil.tell_player_message(player, msg);
	}

	/**
	 * 创建副本地图
	 *
	 * @param mapModelId 地图模板id
	 * @param zones 副本id
	 * @return 地图唯一编号 ， -1创建失败（副本达到上限）
	 */
	public MServer createMap(String name, long zoneId, int zoneModelId, List<MapConfig> mapConfigs) {
		return WServer.getInstance().createMapServer(name, zoneId, zoneModelId, mapConfigs);
	}

	public void playerChangeDirection(Player player, byte dir) {
		ResChangeDirectMessage msg = new ResChangeDirectMessage();
		msg.setPersonId(player.getId());
		msg.setDir(dir);
		MessageUtil.tell_round_message(player, msg);
	}

	/**
	 * 检测是否可进行传送
	 *
	 * @param player
	 * @return
	 */
	public boolean ischangMap(Player player) {
		//判断是否可以退出战斗状态
		if (PlayerState.FIGHT.compare(player.getState()) && player.getLastFightTime() + Global.FIGHT_OVERDUE < System.currentTimeMillis()) {
			log.debug("out fight state:" + System.currentTimeMillis());
			//设置状态为普通
			player.setState(PlayerState.NOTHING);
		}
		// 战斗状态不允许切换地图
		if (PlayerState.FIGHT.compare(player.getState())) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您尚未脱离战斗状态，无法进行传送"));
			return false;
		}
//		if (PlayerState.AUTOFIGHT.compare(player.getState())) {
//			MessageUtil.notify_player(player, Notifys.NORMAL, "挂机状态不能切换地图。");
//			return false;
//		}
//		if (PlayerState.LONGYUANSTART.compare(player.getState())) {
//			MessageUtil.notify_player(player, Notifys.ERROR, "很抱歉，龙元心法激活倒计时中，暂时不能切换地图。");
//			return false;
//		}
		if (PlayerManager.getInstance().hasAbleReceive(player)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("切换地图前请先领走您的物品奖励"));
			return false;
		}
		//监狱不可以传送
		if(player.getMapModelId()==prison_map && player.getPrisonRemainTime() > 0){
			return false;
		}

		IBeforeChangeMapScript bscript = (IBeforeChangeMapScript) ManagerPool.scriptManager.getScript(ScriptEnum.BEFORE_CHANGE_MAP);
		if (bscript != null) {
			try {
				if (!bscript.beforeChangeMap(player)) {
					return false;
				}
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("地图进入判断脚本不存在！");
		}
		return true;
	}

	/**
	 * 切换地图，强制传送
	 *
	 * @param player
	 * @param mapModelId 地图模板id
	 * @param line 线路id
	 * @param pos 坐标
	 */
	public boolean changeMap(Player player, int mapId, int mapModelId, int line, Position pos, String callClass) {
		return changeMap(player, mapId, mapModelId, line, pos, (byte) 0, callClass);
	}
	
	/**
	 * 切换地图，强制传送
	 *
	 * @param player
	 * @param mapModelId 地图模板id
	 * @param line 线路id
	 * @param pos 坐标
	 */
	public boolean changeMap(Player player, int mapId, int mapModelId, int line, Position pos) {
		return changeMap(player, mapId, mapModelId, line, pos, (byte) 0, "");
	}

	/**
	 * 切换地图，强制传送
	 *
	 * @param player
	 * @param mapModelId 地图模板id
	 * @param line 线路id
	 * @param pos 坐标
	 */
	public boolean changeMap(Player player, int mapId, int mapModelId, int line, Position pos, byte reason, String callClass) {
		try {
			log.error("玩家[" + player.getName() + "(" + player.getId() + ")](" + callClass + ")切换地图(" + mapId + "," + mapModelId + "," + line + "," + pos + "," + reason + ")");
		} catch (Exception e) {
			log.error(e, e);
		}
		ManagerPool.playerManager.endAuto(player);//结束挂机
		//停止采集
		ManagerPool.npcManager.playerStopGather(player);

		//攻城战换地图
		if (ManagerPool.countryManager.getSiegestate() == 1 && player.getId() == ManagerPool.countryManager.getKingcity().getHoldplayerid()) {
			ManagerPool.countryManager.SiegeHomingYuXi(player);
			MessageUtil.notify_All_player(Notifys.CUTOUT, ResManager.getInstance().getString("帮主{1}离开承玺台地图，秦王玉玺回归到了王座上"),player.getName());
		}
		
		//监狱不可以传送
		if(player.getMapModelId()==prison_map && player.getPrisonRemainTime() > 0){
			return false;
		}
		
		if (player.getMap() == mapId && player.getMapModelId() == mapModelId && player.getLine() == line) {
			return false;
		}

		if (player.getTransactionsinfo() != null) {//打断交易
			ManagerPool.transactionsManager.stTransactionsCanceled(player);
		}
		
		// 获取原地图
		Map oldMap = getMap(player);
		if (oldMap!= null && !oldMap.isCopy()) {
			player.setFormerline(player.getLine());
			player.setFormermapid(player.getMap());
			player.setFormerposition(player.getPosition());
		}

		Q_mapBean oldMapBean = null;
		if (oldMap != null) {
			oldMapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(oldMap.getMapModelid());
		} else {
			oldMapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(player.getMapModelId());
		}

		Q_mapBean newMapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(mapModelId);

		if (newMapBean == null) {
			return false;
		}

		if (pos.getX() > newMapBean.getQ_map_width() || pos.getY() > newMapBean.getQ_map_height()) {
			return false;
		}

		
		PlayerDaZuoManager.getInstacne().breakDaZuo(player);
		if (oldMap != null) {
			// 退出地图
			quitMap(player);
		}

		player.setState(PlayerState.CHANGEMAP);
		// 清除待移动列表
		player.setLastRoads(null);
		// 清空移动列表
		player.getRoads().clear();
		//跳跃状态移除
		if (PlayerState.JUMP.compare(player.getState()) || PlayerState.DOUBLEJUMP.compare(player.getState())) {
			ManagerPool.cooldownManager.removeCooldown(player, CooldownTypes.JUMP, null);
			//移除跳跃保护
			player.setJumpProtect(false);
		}
		//站立状态
		//player.setState(PlayerState.STAND);
		ManagerPool.mapManager.setPlayerPosition(player, player.getPosition());

		if (line == 0 && newMapBean.getQ_map_subsidiary() == 1) {
			//地图为boss地图，选择有boss线路进入
			// 获取地图已经开放线路列表
			synchronized (mapLines) {
				Vector<Integer> openLines = mapLines.get(newMapBean.getQ_map_id());
				if (openLines != null) {
					boolean select = false;
					for (int i = 0; i < openLines.size(); i++) {
						Map map = getMap(WServer.getInstance().getServerId(), openLines.get(i), newMapBean.getQ_map_id());
						if (map != null) {
							Monster boss = findMonster(map, newMapBean.getQ_boss_id());
							if(boss==null || boss.isDie()) continue;
							else{
								player.setLine(openLines.get(i));
								select = true;
							}
						}
					}
					if (!select) {
						player.setLine(line);
					}
				} else {
					// 换线
					player.setLine(line);
				}
			}
		} else {
			// 换线
			player.setLine(line);
		}
		log.debug("切换地图玩家" + player.getId() + "选线为" + player.getLine());
		// 切换地图Id，坐标
		player.setMap(mapId);
		player.setMapModelId(mapModelId);
		player.setPosition(pos);


		IChangeMapScript script = (IChangeMapScript) ManagerPool.scriptManager.getScript(ScriptEnum.CHANGE_MAP);
		if (script != null) {
			try {
				script.onChangeMap(player);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("地图改变脚本不存在！");
		}

		// 不同服务器地图切换
		if (oldMapBean.getQ_map_public() != newMapBean.getQ_map_public()) {
			// 切换服务器
			if (newMapBean.getQ_map_public() == 1) {
				// 新地图是公共地图
				player.setLocate(ServerType.PUBLIC.getValue());
			} else {
				// 新地图是普通地图
				player.setLocate(player.getCountry());
			}
//			/**
//			 * 普通地图切换到公共地图 公共地图切换到普通地图 把本服务器缓存数据移除并保存
//			 */
//			ManagerPool.playerManager.updatePlayer(player);
//			ManagerPool.playerManager.removePlayer(player);
//
//
//			// 通知网关服务器玩家切换服务器
//			ResLoginSuccessToGateMessage gate_msg = new ResLoginSuccessToGateMessage();
//			gate_msg.setCreateServerId(player.getCreateServerId());
//			gate_msg.setServerId(player.getServerId());
//			gate_msg.setUserId(player.getUserId());
//			gate_msg.setPlayerId(player.getId());
//			MessageUtil.send_to_gate(player.getGateId(), player.getId(),
//				gate_msg);
//
//			// 通知世界服务器玩家切换服务器
//			ResLoginSuccessToWorldMessage world_msg = new ResLoginSuccessToWorldMessage();
//			world_msg.setGateId(player.getGateId());
//			world_msg.setServerId(player.getServerId());
//			world_msg.setUserId(player.getUserId());
//			world_msg.setPlayerId(player.getId());
//			world_msg.setIsAdult((byte) 1);
//			MessageUtil.send_to_world(world_msg);
			ReqChangeServerHandler handler = new ReqChangeServerHandler();
			handler.setParameter(player);
			WServer.getInstance().getServerThread().addCommand(handler);

			return true;
		}

		ResChangeMapMessage msg = new ResChangeMapMessage();
		msg.setType(reason);
		msg.setMapId(mapModelId);
		MessageUtil.tell_player_message(player, msg);
		return true;
	}
	
	private Monster findMonster(Map map, int modelId){
		Iterator<Monster> iter = map.getMonsters().values().iterator();
		while (iter.hasNext()) {
			Monster monster = (Monster) iter.next();
			if(monster.getModelId() == modelId) return monster;
		}
		return null;
	}

	/**
	 * 切换地点
	 *
	 * @param player
	 * @param pos 坐标
	 *
	 */
	public void changePosition(Player player, Position pos) {

		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(player.getMapModelId());

		if (pos.getX() > mapBean.getQ_map_width() || pos.getY() > mapBean.getQ_map_height()) {
			return;
		}

		Position old = player.getPosition();
		// 清空移动列表
		player.getRoads().clear();
		//跳跃状态移除
		if (PlayerState.JUMP.compare(player.getState()) || PlayerState.DOUBLEJUMP.compare(player.getState())) {
			ManagerPool.cooldownManager.removeCooldown(player, CooldownTypes.JUMP, null);
			//移除跳跃保护
			player.setJumpProtect(false);
		}
//		//站立状态
//		player.setState(PlayerState.STAND);
		PlayerDaZuoManager.getInstacne().breakDaZuo(player);

		ResChangePositionMessage tranmsg = new ResChangePositionMessage();
		tranmsg.setPersonId(player.getId());
		tranmsg.setPosition(pos);
		MessageUtil.tell_round_message(player, tranmsg);
//		player.setPosition(pos);


		setPlayerPosition(player, pos);
		
		/**
		 * 计算区域*
		 */
		//原来所在区域
		int oldAreaId = ManagerPool.mapManager.getAreaId(old);
		//现在所在区域
		int newAreaId = ManagerPool.mapManager.getAreaId(player.getPosition());
		//区域未变
		if (oldAreaId != newAreaId) {
			playerChangeArea(player, oldAreaId, newAreaId);
		}
		if (player.getAutohorse() == 1) {//跨地图的时候检测是否需要上坐骑
			player.setAutohorse((byte) 0);
			Horse horse = ManagerPool.horseManager.getHorse(player);
			ManagerPool.horseManager.stChangeRidingState(player, (byte) 1, horse.getCurlayer());
		}
	}

	/**
	 * 切换地点
	 *
	 * @param player
	 * @param pos 坐标
	 *
	 */
	public void setPlayerPosition(Player player, Position pos) {

		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(player.getMapModelId());

		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(mapBean.getQ_map_id());

		if (pos.getX() > mapBean.getQ_map_width() || pos.getY() > mapBean.getQ_map_height()) {
			return;
		}
		PlayerDaZuoManager.getInstacne().breakDaZuo(player);
		player.setPosition(pos);

		Grid grid = MapUtils.getGrid(pos, grids);
		if (isSwimGrid(grid)) {
			if (!PlayerState.SWIM.compare(player.getState())) {
				//进入游泳状态
				player.setState(PlayerState.SWIM);
				//增加游泳buff
				ManagerPool.buffManager.addBuff(player, player, Global.BUFF_FOR_SWIM, 0, 0, 0);
				log.debug("切换游泳状态");
//				//自动下马
//				ManagerPool.horseManager.unride(player);
			}
		} else {
			if (PlayerState.SWIM.compare(player.getState())) {
				//移除游泳buff
				PlayerDaZuoManager.getInstacne().breakDaZuo(player);
				ManagerPool.buffManager.removeByBuffId(player, Global.BUFF_FOR_SWIM);
				log.debug("退出游泳状态");
			}

			//切换状态
			if (player.getRoads().size() > 0) {
				player.setState(PlayerState.RUN);
			} else {
				player.setState(PlayerState.STAND);
			}
		}
	}

	/**
	 * 格子是否游泳区
	 *
	 * @param grid
	 * @return
	 */
	public boolean isSwimGrid(Grid grid) {
		if ((grid.getEffect() & 0x01) > 0) {
			return true;
		} else {
			return false;
		}
	}

	
	/**获取玩家所在格子场景类型,-1表示出错
	 * 
	 * @return
	 */
	public int getGridType(Player player){
		Map map = getMap(player);
		if (map != null ) {
			Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
			if (grids != null) {
				return getGridType(grids,player.getPosition());
			}
		}
		return -1;
	}
	
	
	
	/**获取指定格子场景类型
	 * 
	 * @param grids
	 * @param pos
	 * @return
	 */
	public int getGridType(Grid[][] grids , Position pos){
		Grid grid = MapUtils.getGrid(pos, grids);
		return grid.getEffect();
	}
	
	
	
	
	
//	/**
//	 * 切换地图
//	 *
//	 * @param player
//	 * @param mapId 地图id
//	 * @param line 线路id
//	 * @param pos 坐标
//	 */
//	public void gotoMap(Player player, int mapId, int line, Position pos) {
//		Map newMap = getMap(WServer.getInstance().getServerId(), line, mapId);
//		if (newMap == null) {
//			return;
//		}
//		// 获取原地图
//		Map oldMap = getMap(player);
//		if (oldMap != null) {
//			// 退出地图
//			quitMap(player);
//		}
//
//		// 清空移动列表
//		player.getRoads().clear();
//		player.setState(PlayerState.STAND);
//		// 换线
//		player.setLine(line);
//		log.debug("玩家" + player.getId() + "移动到地图选线为" + player.getLine());
//
//
//		// 切换地图Id，坐标
//		player.setMap(mapId);
//		player.setMapModelId(newMap.getMapModelid());
//		player.setPosition(pos);
//
//		ResChangeMapMessage msg = new ResChangeMapMessage();
//		msg.setMapId(player.getMapModelId());
//		MessageUtil.tell_player_message(player, msg);
//	}
	/**
	 * 玩家进入地图
	 *
	 * @param player 玩家
	 */
	public void enterMap(Player player, int width, int height) {
		
		long start = System.currentTimeMillis();
		
		player.setState(PlayerState.INMAP);
		player.setWidth(width);
		player.setHeight(height);
		Map map = getMap(player);
		if (map == null) {
			log.error("loadfinishfromchangemap entermap player " + player.getId() + " server "
				+ player.getServerId() + " line " + player.getLine()
				+ " map " + player.getMap() + " not found!");
			//副本地图已经消失
			Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(player.getMapModelId());
			if (mapBean.getQ_map_quit() != 0) {
				Grid[][] grids = ManagerPool.mapManager.getMapBlocks(mapBean.getQ_map_quit());
				Grid grid = MapUtils.getGrid(mapBean.getQ_map_quit_x(), mapBean.getQ_map_quit_y(), grids);
				changeMap(player, mapBean.getQ_map_quit(), mapBean.getQ_map_quit(), 0, grid.getCenter(), this.getClass().getName() + ".enterMap");
			}
			return;
		}

		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());


		int areaId = getAreaId(player.getPosition());
		Area area = map.getAreas().get(areaId);
		if (area == null) {
			log.error("loadfinishfromchangemap player " + player.getId() + " server "
				+ player.getServerId() + " line " + player.getLine()
				+ " map " + player.getMap() + " area " + areaId + " not found, position" + player.getPosition());
			return;
		}
		if (!area.getPlayers().contains(player)) {
			area.getPlayers().add(player);
			log.debug("Area " + area.getId() + " add " + player.getId());
		} else {
			log.debug("Area " + area.getId() + " already contains player"
				+ player.getId());
		}
		
		map.getPlayers().put(player.getId(), player);

		log.error("player " + player.getId() + " " + player.getName() + " entermap line "
			+ player.getLine() + " map " + map.getId() + " area "
			+ area.getId() + "position " + player.getPosition() + " players " + map.getPlayerNumber());

		setPlayerPosition(player, player.getPosition());

		IEnterMapScript script = (IEnterMapScript) ManagerPool.scriptManager.getScript(ScriptEnum.ENTER_MAP);
		if (script != null) {
			try {
				script.onEnterMap(player, map);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("进入地图脚本不存在！");
		}
		PlayerManager.getInstance().enterMapCheck(player);
		//同步玩家线地图
		ReqSyncPlayerMapMessage syncmsg = new ReqSyncPlayerMapMessage();
		syncmsg.setPlayerId(player.getId());
		syncmsg.setLine(player.getLine());
		syncmsg.setMapId(map.getMapModelid());
		syncmsg.setX(player.getPosition().getX());
		syncmsg.setY(player.getPosition().getY());
		syncmsg.setMaponlyId(map.getId());
		MessageUtil.send_to_world(syncmsg);

		ResEnterMapMessage enter_msg = new ResEnterMapMessage();
		enter_msg.setLine(player.getLine());
		enter_msg.setPos(player.getPosition());
		MessageUtil.tell_player_message(player, enter_msg);
		// 自动下马
		Q_mapBean mapmodel = ManagerPool.dataManager.q_mapContainer.getMap().get(player.getMapModelId());
		if (mapmodel.getQ_map_ride() == 0) {
			ManagerPool.horseManager.unride(player);
		}
//		GuildFlagManager.getInstance().checkAndAddGuildFlagBUFF(player);
		ResRoundPlayerMessage other_msg = new ResRoundPlayerMessage();
		other_msg.setPlayer(getPlayerInfo(player, grids));
		ResRoundObjectsMessage msg = new ResRoundObjectsMessage();

		List<Area> rounds = getRoundAreas(map, areaId);
		for (int i = 0; i < rounds.size(); i++) {
			area = rounds.get(i);
			Iterator<Player> iter = area.getPlayers().iterator();
			while (iter.hasNext()) {
				Player other = (Player) iter.next();
				if (other.getId() == player.getId()) {
					continue;
				}

				if (other.canSee(player)) {
					msg.getPlayer().add(getPlayerInfo(other, grids));
				}
//				ResRoundPlayerMessage msg = new ResRoundPlayerMessage();
//				msg.setPlayer(getPlayerInfo(other, grids));
//				MessageUtil.tell_player_message(player, msg);

				if (player.canSee(other)) {
					other_msg.getRoleId().add(other.getId());
				}
			}

			Iterator<Monster> monster_iter = area.getMonsters().values().iterator();
			while (monster_iter.hasNext()) {
				Monster monster = (Monster) monster_iter.next();

				if (monster.canSee(player)) {
					msg.getMonster().add(getMonsterInfo(monster, grids));
				}
//				ResRoundMonsterMessage msg = new ResRoundMonsterMessage();
//				msg.setMonster(getMonsterInfo(monster, grids));
//				MessageUtil.tell_player_message(player, msg);
			}

			Iterator<MapDropInfo> iterator = area.getDropGoods().values().iterator();
			while (iterator.hasNext()) {
				MapDropInfo next = iterator.next();

				if (next.canSee(player)) {
					msg.getGoods().add(next.getDropinfo());
				}
//				ResRoundGoodsMessage msg = new ResRoundGoodsMessage();
//				msg.setGoods(next.getDropinfo());
//				MessageUtil.tell_player_message(player, msg);
			}

			HashSet<Pet> petset = area.getPets();
			for (Pet pet : petset) {
				if (pet.canSee(player)) {
					msg.getPets().add(getPetInfo(pet, grids));
				}
			}

			Iterator<NPC> npciterator = area.getNpcs().values().iterator();
			while (npciterator.hasNext()) {
				NPC npc = (NPC) npciterator.next();
				if (npc.canSee(player)) {
					msg.getNpcs().add(getNpcInfo(npc));
				}
			}

			Iterator<Effect> effectiterator = area.getEffects().values().iterator();
			while (effectiterator.hasNext()) {
				Effect effect = (Effect) effectiterator.next();
				if (effect.canSee(player)) {
					msg.getEffect().add(getEffectInfo(effect));
				}
			}
		}

		MessageUtil.tell_player_message(player, msg);

		boolean flag = true;
		for (int i = 0; i < player.getPetList().size(); i++) {
			Pet pet = player.getPetList().get(i);
			if (pet != null && pet.isShow()) {
				if (flag) {
					pet.setMap(player.getMap());
					pet.setMapModelId(player.getMapModelId());
					pet.setLine(player.getLine());
					pet.setServerId(player.getServerId());
					log.error("角色[" + player.getId() + "]美人[" + pet.getId() + "]操作[player enter map]");
					enterMap(pet);
					flag = false;
				}
				else {
					log.error("角色[" + player.getId() + "]美人[" + pet.getId() + "]操作[fatal 2 show,enter map]");
					pet.setShow(false);
				}
			}
		}


		if (other_msg.getRoleId().size() > 0) {
			MessageUtil.send_to_gate(map.getSendId(), other_msg);
		}

		if (player.getAutohorse() == 1) {//跨地图的时候检测是否需要上坐骑
			player.setAutohorse((byte) 0);
			Horse horse = ManagerPool.horseManager.getHorse(player);
			ManagerPool.horseManager.stChangeRidingState(player, (byte) 1, horse.getCurlayer());
		}
		//进入攻城战
		if ( ManagerPool.countryManager.getSiegestate() == 1 && map.getMapModelid() == ManagerPool.countryManager.SIEGE_MAPID ) {
			ManagerPool.countryManager.stcountryWarInfo(player,false);
		}
		
		long end=System.currentTimeMillis();
		log.error("玩家(" + player.getId() + ")进入地图(" + player.getMapModelId() + ")第二步耗时:" + (end - start));
	}

	public void enterMap(Pet pet) {
//		if (pet.isShow()) {			
//			log.debug("宠物以经在场景中");
//			return;
//		}

		Player player = PlayerManager.getInstance().getPlayer(pet.getOwnerId());
		if (player == null) {
			log.debug(pet.getOwnerId() + "宠物身上的主人ID找不到对应的角色");
			return;
		}
		Map map = getMap(player);
		if (map == null) {
			log.debug("地图" + player.getMap() + "找不到");
			return;
		}

		Position randRoundPoint = MapUtils.getBackPoint(player.getPosition(), player.getDirection(), map.getMapModelid());
		if (randRoundPoint == null) {
			log.debug("宠物找不到落 点");
			return;
		}
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		Area area = getArea(randRoundPoint, map);
//		pet.changeStateTo(PetState.IDEL);
		PetOptManager.getInstance().changeAttackTarget(pet, null, PetOptManager.FIGHTTYPE_PET_IDEL);
		pet.setServerId(player.getServerId());
		pet.setLine(player.getLine());
		pet.setMap(player.getMap());
		pet.setMapModelId(player.getMapModelId());
		
		ManagerPool.petOptManager.setPetPosition(pet, randRoundPoint);
		pet.setJumpState(PetJumpState.NOMAL);
		
		map.getPets().put(pet.getId(), pet);
		area.getPets().add(pet);
		log.error("pet " + pet.getId() + "(" + pet.getModelId() + ") owner " + player.getId() + " enter map " + map.getMapModelid() + " line " + player.getLine() + " area " + area);
		
		ResRoundPetMessage msg = new ResRoundPetMessage();
		msg.setPet(getPetInfo(pet, grids));
		
		log.error("角色[" + player.getId() + "]美人[" + pet.getId() + "]操作[pet enter map]");
		pet.setShow(true);
		log.debug("宠物进入场景");
		MessageUtil.tell_round_message(pet, msg);
	}

	public void enterMap(MapDropInfo info) {
		Position buildPosition = MapUtils.buildPosition((short) info.getDropinfo().getX(), (short) info.getDropinfo().getY());
		int current = MapManager.getInstance().getAreaId(buildPosition);
		Map map = info.getMapIns();
		Area area = map.getAreas().get(current);
		area.getDropGoods().put(info.getItem().getId(), info);
		ResRoundGoodsMessage msg = new ResRoundGoodsMessage();
		msg.setGoods(info.getDropinfo());
		MessageUtil.tell_round_message(info, msg);
	}

	public void enterMap(NPC npc) {
		int current = MapManager.getInstance().getAreaId(npc.getPosition());
		Map map = MapManager.getInstance().getMap(npc);
		Area area = map.getAreas().get(current);
		area.getNpcs().put(npc.getId(), npc);
		ResRoundNpcMessage msg = new ResRoundNpcMessage();
		msg.setNpc(getNpcInfo(npc));
		MessageUtil.tell_round_message(npc, msg);
	}

	public void enterMap(Effect effect) {
		Position buildPosition = effect.getPosition();
		int current = MapManager.getInstance().getAreaId(buildPosition);
		Map map = MapManager.getInstance().getMap(effect.getServerId(), effect.getLine(), effect.getMap());
		Area area = map.getAreas().get(current);
		area.getEffects().put(effect.getId(), effect);
		ResRoundEffectMessage msg = new ResRoundEffectMessage();
		msg.setEffect(getEffectInfo(effect));
		MessageUtil.tell_round_message(effect, msg);
	}

	/**
	 * 怪物进入地图
	 *
	 * @param monster
	 */
	public void enterMap(Monster monster) {
		// 广播到世界服
		Q_monsterBean monsterBean = ManagerPool.dataManager.q_monsterContainer.getMap().get(monster.getModelId());
		if (monsterBean.getQ_info_sync() > 0) {
			ReqMonsterSyncMessage syncmsg = new ReqMonsterSyncMessage();
			syncmsg.setModelId(monster.getModelId());
			syncmsg.setMonsterId(monster.getLine());
			syncmsg.setServerId(monster.getServerId());
			syncmsg.setMonsterId(monster.getId());
			syncmsg.setCurrentHp(monster.getHp());
			syncmsg.setMaxHp(monster.getMaxHp());
			syncmsg.setState(1);
			syncmsg.setBirthX(monster.getBirthPos().getX());
			syncmsg.setBirthY(monster.getBirthPos().getY());
			MessageUtil.send_to_world(syncmsg);
		}
		Map map = getMap(monster);
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());

		Area area = getArea(monster.getPosition(), map);

		monsterlog.error("Monster " + monster.getId() + "[" + monster.getModelId() + "] enter map " + map.getId() + " [" + map.getMapModelid() + ":"+ map.getLineId() +"] " + " area " + area.getId() + " position " + monster.getPosition() + "!");
		area.getMonsters().put(monster.getId(), monster);
		map.getMonsters().put(monster.getId(), monster);
		ResRoundMonsterMessage msg = new ResRoundMonsterMessage();
		msg.setMonster(getMonsterInfo(monster, grids));
		MessageUtil.tell_round_message(monster, msg);
	}

	/**
	 * 退出地图
	 *
	 * @param roleId
	 */
	public void quitMap(Player player) {
		// 获得所在地图
		Map map = getMap(player.getServerId(), player.getLine(),
			player.getMap());
		if (map == null) {
			log.error("map " + player.getMap() + " not found!");
			return;
		}
		IQuitMapScript script = (IQuitMapScript) ManagerPool.scriptManager.getScript(ScriptEnum.QUIT_MAP);
		if (script != null) {
			try {
				script.onQuitMap(player, map);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("退出地图脚本不存在！");
		}
		// 停止移动
		playerStopRun(player);

		// 获得所在区域
		int areaId = getAreaId(player.getPosition());
		Area area = map.getAreas().get(areaId);
		// 移除玩家
		if (area.getPlayers().contains(player)) {
			area.getPlayers().remove(player);
			log.debug("Area " + area.getId() + " remove " + player.getId());
		} else {
			log.error("Area " + area.getId() + " not contains player "
				+ player.getId());
			Iterator<Area> iter = map.getAreas().values().iterator();
			while (iter.hasNext()) {
				Area area2 = (Area) iter.next();
				if (area2.getPlayers().contains(player)) {
					log.error("changearea area " + area2.getId() + " contains player "
						+ player.getId());
					area2.getPlayers().remove(player);
				}
			}
		}
		
		map.getPlayers().remove(player.getId());

		log.error("player " + player.getId() + " " + player.getName() + " quitmap line "
			+ player.getLine() + " map " + map.getId() + " area "
			+ area.getId() + "position " + player.getPosition() + " players " + map.getPlayerNumber());

		// 通知周围玩家
		ResRoundPlayerDisappearMessage other_msg = new ResRoundPlayerDisappearMessage();
		other_msg.getPlayerIds().add(player.getId());

		List<Area> rounds = getRoundAreas(map, areaId);
		for (int i = 0; i < rounds.size(); i++) {
			area = rounds.get(i);
			Iterator<Player> iter = area.getPlayers().iterator();
			while (iter.hasNext()) {
				Player other = (Player) iter.next();
				if (other.getId() == player.getId()) {
					continue;
				}

				MessageUtil.tell_player_message(other, other_msg);
			}
		}
		//宠物随主人移出地图
		for (int i = 0; i < player.getPetList().size(); i++) {
			Pet pet = player.getPetList().get(i);
			if (pet != null && pet.isShow()) {
				quitMap(pet);
				pet.setLine(0);
			}
		}
		// 清除怪物仇恨
		ManagerPool.monsterManager.cleanHatred(map, player);
	}

	public void quitMap(Pet pet) {
		if (pet.isShow()) {
			petStopRun(pet);
			// 停止移动
			pet.setDest(null);
			
			//停止攻击
			PetOptManager.getInstance().changeAttackTarget(pet, null, PetOptManager.FIGHTTYPE_PET_IDEL);
			
			pet.setJumpState(PetJumpState.NOMAL);
			// 获得所在地图
			Map map = getMap(pet.getServerId(), pet.getLine(), pet.getMap());
			if (map == null) {
				log.error("map " + pet.getMap() + " not found!");
				return;
			}
			
			map.getPets().remove(pet.getId());
			// 获得所在区域
			int areaId = getAreaId(pet.getPosition());
			Area area = map.getAreas().get(areaId);
			Area removeArea = null;
			//移出宠物
			if (area.getPets().contains(pet)) {
				area.getPets().remove(pet);
				removeArea = area;
//				pet.setShow(false);
				log.debug("pet " + pet.getId() + " " + pet.getName() + " quitmap line " + pet.getLine() + " map " + map.getId() + " area " + area.getId()
					+ "position " + pet.getPosition());
			}else{
				log.error("changearea area " + area.getId() + " not contains pet "
						+ pet.getMapModelId());
				Iterator<Area> iter = map.getAreas().values().iterator();
				while (iter.hasNext()) {
					Area area2 = (Area) iter.next();
					if (area2.getPets().contains(pet)) {
						log.error("changearea area " + area2.getId() + " contains pet "
							+ pet.getMapModelId());
						area2.getPets().remove(pet);
						removeArea = area2;
					}
				}
			}
			
			try{
				Player player = PlayerManager.getInstance().getPlayer(pet.getOwnerId());
				log.error("pet " + pet.getId() + "(" + pet.getModelId() + ") owner " + player.getId() + " out map " + map.getMapModelid() + " line " + player.getLine() + " area " + removeArea);
			}catch (Exception e) {
				log.error(e, e);
			}
			// 通知周围玩家
			ResRoundPetDisappearMessage other_msg = new ResRoundPetDisappearMessage();
			other_msg.getPetIds().add(pet.getId());
			MessageUtil.tell_round_message(pet, other_msg);

		}
	}

	public void quitMap(NPC npc) {
		Map map = getMap(npc);
		if (map == null) {
			log.error("map " + npc.getMap() + " not found!" + "serverid=" + npc.getServerId() + "line" + npc.getLine() + "modelid" + npc.getMapModelId());
			return;
		}
		Area area = getArea(npc.getPosition(), map);
		if (area.getNpcs().containsKey(npc.getId())) {
			area.getNpcs().remove(npc.getId());
			ResRoundNpcDisappearMessage disappear = new ResRoundNpcDisappearMessage();
			disappear.getNpcids().add(npc.getId());
			MessageUtil.tell_round_message(npc, disappear);
		}
	}

	public void quitMap(Effect effect) {
		Map map = getMap(effect.getServerId(), effect.getLine(), effect.getMap());
		if (map == null) {
			log.error("map " + effect.getMap() + " not found!" + "serverid=" + effect.getServerId() + "line" + effect.getLine() + "modelid" + effect.getMapmodelid());
			return;
		}
		Position position = effect.getPosition();
		Area area = getArea(position, map);
		if (area.getEffects().containsKey(effect.getId())) {
			area.getEffects().remove(effect.getId());
			ResRoundEffectDisappearMessage dispmsg = new ResRoundEffectDisappearMessage();
			dispmsg.getEffectid().add(effect.getId());
			MessageUtil.tell_round_message(effect, dispmsg);
		}
	}

	public void quitMap(MapDropInfo mapDropInfo) {
		Position position = mapDropInfo.getPosition();
		if (position != null) {
			Area area = getArea(position, mapDropInfo.getMapIns());
			if (area != null) {
				if (area.getDropGoods().containsKey(mapDropInfo.getItem().getId())) {
					area.getDropGoods().remove(mapDropInfo.getItem().getId());
					ResRoundGoodsDisappearMessage msg = new ResRoundGoodsDisappearMessage();
					msg.getGoodsIds().add(mapDropInfo.getDropinfo().getDropGoodsId());
					MessageUtil.tell_round_message(mapDropInfo, msg);
				}
			}
		}
	}

	/**
	 * 按坐标获取区域编号
	 *
	 * @param position
	 * @return
	 */
	public int getAreaId(Position position) {
		int width = position.getX() / AREA_WIDTH;
		int height = position.getY() / AREA_HEIGHT;
		return width * 1000 + height;
	}
	private static int ROUND = 7;

	/**
	 * 获取周围区域编号
	 *
	 * @param areaId
	 * @return
	 */
	public List<Area> getRoundAreas(Map map, int areaId) {
		int x = areaId / 1000;
		int y = areaId % 1000;
		int round = map.getRound();
		if (round == 0) {
			round = ROUND;
		}
//		System.out.println("map " + map.getId() + " mapmodel " + map.getMapModelid() + " round " + round);
		int radius = round / 2;
		int startX = x - radius;
		int startY = y - radius;

		int width_num = map.getWidth();
		int height_num = map.getHeight();

		List<Area> areas = new ArrayList<Area>();
		for (int i = 0; i < round; i++) {
			int areaY = startY + i;
			if (areaY < 0 || areaY >= height_num) {
				continue;
			}
			for (int j = 0; j < round; j++) {
				int areaX = startX + j;
				if (areaX < 0 || areaX >= width_num) {
					continue;
				}
				
				int id = areaX * 1000 + areaY;
//				System.out.println(" Area " + areaId + " Round " + id);
				Area area = map.getAreas().get(id);
				if (area != null) {
					areas.add(area);
				}
			}
		}
		return areas;
	}

	/**
	 * 按度座标获得区域
	 *
	 * @param position
	 * @param map
	 * @return
	 */
	public Area getArea(Position position, Map map) {
		return map.getAreas().get(getAreaId(position));
	}

	/**
	 * 按格子获得区域
	 *
	 * @param grid
	 * @param map
	 * @return
	 */
	public Area getArea(Grid grid, Map map) {
		return map.getAreas().get(getAreaId(grid.getCenter()));
	}

	/**
	 * 按座标点获取周围区域
	 *
	 * @param position
	 * @param map
	 * @return
	 */
	public List<Area> getRound(Map map, Position position) {
		return getRoundAreas(map, getAreaId(position));
	}

	/**
	 * 获得半径内的区域Id列表
	 *
	 * @param position 中心点
	 * @param radius 半径
	 * @return
	 */
	public int[] getRoundAreas(Position position, Map map, int radius) {
		// 上边界
		short topPosition = (short) (position.getY() - radius);
		// 下边界
		short buttomPisition = (short) (position.getY() + radius);
		// 左边界
		short leftPosition = (short) (position.getX() - radius);
		// 右边界
		short rightPosition = (short) (position.getX() + radius);

		Q_mapBean mapInfo = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());

		if (topPosition < 0) {
			topPosition = 0;
		}

		if (buttomPisition >= mapInfo.getQ_map_height()) {
			buttomPisition = (short) (mapInfo.getQ_map_height() - 1);
		}

		if (leftPosition < 0) {
			leftPosition = 0;
		}

		if (rightPosition >= mapInfo.getQ_map_width()) {
			rightPosition = (short) (mapInfo.getQ_map_width() - 1);
		}
		int topArea = getAreaId(new Position(position.getX(), topPosition));
		int buttomArea = getAreaId(new Position(position.getX(), buttomPisition));
		int leftArea = getAreaId(new Position(leftPosition, position.getY()));
		int rightArea = getAreaId(new Position(rightPosition, position.getY()));

		int width = (rightArea - leftArea) / 1000 + 1;
		int height = buttomArea - topArea + 1;
		int left = leftArea / 1000;
		int top = topArea % 1000;

		int[] areas = new int[width * height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				areas[i * height + j] = (left + i) * 1000 + top + j;
			}

		}
		return areas;
	}

	/**
	 * 玩家移动
	 *
	 * @param roleId 玩家id
	 * @param positions 移动路径
	 */
	public void playerRunning(Player player, Position position, List<Byte> roads, long rtime) {
		long now = System.currentTimeMillis();
		double delay = now - rtime;

		log.error("player " + player.getId() + ",开始坐标:" + position + ",开始移动:" + roads);

		if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.RUN_STEP, null, 0)) {
			player.setLastPosition(position);
			player.setLastRoads(roads);
			return;
		} else {
			player.setLastPosition(null);
			player.setLastRoads(null);
		}
		
		log.error("player " + player.getId() + "可以移动,开始坐标:" + position + ",开始移动:" + roads);

		//100毫秒冷却
		ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.RUN_STEP, null, 200);

		//停止采集
		ManagerPool.npcManager.playerStopGather(player);

		// 路径合法检查
		if (position == null || roads == null) {
			return;
		}

		if (PlayerState.CHANGEMAP.compare(player.getState())) {
			return;
		}

		// 路径为空
		if (roads.size() == 0) {
			return;
		}

		if (player.isDie()) {
			log.debug("死亡中不能走路");
			// 清空当前移动路径
			broadcastPlayerForceStop(player);
			return;
		}

		// 跳跃中
		if (PlayerState.JUMP.compare(player.getState())
			|| PlayerState.DOUBLEJUMP.compare(player.getState())) {
			log.error("跳跃中不能走路");
			// 清空当前移动路径
			broadcastPlayerForceStop(player);
			return;
		}
		// 定身或睡眠中
		if (FighterState.DINGSHEN.compare(player.getFightState())
			|| FighterState.SHUIMIAN.compare(player.getFightState())) {
			log.debug("定身中不能走路");
			// 清空当前移动路径
			broadcastPlayerForceStop(player);
			return;
		}

		// 获取地图
		Map map = getMap(player);

		if (map == null) {
			return;
		}
		PlayerDaZuoManager.getInstacne().breakDaZuo(player);
		// 获取阻挡信息
		Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		// 判断起点是否可以站立
		Grid startGrid = MapUtils.getGrid(position, blocks);
		if (startGrid == null || startGrid.getBlock() == 0) {
			log.error("地图 " + player.getMapModelId() + " 起始点阻挡 " + position.getX() + ","
				+ position.getY() + "----->" + (startGrid == null ? "" : (startGrid.getX()
				+ "," + startGrid.getY())));
			// 清空当前移动路径
			resetPlayerPosition(player);
			return;
		}

		// 判断坐标差值
		double distance = MapUtils.countDistance(position, player.getPosition());
//		log.error("player " + player.getId() + " 移动起始点距离distance "
//				+ distance + ", now " + player.getPosition() + ", start "
//				+ position + ", delay" + delay);
		double allow_distance = (double) Global.DISTANCE * player.getSpeed() / Global.BASE_SPEED * (1 + delay / 1000);
		if (allow_distance < Global.DISTANCE) {
			allow_distance = Global.DISTANCE;
		}
		if(distance > 50){
			ManagerPool.playerManager.playercheck(player, PlayerCheckType.RUN_DISTANCE, distance);
		}
		if (distance > allow_distance) {
			log.error("player " + player.getId() + " 移动起始点距离过远, distance "
				+ distance + " allow_distance "
				+ allow_distance + ", now " + player.getPosition() + ", start "
				+ position + ", delay" + delay);
//			return;
			// 清空当前移动路径
			broadcastPlayerForceStop(player);
			return;
		}

		int dir = 4;
		// 跑步中拐点距离检查
		Grid prevGrid = startGrid;
		List<Position> positions = new ArrayList<Position>();
		for (int i = 0; i < roads.size(); i++) {
			byte b = roads.get(i);
			int direct = (b & 0xE0) >> 5;
			int step = b & 0x1F;
			// 确定人物朝向
			if (prevGrid.equal(startGrid)) {
				dir = direct;
			}
			int[] add = MapUtils.countDirectionAddtion(direct);
			for (int j = 0; j < step; j++) {
				Grid grid = MapUtils.getGrid(prevGrid.getX() + add[0], prevGrid.getY() + add[1], blocks);
				if (grid == null || grid.getBlock() == 0) {
					log.debug("地图 " + player.getMapModelId() + "路中阻挡点：" + grid.getX()
						+ "," + grid.getY());
					broadcastPlayerForceStop(player);
					return;
				}

				if (grid.getBlock() != prevGrid.getBlock()) {
					log.debug("路中不同区域点：" + grid.getX()
						+ "," + grid.getY());
					broadcastPlayerForceStop(player);
					return;
				}

				prevGrid = grid;
				positions.add(grid.getCenter());
			}
		}
		
//		log.error("player " + player.getId() + " 开始移动:" + MessageUtil.castListToString(positions));
		
		// 开始移动 执行作弊检查
		PlayerManager.getInstance().playercheck(player, 0, 0.0);
		
		// 清空当前移动路径
		player.getRoads().clear();


		// 设置移动起点
		Position old = player.getPosition();
		player.setDirection((byte) dir);

		// 设置移动路径
		player.setRoads(positions);

		setPlayerPosition(player, startGrid.getCenter());
		
		//移除跳跃保护
		player.setJumpProtect(false);
//		player.setPosition(startGrid.getCenter());
		//player.setState(PlayerState.RUN);
		/**
		 * 计算区域 *
		 */
		// 原来所在区域
		int oldAreaId = ManagerPool.mapManager.getAreaId(old);
		// 现在所在区域
		int newAreaId = ManagerPool.mapManager.getAreaId(player.getPosition());
		// 区域未变
		if (oldAreaId != newAreaId) {
			playerChangeArea(player, oldAreaId, newAreaId);
		}

		player.setPrevStep(rtime);
		player.setCost(0);
		ManagerPool.cooldownManager.removeCooldown(player, CooldownTypes.RUN, null);

//		ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.STEP, null, MIN_STEP_TIME);

		// 开始移动
		HashMap<Long, Player> runnings = map.getRunningPlayers();
		if (!runnings.containsKey(player.getId())) {
			runnings.put(player.getId(), player);
		}

		ResRunPositionsMessage other_msg = new ResRunPositionsMessage();
		other_msg.setPersonId(player.getId());
		other_msg.setPosition(player.getPosition());
		other_msg.setPositions(roads);

		MessageUtil.tell_round_message(player, other_msg);

		syncPlayerPosition(player);
		if (player.getTeamid() > 0) {
			ManagerPool.teamManager.showMapTeamMember(player);
		}
	}

	public void resetPlayerPosition(Player player) {
		//停止移动
		player.getRoads().clear();
		// 清除待移动路径
		player.setLastRoads(null);
		//广播拉回消息
		ResSetPositionMessage msg = new ResSetPositionMessage();
		msg.setPersonId(player.getId());
		msg.setPosition(player.getPosition());
		MessageUtil.tell_round_message(player, msg);
	}
	
	public void resetPlayerJumpPosition(Player player) {
		//停止移动
		player.getRoads().clear();
		// 清除待移动路径
		player.setLastRoads(null);
		// 人物停止消息
		ResJumpSetPositionMessage other_msg = new ResJumpSetPositionMessage();
		other_msg.setPersonId(player.getId());
		other_msg.setPosition(player.getPosition());

		MessageUtil.tell_player_message(player, other_msg);
	}

	public void syncPlayerPosition(Player player) {
//		ReqSyncPlayerPositionMessage syncmsg = new ReqSyncPlayerPositionMessage();
//		syncmsg.setPlayerId(player.getId());
//		syncmsg.setX(player.getPosition().getX());
//		syncmsg.setY(player.getPosition().getY());
//		MessageUtil.send_to_world(syncmsg);
	}

	/**
	 * 玩家停止移动
	 *
	 * @param roleId 玩家id
	 */
	public void playerStopRun(Player player) {
		// 跳跃中
		if (PlayerState.JUMP.compare(player.getState())
			|| PlayerState.DOUBLEJUMP.compare(player.getState())) {
			return;
		}
		// 清除路径
		player.getRoads().clear();
		// 清除待移动路径
		player.setLastRoads(null);
		if (PlayerState.RUN.compare(player.getState()) || PlayerState.SWIM.compare(player.getState())) {
//			// 清除路径
//			player.getRoads().clear();
//			// 清除待移动路径
//			player.setLastRoads(null);
			//站立状态
			setPlayerPosition(player, player.getPosition());
			// 人物停止消息
			broadcastPlayerForceStop(player);
		}
	}

	/**
	 * 玩家停止移动
	 *
	 * @param roleId 玩家id
	 */
	public void playerStopRun(Player player, Position position) {
//		log.error("请求停止");
		// 跳跃中
		if (PlayerState.JUMP.compare(player.getState())
			|| PlayerState.DOUBLEJUMP.compare(player.getState())) {
			log.debug("跳跃状态");
			return;
		}

		// 获取地图
		Map map = getMap(player);
		if (map == null) {
			log.debug("地图不存在");
			return;
		}
		// 获取阻挡信息
		Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());

		// 判断起点是否可以站立
		Grid startGrid = MapUtils.getGrid(position, blocks);
		if (startGrid == null || startGrid.getBlock() == 0) {
			log.debug("起始点阻挡");
			return;
		}

		// 判断坐标差值
		double distance = MapUtils.countDistance(position, player.getPosition());
		double allow_distance = (double) Global.DISTANCE * player.getSpeed() / Global.BASE_SPEED;
		if (allow_distance < Global.DISTANCE) {
			allow_distance = Global.DISTANCE;
		}
		if (distance > allow_distance) {
			log.error("player " + player.getId() + " 移动起始点距离过远, distance "
				+ distance + " allow_distance "
				+ allow_distance + ", now " + player.getPosition() + ", stop "
				+ position);

			// 清空当前移动路径
			resetPlayerPosition(player);
			return;
		}
//		log.debug("停止走动");
		Position old = player.getPosition();
		// 停止到格子中间
		player.getRoads().clear();
		// 清除待移动路径
		player.setLastRoads(null);
		//站立状态
		//player.setState(PlayerState.STAND);
		Grid grid = MapUtils.getGrid(position, blocks);
//		player.setPosition(grid.getCenter());
		setPlayerPosition(player, grid.getCenter());

		/**
		 * 计算区域 *
		 */
		// 原来所在区域
		int oldAreaId = ManagerPool.mapManager.getAreaId(old);
		// 现在所在区域
		int newAreaId = ManagerPool.mapManager.getAreaId(player.getPosition());
		// 区域未变
		if (oldAreaId != newAreaId) {
			ManagerPool.mapManager.playerChangeArea(player, oldAreaId, newAreaId);
		}
		log.debug("广播停止");
		broadcastPlayerForceStop(player);
	}

	public void broadcastPlayerForceStop(Player player) {
		//停止移动
		player.getRoads().clear();
		// 人物停止消息
		ResPlayerStopMessage other_msg = new ResPlayerStopMessage();
		other_msg.setPersonId(player.getId());
		other_msg.setPosition(player.getPosition());

		MessageUtil.tell_round_message(player, other_msg);
		if (player.getTeamid() > 0) {
			ManagerPool.teamManager.showMapTeamMember(player);
		}
	}
	
	public void broadcastPlayerStop(Player player) {
		// 人物停止消息
		ResPlayerRunEndMessage other_msg = new ResPlayerRunEndMessage();
		other_msg.setPersonId(player.getId());
		other_msg.setPosition(player.getPosition());

		MessageUtil.tell_round_message(player, other_msg);
		if (player.getTeamid() > 0) {
			ManagerPool.teamManager.showMapTeamMember(player);
		}
	}

	/**
	 * 怪物移动
	 *
	 * @param monsterId 怪物id
	 * @param positions 移动路径
	 */
	public void monsterRunning(Monster monster, List<Position> positions) {
//		System.out.println(monster.getId() + " run " + MessageUtil.castListToString(positions));
		// 获取怪物
		if (monster == null) {
			return;
		}

		// 路径合法检查
		if (positions == null) {
			return;
		}
		// 路径为空
		if (positions.size() == 0) {
			return;
		}
		// 获取地图
		Map map = getMap(monster.getServerId(), monster.getLine(),
			monster.getMap());
		if(map==null){
			return;
		}
		// 获取地图格子
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		// 清空当前移动路径
		monster.getRoads().clear();
		// 取起点坐标
		Position start = positions.remove(0);

		// 设置移动起点
		monster.setPosition(start);

		// 确定方向
		if (positions.size() > 0) {
			monster.setDirection((byte) MapUtils.countDirection(
				MapUtils.getGrid(monster.getPosition(), grids),
				MapUtils.getGrid(positions.get(0), grids)));
		}

		// 设置移动路径
		monster.setRoads(positions);

		if (!ManagerPool.cooldownManager.isCooldowning(monster, CooldownTypes.MONSTER_RUN, null)) {
			monster.setPrevStep(System.currentTimeMillis());
			monster.setCost(0);
		}

		// 开始移动
		HashMap<Long, Monster> runnings = map.getRunningMonsters();
		if (!runnings.containsKey(monster.getId())) {
			runnings.put(monster.getId(), monster);
		}

		ResMonsterRunPositionsMessage other_msg = new ResMonsterRunPositionsMessage();
		other_msg.setMonsterId(monster.getId());
		other_msg.setPosition(monster.getPosition());
		other_msg.setPositions(castPositionToByte(monster.getPosition(), monster.getRoads(), grids));

		MessageUtil.tell_round_message(monster, other_msg);
	}

	/**
	 * 怪物停止移动
	 *
	 * @param roleId 玩家id
	 */
	public void monsterStopRun(Monster monster) {
		
//		System.out.println(monster.getId() + " stop " + monster.getPosition());
		
		if (monster.getRoads().size() > 0) {
			// 清除路径
			monster.getRoads().clear();

			// 广播怪物停止消息
			ResMonsterStopMessage other_msg = new ResMonsterStopMessage();
			other_msg.setMonsterId(monster.getId());
			other_msg.setPosition(monster.getPosition());

			MessageUtil.tell_round_message(monster, other_msg);
		}
	}

//	private long preJump = 0;
	/**
	 * 玩家跳跃
	 *
	 * @param roleId 玩家Id
	 * @param positions 跳跃路径
	 */
	public void playerJump(Player player, List<Position> positions, int type) {
		// 路径合法检查
		if (positions == null) {
			return;
		}
		// 路径非法
		if (positions.size() != 2) {
			return;
		}
		
		if (PlayerState.CHANGEMAP.compare(player.getState())) {
			return;
		}
		
		IPlayerJumpScript script = (IPlayerJumpScript) ManagerPool.scriptManager.getScript(ScriptEnum.PLAYER_JUMP);
		if (script != null) {
			try {
				if(!script.onJump(player, positions, type)){
					return;
				}
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("地图创建脚本不存在！");
		}

		//log.error("玩家:" + player.getName() + "(" + player.getId() + ")跳跃" + MessageUtil.castListToString(positions));
		//停止采集
		ManagerPool.npcManager.playerStopGather(player);
				
		// 死亡中
		if (player.isDie()) {
			log.debug("玩家死亡");
			resetPlayerJumpPosition(player);
			return;
		}

		// 定身或睡眠中
		if (FighterState.DINGSHEN.compare(player.getFightState())
			|| FighterState.SHUIMIAN.compare(player.getFightState())) {
			log.debug("定身中不能跳跃");
			resetPlayerJumpPosition(player);
			return;
		}
		
//		if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.JUMP_COOLDOWN, null, 0)) {
//			log.error("玩家(" + player.getId() + ")跳跃冷却中");
//			resetPlayerJumpPosition(player);
//			return;
//		}

//		// 游泳中
//		if (PlayerState.SWIM.compare(player.getState())) {
//			log.debug("已经游泳中了");
//			MessageUtil.notify_player(player, Notifys.ERROR, "游泳区内无法跳跃");
//			return;
//		}
		
//		//二次跳跃不检查冷却
//		if (!PlayerState.JUMP.compare(player.getState())) {
//			
////			log.error("当前时间:" + System.currentTimeMillis() + "跳跃剩余时间:" + ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.JUMP, null));
//			
//			// 跳跃冷却
//			if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.JUMP, null, 0)) {
//				log.debug("玩家(" + player.getId() + ")在跳跃冷却中了");
//				ManagerPool.playerManager.playercheck(player, PlayerCheckType.JUMP_COOLDOWN, ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.JUMP, null));
//				resetPlayerJumpPosition(player);
//				return;
//				//return;
//			}
//			
//			if(preJump!=0){
////				MessageUtil.notify_player(player, Notifys.CHAT_BULL, "跳跃间隔时间{1}毫秒", String.valueOf(System.currentTimeMillis() - preJump));
////				log.error("玩家(" + player.getId() + ")跳跃间隔时间" + String.valueOf(System.currentTimeMillis() - preJump) + "毫秒");
//				preJump = System.currentTimeMillis();
//			}else{
//				preJump = System.currentTimeMillis();
//			}
//		}
		
		// 体力检查
		int cost = 0;
		// 不消耗体力跳跃
		if (!FighterState.TIAOYUEBUJIANSHAOTILI.compare(player.getFightState())) {
			// 计算体力消耗
			if (type == 2) {
				cost = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SECOND_JUMP_RESUM.getValue()).getQ_int_value();
			} else {
				cost = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.FIRST_JUMP_RESUM.getValue()).getQ_int_value();
			}
		}
		// 体力不足
		if (player.getSp() < cost) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉、体力不足无法使用轻功"));
			resetPlayerJumpPosition(player);
			return;
		}

		// 获取地图
		Map map = getMap(player.getServerId(), player.getLine(), player.getMap());
		if (map == null) {
			return;
		}

		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
		if (mapBean.getQ_map_jump() == 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，本地图禁止轻功跳跃"));
			resetPlayerJumpPosition(player);
			return;
		}
		// 获取阻挡信息
		Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		// 取起点坐标
		Position start = positions.get(0);

		// 判断起点是否可以站立
		Grid startGrid = MapUtils.getGrid(start, blocks);
//		if (!PlayerState.JUMP.compare(player.getState())
//			&& (startGrid == null || startGrid.getBlock() == 0)) {
//			log.debug("开始点是阻挡点");
//			return;
//		}
		
		//long fcost = 0;
		
		// 取得结束点
		Position end = positions.get(1);
		// 判断结束点是否可以站立
		Grid endGrid = MapUtils.getGrid(end, blocks);
		if (endGrid == null || endGrid.getBlock() == 0) {
			log.debug("结束点是阻挡点");
			resetPlayerJumpPosition(player);
			return;
		}
//		log.error("startGrid="+startGrid.getX() + ","+ startGrid.getY());
//		log.error("endGrid  ="+endGrid.getX() + ","+ endGrid.getY());
		
		//检测路径中间是否有禁止跳跃的障碍
		if (!canJumpPath(startGrid,endGrid,map.getMapModelid())) {
			log.debug("路径中间否有禁止跳跃的障碍");
			return;
		}
		
		// 跳跃距离检查
		double distance = MapUtils.countDistance(start, end);
		// 计算跳跃最大距离
		if (distance > Global.MAX_JUMPDISTANCE + 20) {
			log.debug("超过跳跃最大距离");
			resetPlayerJumpPosition(player);
			return;
		}
		
		log.debug("跳跃开始!" + System.currentTimeMillis());
		
		PlayerDaZuoManager.getInstacne().breakDaZuo(player);
		// 扣除体力
		if (cost > 0) {
			player.setSp(player.getSp() - cost);
			ManagerPool.playerManager.onSpChange(player);
		}

		// 清空当前移动路径
		player.getRoads().clear();

		// 设置玩家坐标
		Position old = player.getPosition();
		player.setPosition(endGrid.getCenter());
		player.setDirection((byte) MapUtils.countDirection(start, end));
		
//		if (!isSwimGrid(endGrid)) {
//			if (PlayerState.SWIM.compare(player.getState())) {
//				//移除游泳buff
//				PlayerDaZuoManager.getInstacne().breakDaZuo(player);
//				ManagerPool.buffManager.removeByBuffId(player, Global.BUFF_FOR_SWIM);
//				log.debug("退出游泳状态");
//			}
//		}
		
		// 设置起跳信息
		Jump jump = player.getJump();
		// 设置起跳点
		jump.setJumpStart(start);
		// 设置结束点
		jump.setJumpEnd(end);
		// 设置起跳时间
		jump.setJumpStartTime(System.currentTimeMillis());
		
		int speed = (int)((double)player.getSpeed() * Global.JUMP_SPEED
				/ Global.MAX_PROBABILITY);
		
		// 是否超过当前最大跳跃速度
		int jumpmaxspeed = DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.JUMP_MAX_SPEED.getValue()).getQ_int_value(); //最大跳跃速度
		if(speed > jumpmaxspeed) speed = jumpmaxspeed;
		
		log.debug("跳跃数据!距离：" + distance + ",速度:" + speed);
		// 设置起跳速度
		jump.setSpeed(speed);
		// 设置跳跃总时间
		int time = (int) (distance * 1000 / jump.getSpeed());

		if (time == 0) {
			time = Global.MIN_JUMPTIME;
		}
		// 跳跃总时间
		jump.setTotalTime(time);

		if (type==2) {
			// log.debug(me.getId() + "开始二次跳跃");
			player.setState(PlayerState.DOUBLEJUMP);
//			long allcost = System.currentTimeMillis() - preJump + time;
//			MessageUtil.notify_player(player, Notifys.CHAT_BULL, "二次跳跃一共耗时{1}毫秒", String.valueOf(allcost));
		} else {
			// log.debug(me.getId() + "开始一次跳跃");
			player.setState(PlayerState.JUMP);
		}
		
		//未在跳跃保护冷却中
		if (!ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.JUMP_COOLDOWN, null, 0)) {
			//增加跳跃保护
			player.setJumpProtect(true);
		}
		

//		log.error("当前时间:" + System.currentTimeMillis() + ", 一跳耗费时间:" + fcost + ", 时间跳跃时间:" + time);
//		MessageUtil.notify_player(player, Notifys.CHAT_BULL, "跳跃时间{1}毫秒", String.valueOf(time));
		
		ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.JUMP, null, time);
		
//		//跳跃冷却
//		int cooltime = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.JUMP_COOLDOWN.getValue()).getQ_int_value();
//		ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.JUMP_COOLDOWN, null, cooltime);

		//跳跃触发的BUFF
		List<Buff> buffs = ManagerPool.buffManager.getBuffTriggerByType(player, TriggerType.JUMP);
		for (int i = 0; i < buffs.size(); i++) {
			Buff buff = buffs.get(i);
			buff.action(player, player);
		}

		/**
		 * 计算区域 *
		 */
		// 原来所在区域
		int oldAreaId = ManagerPool.mapManager.getAreaId(old);
		// 起跳所在区域
		int startAreaId = ManagerPool.mapManager.getAreaId(start);
		// 结束所在区域
		int endAreaId = ManagerPool.mapManager.getAreaId(player.getPosition());
		// 站立区域改变
		if (oldAreaId != startAreaId) {
			playerChangeArea(player, oldAreaId, startAreaId);
		}
		// 跳跃区域改变
		if (startAreaId != endAreaId) {
			Area startArea = map.getAreas().get(startAreaId);
			if (startArea == null) {
				log.debug("start area " + startAreaId + " is null, player "
					+ player.getId() + " position" + player.getPosition());
			}
			if (!startArea.getPlayers().contains(player)) {
				log.debug("changearea area " + startArea.getId() + " not contains "
					+ player.getId());
			}
			startArea.getPlayers().remove(player);
			Area newArea = map.getAreas().get(endAreaId);
			if (newArea == null) {
				log.debug("new area " + endAreaId + " is null, player "
					+ player.getId() + " position" + player.getPosition());
			}
			newArea.getPlayers().add(player);
		}

		ManagerPool.playerManager.savePlayer(player);

		// 开始移动
		HashMap<Long, Player> runnings = map.getRunningPlayers();
		if (!runnings.containsKey(player.getId())) {
			runnings.put(player.getId(), player);
		}

		// 跳跃信息
		ResPlayerJumpPositionsMessage other_msg = new ResPlayerJumpPositionsMessage();
		other_msg.setPersonId(player.getId());
		other_msg.setState((byte) (PlayerState.DOUBLEJUMP.compare(player.getState()) ? 2 : 1));
		other_msg.setTime(player.getJump().getTotalTime());
		other_msg.setPositions(positions);

		//对起点区域和落点区域附近广播
		List<Area> startRounds = getRoundAreas(map, startAreaId);
		HashSet<Area> round = new HashSet<Area>();
		for (int i = 0; i < startRounds.size(); i++) {
			round.add(startRounds.get(i));
		}
		List<Area> endRounds = getRoundAreas(map, endAreaId);
		for (int i = 0; i < endRounds.size(); i++) {
			round.add(endRounds.get(i));
		}

		Iterator<Area> iter = round.iterator();
		while (iter.hasNext()) {
			Area area = (Area) iter.next();
			Iterator<Player> iterPlayer = area.getPlayers().iterator();
			while (iterPlayer.hasNext()) {
				Player other = (Player) iterPlayer.next();
				other_msg.getRoleId().add(other.getId());
			}
		}

		MessageUtil.send_to_gate(map.getSendId(), other_msg);

		if (startAreaId != endAreaId) {
			playerChangeArea(player, startAreaId, endAreaId);
		}

		if (player.getTeamid() > 0) {
			ManagerPool.teamManager.showMapTeamMember(player);
		}
	}

	/**
	 * 玩家格挡
	 *
	 * @param roleId 玩家Id
	 */
	public void playerBlock(Player me) {
		// 获取地图
		Map map = getMap(me.getServerId(), me.getLine(), me.getMap());
		// 跳跃中不可以格挡
		if (PlayerState.JUMP.compare(me.getState())
			|| PlayerState.DOUBLEJUMP.compare(me.getState())) {
			return;
		}
		// 格挡中不可以格挡
		if (PlayerState.BLOCKPREPARE.compare(me.getState()) || PlayerState.BLOCK.compare(me.getState())) {
			return;
		}
		// 定身或睡眠中
		if (FighterState.DINGSHEN.compare(me.getFightState())
			|| FighterState.SHUIMIAN.compare(me.getFightState())) {
			return;
		}

		//停止采集
		ManagerPool.npcManager.playerStopGather(me);
				
		// 游泳中
		if (PlayerState.SWIM.compare(me.getState())) {
			log.debug("攻击者（玩家）游泳中了");
			MessageUtil.notify_player(me, Notifys.ERROR, ResManager.getInstance().getString("游泳区内无法使用技能"));
			return;
		}

		// 内力判断
		int cost = 0;
		int percent = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SHIELD_RESUM_PS.getValue()).getQ_int_value();
		// 不消耗内力格挡
		if (!FighterState.GEDANGBUJIANSHAONEILI.compare(me.getFightState())) {
			cost = me.getMaxMp() * percent / Global.MAX_PROBABILITY;
		}

		if (me.getMp() < cost) {
			MessageUtil.notify_player(me, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，内力不足无法使用内力盾"));
			return;
		}
		PlayerDaZuoManager.getInstacne().breakDaZuo(me);

//		if (cost > 0) {
//			me.setMp(me.getMp() - cost);
//			ManagerPool.playerManager.onMpChange(me);
//		}

		log.debug("玩家" + me.getId() + "开始格挡");

		// 设置格挡
		me.setState(PlayerState.BLOCKPREPARE);
		ManagerPool.playerManager.savePlayer(me);

		// 增加开始CD
		ManagerPool.cooldownManager.addCooldown(me, CooldownTypes.BLOCK, null,
			Global.BLOCK_START);

		// 开始格挡
		HashMap<Long, Player> blocking = map.getBlockingPlayers();
		if (!blocking.containsKey(me.getId())) {
			blocking.put(me.getId(), me);
		}

		// 广播开始格挡消息
		ResStartBlockMessage msg = new ResStartBlockMessage();
		msg.setPersonId(me.getId());
		MessageUtil.tell_round_message(me, msg);
	}

	/**
	 * 玩家格挡
	 *
	 * @param roleId 玩家Id
	 */
	public void playerStopBlock(Player player) {
		if (PlayerState.BLOCK.compare(player.getState()) || PlayerState.BLOCKPREPARE.compare(player.getState())) {
			setPlayerPosition(player, player.getPosition());
			log.debug("玩家" + player.getId() + "结束格挡");

			// 广播结束格挡消息
			ResStopBlockMessage msg = new ResStopBlockMessage();
			msg.setPersonId(player.getId());
			MessageUtil.tell_round_message(player, msg);
		}
	}

	/**
	 * 玩家切换区域
	 *
	 * @param roleId
	 * @param oldAreaId
	 * @param newAreaId
	 */
	public void playerChangeArea(Player player, int oldAreaId, int newAreaId) {
		Map map = getMap(player);

		List<Area> oldRound = getRoundAreas(map, oldAreaId);
		List<Area> newRound = getRoundAreas(map, newAreaId);

		HashSet<Area> oldSet = new HashSet<Area>();
		for (int i = 0; i < oldRound.size(); i++) {
//			System.out.println("oldArea:" + oldRound.get(i).getId());
			oldSet.add(oldRound.get(i));
		}

		HashSet<Area> newSet = new HashSet<Area>();
		for (int i = 0; i < newRound.size(); i++) {
//			System.out.println("newArea" + newRound.get(i).getId());
			newSet.add(newRound.get(i));
		}

		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());

		Area oldArea = map.getAreas().get(oldAreaId);
		if (oldArea == null) {
			log.debug("old area " + oldAreaId + " is null, player "
				+ player.getId() + " position" + player.getPosition());
		}
		if (!oldArea.getPlayers().contains(player)) {
			log.debug("changearea area " + oldArea.getId() + " not contains "
				+ player.getId());
			Iterator<Area> iter = map.getAreas().values().iterator();
			while (iter.hasNext()) {
				Area area = (Area) iter.next();
				if (area.getPlayers().contains(player)) {
					log.debug("changearea area " + area.getId() + " contains "
						+ player.getId());
					area.getPlayers().remove(player);
				}
			}
		}
		oldArea.getPlayers().remove(player);
		Area newArea = map.getAreas().get(newAreaId);
		if (newArea == null) {
			log.debug("new area " + newAreaId + " is null, player "
				+ player.getId() + " position" + player.getPosition());
		}
		newArea.getPlayers().add(player);

//		 log.debug("player " + player.getId() + " changearea line " +
//		 player.getLine() + " map " + map.getId() + " oldarea " +
//		 oldArea.getId() + " newarea " + newArea.getId());

		ResRoundPlayerDisappearMessage hidemsg = new ResRoundPlayerDisappearMessage();
		hidemsg.getPlayerIds().add(player.getId());

		ResRoundObjectsMessage msg = new ResRoundObjectsMessage();

		for (int i = 0; i < oldRound.size(); i++) {
			Area area = oldRound.get(i);
			if (!newSet.contains(area)) {
				// log.debug("消失区块" + area.getId());
//				ResRoundPlayerDisappearMessage msg = new ResRoundPlayerDisappearMessage();
				Iterator<Player> iter = area.getPlayers().iterator();
				while (iter.hasNext()) {
					Player other = (Player) iter.next();
					if (other.getId() == player.getId()) {
						continue;
					}

					// log.debug("消失玩家" + other.getId());

					if (other.canSee(player)) {
						msg.getPlayerIds().add(other.getId());
					}
					if (player.canSee(other)) {
						hidemsg.getRoleId().add(other.getId());
					}
				}
//				if (msg.getPlayerIds().size() > 0) {
//					MessageUtil.tell_player_message(player, msg);
//				}

//				ResRoundMonsterDisappearMessage mdmsg = new ResRoundMonsterDisappearMessage();
				Iterator<Monster> monster_iter = area.getMonsters().values().iterator();
				while (monster_iter.hasNext()) {
					Monster monster = (Monster) monster_iter.next();
					if (monster.canSee(player)) {
						msg.getMonstersIds().add(monster.getId());
					}
					//log.error("player " + player.getId() + " --> monster" + monster.getId() + " x:" + (player.getPosition().getX() - monster.getPosition().getX()));
				}
//				if (mdmsg.getMonstersIds().size() > 0) {
//					MessageUtil.tell_player_message(player, mdmsg);
//				}

//				ResRoundGoodsDisappearMessage gdmsg = new ResRoundGoodsDisappearMessage();
				Iterator<MapDropInfo> iterator = area.getDropGoods().values().iterator();
				while (iterator.hasNext()) {
					MapDropInfo next = iterator.next();
					if (next.canSee(player)) {
						msg.getGoodsIds().add(next.getDropinfo().getDropGoodsId());
					}
				}
//				if (gdmsg.getGoodsIds().size() > 0) {
//					MessageUtil.tell_player_message(player, gdmsg);
//				}

//				ResRoundPetDisappearMessage pdmsg = new ResRoundPetDisappearMessage();
				HashSet<Pet> pets = area.getPets();
				for (Pet pet : pets) {
					if (pet.canSee(player)) {
						msg.getPetIds().add(pet.getId());
					}
				}
//				if (pdmsg.getPetIds().size() > 0) {
//					MessageUtil.tell_player_message(player, pdmsg);
//				}
				Iterator<NPC> npciterator = area.getNpcs().values().iterator();
				while (npciterator.hasNext()) {
					NPC npc = (NPC) npciterator.next();
					if (npc.canSee(player)) {
						msg.getNpcid().add(npc.getId());
					}
				}
				Iterator<Effect> Effectiterator = area.getEffects().values().iterator();
				while (Effectiterator.hasNext()) {
					Effect effect = (Effect) Effectiterator.next();
					if (effect.canSee(player)) {
						msg.getEffectid().add(effect.getId());
					}
				}
			}
		}

		if (hidemsg.getRoleId().size() > 0) {
			MessageUtil.send_to_gate(map.getSendId(), hidemsg);
		}

		ResRoundPlayerMessage showmsg = new ResRoundPlayerMessage();
		showmsg.setPlayer(getPlayerInfo(player, grids));

		for (int i = 0; i < newRound.size(); i++) {
			Area area = newRound.get(i);
			if (!oldSet.contains(area)) {
				Iterator<Player> iter = area.getPlayers().iterator();
				while (iter.hasNext()) {
					Player other = (Player) iter.next();
					if (other.getId() == player.getId()) {
						continue;
					}

					if (other.canSee(player)) {
						msg.getPlayer().add(getPlayerInfo(other, grids));
					}
//					ResRoundPlayerMessage msg = new ResRoundPlayerMessage();
//					msg.setPlayer(getPlayerInfo(other, grids));
//					MessageUtil.tell_player_message(player, msg);

					if (player.canSee(other)) {
						showmsg.getRoleId().add(other.getId());
					}
				}
				Iterator<Monster> monster_iter = area.getMonsters().values().iterator();
				while (monster_iter.hasNext()) {
					Monster monster = (Monster) monster_iter.next();

					if (monster.canSee(player)) {
						msg.getMonster().add(getMonsterInfo(monster, grids));
					}
//					ResRoundMonsterMessage msg = new ResRoundMonsterMessage();
//					msg.setMonster(getMonsterInfo(monster, grids));
//					MessageUtil.tell_player_message(player, msg);
				}

				Iterator<MapDropInfo> iterator = area.getDropGoods().values().iterator();
				while (iterator.hasNext()) {
					MapDropInfo next = iterator.next();

					if (next.canSee(player)) {
						msg.getGoods().add(next.getDropinfo());
					}
//					ResRoundGoodsMessage msg = new ResRoundGoodsMessage();
//					msg.setGoods(next.getDropinfo());
//					MessageUtil.tell_player_message(player, msg);
				}

				HashSet<Pet> pets = area.getPets();
				for (Pet pet : pets) {
//					ResRoundPetMessage msg = new ResRoundPetMessage();
					if (pet.canSee(player)) {
						msg.getPets().add(getPetInfo(pet, grids));
					}
				}
				Iterator<NPC> npcs = area.getNpcs().values().iterator();
				while (npcs.hasNext()) {
					NPC npc = (NPC) npcs.next();
					if (npc.canSee(player)) {
						msg.getNpcs().add(getNpcInfo(npc));
					}
				}
				Iterator<Effect> effectIterator = area.getEffects().values().iterator();
				while (effectIterator.hasNext()) {
					Effect effect = (Effect) effectIterator.next();
					if (effect.canSee(player)) {
						msg.getEffect().add(getEffectInfo(effect));
					}
				}


			}
		}

		if (showmsg.getRoleId().size() > 0) {
			MessageUtil.send_to_gate(map.getSendId(), showmsg);
		}

		MessageUtil.tell_player_message(player, msg);
	}

	/**
	 * 怪物切换区域
	 *
	 * @param lineId
	 * @param monsterId
	 * @param oldAreaId
	 * @param newAreaId
	 */
	public void monsterChangeArea(Monster monster, int oldAreaId, int newAreaId) {

		// 获取怪物
		if (monster == null) {
			return;
		}

		Map map = getMap(monster);

		List<Area> oldRound = getRoundAreas(map, oldAreaId);
		List<Area> newRound = getRoundAreas(map, newAreaId);

		HashSet<Area> oldSet = new HashSet<Area>();
		for (int i = 0; i < oldRound.size(); i++) {
			oldSet.add(oldRound.get(i));
		}

		HashSet<Area> newSet = new HashSet<Area>();
		for (int i = 0; i < newRound.size(); i++) {
			newSet.add(newRound.get(i));
		}

		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());

		Area oldArea = map.getAreas().get(oldAreaId);
		oldArea.getMonsters().remove(monster.getId());
		Area newArea = map.getAreas().get(newAreaId);
		newArea.getMonsters().put(monster.getId(), monster);

		ResRoundMonsterDisappearMessage hidemsg = new ResRoundMonsterDisappearMessage();
		hidemsg.getMonstersIds().add(monster.getId());

		for (int i = 0; i < oldRound.size(); i++) {
			Area area = oldRound.get(i);
			if (!newSet.contains(area)) {
				Iterator<Player> iter = area.getPlayers().iterator();
				while (iter.hasNext()) {
					Player other = (Player) iter.next();
					if (monster.canSee(other)) {
						hidemsg.getRoleId().add(other.getId());
					}
				}
			}
		}
		if (hidemsg.getRoleId().size() > 0) {
			MessageUtil.send_to_gate(map.getSendId(), hidemsg);
		}

		ResRoundMonsterMessage showmsg = new ResRoundMonsterMessage();
		showmsg.setMonster(getMonsterInfo(monster, grids));

		for (int i = 0; i < newRound.size(); i++) {
			Area area = newRound.get(i);
			if (!oldSet.contains(area)) {
				Iterator<Player> iter = area.getPlayers().iterator();
				while (iter.hasNext()) {
					Player other = (Player) iter.next();
					if (monster.canSee(other)) {
						showmsg.getRoleId().add(other.getId());
					}
				}
			}
		}
		if (showmsg.getRoleId().size() > 0) {
			MessageUtil.send_to_gate(map.getSendId(), showmsg);
		}
	}

	/**
	 * 宠物移动切换区域
	 *
	 * @param pet
	 * @param old
	 * @param position
	 */
	public void petChangeArea(Pet pet, Position oldposition,
		Position newposition) {
		// 获取怪物
		if (pet == null) {
			return;
		}
		/**
		 * 计算区域 *
		 */
		// 原来所在区域
		int oldAreaId = ManagerPool.mapManager.getAreaId(oldposition);
		// 现在所在区域
		int newAreaId = ManagerPool.mapManager.getAreaId(newposition);
		// 区域未变
		if (oldAreaId == newAreaId) {
			return;
		}

		log.debug("pet " + pet.getId() + "(" + pet.getModelId() + ") changarea from " +oldAreaId + " to " + newAreaId);
		Map map = getMap(pet);

		List<Area> oldRound = getRoundAreas(map, oldAreaId);
		List<Area> newRound = getRoundAreas(map, newAreaId);

		HashSet<Area> oldSet = new HashSet<Area>();
		for (int i = 0; i < oldRound.size(); i++) {
			oldSet.add(oldRound.get(i));
		}

		HashSet<Area> newSet = new HashSet<Area>();
		for (int i = 0; i < newRound.size(); i++) {
			newSet.add(newRound.get(i));
		}

		Area oldArea = map.getAreas().get(oldAreaId);
		if(oldArea.getPets().contains(pet)){
			oldArea.getPets().remove(pet);
		}else{
			log.error("changearea area " + oldArea.getId() + " not contains pet "
				+ pet.getMapModelId());
			Iterator<Area> iter = map.getAreas().values().iterator();
			while (iter.hasNext()) {
				Area area = (Area) iter.next();
				if (area.getPets().contains(pet)) {
					log.error("changearea area " + area.getId() + " contains pet "
						+ pet.getMapModelId());
					area.getPets().remove(pet);
				}
			}
		}
		Area newArea = map.getAreas().get(newAreaId);
		newArea.getPets().add(pet);
		
		ResRoundPetDisappearMessage hidemsg = new ResRoundPetDisappearMessage();
		hidemsg.getPetIds().add(pet.getId());
		for (int i = 0; i < oldRound.size(); i++) {
			Area area = oldRound.get(i);
			if (!newSet.contains(area)) {
//				System.out.println("area " + newAreaId + " hideid " + area.getId());
				Iterator<Player> iter = area.getPlayers().iterator();
				while (iter.hasNext()) {
					Player other = (Player) iter.next();
					if (pet.canSee(other)) {
						hidemsg.getRoleId().add(other.getId());
//						System.out.println("hide pet area " + area.getId() + " player " + other.getId());
					}
				}
			}
		}
		//宠物在原视野消失
		if (hidemsg.getRoleId().size() > 0) {
			MessageUtil.send_to_gate(map.getSendId(), hidemsg);
		}
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		ResRoundPetMessage showmsg = new ResRoundPetMessage();
		showmsg.setPet(getPetInfo(pet, grids));

		for (int i = 0; i < newRound.size(); i++) {
			Area area = newRound.get(i);
			if (!oldSet.contains(area)) {
//				System.out.println("area " + newAreaId + " showid " + area.getId());
				Iterator<Player> iter = area.getPlayers().iterator();
				while (iter.hasNext()) {
					Player other = (Player) iter.next();

					if (pet.canSee(other)) {
						showmsg.getRoleId().add(other.getId());
//						System.out.println("show pet area " + area.getId() + " player " + other.getId());
					}
				}
			}
		}
		//宠物在新视野中出现
		if (showmsg.getRoleId().size() > 0) {
			MessageUtil.send_to_gate(map.getSendId(), showmsg);
		}
	}

	public void petRunning(Pet pet, List<Position> positions) {
		// 获取怪物
		if (pet == null) {
			return;
		}

		// 路径合法检查
		if (positions == null) {
			return;
		}
		// 路径为空
		if (positions.size() == 0) {
			return;
		}

		// 获取地图
		Map map = getMap(pet.getServerId(), pet.getLine(),
			pet.getMap());
		
		if(map==null){
			return;
		}
		// 获取地图格子
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		// 清空当前移动路径
		pet.getRoads().clear();
		// 取起点坐标
		Position start = positions.remove(0);

		// 设置移动起点
		ManagerPool.petOptManager.setPetPosition(pet, start);

		// 确定方向
		if (positions.size() > 0) {
			pet.setDirection((byte) MapUtils.countDirection(
				MapUtils.getGrid(pet.getPosition(), grids),
				MapUtils.getGrid(positions.get(0), grids)));
		}

		// 设置移动路径
		pet.setRoads(positions);

		if (!ManagerPool.cooldownManager.isCooldowning(pet, CooldownTypes.PET_RUN, null)) {
			pet.setPrevStep(System.currentTimeMillis());
			pet.setCost(0);
		}
		
		// 开始移动
		HashMap<Long, Pet> runnings = map.getRunningPets();
		if (!runnings.containsKey(pet.getId())) {
			runnings.put(pet.getId(), pet);
		}
		
		ResPetRunPositionsMessage other_msg = new ResPetRunPositionsMessage();
		other_msg.setPosition(pet.getPosition());
		other_msg.setPetId(pet.getId());
		other_msg.setPositions(castPositionToByte(pet.getPosition(), positions, grids));
		MessageUtil.tell_round_message(pet, other_msg);
	}

	public void petStopRun(Pet pet) {
		if (pet.getRoads().size() > 0) {
			pet.getRoads().clear();
			ResPetStopMessage msg = new ResPetStopMessage();
			msg.setPetId(pet.getId());
			msg.setPosition(pet.getPosition());
			MessageUtil.tell_round_message(pet, msg);
		}
	}
	
	public void broadcastPetStop(Pet pet) {
		ResPetStopMessage msg = new ResPetStopMessage();
		msg.setPetId(pet.getId());
		msg.setPosition(pet.getPosition());
		MessageUtil.tell_round_message(pet, msg);
	}
	
	public void petTrans(Pet pet, Position pos){
		
		if (!pet.isShow() || pet.isDie()) {
			// 不显示的 和死的不处理
			return;
		}
		
		//定身或睡眠中
		if (FighterState.DINGSHEN.compare(pet.getFightState()) || FighterState.SHUIMIAN.compare(pet.getFightState())) {
			return;
		}
		pet.getRoads().clear();
		/**
		 * 计算区域 *
		 */
		// 原来所在区域
		int oldAreaId = ManagerPool.mapManager.getAreaId(pet.getPosition());
		// 现在所在区域
		int newAreaId = ManagerPool.mapManager.getAreaId(pos);

		log.debug("pet " + pet.getId() + "(" + pet.getModelId() + ") changarea from " +oldAreaId + " to " + newAreaId);
		
		ManagerPool.petOptManager.setPetPosition(pet, pos);
		
		//修正坐标消息
		ResPetTranMoveMessage tranmsg = new ResPetTranMoveMessage();
		tranmsg.setPetId(pet.getId());
		tranmsg.setPosition(pos);
		
		// 区域未变
		if (oldAreaId == newAreaId) {
			MessageUtil.tell_round_message(pet, tranmsg);
			return;
		}

		Map map = getMap(pet);

		List<Area> oldRound = getRoundAreas(map, oldAreaId);
		List<Area> newRound = getRoundAreas(map, newAreaId);

		HashSet<Area> oldSet = new HashSet<Area>();
		for (int i = 0; i < oldRound.size(); i++) {
			oldSet.add(oldRound.get(i));
		}

		HashSet<Area> newSet = new HashSet<Area>();
		for (int i = 0; i < newRound.size(); i++) {
			newSet.add(newRound.get(i));
		}

		Area oldArea = map.getAreas().get(oldAreaId);
		if(oldArea.getPets().contains(pet)){
			oldArea.getPets().remove(pet);
		}else{
			log.error("changearea area " + oldArea.getId() + " not contains pet "
				+ pet.getMapModelId());
			Iterator<Area> iter = map.getAreas().values().iterator();
			while (iter.hasNext()) {
				Area area = (Area) iter.next();
				if (area.getPets().contains(pet)) {
					log.error("changearea area " + area.getId() + " contains pet "
						+ pet.getMapModelId());
					area.getPets().remove(pet);
				}
			}
		}
		Area newArea = map.getAreas().get(newAreaId);
		newArea.getPets().add(pet);
		
		ResRoundPetDisappearMessage hidemsg = new ResRoundPetDisappearMessage();
		hidemsg.getPetIds().add(pet.getId());
		for (int i = 0; i < oldRound.size(); i++) {
			Area area = oldRound.get(i);
			if (!newSet.contains(area)) {
//				System.out.println("area " + newAreaId + " hideid " + area.getId());
				Iterator<Player> iter = area.getPlayers().iterator();
				while (iter.hasNext()) {
					Player other = (Player) iter.next();
					if (pet.canSee(other)) {
						hidemsg.getRoleId().add(other.getId());
//						System.out.println("hide pet area " + area.getId() + " player " + other.getId());
					}
				}
			}else{
				Iterator<Player> iter = area.getPlayers().iterator();
				while (iter.hasNext()) {
					Player other = (Player) iter.next();
					if (pet.canSee(other)) {
						tranmsg.getRoleId().add(other.getId());
					}
				}
			}
		}
		//宠物在原视野消失
		if (hidemsg.getRoleId().size() > 0) {
			MessageUtil.send_to_gate(map.getSendId(), hidemsg);
		}
		
		//宠物在视野中传送
		if (tranmsg.getRoleId().size() > 0) {
			MessageUtil.send_to_gate(map.getSendId(), tranmsg);
		}
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		ResRoundPetMessage showmsg = new ResRoundPetMessage();
		showmsg.setPet(getPetInfo(pet, grids));

		for (int i = 0; i < newRound.size(); i++) {
			Area area = newRound.get(i);
			if (!oldSet.contains(area)) {
//				System.out.println("area " + newAreaId + " showid " + area.getId());
				Iterator<Player> iter = area.getPlayers().iterator();
				while (iter.hasNext()) {
					Player other = (Player) iter.next();

					if (pet.canSee(other)) {
						showmsg.getRoleId().add(other.getId());
//						System.out.println("show pet area " + area.getId() + " player " + other.getId());
					}
				}
			}
		}
		//宠物在新视野中出现
		if (showmsg.getRoleId().size() > 0) {
			MessageUtil.send_to_gate(map.getSendId(), showmsg);
		}
	}

	public PlayerInfo getPlayerInfo(Player player, Grid[][] grids) {
		PlayerInfo info = new PlayerInfo();
		info.setPersonId(player.getId());
		info.setWebvip(player.getVipright().getWebVipLevel());
		info.setName(player.getName());
		info.setSex(player.getSex());
		info.setLevel(player.getLevel());
		info.setMapId(player.getMap());
		info.setAvatar(player.getAvatarid());
		info.setX(player.getPosition().getX());
		info.setY(player.getPosition().getY());
		info.setState(player.getState());
		info.setHp(player.getHp());
		info.setMaxHp(player.getMaxHp());
		info.setMp(player.getMp());
		info.setMaxMp(player.getMaxMp());
		info.setSp(player.getSp());
		info.setMaxSp(player.getMaxSp());
		info.setSpeed(player.getSpeed());
		info.setDir(player.getDirection());
		info.setTeam(player.getTeamid());
		info.setGuild(player.getGuildId());
		if (player.getEquips()[0] != null) {
			info.setWeapon(player.getEquips()[0].getItemModelId());
			info.setWeaponStreng((byte)player.getEquips()[0].getGradeNum());
		}
		if (player.getEquips()[1] != null) {
			info.setArmor(player.getEquips()[1].getItemModelId());
		}
		if (PlayerState.JUMP.compare(player.getState())
			|| PlayerState.DOUBLEJUMP.compare(player.getState())) {
//			info.setTime((int) (player.getJump().getTotalTime() - (System
//					.currentTimeMillis() - player.getJump().getJumpStartTime())));
//			info.setPositions(castPositionToByte(player.getPosition(), roads, grids));
		} else {
			info.setPositions(castPositionToByte(player.getPosition(), player.getRoads(), grids));
		}
		Iterator<Buff> buffIter = player.getBuffs().iterator();
		while (buffIter.hasNext()) {
			Buff buff = (Buff) buffIter.next();
			// 获取BUFF模型
			Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());

			if (buffModel.getQ_cast_type() == 2) {
				info.getBuffs().add(ManagerPool.buffManager.getBuffInfo(player, buff));
			}
		}
		Horse horse = ManagerPool.horseManager.getHorse(player);
		if (horse != null && horse.getStatus() == 1) {	//骑马状态
			info.setHorseid(horse.getCurlayer());
		}
		
		if (ManagerPool.horseWeaponManager.isWearHorseWeapon(player)){
			HorseWeapon weapon = ManagerPool.horseWeaponManager.getHorseWeapon(player);
			info.setHorseweaponid((short)weapon.getCurLayer());
		}
		
		if (ManagerPool.hiddenWeaponManager.isWearHiddenWeapon(player)) {
			HiddenWeapon weapon = ManagerPool.hiddenWeaponManager.getHiddenWeapon(player);
			info.setHiddenweaponid((short)weapon.getLayer());
		}
		
		info.setDazuoState(player.getDazuoState());
		if (player.getDazuoState() == PlayerDaZuoManager.ROLESHUANGXIUE) {
			Player target = PlayerManager.getInstance().getPlayer(player.getDazuotarget());
			info.setSxroleid(player.getDazuotarget());
			info.setSxtargetx(target.getPosition().getX());
			info.setSxtargety(target.getPosition().getY());
			Pet showPet = PetInfoManager.getInstance().getShowPet(player);
			if(showPet!=null){
				info.getSxpets().add(showPet.getId());	
			}
		}
		if (player.getDazuoState() == PlayerDaZuoManager.PETSHUANGXIU) {
			Pet showPet = PetInfoManager.getInstance().getShowPet(player);
			if(showPet!=null){
				info.getSxpets().add(showPet.getId());	
			}
		}
		info.setRanklevel((byte) player.getRanklevel());
		info.setArrowid(player.getArrowData().getArrowlv());
		info.setRealmlevel(player.getRealm().getRealmlevel());
		return info;
	}

	public NpcInfo getNpcInfo(NPC npc) {
		NpcInfo info = new NpcInfo();
		info.setNpcId(npc.getId());
		info.setNpcModelId(npc.getModelId());
		if(npc.getName()!=null && !"".equals(npc.getName())){
			info.setNpcName(npc.getName());
		}
		if(npc.getRes()!=null && !"".equals(npc.getRes())){
			info.setNpcRes(npc.getRes());
		}
		if(npc.getIcon()!=null && !"".equals(npc.getIcon())){
			info.setNpcIcon(npc.getIcon());
		}
		info.setX(npc.getPosition().getX());
		info.setY(npc.getPosition().getY());
		return info;
	}

	public EffectInfo getEffectInfo(Effect effect) {
		EffectInfo info = new EffectInfo();
		info.setEffectId(effect.getId());
		info.setType(effect.getType());
		info.setEffectModelId(effect.getEffectModelId());
		info.setX(effect.getPosition().getX());
		info.setY(effect.getPosition().getY());
		return info;
	}

	public PetInfo getPetInfo(Pet pet, Grid[][] grids) {
		Player player = PlayerManager.getInstance().getPlayer(pet.getOwnerId());
		PetInfo info = new PetInfo();
		info.setPetId(pet.getId());
		info.setPetModelId(pet.getModelId());
		info.setLevel(pet.getLevel());
		info.setMapId(pet.getMap());
		info.setOwnerName(player.getName());
		info.setOwnerId(pet.getOwnerId());
		// info.setX(pet.getPosition().getX());
		// info.setY(pet.getPosition().getY());
		info.setHp(pet.getHp());
		info.setMaxHp(pet.getMaxHp());
		info.setMp(pet.getMp());
		info.setMaxMp(pet.getMaxMp());
		info.setSp(pet.getSp());
		info.setMaxSp(pet.getMaxSp());
		info.setSpeed(pet.getSpeed());
		info.setDir(pet.getDirection());
		if (pet.getPosition() != null) {
			info.setX(pet.getPosition().getX());
			info.setY(pet.getPosition().getY());
		}
		try{
			info.setPositions(castPositionToByte(pet.getPosition(), pet.getRoads(), grids));
		}catch (Exception e) {
			log.error(e, e);
		}
		Iterator<Buff> buffIter = pet.getBuffs().iterator();
		while (buffIter.hasNext()) {
			Buff buff = (Buff) buffIter.next();
			// 获取BUFF模型
			Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());

			if (buffModel.getQ_cast_type() == 2) {
				info.getBuffs().add(ManagerPool.buffManager.getBuffInfo(pet, buff));
			}
		}
		byte state=0;
		if(PlayerDaZuoManager.getInstacne().isPetShuangXiu(player)){
			state=1;
		}
		if(PlayerDaZuoManager.getInstacne().isRoleShuangXiu(player)){
			state=2;
		}
		info.setSxstate(state);
		return info;
	}

	public MonsterInfo getMonsterInfo(Monster monster, Grid[][] grids) {
		MonsterInfo info = new MonsterInfo();
		info.setMonsterId(monster.getId());
		info.setMonsterModelId(monster.getModelId());
		if(monster.getName()!=null && !"".equals(monster.getName())){
			info.setMonsterName(monster.getName());
		}
		if(monster.getRes()!=null && !"".equals(monster.getRes())){
			info.setMonsterRes(monster.getRes());
		}
		if(monster.getIcon()!=null && !"".equals(monster.getIcon())){
			info.setMonsterIcon(monster.getIcon());
		}
		info.setLevel(monster.getLevel());
		info.setMapId(monster.getMap());
		info.setX(monster.getPosition().getX());
		info.setY(monster.getPosition().getY());
		info.setHp(monster.getHp());
		info.setMaxHp(monster.getMaxHp());
		info.setMp(monster.getMp());
		info.setMaxMp(monster.getMaxMp());
		info.setSp(monster.getSp());
		info.setMaxSp(monster.getMaxSp());
		info.setSpeed(monster.getSpeed());
		info.setPositions(castPositionToByte(monster.getPosition(), monster.getRoads(), grids));
		info.setDir(monster.getDirection());
		info.setFriend((byte)monster.getFriend());
		info.setFriendPara(monster.getFriendPara());
		// 变身类型
		int morphs = 0;
		Iterator<Morph> iter = monster.getMorph().values().iterator();
		while (iter.hasNext()) {
			Morph morph = (Morph) iter.next();
			morphs = morphs | morph.getType();
		}
		info.setMorph(morphs);
		Iterator<Buff> buffIter = monster.getBuffs().iterator();
		while (buffIter.hasNext()) {
			Buff buff = (Buff) buffIter.next();
			// 获取BUFF模型
			Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());

			if (buffModel.getQ_cast_type() == 2) {
				info.getBuffs().add(ManagerPool.buffManager.getBuffInfo(monster, buff));
			}
		}
		return info;
	}

	public boolean isSafe(Position position, int mapModelId) {
		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(mapModelId);
		if (mapBean.getQ_map_safe() == 1) {
			return true;
		}
		Grid grid = MapUtils.getGrid(position, getMapBlocks(mapModelId));
		if(grid==null){
			log.error(position + " out of map " + mapModelId);
			return true;
		}
		return grid.getSafe() == 1;
	}

	private List<Byte> castPositionToByte(Position position, List<Position> positions, Grid[][] grids) {
//		log.debug("now:" + position + " run:" + MessageUtil.castListToString(positions));
		List<Byte> bytes = new ArrayList<Byte>();
		Position pos = position;
		byte dir = -1;
		byte index = 0;
		int length = positions.size();
		for (int i = 0; i < length; i++) {
			Position dst = positions.get(i);
			Grid grid1 = MapUtils.getGrid(pos, grids);
			Grid grid2 = MapUtils.getGrid(dst, grids);
			if(grid1==null){
				log.error("pos " + pos + " out of map!");
				return new ArrayList<Byte>();
			}
			if(grid2==null){
				log.error("dst " + dst + " out of map!");
				return new ArrayList<Byte>();
			}
			byte direct = (byte) MapUtils.countDirection(grid1, grid2);
			if (dir == direct) {
				index++;
				if (index == 31) {
					byte b = (byte) (((direct << 5) | index) & 0xFF);
					bytes.add(b);
					dir = -1;
					index = 0;
				}
			} else if (dir == -1) {
				dir = direct;
				index++;
			} else {
				byte b = (byte) (((dir << 5) | index) & 0xFF);
				bytes.add(b);
				dir = direct;
				index = 1;
			}

			if (i == length - 1 && dir > -1) {
				byte b = (byte) (((direct << 5) | index) & 0xFF);
				bytes.add(b);
			}

			pos = dst;
		}
//		log.debug("now:" + position + " bytes:" + MessageUtil.castListToString(bytes));
		return bytes;
	}

	public boolean isSameBlock(Position point1, Position point2, int mapModelId) {
		Grid[][] mapBlocks = getMapBlocks(mapModelId);
		Grid grid1 = MapUtils.getGrid(point1, mapBlocks);
		Grid grid2 = MapUtils.getGrid(point2, mapBlocks);
		return grid1.getBlock() == grid2.getBlock();
	}

	/**
	 * 计算路径花费
	 *
	 * @param mapModelId1 地图模板id1
	 * @param mapModelId2 地图模板id2
	 * @return -1无法到达
	 */
	public int countMapCost(int mapModelId1, int mapModelId2, List<Integer> transfer) {
		//同一地图
		if (mapModelId1 == mapModelId2) {
			return -10;
		}

		Q_mapBean mapBean1 = ManagerPool.dataManager.q_mapContainer.getMap().get(mapModelId1);
		Q_mapBean mapBean2 = ManagerPool.dataManager.q_mapContainer.getMap().get(mapModelId2);
		//副本地图 收费5元宝
		if (mapBean1.getQ_map_zones() == 1) {
			//所有传送点
			List<Q_transferBean> trans = ManagerPool.dataManager.q_transferContainer.getList();
			for (int i = 0; i < trans.size(); i++) {
				if (trans.get(i).getQ_tran_to_map() == mapModelId2) {
					transfer.add(trans.get(i).getQ_tran_id());
					return 5;
				}
			}
			return -1;
		}
		//不在同一区
		if (mapBean1.getQ_map_public() != mapBean2.getQ_map_public()) {
			return -1;
		}

		//所有传送点
		List<Q_transferBean> trans = ManagerPool.dataManager.q_transferContainer.getList();
		//路径花费路费
		HashMap<Integer, Integer> roads = new HashMap<Integer, Integer>();
		//寻找过的地图
		HashSet<Integer> finded = new HashSet<Integer>();
		//待寻找地图
		List<Integer> wait = new ArrayList<Integer>();
		wait.add(mapModelId1);
		roads.put(mapModelId1, 0);
		finded.add(mapModelId1);

		while (wait.size() > 0) {
			Integer modelId = wait.remove(0);
			for (int i = 0; i < trans.size(); i++) {
				Q_transferBean tran = trans.get(i);
				if (tran.getQ_tran_from_map() == modelId) {
					if (finded.contains(tran.getQ_tran_to_map())) {
						continue;
					}
					if (tran.getQ_tran_to_map() == mapModelId2) {
						transfer.add(tran.getQ_tran_id());
						return roads.get(modelId) + 1;
					} else {
						wait.add(tran.getQ_tran_to_map());
						roads.put(tran.getQ_tran_to_map(), roads.get(modelId) + 1);
						finded.add(tran.getQ_tran_to_map());
					}
				}
			}
		}
		return -1;
	}

	/**
	 * 取得周围指定半径范围内的玩家(传入格子数量)
	 *
	 * @param player
	 * @param radius
	 * @return
	 */
	public List<Player> getAroundPlayer(Player player, int radius) {
		Map map = MapManager.getInstance().getMap(player.getServerId(), player.getLine(), player.getMap());
		if (map != null) {
			List<Area> round = MapManager.getInstance().getRound(map, player.getPosition());
			if (round != null) {
				List<Player> playertab = new ArrayList<Player>();
				for (Area area : round) {
					HashSet<Player> players = area.getPlayers();
					if (players != null && players.size() > 0) {
						Iterator<Player> it = players.iterator();
						while (it.hasNext()) {
							Player splayer = (Player) it.next();
							double distance = MapUtils.countDistance(player.getPosition(), splayer.getPosition());	//得到距离
							if (splayer.getId() != player.getId() && distance <= MapUtils.GRID_BORDER * radius) {
								playertab.add(splayer);
							}
						}
					}
				}
				return playertab;
			}
		}
		return null;
	}

	public void reqGetLines(Player player, ReqGetLinesMessage message) {
		if (player != null) {
			Q_mapBean q_mapBean = DataManager.getInstance().q_mapContainer.getMap().get(player.getMap());
			if (q_mapBean != null) {
				if (q_mapBean.getQ_map_zones() == 0) {
					ResSendLinesMessage sendMessage = new ResSendLinesMessage();
					Vector<Integer> lines = MapManager.mapLines.get(q_mapBean.getQ_map_id());
					for (int i = 0; i < lines.size(); i++) {
						sendMessage.getLines().add(lines.get(i));
					}
					MessageUtil.tell_player_message(player, sendMessage);
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("副本地图中不能换线"));
				}
			}
		}
	}

	public void reqSelectLine(Player player, ReqSelectLineMessage message) {
		//停止采集
		ManagerPool.npcManager.playerStopGather(player);
				
		if (player != null) {
			//战斗状态不允许切换线路
			if (PlayerState.FIGHT.compare(player.getState())) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您尚未脱离战斗状态，无法进行选线"));
				return;
			}
			//死亡状态不允许切换线路
			if (PlayerState.DIE.compare(player.getState())) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您已经死亡，无法进行选线"));
				return;
			}
			if (player.getLine() != message.getLine()) {
				Q_mapBean q_mapBean = DataManager.getInstance().q_mapContainer.getMap().get(player.getMap());
				if (q_mapBean != null) {
					if (q_mapBean.getQ_map_zones() == 0) {
						Vector<Integer> lines = MapManager.mapLines.get(q_mapBean.getQ_map_id());
						if (lines.contains(message.getLine())) {
							if (ischangMap(player)) {
								changeMap(player, player.getMap(), player.getMapModelId(), message.getLine(), player.getPosition(), this.getClass().getName() + ".reqSelectLine");
								MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("选线成功"));
								MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("选线成功"));
							}
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您选择的线不存在"));
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("副本地图中不能换线"));
					}
				}
			} else {
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("您已经在本线了"));
			}
		}
	}

	public static ConcurrentHashMap<String, Map> getMapping() {
		return mapping;
	}

	/**
	 * 是否在视野范围内
	 *
	 * @param map
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	public boolean inVisualField(Map map, Position pos1, Position pos2) {
		Area area1 = getArea(pos1, map);
		Area area2 = getArea(pos2, map);
		if (area1 == null || area2 == null) {
			return false;
		}
		int x1 = (int) (area1.getId() / 1000);
		int y1 = (int) (area1.getId() % 1000);
		int x2 = (int) (area2.getId() / 1000);
		int y2 = (int) (area2.getId() % 1000);
		int width = Math.abs(x1 - x2);
		int height = Math.abs(y1 - y2);
		int radius = ROUND / 2;
		if (width <= radius && height <= radius) {
			return true;
		}
		return false;
	}

	/**
	 * 地图内怪物是否全部死亡 true 全部死亡
	 *
	 * @param monster
	 * @param map
	 * @return
	 */
	public boolean ismapAllMonsterDie(Map map) {
		HashMap<Long, Monster> monsters = map.getMonsters();
		Iterator<Entry<Long, Monster>> monstersdata = monsters.entrySet().iterator();
		while (monstersdata.hasNext()) {
			Entry<Long, Monster> mondb = monstersdata.next();
			Monster mon = mondb.getValue();
			if (!mon.isDie()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 地图内活着的怪物数量
	 *
	 * @param map
	 * @return
	 */
	public int getMonsterNum(Map map) {
		int num = 0;
		HashMap<Long, Monster> monsters = map.getMonsters();
		Iterator<Entry<Long, Monster>> monstersdata = monsters.entrySet().iterator();
		while (monstersdata.hasNext()) {
			Entry<Long, Monster> mondb = monstersdata.next();
			Monster mon = mondb.getValue();
			if (!MonsterState.DIE.compare(mon.getState())) {
				num = num + 1;
			}
		}
		return num;
	}

	/**
	 * 获取地图中指定ID的美人
	 *
	 * @param map
	 * @param targetId
	 * @return
	 */
	public Pet getMapPet(Map map, long targetId) {
		Iterator<Area> iterator = map.getAreas().values().iterator();
		while (iterator.hasNext()) {
			Area area = (Area) iterator.next();
			Iterator<Pet> petIterator = area.getPets().iterator();
			while (petIterator.hasNext()) {
				Pet pet = (Pet) petIterator.next();
				if (pet.getId() == targetId) {
					return pet;
				}
			}
		}
		return null;
	}
	
	
	
	
	/**获取死亡随机回城点
	 * 
	 * @return
	 */
	public Position RandomDieBackCity(Q_mapBean mapBean){
		int id =0;
		try {
			id = mapBean.getQ_map_die();
			String xystr = mapBean.getQ_rnd_die_xy();
			List<Integer[]> xyList = JSON.parseArray(xystr, Integer[].class);
			int rnd = RandomUtils.random(1, xyList.size())-1;
			Integer[] xydata = xyList.get(rnd);
			Grid grid = MapUtils.getGrid(xydata[0], xydata[1], ManagerPool.mapManager.getMapBlocks(id));
//			Position position = new Position();
//			position.setX((short) (xydata[0] * MapUtils.GRID_BORDER));
//			position.setY((short) (xydata[1] * MapUtils.GRID_BORDER));
//			return position;
			return grid.getCenter();
		} catch (Exception e) {
			log.error(mapBean.getQ_map_id()+"没有设置随机回城点"+e, e);
		}
		return null;
	}
	
	/**
	 * 刷新玩家周围信息
	 * @param player
	 */
	public void refreshPlayerRound(Player player){
		
		Map map = getMap(player);
		if (map == null) {
			return;
		}

		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());


		int areaId = getAreaId(player.getPosition());
		
		ResRoundObjectsMessage msg = new ResRoundObjectsMessage();
		List<Area> rounds = getRoundAreas(map, areaId);
		for (int i = 0; i < rounds.size(); i++) {
			Area area = rounds.get(i);
			Iterator<Player> iter = area.getPlayers().iterator();
			while (iter.hasNext()) {
				Player other = (Player) iter.next();
				if (other.getId() == player.getId()) {
					continue;
				}

				if (other.canSee(player)) {
					msg.getPlayer().add(getPlayerInfo(other, grids));
				}
			}

			Iterator<Monster> monster_iter = area.getMonsters().values().iterator();
			while (monster_iter.hasNext()) {
				Monster monster = (Monster) monster_iter.next();

				if (monster.canSee(player)) {
					msg.getMonster().add(getMonsterInfo(monster, grids));
				}
			}

			Iterator<MapDropInfo> iterator = area.getDropGoods().values().iterator();
			while (iterator.hasNext()) {
				MapDropInfo next = iterator.next();

				if (next.canSee(player)) {
					msg.getGoods().add(next.getDropinfo());
				}
			}

			HashSet<Pet> petset = area.getPets();
			for (Pet pet : petset) {
				if (pet.canSee(player)) {
					msg.getPets().add(getPetInfo(pet, grids));
				}
			}

			Iterator<NPC> npciterator = area.getNpcs().values().iterator();
			while (npciterator.hasNext()) {
				NPC npc = (NPC) npciterator.next();
				if (npc.canSee(player)) {
					msg.getNpcs().add(getNpcInfo(npc));
				}
			}

			Iterator<Effect> effectiterator = area.getEffects().values().iterator();
			while (effectiterator.hasNext()) {
				Effect effect = (Effect) effectiterator.next();
				if (effect.canSee(player)) {
					msg.getEffect().add(getEffectInfo(effect));
				}
			}
		}

		MessageUtil.tell_player_message(player, msg);
	}
	
	public boolean canJumpPath(Grid grid1, Grid grid2, int mapModelId){
		int xRange1 = Math.abs(grid2.getX() - grid1.getX());
		int yRange1 = Math.abs(grid2.getY() - grid1.getY());
		int	xRange2 = xRange1 << 1;
		int yRange2 = yRange1 << 1;
		int xDelta = grid2.getX() < grid1.getX() ? -1 : 1;
		int yDelta = grid2.getY() < grid1.getY() ? -1 : 1;
		int judge = 0;
		int x = grid1.getX();
		int y = grid1.getY();
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(mapModelId);
		
		if ( !canJumpGrid(grid1, mapModelId) ){
			log.error("地图：" + mapModelId + ",格子:[" + grid1.getX() + "," + grid1.getY() + "]不能跳,格子属性:" + grid1.getJump());
			return false;
		}
		
		if (yRange1 > xRange1){
			while (y != grid2.getY())
			{
				if (judge - xRange2 < -yRange1)
				{
					x += xDelta;
					judge += yRange2;
				}
				
				y += yDelta;
				judge -= xRange2;
				Grid grid = MapUtils.getGrid(x, y, grids);
				if ( !canJumpGrid(grid, mapModelId)){
					log.error("地图：" + mapModelId + ",格子:[" + grid.getX() + "," + grid.getY() + "]不能跳,格子属性:" + grid.getJump());
					return false;
				}
				
			}
		}
		else if (yRange1 < xRange1)
		{
			while (x != grid2.getX())
			{
				if (judge - yRange2 < -xRange1)
				{
					y += yDelta;
					judge += xRange2;
				}
				
				x += xDelta;
				judge -= yRange2;
				Grid grid = MapUtils.getGrid(x, y, grids);
				if ( !canJumpGrid(grid, mapModelId)){
					log.error("地图：" + mapModelId + ",格子:[" + grid.getX() + "," + grid.getY() + "]不能跳,格子属性:" + grid.getJump());
					return false;
				}
			}
		}
		else
		{
			while (x != grid2.getX())
			{
				y += yDelta;
				x += xDelta;
				Grid grid = MapUtils.getGrid(x, y, grids);
				if ( !canJumpGrid(grid, mapModelId)){
					log.error("地图：" + mapModelId + ",格子:[" + grid.getX() + "," + grid.getY() + "]不能跳,格子属性:" + grid.getJump());
					return false;		
				}
			}
		}
		
		return true;
	}
	
	
	private boolean canJumpGrid(Grid grid, int mapModelId){
		if(grid.getJump()!=1 && mapModelId==42124){
			return false;
		}
		return true;
	}
	
	
	
	
	
}
