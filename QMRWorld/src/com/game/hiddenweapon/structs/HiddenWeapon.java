package com.game.hiddenweapon.structs;

import com.game.object.GameObject;

public class HiddenWeapon extends GameObject {

	private static final long serialVersionUID = -7622237898913099222L;

	/**
	 * 等级
	 */
	private int level;
	
	/**
	 * 状态 0-为装备 1-装备
	 */
	private int state;

	/**
	 * 暗器最高等级
	 */
	private int layer;
	
	/**
	 * 暗器当前等级
	 */
	private int curLayer;
	
	/**
	 * 暗器过期时间
	 */
	private long overtime;
	
	/**
	 * 升级经验
	 */
//	private int exp;
	
	/**
	 * 技能列表
	 */
	private int[] skills = new int[5];
	
	/**
	 * 时间保存 每天X点进1，发生变化说明已经到下个时间点
	 */
	private int timeday ;
		
	/**
	 * 当前祝福值
	 */
	private int dayblessvalue ;

	/**
	 * 当前进阶次数
	 */
	private int dayupnum ;

	/**
	 * 历史祝福值
	 */
	private int hisblessvalue ;

	/**
	 * 历史进阶次数
	 */
	private int hisupnum ;

	/**
	 * 今日所获的暗器进阶经验
	 */
	private long dayexp;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public int getCurLayer() {
		return curLayer;
	}

	public void setCurLayer(int curLayer) {
		this.curLayer = curLayer;
	}

	public long getOvertime() {
		return overtime;
	}

	public void setOvertime(long overtime) {
		this.overtime = overtime;
	}

//	public int getExp() {
//		return exp;
//	}
//
//	public void setExp(int exp) {
//		this.exp = exp;
//	}

	public int[] getSkills() {
		return skills;
	}

	public void setSkills(int[] skills) {
		this.skills = skills;
	}

	public int getDayblessvalue() {
		return dayblessvalue;
	}

	public void setDayblessvalue(int dayblessvalue) {
		this.dayblessvalue = dayblessvalue;
	}

	public int getDayupnum() {
		return dayupnum;
	}

	public void setDayupnum(int dayupnum) {
		this.dayupnum = dayupnum;
	}

	public int getHisblessvalue() {
		return hisblessvalue;
	}

	public void setHisblessvalue(int hisblessvalue) {
		this.hisblessvalue = hisblessvalue;
	}

	public int getHisupnum() {
		return hisupnum;
	}

	public void setHisupnum(int hisupnum) {
		this.hisupnum = hisupnum;
	}

	public long getDayexp() {
		return dayexp;
	}

	public void setDayexp(long dayexp) {
		this.dayexp = dayexp;
	}

	public int getTimeday() {
		return timeday;
	}

	public void setTimeday(int timeday) {
		this.timeday = timeday;
	}
	
}
