package com.game.map.timer;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.cooldown.structs.CooldownTypes;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.monster.script.IMonsterStopScript;
import com.game.monster.structs.Monster;
import com.game.monster.structs.MonsterState;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.timer.TimerEvent;
import com.game.utils.MapUtils;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-8-31
 * 
 * 怪物移动
 */
public class MonsterStepTimer extends TimerEvent {
	
	Logger log = Logger.getLogger(MonsterStepTimer.class);
	
	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public MonsterStepTimer(int serverId, int lineId, int mapId){
		super(-1, 50);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}

	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		//获取地图格子
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		
		Iterator<Monster> iter = map.getRunningMonsters().values().iterator();
		while (iter.hasNext()) {
			Monster monster = (Monster) iter.next();
			//怪物死亡
			if(MonsterState.DIE.compare(monster.getState()) || MonsterState.DIEING.compare(monster.getState()) || MonsterState.DIEWAIT.compare(monster.getState())){
				monster.getRoads().clear();
				iter.remove();
				continue;
			}
			
			if(ManagerPool.cooldownManager.isCooldowning(monster, CooldownTypes.MONSTER_RUN, null)){
				continue;
			}
			
			//移动路径
			List<Position> roads = monster.getRoads();
			//路径为空或不存在
			if(roads==null || roads.size()==0){
				if(MonsterState.RUNBACK.compare(monster.getState())){
					monster.cleanHatreds();
					monster.setTarget(null);
					monster.setState(MonsterState.NORMAL);
				}
				//怪物移动完成脚本
				IMonsterStopScript script = (IMonsterStopScript)ManagerPool.scriptManager.getScript(ScriptEnum.MONSTER_STOP);
				if (script != null) {
					try {
						script.stop(monster);
					} catch (Exception e) {
						log.error(e, e);
					}
				} else {
					log.error("怪物停止脚本不存在！");
				}
				
				//移动完成
				iter.remove();
				continue;
			}
			
			int time = 0;
			double cost = monster.getPrevStep() + monster.getCost() - System.currentTimeMillis();
			
			//人物原来坐标
			Position old = monster.getPosition();
			//移动距离
			double speed = monster.getSpeed();
			
			while(time<=0 && roads.size()>0){
				//第一拐点
				Position position = roads.remove(0);
				//与第一拐点距离
				double distance = MapUtils.countDistance(monster.getPosition(), position);
				
				double use = distance * 1000 / speed;
				cost += use;
				monster.setDirection((byte)MapUtils.countDirection(MapUtils.getGrid(monster.getPosition(), grids), MapUtils.getGrid(position, grids)));
				monster.setPosition(position);
//				log.error("[" + System.currentTimeMillis() + "]怪物" + monster.getId() + "移动到：" + position.getX() + "," + position.getY() + ",移动真实耗时" + use + ", 移动补充后耗时:" + cost);
				
				time = (int)cost;
				if(time > 0){
					monster.setPrevStep(System.currentTimeMillis());
					monster.setCost(time);
					ManagerPool.cooldownManager.addCooldown(monster, CooldownTypes.MONSTER_RUN, null, time);
				}
			}
//			//第一拐点左边
//			Position position = roads.get(0);
//			//人物原来坐标
//			Position old = monster.getPosition();
//			//与第一拐点距离
//			double distance = MapUtils.countDistance(monster.getPosition(), position);
//			//移动距离
//			double speed = monster.getSpeed() / 10;
//			//移动是否完成
//			boolean moveOver = false;
//			//移动是否过拐点
//			while(distance < speed){
//				//已过拐点，路径中移除此点
//				roads.remove(0);
//				//移动距离中减去距离拐点的距离
//				speed = speed - distance;
//				//玩家当前坐标移动到拐点
//				monster.setPosition(position);
//				//确定方向
//				if(roads.size() > 0){
//					monster.setDirection((byte)MapUtils.countDirection(MapUtils.getGrid(monster.getPosition(), grids), MapUtils.getGrid(roads.get(0), grids)));
//				}
//				//是否移动完成
//				if(roads.size()==0 || speed==0){
//					moveOver = true;
//					break;
//				}
//				//取下一拐点
//				position = roads.get(0);
//				//计算距离
//				distance = MapUtils.countDistance(monster.getPosition(), position);
//			}
//			if(!moveOver){
//				//计算X轴移动距离
//				int x = (int)((position.getX() - monster.getPosition().getX()) * speed / distance);
//				//计算Y轴移动距离
//				int y = (int)((position.getY() - monster.getPosition().getY()) * speed / distance);
//				
//				Position movePostion = new Position();
//				movePostion.setX((short)(monster.getPosition().getX() + x));
//				movePostion.setY((short)(monster.getPosition().getY() + y));
//				//移动
//				monster.setPosition(movePostion);
//				
//			}
//			
//			//log.info("怪物" + monster.getId() + "移动到" + monster.getPosition());
//	
			/**计算区域**/
			//原来所在区域
			int oldAreaId = ManagerPool.mapManager.getAreaId(old);
			//现在所在区域
			int newAreaId = ManagerPool.mapManager.getAreaId(monster.getPosition());
			//区域未变
			if(oldAreaId != newAreaId){
				ManagerPool.mapManager.monsterChangeArea(monster, oldAreaId, newAreaId);
			}
//			
//			//路径为空或不存在
//			if(roads==null || roads.size()==0){
//				if(MonsterState.RUNBACK.compare(monster.getState())){
//					monster.setHp(monster.getMaxHp());
//					ManagerPool.monsterManager.onHpChange(monster);
//					monster.setMp(monster.getMaxMp());
//					ManagerPool.monsterManager.onMpChange(monster);
//					monster.setSp(monster.getMaxSp());
//					ManagerPool.monsterManager.onSpChange(monster);
//					monster.setState(MonsterState.NORMAL);
//				}
//				//移动完成
//				iter.remove();
//			}
		}
	}
}
