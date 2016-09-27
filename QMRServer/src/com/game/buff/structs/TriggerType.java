package com.game.buff.structs;

/**
 * 触发类型
 * @author heyang
 *
 */
public class TriggerType {
	//攻击命中
	public final static int ATTACK_SUCCESS = 1;
	
	//防御触发
	public final static int DEFENSE = 2;
	
	//格挡消耗触发
	public final static int BLOCK_COST = 3;
	
	//跳跃触发
	public final static int JUMP = 4;
	
	//伤害暴击计算
	public final static int CRIT = 5;
}
