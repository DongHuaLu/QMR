package com.game.arrow.structs;

import com.game.arrow.bean.FightSpiritInfo;
import com.game.object.GameObject;

/**
 * 战魂数据
 *
 * @author 杨鸿岚
 */
public class FightSpiritData extends GameObject{
	
	private static final long serialVersionUID = 6697307896149123345L;
	
	private int type;		//战魂类型 1日 2月 3金 4木 5水 6火 7土
	private int num;		//战魂数量

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public FightSpiritInfo toInfo(){
		FightSpiritInfo fightSpiritInfo = new FightSpiritInfo();
		fightSpiritInfo.setType(getType());
		fightSpiritInfo.setNum(getNum());
		return fightSpiritInfo;
	}
}
