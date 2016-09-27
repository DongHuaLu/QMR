package com.game.player.structs;

public enum UserState {

	/**
	 * 1	登陆中
	 */
	LOGINING	(1),
	
	/**
	 * 2	创建中
	 */
	CREATING	(2),
	
	/**
	 * 3	选择角色中
	 */
	SELECTING	(3),
	
	/**
	 * 4	等待退出中
	 */
	WAITQUITING	(4),
	
	/**
	 * 5	退出中
	 */
	QUITING		(5),
	/**
	 * 6	选择角色中
	 */
	DELETEING	(6),
	;
	
	
	private int value;
	
	UserState(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public boolean compare(int value){
		return this.value == value;
	}
}
