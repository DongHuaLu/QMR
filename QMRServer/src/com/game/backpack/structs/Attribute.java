package com.game.backpack.structs;

import com.game.backpack.bean.GoodsAttribute;
import com.game.object.GameObject;

/**
 * 附加属性
 * @author heyang
 *
 */
public class Attribute extends GameObject {

	private static final long serialVersionUID = 1822686631979195706L;
	//属性类型
	private int type;
	//属性值
	private int value;
	
	private transient GoodsAttribute attribute=new GoodsAttribute();

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
	
	public GoodsAttribute buildGoodsAttribute(){
		attribute.setType(type);
		attribute.setValue(value);
		return attribute;
	}
}
