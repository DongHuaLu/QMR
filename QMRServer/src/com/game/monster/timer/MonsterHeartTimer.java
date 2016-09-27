package com.game.monster.timer;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.map.message.ResRoundMonsterDisappearMessage;
import com.game.map.structs.Area;
import com.game.map.structs.Map;
import com.game.monster.message.ResMonsterDieMessage;
import com.game.monster.script.IMonsterDieScript;
import com.game.monster.structs.Monster;
import com.game.monster.structs.MonsterState;
import com.game.pet.struts.Pet;
import com.game.script.structs.ScriptEnum;
import com.game.timer.TimerEvent;
import com.game.utils.Global;
import com.game.utils.MessageUtil;

public class MonsterHeartTimer extends TimerEvent {

	private Logger log = Logger.getLogger(MonsterHeartTimer.class);
	
	private Logger monsterlog = Logger.getLogger("MONSTER");
	
	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public MonsterHeartTimer(int serverId, int lineId, int mapId){
		super(-1, 1000);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}
	
	@Override
	public void action() {
//		long start = System.currentTimeMillis();
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
						
		boolean empty = map.isEmpty();
		
		Monster[] monsters = map.getMonsters().values().toArray(new Monster[0]);
		for (Monster monster : monsters) {
		
			//怪物死亡
			if(MonsterState.DIE.compare(monster.getState())){
				continue;
			}
			
			if(monster.getDisappearTime()>0 && System.currentTimeMillis()>monster.getDisappearTime()){
				ManagerPool.mapManager.monsterStopRun(monster);

				int areaId = ManagerPool.mapManager.getAreaId(monster.getPosition());
				//获得怪物所在区域
				Area area = map.getAreas().get(areaId);
				
				if(!area.getMonsters().containsKey(monster.getId())){
					monsterlog.error("Monster " + monster.getId() + " not in area " + area.getId());
					Iterator<Area> areas = map.getAreas().values().iterator();
					while (areas.hasNext()) {
						Area area2 = (Area) areas.next();
						if(area2.getMonsters().containsKey(monster.getId())){
							monsterlog.error("Monster " + monster.getId() + " in area " + area2.getId());
							area2.getMonsters().remove(monster.getId());
						}
					}
				}
				//地图上移除怪物
				area.getMonsters().remove(monster.getId());
				
				//周围区域宠物攻击列表中移除
				List<Area> areas = ManagerPool.mapManager.getRoundAreas(map, areaId);
				for (Area area2 : areas) {
					Iterator<Pet> iter = area2.getPets().iterator();
					while (iter.hasNext()) {
						Pet pet = (Pet) iter.next();
						pet.getAttackTargets().remove(monster);
					}
				}
				
				log.debug("Monster " + monster.getId() + " remove from map!");
				
				map.getMonsters().remove(monster.getId());
					
				monsterlog.error("Monster " + monster.getId() + "[" + monster.getModelId() + "] remove from map " + map.getId() + " [" + map.getMapModelid() + "] " + " area " + area.getId() + "!");

				ResRoundMonsterDisappearMessage msg = new ResRoundMonsterDisappearMessage();
				msg.getMonstersIds().add(monster.getId());
				MessageUtil.tell_round_message(monster, msg);
				continue;
			}
			
			//死亡中
			if((MonsterState.DIEING.compare(monster.getState()) && System.currentTimeMillis() >= monster.getDieTime() + Global.BOSS_DIEING) || MonsterState.DIEWAIT.compare(monster.getState())){

				//boss死亡
				monster.setState(MonsterState.DIE);
				
				IMonsterDieScript script = (IMonsterDieScript)ManagerPool.scriptManager.getScript(ScriptEnum.MONSTER_DIE);
				if(script!=null){
					try{
						script.onMonsterDie(monster, monster.getKiller());
					}catch (Exception e) {
						log.error(e, e);
					}
				}else{
					log.error("怪物死亡脚本不存在！");
				}
				
//				if (map.getZoneModelId() > 0) {
//					ManagerPool.zonesManager.zonesMonsterDie(monster,map);	//副本怪物死亡后触发
//				}
				
				int areaId = ManagerPool.mapManager.getAreaId(monster.getPosition());
				//获得怪物所在区域
				Area area = map.getAreas().get(areaId);
				
				if(!area.getMonsters().containsKey(monster.getId())){
					log.error("Monster " + monster.getId() + " not in area " + area.getId());
					Iterator<Area> areas = map.getAreas().values().iterator();
					while (areas.hasNext()) {
						Area area2 = (Area) areas.next();
						if(area2.getMonsters().containsKey(monster.getId())){
							log.error("Monster " + monster.getId() + " in area " + area2.getId());
							area2.getMonsters().remove(monster.getId());
						}
					}
				}
				//地图上移除怪物
				area.getMonsters().remove(monster.getId());
				
				//周围区域宠物攻击列表中移除
				List<Area> areas = ManagerPool.mapManager.getRoundAreas(map, areaId);
				for (Area area2 : areas) {
					Iterator<Pet> iter = area2.getPets().iterator();
					while (iter.hasNext()) {
						Pet pet = (Pet) iter.next();
						pet.getAttackTargets().remove(monster);
					}
				}
				
				log.debug("Monster " + monster.getId() + " remove from map!");
				
				if(monster.getDistributeId() > 0){
					//设定复活时间
					monster.setRevive(monster.getReviveTime() + 1);
					//添加到复活队列
					map.getRevives().put(monster.getId(), monster);
				}else{
					map.getMonsters().remove(monster.getId());
					
					monsterlog.error("Monster " + monster.getId() + "[" + monster.getModelId() + "] remove from map " + map.getId() + " [" + map.getMapModelid() + "] " + " area " + area.getId() + "!");
				}
				
				//发送怪物死亡消息
				ResMonsterDieMessage msg = new ResMonsterDieMessage();
				msg.setMonsterId(monster.getId());
				if(monster.getKiller()!=null){
					msg.setKiller(monster.getKiller().getId());
				}else{
					log.error("怪物死亡杀死者不明！");
				}
				msg.setDie((byte)monster.getState());
				MessageUtil.tell_round_message(monster, msg);
				
				monster.setKiller(null);
				
				continue;
			}
			
			//计算仇恨
			if(!empty)  monster.countHatreds();
		}
	}
}
