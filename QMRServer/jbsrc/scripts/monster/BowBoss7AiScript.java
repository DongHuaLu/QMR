package scripts.monster;

import java.util.ArrayList;
import java.util.List;

import com.game.config.Config;
import com.game.fight.structs.Fighter;
import com.game.monster.script.IMonsterAiScript;
import com.game.monster.structs.Monster;
import com.game.skill.structs.Skill;
import com.game.utils.RandomUtils;

public class BowBoss7AiScript implements IMonsterAiScript {

	//10010_70;  24101_1;  10013_65;  10014_65;  24102_1;   24103_1;
	int[][] skillinfos = new int[][]{{10010, 70}, {24101, 1}, {10013, 65}, {10014, 65}, {24102, 1}, {24103, 1}};
	
	List<Skill> skills = new ArrayList<Skill>();
	
	public BowBoss7AiScript(){
		for (int i = 0; i < skillinfos.length; i++) {
			Skill skill = new Skill();
			skill.setId(Config.getId());
			skill.setSkillModelId(skillinfos[i][0]);
			skill.setSkillLevel(skillinfos[i][1]);
			skills.add(skill);
		}
	}
	
	@Override
	public int getId() {
		return 2014007;
	}

	@Override
	public boolean wasHit(Monster monster,Fighter attk) {
		return false;
	}

	@Override
	public Fighter getAttackTarget(Monster monster ) {
		//获取默认目标，如果没有就会攻击阿斗
		return monster.getDefaultAttackTarget();
	}

	@Override
	public Skill getSkill(Monster monster ) {
		return skills.get(RandomUtils.random(skills.size()));
	}

}
