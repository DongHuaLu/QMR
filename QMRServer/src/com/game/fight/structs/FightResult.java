package com.game.fight.structs;

public class FightResult {
	//攻击结果0-成功 1-MISS 2-跳闪 4-暴击  8-格挡 6-跳闪+暴击 12-格挡+暴击 16-无敌 32-死亡中被打 64-被秒杀
	public int fightResult = 0;
	//伤害值
	public int damage = 0;
	//反伤伤害
	public int backDamage = 0;
	//加成数值
	public int hitDamage = 0;
}
