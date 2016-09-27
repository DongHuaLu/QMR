package scripts.monster;

import java.util.List;

import com.game.fight.structs.Fighter;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.monster.manager.MonsterManager;
import com.game.monster.script.IMonsterAiScript;
import com.game.monster.structs.Monster;
import com.game.skill.structs.Skill;

public class SimenBossAiScript implements IMonsterAiScript {

	
	private static int ADOU = 13501;
	@Override
	public int getId() {
		return 2012002;
	}

	@Override
	public boolean wasHit(Monster monster,Fighter attk) {
		return false;
	}

	@Override
	public Fighter getAttackTarget(Monster monster ) {
		//获取默认目标，如果没有就会攻击阿斗
		Fighter target = monster.getDefaultAttackTarget();
		if(target==null){
			Map map = ManagerPool.mapManager.getMap(monster);
			if(map==null) return null;
			List<Monster> monsters = MonsterManager.getInstance().getSameMonster(map, ADOU);
			if(monster!=null && monsters.size()>0){
				if(!monster.isDie()) return monsters.get(0);
			}
		}
		return target;
	}

	@Override
	public Skill getSkill(Monster monster ) {
		return monster.getUseSkill();
	}

}
