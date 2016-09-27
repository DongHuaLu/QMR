package com.game.monster.structs;

/**
 * 变身类型
 * @author heyang
 *
 */
public enum MorphType {

	/**
	 * 1	生命值变身
	 */
	HP			(1),		
	/**
	 * 2	攻击力变身
	 */
	ATTACK		(2),
	/**
	 * 3	防御力变身
	 */
	DEFENSE		(4),
	/**
	 * 4	暴击值变身
	 */
	CRIT		(8),
	/**
	 * 5	闪避值变身
	 */
	DODGE		(16),
	/**
	 * 6	攻击速度变身
	 */
	ATTACKSPEED	(32),
	/**
	 * 7	移动速度变身
	 */
	SPEED		(64),
	/**
	 * 8	经验值携带数变身
	 */
	EXP			(128),
	/**
	 * 9	铜币携带数变身
	 */
	MONEY		(256),
	;
	
	private int value;
	
	MorphType(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
