package com.game.plugset.structs;

import com.game.object.GameObject;

public class PlugSet extends GameObject{

	private static final long serialVersionUID = -2091149920306807681L;
	
	private int parameter;	//参数
	private byte hpper;		//血量百分比
	private byte mpper;		//内力百分比
	private byte spper;		//体力百分比
	

	private byte itemcolor;	//自动出售道具颜色类型
	private byte itemlv;	//自动出售道具强化等级

	private int skillid;	//自动打怪技能

	private byte range;		//挂机范围 0，1，2

	private byte pickup;		//拾取道具类型

	private int time;		//挂机时间 分钟

	public int getParameter() {
		return parameter;
	}

	public void setParameter(int parameter) {
		this.parameter = parameter;
	}

	public byte getHpper() {
		return hpper;
	}

	public void setHpper(byte hpper) {
		this.hpper = hpper;
	}

	public byte getMpper() {
		return mpper;
	}

	public void setMpper(byte mpper) {
		this.mpper = mpper;
	}

	public byte getSpper() {
		return spper;
	}

	public void setSpper(byte spper) {
		this.spper = spper;
	}

	public byte getItemcolor() {
		return itemcolor;
	}

	public void setItemcolor(byte itemcolor) {
		this.itemcolor = itemcolor;
	}

	public byte getItemlv() {
		return itemlv;
	}

	public void setItemlv(byte itemlv) {
		this.itemlv = itemlv;
	}

	public int getSkillid() {
		return skillid;
	}

	public void setSkillid(int skillid) {
		this.skillid = skillid;
	}

	public byte getRange() {
		return range;
	}

	public void setRange(byte range) {
		this.range = range;
	}

	public byte getPickup() {
		return pickup;
	}

	public void setPickup(byte pickup) {
		this.pickup = pickup;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
	
	
	
}
