package scripts.item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.log.ItemChangeLog;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemAction;
import com.game.backpack.structs.ItemChangeAction;
import com.game.buff.structs.Buff;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.horseweapon.structs.HorseWeapon;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.scripts.bean.PanelInfo;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;

/**骑兵锻造符
 * 激活隐藏属性
 * 
 * @author zhangrong
 *
 */
public class HorseWeaponForgingBuff  implements IItemScript{
	private static final Logger logger = Logger.getLogger(HorseWeaponForgingBuff.class);
	@Override
	public int getId() {
		return 1009175;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		if (item.getItemModelId() == 9175) {
			int panelId = 20;
			PanelInfo panel = NpcParamUtil.getPanelInfo(player, panelId);
			List<String> list = new ArrayList<String>();
			list.add("btnStarting#" + this.getId() + "#useitem#"+item.getId());
			panel.setButtoninfolist(NpcParamUtil.getbuttonInfo(player , list));
			NpcParamUtil.showPanel(player, panel);
		}
		return false;
	}
	
	
	
	
	public void useitem(List<Object> list){
		Player player = (Player)list.get(0);
		if (player == null) {
			return;
		}
		
		long itemId = Long.valueOf((String)list.get(1));
		Item item = ManagerPool.backpackManager.getItemById(player, itemId);
		if(item == null){
			return;
		}
		List<Buff> buffs1 = ManagerPool.buffManager.getBuffByModelId(player, 9157);//版本更新前，骑兵神锻符BUFFID
		List<Buff> buffs2 = ManagerPool.buffManager.getBuffByModelId(player, 9158);//版本更新前，骑兵神锻符BUFFID
		if (buffs1.size() > 0 || buffs2.size() > 0) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("您已经激活骑兵隐藏属性，无需再激活。"));
			return ;
		}
		
		HorseWeapon horseWeapon = ManagerPool.horseWeaponManager.getHorseWeapon(player);
		if (horseWeapon == null || horseWeapon.getLayer() < 7) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("骑兵兵器需要达到顶级（7级）才可激活。"));
			return;
		}
		if (getItemNum(player,3019) < 300) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("需要300个无时限的海心铁精才可激活。"));
			return;
		}
		long actid = Config.getId();
		if (removeItem(player, 3019, 300, Reasons.def24, actid) && ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.def24, actid)) {

			ManagerPool.buffManager.addBuff(player, player, 9158, 0,0, 0);//空属性BUFF
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE_WEAPON);
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("恭喜获得骑兵隐藏属性，可在骑兵面板查看。"));
			//关闭面板
			NpcParamUtil.setPanel(player, 20, new ArrayList<String>(), 1);
		}
	}
	/**
	 * 获得物品数量
	 *
	 * @param roleId 玩家id
	 * @param itemModelId 物品模板id
	 * @return 物品数量
	 */
	@SuppressWarnings("deprecation")
	public int getItemNum(Player player, int itemModelId) {
		int num = 0;
		Iterator<Item> iter = player.getBackpackItems().values().iterator();
		// 遍历物品
		while (iter.hasNext()) {
			Item item = (Item) iter.next();
			if (item.getItemModelId() == itemModelId && item.getLosttime() == 0 && !item.isTrade()) {
				num += item.getNum();
			}
		}
		return num;
	}

	/**
	 * 移除指定绑定类型 指定数量的物品(有过期时限的不移除)
	 *
	 * @param player
	 * @param itemModelId
	 * @param num
	 * @param reasons
	 * @param action
	 */

	@SuppressWarnings({ "unused", "deprecation" })
	private boolean removeItem(Player player, int itemModelId, int num, Reasons reasons, long action) {
		if (getItemNum(player, itemModelId) < num) {
			// 数量不足
			return false;
		}
		long actionId = action != 0 ? action : Config.getId();
		for (int i = player.getBagCellsNum(); i >= 1 && num > 0; i--) {
			Item item = player.getBackpackItems().get(String.valueOf(i));
			if (item == null) {
				continue;
			}
			Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(itemModelId);
			if (item.getItemModelId() == itemModelId && item.getLosttime() == 0 && !item.isTrade()) {
				if (item.getNum() <= num) {
					BackpackManager.getInstance().removeItemByCellId(player, i, reasons, actionId);
					num = num - item.getNum();
				} else {
					String before = JSONserializable.toString(item);
					item.setNum(item.getNum() - num);
					num = 0;
					MessageUtil.tell_player_message(player, BackpackManager.getInstance().getItemChangeMessage(item));
					try {
						if (model.getQ_log() == 1) {
							ItemChangeLog log = new ItemChangeLog();
							log.setActionId(actionId);
							log.setAction(ItemAction.REMOVE.toString());
							log.setChangeAction(ItemChangeAction.CHANGE.toString());
							log.setItemafterInfo(JSONserializable.toString(item));
							log.setItembeforeInfo(before);
							log.setItemid(item.getId());
							log.setModelid(item.getItemModelId());
							log.setNum(item.getNum());
							log.setReason(reasons.getValue());
							log.setRoleid(player.getId());
							log.setUserId(player.getUserId());
							log.setSid(player.getCreateServerId());
							LogService.getInstance().execute(log);
						}
					} catch (Exception e) {
						logger.error(e, e);
					}
				}

			}
		}
//		Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(itemModelId);
//		if (num == 1) {
//			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "失去物品{1}", q_itemBean.getQ_name());
//		} else {
//			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "失去物品{1}({2})", q_itemBean.getQ_name(), num + "");
//		}
		return true;
	}


}
