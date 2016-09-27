package scripts.item;

import com.game.backpack.script.IItemScript;

import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;

/**套装属性符
 * 
 * @author zhangrong
 *
 */


public class EquipSetStarBuff  implements IItemScript{
	//	七星套装属性符：
	//	使用时，若玩家当前装备不满足全身均为7星紫装时，则给予提示：“您当前装备尚未全部达到七星或以上紫装，请满足条件后在使用！”
	//	十星套装属性符：
	//	使用时，若玩家当前装备不满足全身均为十星紫装时，则给予提示：“您当前装备尚未全部达到十星紫装，请满足条件后在使用！”
	@Override
	public int getId() {
		return 1009159;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		String nameString = item.acqItemModel().getQ_name();
		String key = "equip_"+item.getItemModelId();
		int[] tab = ckequipsetstar(player);
		int buffid1 = 0;
		int buffid2 = 0;
		if (player.getActivitiesReward().containsKey(key)) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("您已经使用过【{1}】，无需再使用。"),nameString);
			return false;
		}
		
		if (item.getItemModelId() == 9159) {
			if (tab[0] >= 7 && tab[1] >= 4) {
				buffid1 = 9150;
				buffid2 = 9151;
			}else {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("必须是强七紫装才可使用【{1}】。"),nameString);
				return false;
			}
		}else if (item.getItemModelId() == 9160) {	
			if (tab[0] >= 10 && tab[1] >= 4) {
				buffid1 = 9152;
				buffid2 = 9153;
			}else {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("必须是强十紫装才可使用【{1}】。"),nameString);
				return false;
			}
		}else {
			return false;
		}
		
		if(ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.def17,Config.getId())){
			player.getActivitiesReward().put(key, key);
			ManagerPool.buffManager.addBuff(player, player, buffid1, 0, 0, 0);
			ManagerPool.buffManager.addBuff(player, player, buffid2, 0, 0, 0);
			MessageUtil.notify_player(player, Notifys.CHAT_BULL, ResManager.getInstance().getString("成功使用【{1}】,获得套装属性翻倍！"),nameString);
		}
		return false;
	}

	
	/**检测全套装备等级和颜色品质
	 * 是否是7或者10
	 * @param player
	 * @return 强化等级,颜色品质
	 */
	 
	public int[] ckequipsetstar(Player player){
		Equip[] equips = player.getEquips();
		int lv = 0;	//强化等级
		int quality = 4;	//品质(默认4)，如果有小于4，则变0
		for (int i = 0; i < 10 ; i++) {	//10个装备项目
			if(equips[i] == null){
				lv = 0;
				break;
			}
			if (equips[i].getQuality() < 4) {
				quality = 0;
			}
			if(equips[i].getGradeNum() >= 10){
				lv = lv +10;
			}else if (equips[i].getGradeNum() >= 7) {
				lv = lv +7;
			}else {
				lv = 0;
				break;
			}
		}
		int[] tab = {lv /10,quality};
		return tab;
	}
	
}
