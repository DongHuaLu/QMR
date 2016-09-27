package com.game.pet.timer;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_skill_modelBean;
import com.game.fight.structs.Fighter;
import com.game.fight.structs.FighterState;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.pet.struts.PetRunState;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.skill.structs.Skill;
import com.game.structs.Grid;
import com.game.timer.TimerEvent;
import com.game.utils.MapUtils;

public class PetAttackTimer extends TimerEvent {

	/**
	 * Logger for this class
	 */
	protected static final Logger logger = Logger.getLogger(PetAttackTimer.class);
	
	private int serverId;
	private int lineId;
	private int mapId;
	
	public PetAttackTimer(int server_id, int line_id,int map_Id) {
		super(-1,100);
		serverId=server_id;
		lineId=line_id;
		mapId=map_Id;
	}
	
	@Override
	public void action() {
		// 获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		if (map.isEmpty())
			return;
		
		//地图阻挡信息
		Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
				
		Iterator<Pet> iterator = map.getPets().values().iterator();
		while (iterator.hasNext()) {
			Pet pet = (Pet) iterator.next();
			
			if(pet.getServerId()!=this.serverId || pet.getLine()!=this.lineId || pet.getMap()!=this.mapId){
				continue;
			}
			
			//定身或睡眠中
			if (FighterState.DINGSHEN.compare(pet.getFightState()) || FighterState.SHUIMIAN.compare(pet.getFightState())) {
				continue;
			}
			
			if (!pet.isShow() || pet.isDie()) {
				// 不显示的 和死的不处理
				continue;
			}
			
			// 宠物游泳中
			if (PetRunState.SWIM == pet.getRunState()) {
				logger.debug("宠物游泳中了");
				continue;
			}
			
			//攻击冷却检查
			if(ManagerPool.cooldownManager.isCooldowning(pet, CooldownTypes.PET_ATTACK, null)){
				continue;
			}
			
			Player owner = ManagerPool.playerManager.getPlayer(pet.getOwnerId());
			if(owner==null){
				logger.error("宠物主人不存在：" + pet.getOwnerId());
				continue;
			}
			
			// 游泳中
			if (PlayerState.SWIM.compare(owner.getState())) {
				logger.debug("攻击者主人（玩家）游泳中了");
				continue;
			}
			
			//获取目标
			Fighter target = pet.getAttTarget();
			
			//无攻击目标
			if(target==null) continue;
			//目标死亡
			if(target.isDie()){
				continue;
			}
			
			if(target instanceof Pet){
				continue;
			}
			
			if(target.getMap()!=pet.getMap() || target.getLine()!=pet.getLine()){
				continue;
			}
			
			if(target instanceof Player){
				Player player = (Player)target;
				//游泳状态
				if(PlayerState.SWIM.compare(player.getState())){
					continue;
				}
			}
			
			//怪物所在格子
			Grid petGrid = MapUtils.getGrid(pet.getPosition(), blocks);
			//获取目标所在格子
			Grid grid = MapUtils.getGrid(target.getPosition(), blocks);
			//是否在攻击范围内
			int distance = MapUtils.countDistance(petGrid, grid);
			//获取默认技能
			Skill skill = null;
			if(target instanceof Monster){
				skill = pet.getDefaultMutileSkill();
			}else if(target instanceof Player){
				skill = pet.getDefaultSingleSkill();
			}else{
				skill = pet.getDefaultSingleSkill();
			}
			if(skill==null) continue;
			//获取技能范围
			Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
			if(skillModel==null){
				continue;
			}
			
			//在攻击范围内
			if(distance <= skillModel.getQ_range_limit() - 1){
				//停止走路
				ManagerPool.mapManager.petStopRun(pet);
				
				if(ManagerPool.cooldownManager.isCooldowning(pet, CooldownTypes.PET_RUN, null)) continue;
				
				if(target instanceof Player){
					ManagerPool.fightManager.petAttackPlayer(pet, (Player)target, skill);
				}else if(target instanceof Monster){
					ManagerPool.fightManager.petAttackMonster(pet, (Monster)target, skill);
				}
			}
		}
	}

}
