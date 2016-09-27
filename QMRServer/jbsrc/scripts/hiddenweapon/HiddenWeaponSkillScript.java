package scripts.hiddenweapon;

import com.game.data.bean.Q_hiddenweaponBean;
import com.game.hiddenweapon.script.IHiddenWeaponSkillScript;
import com.game.hiddenweapon.structs.HiddenWeapon;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;

public class HiddenWeaponSkillScript implements IHiddenWeaponSkillScript {

	@Override
	public int getId() {
		return ScriptEnum.HIDDENWEAPON_SKILL;
	}

	@Override
	public int onHiddenWeaponSkillStudy(Player player, int skillId, int skillLevel) {
		HiddenWeapon weapon = ManagerPool.hiddenWeaponManager.getHiddenWeapon(player);
		if (weapon == null) { // 这里不会为空
			return -1;
		}
		// 技能位置选择,先按照每个格子的概率选择格子，如果选了个空格子，则放到第一个空格子
		Q_hiddenweaponBean hiddenweaponBean = ManagerPool.dataManager.q_hiddenweaponContainer.getMap().get(weapon.getLayer());
		if (hiddenweaponBean == null || hiddenweaponBean.getQ_open_grid() < 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("无可用暗器技能格,请提升暗器等级"));
			return -1;
		}
		
		int index = RandomUtils.random(0, hiddenweaponBean.getQ_open_grid() - 1);
		if (weapon.getSkillByIndex(index) == null) {
			for (int i = 0; i < hiddenweaponBean.getQ_open_grid(); ++i) {
				if (weapon.getSkillByIndex(i) == null) {
					index = i;
					break;
				}
			}
		}
		return index;
	}

}
