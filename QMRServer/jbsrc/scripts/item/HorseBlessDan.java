package scripts.item;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_horse_basicBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.horse.log.HorseLog;
import com.game.horse.manager.HorseManager;
import com.game.horse.struts.Horse;
import com.game.languageres.manager.ResManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;

/**
 * 座骑祝福丹
 * @author 赵聪慧
 * @2012-9-24 下午2:45:14
 */
public class HorseBlessDan implements IItemScript {
	
	private static Logger logger = Logger.getLogger(HorseBlessDan.class);
	
	@Override
	public int getId() {
		return 1001023;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		Horse horse=HorseManager.getInstance().getHorse(player);
		if(horse==null||horse.getLayer()<6){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("老虎以上坐骑才可使用该物品"));
			return false;
		}
		
		Q_horse_basicBean bean=DataManager.getInstance().q_horse_basicContainer.getMap().get((int)horse.getLayer());
		
		if(horse.getDayblessvalue()>=bean.getQ_blessnum_limit()){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("当前座骑升阶祝福值已达上限"));
			return false;
		}
		
		
		
		
		if(BackpackManager.getInstance().removeItem(player, item, 1, Reasons.GOODUSE, Config.getId())){
			//记录日志
			Horse curhorse = HorseManager.getInstance().getHorse(player);
			HorseLog log = new HorseLog();
			log.setPlayerId(player.getId());
			log.setSid(player.getCreateServerId());
			log.setYblessvalue(curhorse.getDayblessvalue());
			//
			int addblessvalue = 150; //增加的祝福值
			HorseManager.getInstance().addDaybless(player, addblessvalue);
			//
			log.setItemid(1023);
			log.setItemnum(1);
			log.setBlessvalue(addblessvalue);
			log.setNewblessvalue(curhorse.getDayblessvalue());
			log.setDayupnum(curhorse.getDayupnum());
			log.setLayer(curhorse.getLayer());
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

}
