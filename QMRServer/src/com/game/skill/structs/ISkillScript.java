package com.game.skill.structs;

import com.game.fight.structs.FightResult;
import com.game.fight.structs.Fighter;
import com.game.script.IScript;

public interface ISkillScript extends IScript {
	/**
	 * 是否可以对目标使用	   false 不继续执行操作
	 * @param attacker
	 * @param defender
	 * @param direction
	 * @return
	 */
	public boolean canUse(Fighter attacker, Fighter defender, int direction);
	/**
	 * 伤害值
	 * @param attacker
	 * @param defender
	 * @param result
	 */
	public void damage(Fighter attacker, Fighter defender, FightResult result);
	/**
	 * 技能默认行为，  true 不继续执行操作
	 * @param attacker
	 * @param defender
	 * @return
	 */
	public boolean defaultAction(Fighter attacker, Fighter defender);
	/**
	 * 是否跳闪有效 ,false 不能被跳闪
	 * @param attacker
	 * @param defender
	 * @return
	 */
	public boolean canJumpSidestep(Fighter attacker, Fighter defender);
}
