package com.game.arrow.structs;

import com.game.arrow.bean.BowInfo;
import com.game.object.GameObject;

/**
 * 弓箭箭支数据
 *
 * @author 杨鸿岚
 */
public class ArrowBowData extends GameObject{
	
	private static final long serialVersionUID = 6697307896139123345L;

	private int bowmainlv;		//主箭支阶数
	private int bowsublv;		//子箭支阶数

	public int getBowmainlv() {
		return bowmainlv;
	}

	public void setBowmainlv(int bowmainlv) {
		this.bowmainlv = bowmainlv;
	}

	public int getBowsublv() {
		return bowsublv;
	}

	public void setBowsublv(int bowsublv) {
		this.bowsublv = bowsublv;
	}
	
	public BowInfo toInfo(){
		BowInfo bowInfo = new BowInfo();
		bowInfo.setBowmainlv(getBowmainlv());
		bowInfo.setBowsublv(getBowsublv());
		return bowInfo;
	}
}
