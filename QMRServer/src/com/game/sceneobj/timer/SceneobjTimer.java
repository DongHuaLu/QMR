package com.game.sceneobj.timer;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.game.data.bean.Q_scene_objBean;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.npc.struts.NPC;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.timer.TimerEvent;
import com.game.utils.MapUtils;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;

public class SceneobjTimer extends TimerEvent{

	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	private int minute;
			
	public SceneobjTimer(int serverId, int lineId, int mapId) {
		super(-1,1000*30);
		this.serverId=serverId;
		this.lineId=lineId;
		this.mapId = mapId;
	}
	

	@Override
	public void action() {
		//30秒调用一次，确保每分钟不跳过
		int min = TimeUtil.getDayOfMin(System.currentTimeMillis());
		if (minute != min ) {
			minute = min;
		}else {
			return;
		}
		
		//获取地图
		List<Q_scene_objBean> data = ManagerPool.dataManager.q_scene_objContainer.getList();
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		Grid[][] mapBlocks = MapManager.getInstance().getMapBlocks(this.mapId);
		//随机坐标xylist
		for (Q_scene_objBean q_scene_objBean : data) {
			if (q_scene_objBean.getQ_class() != null &&q_scene_objBean.getQ_class().equals(this.getClass().toString()) &&  q_scene_objBean.getQ_refresh_map() == map.getMapModelid() ) {
				if(TimeUtil.checkRangeTime(q_scene_objBean.getQ_refresh_time())){
					List<Integer[]> xylist = JSON.parseArray(q_scene_objBean.getQ_refresh_coordinate(),Integer[].class);
					if (q_scene_objBean.getQ_npc_id() !=null && !q_scene_objBean.getQ_npc_id().equals("0")) {
						Integer[] ids = JSON.parseObject(q_scene_objBean.getQ_npc_id(),Integer[].class);
						List<Integer> rnpcs = new ArrayList<Integer>();
						List<Integer[]> tmp = new ArrayList<Integer[]>();
						int tmpid = 0;
						for (int npcid : ids) {
							if (tmpid == npcid) {//相同ID的NPC下面一次就全读出来了，这里跳出
								continue;
							}
							tmpid = npcid;
							List<NPC> npcs = ManagerPool.npcManager.findNpc(map, npcid);
							if (npcs.size() > 0) {
								for (NPC npc : npcs) {
									for (Integer[] xydb : xylist) {
										Grid agrid = MapUtils.getGrid(npc.getPosition(), mapBlocks);
										Grid bgrid = MapUtils.getGrid(xydb[0], xydb[1], mapBlocks);
										if(agrid != null && bgrid != null && agrid.equals(bgrid) ){
											tmp.add(xydb);
										}
									}
								}
							}else {
								rnpcs.add(npcid);
							}
						}
						
						for (int i = 0; i < tmp.size() ; i++) {
							xylist.remove(tmp.get(i));
						}

						for (int rnpcid : rnpcs) {
							if (xylist.size() > 0) {
								int rnd = RandomUtils.random(1, xylist.size()) - 1;
								Integer[] xy =xylist.remove(rnd);
								Position position = new Position();
								position.setX((short) (xy[0]*MapUtils.GRID_BORDER));
								position.setY((short) (xy[1]*MapUtils.GRID_BORDER));
								NPC npc = ManagerPool.npcManager.createNpc(rnpcid, map, true);
								npc.setPosition(position);
								ManagerPool.mapManager.enterMap(npc);
							}
						}
					}
					
					//怪物刷新
					if (q_scene_objBean.getQ_monster_id() !=null && !q_scene_objBean.getQ_monster_id().equals("0")) {	
						Integer[] ids = JSON.parseObject(q_scene_objBean.getQ_monster_id(),Integer[].class);
						List<Integer[]> tmp = new ArrayList<Integer[]>();
						List<Integer> rmons = new ArrayList<Integer>();
						int tmpid = 0;
						for (Integer monid : ids) {
							if (tmpid == monid) {//相同ID的怪物下面一次就全读出来了，这里跳出
								continue;
							}
							tmpid = monid;
							List<Monster> monsters = ManagerPool.monsterManager.getSameMonster(map,monid);
							if (monsters.size() > 0) {
								for (Monster monster : monsters) {
									for (Integer[] xydb : xylist) {
										Grid agrid = MapUtils.getGrid(monster.getPosition(), mapBlocks);
										Grid bgrid = MapUtils.getGrid(xydb[0],xydb[1], mapBlocks);
										if(bgrid != null & agrid != null && agrid.equals(bgrid)){
											tmp.add(xydb);
										}
									}
								}
							}else {
								rmons.add(monid);
							}
						}

						for (int i = 0; i < tmp.size() ; i++) {
							xylist.remove(tmp.get(i));
						}

						
						for (int monid :rmons) {
							if (xylist.size() > 0) {
								int rnd = RandomUtils.random(1, xylist.size()) - 1;
								Integer[] xy =xylist.remove(rnd);
								Position position = new Position();
								position.setX((short) (xy[0]*MapUtils.GRID_BORDER));
								position.setY((short) (xy[1]*MapUtils.GRID_BORDER));
								ManagerPool.monsterManager.createMonsterAndEnterMap(monid,serverId,  lineId,  mapId, position);
							}
						}
					}
				}
			}
		}
	}
	
	
	
	
}
