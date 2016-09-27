package com.game.monster.timer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_monsterBean;
import com.game.data.bean.Q_skill_modelBean;
import com.game.fight.structs.Fighter;
import com.game.fight.structs.FighterState;
import com.game.manager.ManagerPool;
import com.game.map.structs.Area;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.monster.structs.MonsterState;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.skill.structs.Skill;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.timer.TimerEvent;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.RandomUtils;

public class MonsterAiTimer extends TimerEvent {

	private Logger log = Logger.getLogger(MonsterAiTimer.class);
	
	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public MonsterAiTimer(int serverId, int lineId, int mapId){
		super(-1, 500);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}
	

	
	
	@Override
	public void action() {
//		long start = System.currentTimeMillis();
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		
		if(map.isEmpty()) return;
		
		//地图阻挡信息
		Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
			
		Iterator<Monster> iter = map.getMonsters().values().iterator();
		while (iter.hasNext()) {
			Monster monster = (Monster) iter.next();
//					//计算仇恨
//					monster.countHatreds();
			
			//获取进攻目标
			Fighter target = monster.getAttackTarget();
			
			//怪物死亡或正在跑回
			if(MonsterState.DIE.compare(monster.getState()) || MonsterState.DIEWAIT.compare(monster.getState()) || MonsterState.RUNBACK.compare(monster.getState())){
				continue;
			}

			//死亡中
			if(MonsterState.DIEING.compare(monster.getState())){
				continue;
			}
			
			//怪物正在攻击动画播放
			if(ManagerPool.cooldownManager.isCooldowning(monster, CooldownTypes.MONSTER_ACTION, null)){
				continue;
			}
			
			//定身或睡眠中
			if(FighterState.DINGSHEN.compare(monster.getFightState()) || FighterState.SHUIMIAN.compare(monster.getFightState())) continue;
			
			//查找怪物模板
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(monster.getModelId());
			if(model==null){
				log.error("Monster Model " + monster.getModelId() + " 没有找到！");
				iter.remove();
				continue;
			}
			//木桩式怪物
			if(monster.getAttackType() == 3) continue;
			
			//怪物所在阻挡格
			Grid monsterGrid = MapUtils.getGrid(monster.getPosition(), blocks);
			
			if(monsterGrid==null){
				log.error("Monster Model " + monster.getModelId() + "(" + monster.getPosition() + ") 所在地点没有找到！");
				iter.remove();
				continue;
			}
			
			//获得怪物出生点
			Position birth = monster.getBirthPos();
			
			//路径集
			List<Position> roads = new ArrayList<Position>();
			//无攻击目标
			if(target==null){
				//是否主动攻击怪
				if(monster.getAttackType() == 1){
					//寻找警戒半径内的玩家
					List<Player> players = new ArrayList<Player>();
					
					//获得警戒半径以内区域
					int[] rounds = ManagerPool.mapManager.getRoundAreas(monster.getPosition(), map, model.getQ_eyeshot() * MapUtils.GRID_BORDER);
					for (int i = 0; i < rounds.length; i++) {
						Area roundArea = map.getAreas().get(rounds[i]);
						if(roundArea==null) continue;
						//遍历区域内玩家
						Iterator<Player> player_iter = roundArea.getPlayers().iterator();
						while (player_iter.hasNext()) {
							Player player = (Player)player_iter.next();
							if(!monster.canSee(player)) continue;
							if(!monster.canAttack(player)) continue;
							//计算距离
							int distance = MapUtils.countDistance(monsterGrid, MapUtils.getGrid(player.getPosition(), blocks));
							//在警戒范围内
							if(distance <= model.getQ_eyeshot()){
								if(!PlayerState.SWIM.compare(player.getState())) players.add(player);
							}
						}
					}
					// 设定攻击目标
					if(players.size() > 0){
						target = players.get(RandomUtils.random(players.size()));
						//设置怪物仇恨度
						ManagerPool.fightManager.addHatred(monster, target, 1);
					}
				}
				
				//无攻击目标
				if(target==null && !ManagerPool.cooldownManager.isCooldowning(monster, CooldownTypes.MONSTER_PATROL, null)){
					
					//战斗中失去目标后回来
					if(MonsterState.FIGHT.compare(monster.getState())){
						monster.setState(MonsterState.NORMAL);
						
						//停止走路
						monster.getRoads().clear();
						//寻路回到出生点
						roads = MapUtils.findRoads(blocks, monster.getPosition(), birth, -1, false);
						
						//开始走动
						if(roads.size() > 0){
							ManagerPool.mapManager.monsterRunning(monster, roads);
							monster.setState(MonsterState.RUNBACK);
						}else{
							monster.setState(MonsterState.NORMAL);
						}
						
						continue;
					}
					
					//是否自动走动
					if(model.getQ_patrol()==0){
						continue;
					}
					
					//计算几率
					if(RandomUtils.random(Global.MAX_PROBABILITY) >= monster.getPatrolPro()){
						continue;
					}
						
					Grid centerGrid = MapUtils.getGrid(birth, blocks);
					
					int dir = RandomUtils.random(8);
					
					int[] add = MapUtils.countDirectionAddtion(dir);
					
					boolean over = false;
					int gridx = monsterGrid.getX();
					int gridy = monsterGrid.getY();
					//确定目标点
					while(!over){
						gridx = gridx + add[0];
						gridy = gridy + add[1];
						Grid grid = MapUtils.getGrid(gridx, gridy, blocks);
						
						//在地图内是不可行走点
						if(grid==null || grid.getBlock() == 0 || grid.getBlock()!=monsterGrid.getBlock()){
							over = true;
							continue;
						}
						//斜向判断周围是不可行走点
						else if(dir % 2!=0){
							if(blocks[gridy][gridx - add[0]].getBlock() == 0 && blocks[gridy - add[1]][gridx].getBlock() == 0){
								over = true;
								continue;
							}
						}
						//就是当前怪物所在格
						else if(MapUtils.countDistance(grid, centerGrid) > model.getQ_patrol()){
							over = true;
							continue;
						}
						//就是当前怪物所在格
						else if(monsterGrid.equals(grid)){
							over = true;
							continue;
						}else{
							roads.add(grid.getCenter());
							if(roads.size()>=5){
								over = true;
							}
						}
					}

					//开始走动
					if(roads.size() > 0){
						roads.add(0, monsterGrid.getCenter());
						ManagerPool.cooldownManager.addCooldown(monster, CooldownTypes.MONSTER_PATROL, null, monster.getPatrolTime());
						ManagerPool.mapManager.monsterRunning(monster, roads);
					}
					
					continue;
				}
			}
			
			//设置攻击目标
			monster.setTarget(target);
			
			//跑出追踪范围
			int distance = MapUtils.countDistance(MapUtils.getGrid(birth, blocks), monsterGrid);
			//在巡逻范围以外
			if(distance > model.getQ_pursuit()){
				//停止走路
				monster.getRoads().clear();
				//寻路回到出生点
				roads = MapUtils.findRoads(blocks, monster.getPosition(), birth, -1, false);
				
				//开始走动
				if(roads.size() > 0){
					ManagerPool.mapManager.monsterRunning(monster, roads);
					monster.setState(MonsterState.RUNBACK);
				}else{
					monster.setState(MonsterState.NORMAL);
				}
				
				continue;
			}
			
			//已有攻击目标
			if(target!=null){
				monster.setState(MonsterState.FIGHT);
				//获取目标所在格子
				Grid grid = MapUtils.getGrid(target.getPosition(), blocks);
				//是否在攻击范围内
				distance = MapUtils.countDistance(monsterGrid, grid);
				//获取默认技能
				Skill skill = monster.getDefaultSkill(model);
				
				if(skill==null) continue;
				//获取技能范围
				Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
				//在攻击范围内
				if(distance > skillModel.getQ_range_limit() - 1){
					if((ManagerPool.monsterManager.isBoss(monster.getModelId()) || ManagerPool.monsterManager.ckWayfindingmapid(map.getMapModelid()))&& grid.getBlock()==monsterGrid.getBlock()){
						//寻路到目标身边
						roads = MapUtils.findRoads(blocks, monster.getPosition(), MapUtils.getRandomGrid(target.getPosition(), 50,map.getMapModelid()).getCenter(), -1, false);
					}else{
						//寻路到目标身边
						roads = MapUtils.findRoads(blocks, monster.getPosition(), MapUtils.getRandomGrid(target.getPosition(), 50,map.getMapModelid()).getCenter(), 6, false);
					}
					log.debug("monster " + monster.getId() + "target" + target.getPosition() + " run " + roads);
					//开始走动
					if(roads.size() > 0){
						if(roads.get(roads.size() - 1).equal(target.getPosition())) roads.remove(roads.size() - 1);
						//log.info("怪物" + monster.getId() + "(" + monster.getPosition() + ")追击目标" + target.getId() + "(" + target.getPosition() + ")路径为" + other_msg.toString());
						
						ManagerPool.mapManager.monsterRunning(monster, roads);
					}
				}
			}
		}
	}
	
	

}
