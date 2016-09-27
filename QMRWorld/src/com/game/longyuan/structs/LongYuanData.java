package com.game.longyuan.structs;

import com.game.object.GameObject;

public class LongYuanData extends GameObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1096502753282509482L;
	//龙元心法阶段
	private byte lysection;
	//龙元心法(当前阶段下的星位)
	private byte lylevel;
	
	
	/**
	 * 龙元心法阶段(星图)
	 * @return
	 */
	public byte getLysection() {
		return lysection;
	}
	public void setLysection(byte lysection) {
		this.lysection = lysection;
	}
	/**
	 * 龙元心法(当前阶段下的星位)
	 * @return
	 */
	public byte getLylevel() {
		return lylevel;
	}
	public void setLylevel(byte lylevel) {
		this.lylevel = lylevel;
	}
	
}
