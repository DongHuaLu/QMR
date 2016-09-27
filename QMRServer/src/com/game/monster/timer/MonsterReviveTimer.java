package com.game.monster.timer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_monsterBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Area;
import com.game.map.structs.Map;
import com.game.monster.message.ReqMonsterSyncMessage;
import com.game.monster.message.ResMonsterReviveMessage;
import com.game.monster.structs.Monster;
import com.game.structs.Grid;
import com.game.timer.TimerEvent;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;

public class MonsterReviveTimer extends TimerEvent {
	
	protected Logger log = Logger.getLogger(MonsterReviveTimer.class);
	
	private int serverId;

	private int lineId;
	
	private int mapId;
	
	//BOSS集合
	private static List<Integer> bosses = Arrays.asList(91,151,211,261,341,391,441,541,581,631,681,682,683,684,2010,2011,2012,2013,2014,2015,2016,2017,2018,2301,4091,4151,4211,4261,4341,4391,4441,4541,4581,4631,4681);
	
	public MonsterReviveTimer(int serverId, int lineId, int mapId){
		super(-1, 1000);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}
	
	@Override
	public void action() {
		//按地图，区域遍历怪物列表
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		
		//遍历怪物列表
		Iterator<Monster> iter = map.getRevives().values().iterator();
		while (iter.hasNext()) {
			Monster monster = (Monster) iter.next();
			
			if(monster.getRevive() > 0){
				monster.setRevive(monster.getRevive() - 1);
				if(monster.getRevive() > 0) continue;
			}
			
			//是否在复活时间段内
			Q_monsterBean q_monsterBean = DataManager.getInstance().q_monsterContainer.getMap().get(monster.getModelId());
			if(q_monsterBean.getQ_refreshtime()!=null && !q_monsterBean.getQ_refreshtime().equals("")){
				if(!TimeUtil.isNowSatisfiedBy(q_monsterBean.getQ_refreshtime())) continue;
			}
			
			//重置怪物状态
			monster.reset();
			monster.setPosition(monster.getBirthPos());
			
			if(bosses.contains(monster.getModelId())) log.error("Monster("+monster.getModelId()+") " + monster.getId() + " revive!");
					
			//获得怪物所在区域
			Area area = map.getAreas().get(ManagerPool.mapManager.getAreaId(monster.getPosition()));
			if(area==null){
				iter.remove();
				continue;
			}
			//地图上添加怪物
			area.getMonsters().put(monster.getId(), monster);
			
			if(bosses.contains(monster.getModelId())) log.error("Monster("+monster.getModelId()+") " + monster.getId() + " enter map " + map.getId() + ":"+map.getLineId()+" area " + area.getId() + "!");
			//通知周围玩家
			ResMonsterReviveMessage msg = new ResMonsterReviveMessage();
			msg.setMonster(ManagerPool.mapManager.getMonsterInfo(monster, grids));
			MessageUtil.tell_round_message(monster, msg);
			
			if(q_monsterBean.getQ_info_sync()>0){
				ReqMonsterSyncMessage syncmsg = new ReqMonsterSyncMessage();
				syncmsg.setModelId(monster.getModelId());
				syncmsg.setLineId(monster.getLine());
				syncmsg.setServerId(monster.getServerId());
				syncmsg.setMonsterId(monster.getId());
				syncmsg.setCurrentHp(monster.getHp());
				syncmsg.setMaxHp(monster.getMaxHp());
				syncmsg.setState(1);
				syncmsg.setBirthX(monster.getBirthPos().getX());
				syncmsg.setBirthY(monster.getBirthPos().getY());
				MessageUtil.send_to_world(syncmsg);
				MessageUtil.tell_world_message(syncmsg);
			}
			
			iter.remove();
		}
	}

}
