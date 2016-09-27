package scripts.item;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;


public class StarEquipProtection implements IItemScript {
	//9星装备保护符：使用后在12小时以内9星装备强化失败不掉星
//	private int buffid = 1406;
//	private String key = "drop_1026_use";
	
	@Override
	public int getId() {
		return 1001026;
	}
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		long id = Config.getId();
		if (item.getItemModelId() == 1026 || item.getItemModelId() == 1035 || item.getItemModelId() == 1037) {
			if(BackpackManager.getInstance().removeItem(player, item, 1, Reasons.GOODUSE, id) ){
				if (item.getItemModelId() == 1026 ) {
					ManagerPool.buffManager.addBuff(player, player, 1406, 0, 0, 0);
					if (ManagerPool.vipManager.getPlayerVipId(player) == 3) {
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜获得9星装备保护符BUFF，至尊VIP用户获得24小时以内9星装备强化失败不掉星"));
					}else {
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜获得9星装备保护符BUFF：12小时以内9星装备强化失败不掉星"));
					}
					return true;
				}else if (item.getItemModelId() == 1035) {//5星装备保护符
					ManagerPool.buffManager.addBuff(player, player, 1413, 0, 0, 0);
					if (ManagerPool.vipManager.getPlayerVipId(player) == 3) {
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜获得5星装备保护符BUFF，至尊VIP用户获得24小时以内5星装备强化失败不掉星"));
					}else {
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜获得5星装备保护符BUFF：12小时以内5星装备强化失败不掉星"));
					}
					return true;
				}else if (item.getItemModelId() == 1037) {//完美强化卡
					ManagerPool.buffManager.addBuff(player, player, 1414, 0, 0, 0);
					if (ManagerPool.vipManager.getPlayerVipId(player) == 3) {
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜获得完美强化卡BUFF，至尊VIP用户获得24小时以内装备强化失败不掉星"));
					}else {
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜获得完美强化卡BUFF：12小时以内装备强化失败不掉星"));
					}
					return true;
				}
			}
		}
		return false;
	}
}
