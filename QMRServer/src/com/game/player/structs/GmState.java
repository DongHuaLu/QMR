package com.game.player.structs;
/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-7-17
 * 
 * GM状态枚举 
 */
public enum GmState {
	/**
	 * 正常
	 */
	GM  		(0x00000001),
	/**
	 * 无敌
	 */
	WUDI   		(0x00000002),
	/**
	 * 隐身
	 */
	YINSHEN    	(0x00000004),
	;
	
	private int value;
	
	GmState(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public boolean compare(int state){
		return ((this.value & state)!=0);
	}
}
