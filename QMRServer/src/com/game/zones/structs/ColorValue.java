package com.game.zones.structs;


public  class ColorValue {

	private int idx;		//梅花桩编号
	private long monid;		//梅花桩对应怪物唯一ID
	private int colortype;		//颜色类型
	private Integer[] pos;		//梅花桩坐标

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public long getMonid() {
		return monid;
	}

	public void setMonid(long monid) {
		this.monid = monid;
	}

	public int getColortype() {
		return colortype;
	}

	public void setColortype(int colortype) {
		this.colortype = colortype;
	}

	public Integer[] getPos() {
		return pos;
	}
	public void setPos(Integer[] pos) {
		this.pos = pos;
	}
}


