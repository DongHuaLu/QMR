package scripts.skills;

import com.game.data.bean.Q_skill_modelBean;
import com.game.fight.structs.Fighter;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.skill.script.ITriggerSkillScript;
import com.game.skill.structs.Skill;
import com.game.utils.MessageUtil;

public class TriggerSkillScript implements ITriggerSkillScript {

	@Override
	public int getId() {
		return ScriptEnum.SKILL_TRIGGER;
	}

	@Override
	public boolean canTrigger(Fighter source, Fighter target, Skill skill) {
		Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
		if (skillModel == null) {
			return false;
		}
		
		// 被动技能无法在秦风,梅花,白起 地图使用
		if (source instanceof Player && skillModel.getQ_target_max() > 1 && skillModel.getQ_trigger_type() == 2) {
			Player player = (Player)source;
			if (player.getMapModelId() == 42101 || player.getMapModelId() == 42121 || player.getMapModelId() == 30013) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该地图无法触发{1}"), skillModel.getQ_skillName());
 				return false;
			}
		}
		return true;
	}

}
