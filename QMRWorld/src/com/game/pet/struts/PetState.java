package com.game.pet.struts;

public enum PetState {
	
	/**
	 * 休息
	 */
	IDEL		(0),
	/**
	 * 战斗
	 */
	FIGHT   	(1),
	/**
	 * 跟随
	 */
	FLLOW		(2),
	
	/**
	 * 死亡
	 */
	DIE			(3),
	
	/**
	 * 不显示
	 */
	UNSHOW		(4),
	
	/**
	 * 追击
	 */
	PURSUIT		(5);
	
	
	private int state;
	PetState(int value){
		this.state=value;
	}
	public int getValue(){
		return state;
	}
}
