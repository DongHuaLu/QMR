package com.game.monster.script;

import com.game.fight.structs.Fighter;
import com.game.monster.structs.Monster;
import com.game.script.IScript;

/**
 * 怪物死亡掉落 
 * @author 赵聪慧
 * @2012-10-8 下午10:18:16
 */
public interface IMonsterDieDropScript extends IScript {
	public void onMonsterDie(Monster monster, Fighter killer);
}
