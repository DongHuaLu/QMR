package com.game.map.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_skill_modelBean;
import com.game.fight.structs.Fighter;
import com.game.manager.ManagerPool;
import com.game.map.structs.GroundMagic;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.player.structs.Player;
import com.game.skill.structs.Skill;
import com.game.structs.Grid;
import com.game.timer.TimerEvent;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.RandomUtils;

public class GroundMagicTimer extends TimerEvent {
	/**
	 * Logger for this class
	 */
	private static final Logger log = Logger.getLogger(GroundMagicTimer.class);
	
	private int mapId;
	
	private int lineId;

	private int serverId;

	public GroundMagicTimer(int serverId, int lineId, int mapId) {
		super(-1,1000);
		this.serverId=serverId;
		this.lineId=lineId;
		this.mapId=mapId;
	}

	@Override
	public void action() {
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);

		//没有地面魔法
		if(map.getMagics().isEmpty()){
			return;
		}
		
		//格子
		Grid[][] mapBlocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		//格子玩家列表
		HashMap<Integer, ArrayList<Player>> players = new HashMap<Integer, ArrayList<Player>>();
		//格子宠物列表
//		HashMap<Integer, ArrayList<Pet>> pets = new HashMap<Integer, ArrayList<Pet>>();
		//格子怪物列表
		HashMap<Integer, ArrayList<Monster>> monsters = new HashMap<Integer, ArrayList<Monster>>();
		//玩家列表
		Iterator<Player> playeriter = map.getPlayers().values().iterator();
		while (playeriter.hasNext()) {
			Player player = (Player) playeriter.next();
			Grid grid = MapUtils.getGrid(player.getPosition(), mapBlocks);
			if (grid == null) {
				log.error("player" + " " + player.getId() + "(" + ") position " + player.getPosition() + " out map " + map.getMapModelid() + "!");
				continue;
			}
			if(players.containsKey(grid.getKey())){
				ArrayList<Player> gridplayers = players.get(grid.getKey());
				gridplayers.add(player);
			}else{
				ArrayList<Player> gridplayers = new ArrayList<Player>();
				gridplayers.add(player);
				players.put(grid.getKey(), gridplayers);
			}
			
		}
		
		//宠物列表
//		Iterator<Pet> petiter = map.getPets().values().iterator();
//		while (petiter.hasNext()) {
//			Pet pet = (Pet) petiter.next();
//			Grid grid = MapUtils.getGrid(pet.getPosition(), mapBlocks);
//			if (grid == null) {
//				log.error("player" + " " + pet.getId() + "(" + pet.getModelId() + ")  position " + pet.getPosition() + " out map " + map.getMapModelid() + "!");
//				continue;
//			}
//			if(pets.containsKey(grid.getKey())){
//				ArrayList<Pet> gridpets = pets.get(grid.getKey());
//				gridpets.add(pet);
//			}else{
//				ArrayList<Pet> gridpets = new ArrayList<Pet>();
//				gridpets.add(pet);
//				pets.put(grid.getKey(), gridpets);
//			}
//			
//		}
		
		//宠物列表
		Iterator<Monster> monsteriter = map.getMonsters().values().iterator();
		while (monsteriter.hasNext()) {
			Monster monster = (Monster) monsteriter.next();
			Grid grid = MapUtils.getGrid(monster.getPosition(), mapBlocks);
			if (grid == null) {
				log.error("player" + " " + monster.getId() + "(" + monster.getModelId() + ")  position " + monster.getPosition() + " out map " + map.getMapModelid() + "!");
				continue;
			}
			if(monsters.containsKey(grid.getKey())){
				ArrayList<Monster> gridmonsters = monsters.get(grid.getKey());
				gridmonsters.add(monster);
			}else{
				ArrayList<Monster> gridmonsters = new ArrayList<Monster>();
				gridmonsters.add(monster);
				monsters.put(grid.getKey(), gridmonsters);
			}
			
		}
				
		//地面魔法列表
		Iterator<GroundMagic> iterator = map.getMagics().values().iterator();
		while (iterator.hasNext()) {
			GroundMagic groundMagic = (GroundMagic) iterator.next();
			
			//获取地面魔法来源
			Player player = ManagerPool.playerManager.getPlayer(groundMagic.getSourceId());
			//地面魔法来源不存在
			if(player==null){
				iterator.remove();
				ManagerPool.fightManager.removeGroundMagic(groundMagic);
				continue;
			}
			//来源已经死亡
			if(player.isDie()){
				iterator.remove();
				ManagerPool.fightManager.removeGroundMagic(groundMagic);
				continue;
			}
			//来源不在本地图
			if(player.getMap()!=(int)map.getId() || player.getMapModelId()!=map.getMapModelid() || player.getLine()!=map.getLineId()){
				iterator.remove();
				ManagerPool.fightManager.removeGroundMagic(groundMagic);
				continue;
			}
			
			Skill skill = groundMagic.getSkill();
			Q_skill_modelBean model = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
			if(model==null){
				iterator.remove();
				ManagerPool.fightManager.removeGroundMagic(groundMagic);
				continue;
			}
			
			//可攻击列表
			List<Fighter> fighters = new ArrayList<Fighter>();
			//地面魔法覆盖格子
			for (Grid grid : groundMagic.getGrids()) {
				if(players.containsKey(grid.getKey())){
					fighters.addAll(players.get(grid.getKey()));
				}
//				if(pets.containsKey(grid.getKey())){
//					fighters.addAll(pets.get(grid.getKey()));
//				}
				// 这里龙皇最终技能特殊处理下,对怪物不触发
				if(skill.getSkillModelId() != 25027 && monsters.containsKey(grid.getKey())){
					fighters.addAll(monsters.get(grid.getKey()));
				}
			}
			
			List<Fighter> targets = ManagerPool.fightManager.randomSelectFighters(getFighter(player, fighters), model.getQ_target_max());
			for (Fighter fighter : targets) {
				ManagerPool.fightManager.damage(player, fighter, countDamage(player, fighter, skill), model.getQ_enmity());
			}
			
			//是否到时
			if(groundMagic.getStartTime() + groundMagic.getLastTime() < System.currentTimeMillis()){
				iterator.remove();
				ManagerPool.fightManager.removeGroundMagic(groundMagic);
				continue;
			}
			
		}

	}

	/**
	 * 技能选择目标
	 *
	 * @param attacker 攻击者
	 * @param fighters 战斗者集合
	 * @return
	 */
	private List<Fighter> getFighter(Fighter attacker, List<Fighter> fighters) {
		//目标集合
		List<Fighter> targets = new ArrayList<Fighter>();

		//选择目标
		Iterator<Fighter> iter = fighters.iterator();
		while (iter.hasNext()) {
			Fighter fighter = (Fighter) iter.next();
			if (fighter.isDie()) {
				continue;
			}
			if (fighter.getId() == attacker.getId()) {
				continue;
			}

			if (!ManagerPool.fightManager.checkCanAttack(attacker, fighter)) {
				continue;
			}

			targets.add(fighter);
		}

		return targets;
	}
	

	public int countDamage(Player attacker, Fighter defender, Skill skill){
		// 这里龙皇最终技能特殊处理下,每次10%的伤害
		if (skill.getSkillModelId() == 25027) {
			if (defender != null) {
				return defender.getMaxHp() / 10;
			}
			return 0;
		}
		
		Q_skill_modelBean model = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(attacker));
		if(model==null){
			return 0;
		}
		
		int damage = attacker.getAttack(skill);
		
		if (damage < 0) {
			damage = 0;
		}

		//随即攻击比例（最大攻击比例和最小攻击比例之间）
		int max_damage = 12000;
		int min_damage = 8000 + attacker.getLuck() * 35;
		int percent = RandomUtils.random(min_damage, max_damage);

		if (!ManagerPool.skillManager.isIgnoreDefense(model)) {
			int defense = defender.getDefense();
			damage = damage - defense;
		}

		//计算伤害加成
		damage = (int) (((double) damage) * model.getQ_hurt_correct_factor() / Global.MAX_PROBABILITY);
		if (damage < 0) {
			damage = 0;
		}
		damage = (int) ((double) damage * percent / Global.MAX_PROBABILITY);
		
		log.debug("attacker " + attacker.getName() + " damage " + damage);

		if (damage < 0) {
			damage = 0;
		}
		
		return damage;
	}
}
