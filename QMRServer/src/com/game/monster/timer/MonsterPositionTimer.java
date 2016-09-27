package com.game.monster.timer;

import java.util.HashSet;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.map.message.ResSynMonsterPositionMessage;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.timer.TimerEvent;
import com.game.utils.MessageUtil;

//测试专用，前后台位置同步
public class MonsterPositionTimer extends TimerEvent {

	protected Logger log = Logger.getLogger(MonsterAiTimer.class);
	
	public static HashSet<Integer> monsters = new HashSet<Integer>();
	
	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public MonsterPositionTimer(int serverId, int lineId, int mapId){
		super(-1, 100);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}
	
	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		//遍历怪物列表
		Iterator<Monster> iter = map.getMonsters().values().iterator();
		while (iter.hasNext()) {
			Monster monster = (Monster) iter.next();
			
			if(!monsters.contains(monster.getModelId())) continue;
			
			ResSynMonsterPositionMessage msg = new ResSynMonsterPositionMessage();
			msg.setPosition(monster.getPosition());
			MessageUtil.tell_round_message(monster, msg);
		}
	}
}
