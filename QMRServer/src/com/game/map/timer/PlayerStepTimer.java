package com.game.map.timer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.cooldown.structs.CooldownTypes;
import com.game.fight.structs.FighterState;
import com.game.manager.ManagerPool;
import com.game.map.script.IEnterGridScript;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.server.impl.WServer;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.timer.TimerEvent;
import com.game.utils.CommonConfig;
import com.game.utils.Global;
import com.game.utils.MapUtils;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-8-31
 * 
 * 玩家移动
 */
public class PlayerStepTimer extends TimerEvent {
	
	protected Logger log = Logger.getLogger(PlayerStepTimer.class);
	
	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	private long last;
	
	public PlayerStepTimer(int serverId, int lineId, int mapId){
		super(-1, 50);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}

	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		
		if(true){
			int max = 0;
			if(last!=0 && map.getPlayers().size()>0){
				String key = lineId + "_" + mapId;
				int maxstep = 0;
				if(WServer.delay.containsKey(key)){
					maxstep = WServer.delay.get(key);
				}
				max = (int)(System.currentTimeMillis() - last);
				if(maxstep < max){
					maxstep = max;
					WServer.delay.put(key, maxstep);
				}
			}
			last = System.currentTimeMillis();
		}
		//获取地图格子
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		
		ConcurrentHashMap<Integer, Vector<IEnterGridScript>> pitfalls = ManagerPool.pitfallManager.getPitfalls().get(map.getMapModelid());
		
		HashMap<Long, Player> players = map.getRunningPlayers();
		//获取玩家
		Iterator<Player> iter = players.values().iterator();
		while (iter.hasNext()) {
			Player player = (Player) iter.next();
			
			if(player.getMap()!=mapId || player.getLine()!=lineId){
				iter.remove();
				continue;
			}
			
			if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.JUMP, null)){
				continue;
			}else if(PlayerState.JUMP.compare(player.getState()) || PlayerState.DOUBLEJUMP.compare(player.getState())){
				//在跳跃保护中，添加跳跃保护冷却
				if(player.isJumpProtect()){
					//跳跃结束
					int time = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.JUMP_COOLDOWN.getValue()).getQ_int_value();
					if(time>0) ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.JUMP_COOLDOWN, null, time);
				}
				//移除跳跃保护
				player.setJumpProtect(false);
//					//通知前台跳跃完成调试用
//					if(PlayerState.JUMP.compare(me.getState())){
//						MessageUtil.notify_player(me, Notifys.ERROR, "一次跳跃完成");
//						log.debug(me.getId() + "一次跳跃完成");
//					}else{
//						MessageUtil.notify_player(me, Notifys.ERROR, "二次跳跃完成");
//						log.debug(me.getId() + "二次跳跃完成");
//					}
				log.debug("跳跃结束!" + System.currentTimeMillis());
				ManagerPool.mapManager.syncPlayerPosition(player);
				ManagerPool.mapManager.broadcastPlayerStop(player);
				//player.setState(PlayerState.STAND);
				ManagerPool.mapManager.setPlayerPosition(player, player.getPosition());
				
//				IEnterGridScript script = (IEnterGridScript)ManagerPool.scriptManager.getScript(ScriptEnum.ENTER_GRID);
//				if(script!=null){
//					try{
//						script.onEnterGrid(player, map, player.getPosition());
//					}catch (Exception e) {
//						log.error(e, e);
//					}
//				}else{
//					log.error("进入格子脚本不存在！");
//				}
				
				iter.remove();
				
				
				int tranId = ManagerPool.mapManager.isOnTransPoint(player);
				if(tranId!=-1){
					int line = 0;
					if(player.getChangeLine() > 0){
						line = player.getChangeLine();
						player.setChangeLine(0);
					}
					
					ManagerPool.mapManager.changeMapByMove(player, line, tranId);
				}

				continue;
			}
			
			if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.RUN, null, 0)){
				continue;
			}
			
			//移动路径
			List<Position> roads = player.getRoads();
			
			//路径为空或不存在
			if(roads==null || roads.size()==0){
				//移动完成
				if(PlayerState.RUN.compare(player.getState())) ManagerPool.mapManager.setPlayerPosition(player, player.getPosition());
				iter.remove();
				
				ManagerPool.mapManager.syncPlayerPosition(player);
				ManagerPool.mapManager.broadcastPlayerStop(player);
				
				int tranId = ManagerPool.mapManager.isOnTransPoint(player);
				if(tranId!=-1){
					int line = 0;
					if(player.getChangeLine() > 0){
						line = player.getChangeLine();
						player.setChangeLine(0);
					}
					
					ManagerPool.mapManager.changeMapByMove(player, line, tranId);
				}
				
				
//					//通知前台走路完成调试用
//					MessageUtil.notify_player(me, Notifys.ERROR, "走路完成");
//				log.error("走路完成");
				continue;
			}
			
			int time = 0;
			double cost = player.getPrevStep() + player.getCost() - System.currentTimeMillis();
			
			//人物原来坐标
			Position old = player.getPosition();
			
			while(time <= 0 && roads.size()>0){
				//移动距离
				double speed = player.getSpeed();
				if(PlayerState.SWIM.compare(player.getState())){
					speed = Global.SPEED_FOR_SWIM;
				}
				//第一拐点
				Position position = roads.remove(0);
				//与第一拐点距离
				double distance = MapUtils.countDistance(player.getPosition(), position);
				
				double use = distance * 1000 / speed;
				cost += use;
				
				Grid destGrid = MapUtils.getGrid(position, grids);
				player.setDirection((byte)MapUtils.countDirection(MapUtils.getGrid(player.getPosition(), grids), destGrid));

				ManagerPool.mapManager.setPlayerPosition(player, position);
				
				if(FighterState.DAMAGEONMOVE.compare(player.getFightState())){
					player.setMovestep(player.getMovestep() + 1);
					if(player.getMovestep() % 6 == 0){
						player.setMovestep(0);
						//减少30%血，最少减到1
						int hp = (int)(((double)player.getMaxHp() * 3000) / Global.MAX_PROBABILITY);
						player.setHp(player.getHp() - hp);
						if(player.getHp() <=0 ) player.setHp(1);
						ManagerPool.playerManager.onHpChange(player);
					}
				}
				
				log.debug("[" + System.currentTimeMillis() + "]玩家" + player.getId() +  "移动到：" + position.getX() + "," + position.getY() + ",移动真实耗时" + use + ", 移动补充后耗时:" + cost);
				
				if(pitfalls!=null){
					if(pitfalls.containsKey(destGrid.getKey())){
						Vector<IEnterGridScript> scripts = pitfalls.get(destGrid.getKey());
						if(scripts!=null){
							for (int i = 0; i < scripts.size(); i++) {
								IEnterGridScript script = scripts.get(i);
								try{
									script.onEnterGrid(player, map, destGrid);
								}catch (Exception e) {
									log.error(e, e);
								}
							}
						}
					}
				}
				
				time = (int)cost;
				if(time > 0){
					player.setPrevStep(System.currentTimeMillis());
					player.setCost(time);
					ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.RUN, null, time);
				}
			}
			
//			double moveDistance = 0;
//			//移动是否完成
//			boolean moveOver = false;
//			//移动是否过拐点
//			while(distance < speed){
//				//已过拐点，路径中移除此点
//				roads.remove(0);
//				//移动距离中减去距离拐点的距离
//				speed = speed - distance;
//				
//				moveDistance += MapUtils.countDistance(me.getPosition(), position);
//				//玩家当前坐标移动到拐点
//				me.setPosition(position);
//				log.debug(System.currentTimeMillis() + " 移动到拐点：" + position.getX() + "," + position.getY());
//				//确定方向
//				if(roads.size() > 0){
//					if(PlayerState.RUN.compare(me.getState())) me.setDirection((byte)MapUtils.countDirection(MapUtils.getGrid(me.getPosition(), grids), MapUtils.getGrid(roads.get(0), grids)));
//				}
//				//是否移动完成
//				if(roads.size()==0 || speed==0){
//					moveOver = true;
//					break;
//				}
//				//取下一拐点
//				position = roads.get(0);
//				
//				//计算距离
//				distance = MapUtils.countDistance(me.getPosition(), position);
//			}
//			if(!moveOver){
//				//计算X轴移动距离
//				int x = (int)Math.round((position.getX() - me.getPosition().getX()) * speed / distance);
//				//计算Y轴移动距离
//				int y = (int)Math.round((position.getY() - me.getPosition().getY()) * speed / distance);
//				
//				Position movePostion = new Position();
//				movePostion.setX((short)(me.getPosition().getX() + x));
//				movePostion.setY((short)(me.getPosition().getY() + y));
//				//移动
//				moveDistance += MapUtils.countDistance(me.getPosition(), movePostion);
//				me.setPosition(movePostion);
//				
//			}
//			
			ManagerPool.playerManager.savePlayer(player);
	
			/**计算区域**/
			//原来所在区域
			int oldAreaId = ManagerPool.mapManager.getAreaId(old);
			//现在所在区域
			int newAreaId = ManagerPool.mapManager.getAreaId(player.getPosition());
			//区域未变
			if(oldAreaId != newAreaId){
				ManagerPool.mapManager.playerChangeArea(player, oldAreaId, newAreaId);
			}
			
//			//路径为空或不存在
//			if(roads==null || roads.size()==0){
//				if(PlayerState.JUMP.compare(me.getState()) || PlayerState.DOUBLEJUMP.compare(me.getState())){
//					//跳跃结束
//					if(me.getJump().getJumpStartTime() + me.getJump().getTotalTime() < System.currentTimeMillis()){
//						int time = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.JUMP_COOLDOWN.getValue()).getQ_int_value();
//						ManagerPool.cooldownManager.addCooldown(me, CooldownTypes.JUMP, null, time);
//						
////						//通知前台跳跃完成调试用
////						if(PlayerState.JUMP.compare(me.getState())){
////							MessageUtil.notify_player(me, Notifys.ERROR, "一次跳跃完成");
////							log.debug("一次跳跃完成");
////						}else{
////							MessageUtil.notify_player(me, Notifys.ERROR, "二次跳跃完成");
////							log.debug("二次跳跃完成");
////						}
//						
//						ManagerPool.mapManager.syncPlayerPosition(me);
//						ManagerPool.mapManager.broadcastPlayerStop(me);
//						
//						me.setState(PlayerState.STAND);
//						iter.remove();
//					}
//				}else{
//					if(PlayerState.RUN.compare(me.getState())) me.setState(PlayerState.STAND);
//					//移动完成
//					iter.remove();
//					
//					ManagerPool.mapManager.syncPlayerPosition(me);
//					ManagerPool.mapManager.broadcastPlayerStop(me);
////					//通知前台走路完成调试用
////					MessageUtil.notify_player(me, Notifys.ERROR, "走路完成");
//					log.debug("走路完成");
//				}
//			}
		}
	}
	
}
