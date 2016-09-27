package com.game.monster.structs;

/**
 * 怪物状态
 * @author heyang
 *
 */
public enum MonsterState {
	//正常
	NORMAL	(0),
	//跑回
	RUNBACK	(1),
	//死亡中
	DIEING 	(2),
	//死亡
	DIE		(3),
	//死亡等待移除
	DIEWAIT	(4),
	//战斗
	FIGHT	(5),
	;
	
	private int value;
	
	private MonsterState(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public boolean compare(int state){
		return this.value == state;
	}
}
