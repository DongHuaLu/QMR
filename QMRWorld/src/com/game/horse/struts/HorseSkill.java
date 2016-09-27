package com.game.horse.struts;

import com.game.horse.bean.HorseSkillInfo;
import com.game.skill.structs.Skill;

public class HorseSkill extends Skill {

	/**
	 *
	 */
	private static final long serialVersionUID = -6600776865528543240L;
	//技能经验
	private int skillexp;

	public int getSkillexp() {
		return skillexp;
	}

	public void setSkillexp(int skillexp) {
		this.skillexp = skillexp;
	}

	/**
	 * 产生前端展示用的技能信息
	 *
	 * @return
	 */
	public HorseSkillInfo createSkillInfo() {
		HorseSkillInfo info = new HorseSkillInfo();
		info.setSkillexp(this.getSkillexp());
		info.setSkillmodelid(this.getSkillModelId());
		info.setSkilllevel((short) this.getSkillLevel());
		return info;
	}
}
