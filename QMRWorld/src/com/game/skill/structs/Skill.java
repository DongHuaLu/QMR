package com.game.skill.structs;

import com.game.object.GameObject;
import org.apache.log4j.Logger;

public class Skill extends GameObject {
	/**
	 * Logger for this class
	 */
	protected static final Logger logger = Logger.getLogger(Skill.class);

	private static final long serialVersionUID = -4048773480706290213L;
	//技能模板Id
	private int skillModelId;
	//技能等级
	private int skillLevel;
	//加成过的等级
	private transient int realLevel;

	public int getRealLevel() {
		return realLevel;
	}

	public void setRealLevel(int realLevel) {
		this.realLevel = realLevel;
	}

	public int getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}

	public int getSkillModelId() {
		return skillModelId;
	}

	public void setSkillModelId(int skillModelId) {
		this.skillModelId = skillModelId;
	}

}
