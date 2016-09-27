package com.game.arrow.structs;

import com.game.arrow.bean.StarInfo;
import com.game.object.GameObject;

/**
 * 弓箭星斗数据
 *
 * @author 杨鸿岚
 */
public class ArrowStarData extends GameObject{
	
	private static final long serialVersionUID = 6697307896129123345L;

	private int starmainlv;		//主星阶
	private int starsublv;		//子星阶

	public int getStarmainlv() {
		return starmainlv;
	}

	public void setStarmainlv(int starmainlv) {
		this.starmainlv = starmainlv;
	}

	public int getStarsublv() {
		return starsublv;
	}

	public void setStarsublv(int starsublv) {
		this.starsublv = starsublv;
	}
	
	public StarInfo toInfo(){
		StarInfo starInfo = new StarInfo();
		starInfo.setStarmainlv(getStarmainlv());
		starInfo.setStarsublv(getStarsublv());
		return starInfo;
	}
}
