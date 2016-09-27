package com.game.transactions.structs;

import com.game.object.GameObject;

public class ItemAttributeLogData extends GameObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2144455296501209299L;

	//属性类型
	private int type;
	
	//属性值
	private int value;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
