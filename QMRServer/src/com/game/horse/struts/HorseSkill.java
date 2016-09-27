package com.game.horse.struts;

import com.game.data.bean.Q_horse_skillsBean;
import com.game.horse.bean.HorseSkillInfo;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.skill.structs.Skill;

public class HorseSkill extends Skill {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6600776865528543240L;
	//技能经验
	private int skillexp;

	
	
	
	/**产生前端展示用的技能信息
	 * 
	 * @return
	 */
	public HorseSkillInfo createSkillInfo(){
		HorseSkillInfo info = new HorseSkillInfo();
		info.setSkillexp(this.getSkillexp());
		info.setSkillmodelid(this.getSkillModelId());
		info.setSkilllevel((short) this.getSkillLevel());
		return info;
	}	
	
	
	/**检测当前技能是否激活
	 * @return 
	 * 
	 */
	public boolean checkHorseSkillisUsed(Player player ,int skillid){
		Q_horse_skillsBean skillModel = ManagerPool.dataManager.q_horse_skillsContainer.getMap().get(skillid);
		Horse horse = ManagerPool.horseManager.getHorse(player);
		if(horse.getLayer() >= skillModel.getQ_activate_need_layer() ){
			return true;	
		}
		return false;	
	}
	
	
	
	
	
	
	
	public int getSkillexp() {
		return skillexp;
	}

	public void setSkillexp(int skillexp) {
		this.skillexp = skillexp;
	}
	
	
	
	
	
	
	
	
	
}
