package com.game.structs;

/**
 * 附加属性枚举
 * @author heyang
 *
 */

public enum Attributes {

	/**
	 * 1	增加等级
	 */
	LEVEL			(1),
	/**
	 * 2	增加人物经验
	 */
	EXP				(2),
	/**
	 * 3	增加人物真气
	 */
	ZHENQI			(3),
	/**
	 * 4	增加战场声望
	 */
	BATTLEEXP		(4),
	/**
	 * 5	增加或减少当前生命值
	 */
	HP				(5),
	/**
	 * 6	增加或减少当前内力值
	 */
	MP				(6),
	/**
	 * 7	增加或减少当前体力值
	 */
	SP				(7),
	/**
	 * 8	增加或减少生命值上限
	 */
	MAXHP			(8),
	/**
	 * 9	增加或减少内力值上限
	 */
	MAXMP			(9),
	/**
	 * 10	增加或减少体力值上限
	 */
	MAXSP			(10),
	/**
	 * 11	增加或减少攻击力
	 */
	ATTACK   		(11),
	/**
	 * 12	增加或减少防御力
	 */
	DEFENSE 		(12),
	/**
	 * 13	增加或减少闪避值
	 */
	DODGE    		(13),
	/**
	 * 14	增加或减少爆击值
	 */
	CRIT     		(14),
	/**
	 * 15	增加或减少幸运值
	 */
	LUCK			(15),
	/**
	 * 16	增加或减少攻击速度
	 */
	ATTACKSPEED 	(16),
	/**
	 * 17	增加或减少移动速度
	 */
	SPEED			(17),
	;
	
	private int value;
	
	Attributes(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public boolean compare(int value){
		return this.value == value;
	}
}
