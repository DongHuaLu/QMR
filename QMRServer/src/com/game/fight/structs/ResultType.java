package com.game.fight.structs;

/**
 * 战斗结果枚举
 * @author heyang
 *
 */
public enum ResultType {
	//未命中
	MISS	(1),
	//暴击
	LUCK	(2),
	//无效
	NULLITY	(3),
	;
	
	
	private int value;
	
	private ResultType(int value){
		
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
