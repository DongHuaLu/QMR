package scripts.item;


import java.util.Iterator;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_hiddenweapon_skillBean;
import com.game.data.bean.Q_skill_modelBean;
import com.game.hiddenweapon.manager.HiddenWeaponManager;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;


/**
 * 暗器技能书
 * @author zhangxiangxi
 *
 */
public class HiddenWeaponSkill implements IItemScript{
	@Override
	public int getId() {
		return 1009203;	
	}
	
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		if (item.isTrade()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("操作失败，物品正在交易中"));
			return false;
		}
		
		if (item.isLost()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("操作失败，物品己过期"));
			return false;
		}

		Iterator<Q_hiddenweapon_skillBean> i = ManagerPool.dataManager.q_hiddenweapon_skillContainer.getList().iterator();
		Q_hiddenweapon_skillBean studySkillBean = null;
		while (i.hasNext()) {
			Q_hiddenweapon_skillBean hiddenweaponSkillBean = i.next();
			if (hiddenweaponSkillBean.getQ_item_id() == item.getItemModelId()) {
				studySkillBean = hiddenweaponSkillBean;
				break;
			}
		}
		
		if (studySkillBean == null) {
			return false;
		}
		
		Q_skill_modelBean skillBean = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(studySkillBean.getQ_skill());
		if (skillBean == null) {
			return false;
		}
		
		if (!HiddenWeaponManager.getInstance().studySkill(player, skillBean.getQ_skillID(), skillBean.getQ_grade())) {
			return false;
		}
		
		ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, Config.getId());
		return false;
	}
}
