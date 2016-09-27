package scripts.item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;
/**春节大礼包
 * 
 * @author zhangrong
 *
 */

public class SpringFestivalSpree implements IItemScript {
	@Override
	public int getId() {
		return 1009174;
	}

	
//		礼金*5000
//		坐骑丹*100（有存在时限）
//		宝石精华*500
//		海心铁精*60（有存在时限）
//		战魂丹*50
//		武学参悟丹*50（有存在时限）
	
	//开始时间
	private String actstart = "2013-2-20 00:00:01";
	//礼包内道具
	private String itemstr = "-5,5000,0,1;1011,100,0,1,259200;1007,500,0,1;3019,60,0,1,259200;8440,50,0,1;18138,50,0,1,259200" ;
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		if (item.getItemModelId() == 9174 ) {
			Date startdate = TimeUtil.getDateByString(actstart);
			if (startdate.getTime() > System.currentTimeMillis() ) {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("2月20号以后才能打开 {1}"),item.acqItemModel().getQ_name());
				return false;
			}else {
				long actid = Config.getId();
				if (ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.def23, actid)) {
					List<Item> itemmakes = new ArrayList<Item>();
					ManagerPool.backpackManager.createItems(player, itemstr, itemmakes);
					ManagerPool.backpackManager.addItems(player, itemmakes, actid);
					ManagerPool.backpackManager.notifyitemname(player, itemstr);
				}
			}
		}
		return false;
	}

}
