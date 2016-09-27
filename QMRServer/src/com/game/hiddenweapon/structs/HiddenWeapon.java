package com.game.hiddenweapon.structs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.object.GameObject;
import com.game.skill.structs.Skill;

public class HiddenWeapon extends GameObject {
	
	private static final long serialVersionUID = 704299366105549615L;
	
	/**
	 * 状态 0-为装备 1-装备
	 */
	private int state;

	/**
	 * 暗器最高等级
	 */
	private int layer;
	
	/**
	 * 暗器过期时间
	 */
	private long overtime;
	
	/**
	 * index,skill  0 for start
	 */
	private HashMap<String, Skill> skills = new HashMap<String, Skill>();
	
	/**
	 * skills的序号,表示当前ico显示的技能
	 */
	private byte icoIndex;
	
	/**
	 * 计数器,表示ico上应该显示的数
	 */
	private int iconCount;
	
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

	public long getOvertime() {
		return overtime;
	}

	public void setOvertime(long overtime) {
		this.overtime = overtime;
	}

	public HashMap<String, Skill> getSkills() {
		return skills;
	}

	public void setSkills(HashMap<String, Skill> skills) {
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

	public byte getIcoIndex() {
		return icoIndex;
	}

	public void setIcoIndex(byte index) {
		this.icoIndex = index;
	}

	public int getIconCount() {
		return iconCount;
	}

	public void setIconCount(int iconCount) {
		this.iconCount = iconCount;
	}
	
	public List<Integer> getSkillList() {
		List<Integer> tmp = new ArrayList<Integer>();
		Iterator<Skill> it = skills.values().iterator();
		while (it.hasNext()) {
			Skill skill = it.next();
			tmp.add(skill.getSkillModelId());
		}
		return tmp;
	}
	
	public void addSkill(int index, Skill skill) {
		skills.put(String.valueOf(index), skill);
	}
	
	public Skill getSkillByIndex(int index) {
		return skills.get(String.valueOf(index));
	}

	public void removeSkillByIndex(byte index) {
		skills.remove(String.valueOf(index));
	}
}
