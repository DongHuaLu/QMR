package com.game.sceneobj.timer;

import com.alibaba.fastjson.JSON;
import com.game.data.bean.Q_mapBean;
import com.game.data.bean.Q_scene_objBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.structs.Map;
import com.game.monster.manager.MonsterManager;
import com.game.monster.structs.Monster;
import com.game.structs.Position;
import com.game.timer.TimerEvent;
import com.game.utils.MapUtils;
import com.game.utils.TimeUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.log4j.Logger;

/**
 * 怪物特殊刷新计时器 （金钱盗贼）
 *
 * @author 杨鸿岚
 */
public class SceneObjSpecialRefreshTimer extends TimerEvent {

	protected Logger log = Logger.getLogger(SceneObjSpecialRefreshTimer.class);
	private int serverId;
	private int lineId;
	private int mapId;

	public SceneObjSpecialRefreshTimer(int serverId, int lineId, int mapId) {
		super(1, 500);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}

	@Override
	public void action() {
		//按地图，区域遍历怪物列表
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		if (map.isCopy()) {
			return;
		}
		Q_mapBean q_mapBean = DataManager.getInstance().q_mapContainer.getMap().get(map.getMapModelid());
		if (q_mapBean != null) {
			if (q_mapBean.getQ_map_lines() != null && q_mapBean.getQ_map_lines().equalsIgnoreCase("1")) {
				return;
			}
			if (q_mapBean.getQ_map_subsidiary() == 1) {
				return;
			}
		}
		//获取该地图要刷新的数据
		ListIterator<Q_scene_objBean> listIterator = DataManager.getInstance().q_scene_objContainer.getList().listIterator();
		while (listIterator.hasNext()) {
			Q_scene_objBean q_scene_objBean = listIterator.next();
			if (q_scene_objBean != null && q_scene_objBean.getQ_class().equalsIgnoreCase(this.getClass().toString())) {
				if (TimeUtil.checkRangeTime(q_scene_objBean.getQ_refresh_time())) {
					List<Integer> monsteridList = JSON.parseArray(q_scene_objBean.getQ_monster_id(), Integer.class);
					HashSet<Integer> refreshMonsterSet = new HashSet<Integer>();
					for (int i = 0; i < monsteridList.size(); i++) {
						Integer monid = monsteridList.get(i);
						if (monid != 0 && !refreshMonsterSet.contains(monid)) {
							refreshMonsterSet.add(monid);
						}
					}
					Iterator<Integer> iterator = refreshMonsterSet.iterator();
					while (iterator.hasNext()) {
						Integer monModid = iterator.next();
						int monsterNum = getMonsterNum(map, monModid);
						while (monsterNum < monsteridList.size()) {
							Position position = MapUtils.getMapRandPoint(map.getMapModelid());
							if (position != null) {
								MonsterManager.getInstance().createMonsterAndEnterMap(monModid, serverId, lineId, mapId, position);
								log.info("mapid:" + mapId + ",line:" + lineId + ",x:" + position.getX() + ",y:" + position.getY() + ",monid:" + monModid + ",num:" + (monsterNum + 1));
							}
							monsterNum++;
						}
					}
				}
			}
		}
	}

	public boolean parseRefreshMap(int refreshMap, int mapModId) {
		if (refreshMap == -1) {
			return true;
		}
		if (refreshMap == mapModId) {
			return true;
		}
		return false;
	}

	public int getMonsterNum(Map map, int monsterModId) {
		int ret = 0;
		if (monsterModId != 0) {
			//遍历怪物列表
			Iterator<Monster> iter = map.getMonsters().values().iterator();
			while (iter.hasNext()) {
				Monster monster = iter.next();
				if (monster != null) {
					if (monster.getModelId() == monsterModId) {
						ret++;
					}
				}
			}
		}
		return ret;
	}

	public int getMonsterNumAllMap(int monsterModId) {
		int ret = 0;
		if (monsterModId != 0) {
			//遍历地图列表
			Iterator<Map> iterator = MapManager.getMapping().values().iterator();
			while (iterator.hasNext()) {
				Map map = iterator.next();
				if (map != null) {
					ret = ret + getMonsterNum(map, monsterModId);
				}
			}
		}
		return ret;
	}
}
