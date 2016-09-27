package scripts.monster;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.fight.structs.Fighter;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.monster.manager.MonsterManager;
import com.game.monster.script.IMonsterAiScript;
import com.game.monster.script.IMonsterStopScript;
import com.game.monster.structs.Monster;
import com.game.skill.structs.Skill;

public class SimenMonsterAiScript implements IMonsterAiScript, IMonsterStopScript {

	private Logger log = Logger.getLogger(SimenMonsterAiScript.class);
	
	private static int ADOU = 13501;
	@Override
	public int getId() {
		return 2012001;
	}

	@Override
	public boolean wasHit(Monster monster,Fighter attk) {
		return false;
	}

	@Override
	public Fighter getAttackTarget(Monster monster ) {
		return null;
	}

	@Override
	public Skill getSkill(Monster monster ) {
		return null;
	}

	@Override
	public void stop(Monster monster) {
		
		try{
			if(monster.isDie()) return;
			Map map = ManagerPool.mapManager.getMap(monster);
			if(map==null) return;
//			System.out.println("怪物停止地点：" + monster.getPosition());
			List<Monster> monsters = MonsterManager.getInstance().getSameMonster(map, ADOU);
			if(monster!=null && monsters.size()>0){
				Monster monster2 = monsters.get(0);
				if(!monster2.isDie()){
					monster2.setHp(monster2.getHp() - 1);
					ManagerPool.monsterManager.onHpChange(monster2);
					if(monster2.getHp() <= 0) ManagerPool.monsterManager.die(monster2, null);
				}
			}
		}catch (Exception e) {
			log.error(e, e);
		}
		
		//怪物消失
		ManagerPool.monsterManager.removeMonster(monster);
	}

}
