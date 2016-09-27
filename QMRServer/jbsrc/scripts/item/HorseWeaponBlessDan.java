package scripts.item;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_horseweaponBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.horse.manager.HorseManager;
import com.game.horseweapon.log.HorseWeaponLog;
import com.game.horseweapon.manager.HorseWeaponManager;
import com.game.horseweapon.structs.HorseWeapon;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;

/**骑战兵器祝福丹
 * 
 * @author zhangrong
 *
 */

public class HorseWeaponBlessDan implements IItemScript {
	
	private static Logger logger = Logger.getLogger(HorseBlessDan.class);
	
	@Override
	public int getId() {
		return 1016037;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		HorseWeapon weapon =ManagerPool.horseWeaponManager.getHorseWeapon(player);
		String name=item.acqItemModel().getQ_name();
		if(weapon==null||weapon.getLayer()<3){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("3阶以上骑兵才可以用{1}"),name);
			return false;
		}
		
		Q_horseweaponBean bean=DataManager.getInstance().q_horseweaponContainer.getMap().get((int)weapon.getLayer());
		
		if(weapon.getDayblessvalue()>=bean.getQ_blessnum_limit()){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("当前骑战兵器升阶祝福值已达上限"));
			return false;
		}
		
		

		if(BackpackManager.getInstance().removeItem(player, item, 1, Reasons.GOODUSE, Config.getId())){
			HorseWeapon hw = ManagerPool.horseWeaponManager.getHorseWeapon(player);
			//记录日志
			HorseWeaponLog log = new HorseWeaponLog();
			log.setUserid(player.getUserId());
			log.setUsername(player.getUserName());
			log.setRoleid(String.valueOf(player.getId()));
			log.setBeforeexp(hw.getDayblessvalue());
			log.setBeforerank(hw.getLayer());
			//
			addDaybless(player, 100);
			//
			log.setAfterexp(hw.getDayblessvalue());
			log.setAfterrank(hw.getLayer());
			log.setItemmodel(16037);
			log.setItemnum(1);
			try{
				LogService.getInstance().execute(log);
			}catch (Exception e) {
				logger.error(e, e);
			}
			return true;
		}else{
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("扣除物品失败"));
			return false;
		}
	}
	
	
	/**加祝福值
	 * 
	 * @param player
	 * @param num
	 */
	public void addDaybless(Player player ,int num){
		HorseWeapon weapon = ManagerPool.horseWeaponManager.getHorseWeapon(player);
		ManagerPool.horseWeaponManager.clearDay(weapon);
		weapon.setDayblessvalue(num + weapon.getDayblessvalue());
		MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("祝福值增加了{1}"), num+"");
		ManagerPool.horseWeaponManager.sendHorseWeaponInfo(player);
	}
}
