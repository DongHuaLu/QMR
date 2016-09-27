package com.game.pet.struts;

import com.game.object.GameObject;
import com.game.skill.structs.Skill;

public class Pet extends GameObject {

	/**
	 *
	 */
	protected static final long serialVersionUID = 1L;
	protected long id;
	//地图模板id
	protected int mapModelId;
	//名字
	protected String name;
	//头像
	protected String icon;
	//等级
	protected int level;
	private long ownerId;
	private int modelId;
	//合体次数
	private int htcount = 0;
	//装备列表
//	private Equip[] equips = new Equip[12];
	//天赋技能排在第一个 不能动
	private Skill[] skills = new Skill[8];
	private int htaddhp;
	// 合体增加内力
	private int htaddmp;
	// 合体增加攻击
	private int htaddattack;
	// 合体增加防御
	private int htadddefence;
	// 合体增加暴击
	private int htaddcrit;
	// 合体增加闪避
	private int htadddodge;

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public int getHtaddattack() {
		return htaddattack;
	}

	public void setHtaddattack(int htaddattack) {
		this.htaddattack = htaddattack;
	}

	public int getHtaddcrit() {
		return htaddcrit;
	}

	public void setHtaddcrit(int htaddcrit) {
		this.htaddcrit = htaddcrit;
	}

	public int getHtadddefence() {
		return htadddefence;
	}

	public void setHtadddefence(int htadddefence) {
		this.htadddefence = htadddefence;
	}

	public int getHtadddodge() {
		return htadddodge;
	}

	public void setHtadddodge(int htadddodge) {
		this.htadddodge = htadddodge;
	}

	public int getHtaddhp() {
		return htaddhp;
	}

	public void setHtaddhp(int htaddhp) {
		this.htaddhp = htaddhp;
	}

	public int getHtaddmp() {
		return htaddmp;
	}

	public void setHtaddmp(int htaddmp) {
		this.htaddmp = htaddmp;
	}

	public int getHtcount() {
		return htcount;
	}

	public void setHtcount(int htcount) {
		this.htcount = htcount;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMapModelId() {
		return mapModelId;
	}

	public void setMapModelId(int mapModelId) {
		this.mapModelId = mapModelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public Skill[] getSkills() {
		return skills;
	}

	public void setSkills(Skill[] skills) {
		this.skills = skills;
	}
	
}
