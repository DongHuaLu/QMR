package com.game.monster.timer;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_monsterBean;
import com.game.data.bean.Q_skill_modelBean;
import com.game.fight.structs.Fighter;
import com.game.fight.structs.FighterState;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.monster.structs.MonsterState;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.skill.structs.Skill;
import com.game.structs.Grid;
import com.game.timer.TimerEvent;
import com.game.utils.MapUtils;

public class MonsterAttackTimer extends TimerEvent {

	private Logger log = Logger.getLogger(MonsterAttackTimer.class);
	
	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public MonsterAttackTimer(int serverId, int lineId, int mapId){
		super(-1, 100);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}
	
	@Override
	public void action() {
		//按地图，区域遍历怪物列表
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		
		if(map.isEmpty()) return;
		
		//地图阻挡信息
		Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		
		//遍历地区
//		Iterator<Area> areaIter = map.getAreas().values().iterator();
//		while (areaIter.hasNext()) {
//			Area area = (Area) areaIter.next();
//			
//			Monster[] monsters = area.getMonsters().values().toArray(new Monster[0]);
//			for (Monster monster : monsters) {
		Iterator<Monster> iter = map.getMonsters().values().iterator();
		while (iter.hasNext()) {
			Monster monster = (Monster) iter.next();
			
			//怪物死亡或正在跑回
			if(MonsterState.DIE.compare(monster.getState()) || MonsterState.DIEWAIT.compare(monster.getState()) || MonsterState.RUNBACK.compare(monster.getState())){
				continue;
			}
			
			//死亡中
			if(MonsterState.DIEING.compare(monster.getState())){
				continue;
			}
			
			//怪物正在攻击CD
			if(ManagerPool.cooldownManager.isCooldowning(monster, CooldownTypes.MONSTER_ATTACK, null)){
				continue;
			}
			//查找怪物模板
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(monster.getModelId());
			if(model==null){
				log.error("Monster Model " + monster.getModelId() + " 没有找到！");
				continue;
			}
			//木桩式怪物
			if(monster.getAttackType() == 3) continue;
			//获取目标
			Fighter target = monster.getTarget();
			
			//无攻击目标
			if(target==null) continue;
			//目标死亡
			if(target.isDie()) continue;
			
			if(target.getMap()!=monster.getMap() || target.getLine()!=monster.getLine()) continue;
			
			if(target instanceof Player){
				Player player = (Player)target;
				//游泳状态
				if(PlayerState.SWIM.compare(player.getState())){
					continue;
				}
			}
			//定身或睡眠中
			if(FighterState.DINGSHEN.compare(monster.getFightState()) || FighterState.SHUIMIAN.compare(monster.getFightState())) continue;
			
			//怪物所在格子
			Grid monsterGrid = MapUtils.getGrid(monster.getPosition(), blocks);
			//获取目标所在格子
			Grid grid = MapUtils.getGrid(target.getPosition(), blocks);
			//是否在攻击范围内
			int distance = MapUtils.countDistance(monsterGrid, grid);
			//获取默认技能
			Skill skill = monster.getDefaultSkill(model);
			
			if(skill==null) continue;
			//获取技能范围
			Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
			
			
			//在攻击范围内
			if(distance <= skillModel.getQ_range_limit() - 1){
				//停止走路
				ManagerPool.mapManager.monsterStopRun(monster);
				
				if(ManagerPool.cooldownManager.isCooldowning(monster, CooldownTypes.MONSTER_RUN, null)) continue;
				
				//log.info("怪物" + monster.getId() + "在" + monster.getPosition() + "使用范围为" + skillModel.getQ_range_limit() + "的" + skill.getSkillModelId() + "技能攻击坐标为" + target.getPosition() + "的目标" + target.getId());
				//攻击
				Skill attSkill = monster.getSkill();
				if(attSkill!=null){
					if(target instanceof Player){
						ManagerPool.fightManager.monsterAttackPlayer(monster, (Player)target, attSkill);
					}else if(target instanceof Monster){
						ManagerPool.fightManager.monsterAttackPlayer(monster, (Monster)target, attSkill);
					}
				}
			}
		}
	}
}
