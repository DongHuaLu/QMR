package com.game.skill.structs;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_skill_modelBean;
import com.game.data.manager.DataManager;
import com.game.object.GameObject;
import com.game.player.structs.Player;

public class Skill extends GameObject {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Skill.class);

	private static final long serialVersionUID = -4048773480706290213L;
	//技能模板Id
	private int skillModelId;
	//技能等级
	private int skillLevel;
	//加成过的等级
	private transient int realLevel;

	public int getSkillModelId() {
		return skillModelId;
	}

	public void setSkillModelId(int skillModelId) {
		this.skillModelId = skillModelId;
	}

	public int getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}
	
	//加成过的等级 
	public int getRealLevel(Player player){
		int addlevel=0;
		Integer allAdd = player.getSkillLevelUp().get(-1);
		if(allAdd!=null){
			addlevel+=player.getSkillLevelUp().get(-1);
		}
		Integer currentAdd= player.getSkillLevelUp().get(skillModelId);
		if(currentAdd!=null){
			addlevel+= player.getSkillLevelUp().get(skillModelId);	
		}
		if(getSkillLevel()+addlevel < 1){
			addlevel = 1 - getSkillLevel();
		}
		Q_skill_modelBean model= DataManager.getInstance().q_skill_modelContainer.getMap().get(getSkillModelId()+"_"+(getSkillLevel()+addlevel));
		if(model!=null){
			realLevel=getSkillLevel()+addlevel;
			return realLevel; 
		}else{
			// 加成过的技能没有等级判断
			if(addlevel!=0){
				logger.error("全部加成等级"+allAdd+"当前技能加成"+currentAdd+"找不到等级模型 对应的技能:"+skillModelId);
			}
			return skillLevel;
		}
	}
	
}
