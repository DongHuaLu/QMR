package com.game.buff.timer;

import java.util.Iterator;

import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.monster.structs.MonsterState;
import com.game.timer.TimerEvent;

public class MonsterBuffTimer extends TimerEvent {

	//private Logger log = Logger.getLogger(MonsterBuffTimer.class);
	
	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public MonsterBuffTimer(int serverId, int lineId, int mapId){
		super(-1, 1000);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId=mapId;
	}
	
	@Override
	public void action() {
		//按地图，区域遍历怪物列表
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		
		//遍历地区
//		Iterator<Area> areaIter = map.getAreas().values().iterator();
//		while (areaIter.hasNext()) {
//			Area area = (Area) areaIter.next();
		Iterator<Monster> iter = map.getMonsters().values().iterator();
		while (iter.hasNext()) {
			Monster monster = (Monster) iter.next();
			if(monster.isDie()) continue;
			if(MonsterState.DIEING.compare(monster.getState())){
				continue;
			}
			if(monster.getBuffs().size()==0) continue;
			
			ManagerPool.buffManager.countBuff(monster);
		}
//		}
	}
}
