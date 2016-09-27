package com.game.spirittree.structs;

import com.game.object.GameObject;

public class FruitRewardAttr  extends GameObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4079338225920907249L;
	//附加属性类型
	private int type;
	//附加属性值
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
