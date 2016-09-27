package scripts.monster;

import java.util.List;
import java.util.ListIterator;

import com.game.cooldown.manager.CooldownManager;
import com.game.cooldown.structs.CooldownTypes;
import com.game.drop.manager.DropManager;
import com.game.fight.structs.Fighter;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.structs.Map;
import com.game.monster.script.IMonsterAiScript;
import com.game.monster.structs.Monster;
import com.game.skill.structs.Skill;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.utils.MapUtils;
import com.game.utils.RandomUtils;

/**
 * 测试AI boss血量小于80%的时候每1分钟召唤一次小怪
 *
 * @author heyang
 *
 */
public class TestBossAiScript implements IMonsterAiScript {

//	private static String ZHAO_HUAN = "zhaohuan";
//	//召唤数量
//	private static int NUM = 10;
//	//召唤怪物模板
//	private static int MODEL = 8;
	//金钱盗贼怪物id
	private static int GOLDTHIEF = 5008;
	//金钱盗贼BOSS怪物id
	private static int GOLDTHIEFBOSS = 5009;
	//招财猫ID
	private static int LUCKY_CAT = 5010;
	

	@Override
	public Fighter getAttackTarget(Monster monster ) {
		//return monster.getDefaultAttackTarget();
		return null;
	}

	@Override
	public Skill getSkill(Monster monster ) {
//		double hp = (double) monster.getHp() * 100 / monster.getMaxHp();
//		if (hp < 80) {
//			if (!ManagerPool.cooldownManager.isCooldowning(monster, CooldownTypes.SKILL, ZHAO_HUAN)) {
//				//添加怪物召唤冷却
//				ManagerPool.cooldownManager.addCooldown(monster, CooldownTypes.SKILL, ZHAO_HUAN, (long) 60000);
//				//添加怪物攻击冷却
//				ManagerPool.cooldownManager.addCooldown(monster, CooldownTypes.MONSTER_ATTACK, null, (long) 2000);
//				//添加怪物攻击动画冷却
//				ManagerPool.cooldownManager.addCooldown(monster, CooldownTypes.MONSTER_ACTION, null, (long) 500);
//
//				//召唤小弟
//				List<Grid> roundNoBlockGrid = MapUtils.getRoundNoBlockGrid(monster.getPosition(), 50, monster.getMap());
//				List<Grid> roundNoBlockGrids = new ArrayList<Grid>();
//				int num = NUM;
//				while (num > 0) {
//					if (roundNoBlockGrids.size() == 0) {
//						roundNoBlockGrids.addAll(roundNoBlockGrid);
//					}
//					Grid grid = roundNoBlockGrids.remove(RandomUtils.random(roundNoBlockGrids.size()));
//					ManagerPool.monsterManager.createMonster(MODEL, monster.getServerId(), monster.getLine(), monster.getMap(), grid.getCenter());
//					num--;
//				}
//
//				return null;
//			}
//
//		}
//
//		return monster.getUseSkill();
		return null;
	}

	@Override
	public boolean wasHit(Monster monster,Fighter attk) {
		if (monster.getModelId() == GOLDTHIEF || monster.getModelId() == GOLDTHIEFBOSS || monster.getModelId() == LUCKY_CAT) {
			//判断冷却
			if (CooldownManager.getInstance().isCooldowning(monster, CooldownTypes.GOLDTHIEF_COOL, null)) {
				return false;
			}
			CooldownManager.getInstance().addCooldown(monster, CooldownTypes.GOLDTHIEF_COOL, null, 2000);
			//先掉落
			DropManager.bossCorpseDrop(monster);
			//后随机移动
			Map map = MapManager.getInstance().getMap(monster);
			if (map == null) {
				return false;
			}
			Grid[][] mapInfos = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
			Grid monsterGrid = MapUtils.getGrid(monster.getPosition(), mapInfos);
			List<Grid> grids10 = MapUtils.getRoundNoBlockGrid(monster.getPosition(), 10 * MapUtils.GRID_BORDER, map.getMapModelid());
			List<Grid> grids20 = MapUtils.getRoundNoBlockGrid(monster.getPosition(), 20 * MapUtils.GRID_BORDER, map.getMapModelid());
			for (int i = 0; i < grids10.size(); i++) {
				Grid grid = grids10.get(i);
				if (grid != null) {
					grids20.remove(grid);
				}
			}
			ListIterator<Grid> listIterator = grids20.listIterator();
			while (listIterator.hasNext()) {
				Grid grid = listIterator.next();
				if (grid != null && grid.getBlock() != monsterGrid.getBlock()) {
					listIterator.remove();
				}
			}
			if (!grids20.isEmpty()) {
				Grid grid = grids20.get(RandomUtils.random(grids20.size()));
				if (grid != null) {
					List<Position> positions = MapUtils.findRoads(mapInfos, monster.getPosition(), grid.getCenter(), -1, false);
					MapManager.getInstance().monsterRunning(monster, positions);
				}
			}
			
	
		}
		
		
		
		
		return false;
	}

	@Override
	public int getId() {
		return 1002;
	}
}
