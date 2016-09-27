package com.game.pet.timer;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.cooldown.structs.CooldownTypes;
import com.game.fight.structs.FighterState;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.pet.struts.Pet;
import com.game.pet.struts.PetJumpState;
import com.game.pet.struts.PetRunState;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.timer.TimerEvent;
import com.game.utils.Global;
import com.game.utils.MapUtils;

/**
 * 宠物行走
 * @author 赵聪慧
 *
 */
public class PetStepTimer extends TimerEvent {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PetStepTimer.class);

	private int serverid;
	private int lineid;
	private int mapid;

	public PetStepTimer(int server_id, int line_id,int mapId) {
		super(-1,50);
		this.serverid=server_id;
		this.lineid=line_id;
		this.mapid=mapId;
	}

	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverid, lineid, mapid);
		if(map.isEmpty()) return;
		
		//获取地图格子
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());

		Iterator<Pet> iter = map.getRunningPets().values().iterator();
		while (iter.hasNext()) {
			Pet pet = (Pet) iter.next();
			
			if(pet.getServerId()!=this.serverid || pet.getLine()!=this.lineid || pet.getMap()!=this.mapid){
				iter.remove();
				continue;
			}
			
			if(pet.getJumpState()!=PetJumpState.NOMAL){
				
				if(System.currentTimeMillis() >= pet.getJump().getJumpStartTime() + pet.getJump().getTotalTime()){
					//跳跃结束
					iter.remove();
					
					pet.setJumpState(PetJumpState.NOMAL);
					ManagerPool.cooldownManager.addCooldown(pet, CooldownTypes.JUMP_COOLDOWN, null, 500);
				}
				
//				ManagerPool.mapManager.syncPlayerPosition(player);
//				ManagerPool.mapManager.broadcastPlayerStop(player);
				//player.setState(PlayerState.STAND);
//				ManagerPool.mapManager.setPlayerPosition(player, player.getPosition());

				continue;
			}
			
			// 宠物死亡
			if (pet.isDie()) {
				pet.getRoads().clear();
				iter.remove();
				continue;
			}
			
			if (ManagerPool.cooldownManager.isCooldowning(pet, CooldownTypes.PET_RUN, null)) {
				continue;
			}
			
			//定身或睡眠中
			if (FighterState.DINGSHEN.compare(pet.getFightState()) || FighterState.SHUIMIAN.compare(pet.getFightState())) {
				continue;
			}
			
			//移动路径
			List<Position> roads = pet.getRoads();
			
			//路径为空或不存在
			if(roads==null || roads.size()==0){
				//移动完成
				iter.remove();
				ManagerPool.mapManager.broadcastPetStop(pet);
				
				continue;
			}
			
			int time = 0;
			
			double cost = pet.getPrevStep() + pet.getCost() - System.currentTimeMillis();

			// 宠物原来坐标
			Position old = pet.getPosition();
			// 移动距离
			double speed = 0;

			if(grids==null){
				logger.error(pet.getMapModelId()+"找不到阻挡点信息");
				return;
			}
			while (time <= 0 && roads.size() > 0) {
				if(PetRunState.RUN==pet.getRunState()){
					speed = pet.getSpeed();
				}else{
					speed = Global.SPEED_FOR_SWIM;
				}
	
				// 第一拐点
				Position position = roads.remove(0);
				// 与第一拐点距离
				double distance = MapUtils.countDistance(pet.getPosition(), position);

				double use = distance * 1000 / speed;
				cost += use;
				//获取地图格子
				pet.setDirection((byte) MapUtils.countDirection(MapUtils.getGrid(pet.getPosition(), grids), MapUtils.getGrid(position, grids)));
				ManagerPool.petOptManager.setPetPosition(pet, position);
//				logger.debug("[" + System.currentTimeMillis() + "]宠物" + pet.getId() + "移动到：" + position.getX() + "," + position.getY() + ",移动真实耗时" + use+ ", 移动补充后耗时:" + cost);

				time = (int) cost;
				if (time > 0) {
					pet.setPrevStep(System.currentTimeMillis());
					pet.setCost(time);
					ManagerPool.cooldownManager.addCooldown(pet, CooldownTypes.PET_RUN, null, time);
				}
			}
	
			/**计算区域**/
			ManagerPool.mapManager.petChangeArea(pet, old, pet.getPosition());
		}
	}
}

	
