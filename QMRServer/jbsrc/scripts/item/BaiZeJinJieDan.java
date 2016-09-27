package scripts.item;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.horse.manager.HorseManager;
import com.game.horse.struts.Horse;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;

/**
 * 熊心豹子胆
 * @author 赵聪慧
 * @2012-9-24 下午1:05:02
 */
public class BaiZeJinJieDan implements IItemScript {
	
	//坐骑等级
	int horse_layer=8;
	
	@Override
	public int getId() {
		return 1009105;
	}
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		long id = Config.getId();
		Horse horse = HorseManager.getInstance().getHorse(player);
		if(horse==null||horse.getLayer()<=0){
			MessageUtil.notify_player(player, Notifys.ERROR,"您当前没有座骑，不能使用该物品");
			return false;
		}
		if(horse.getLayer() == horse_layer - 1){
			ManagerPool.horseManager.setHorseLayer(player, horse_layer);
			MessageUtil.notify_player(player, Notifys.SROLL, "恭喜玩家"+player.getName()+"使用通海白泽幻化丹将坐骑直接提升至通海白泽");
			
			BackpackManager.getInstance().removeItem(player, item, 1, Reasons.GOODUSE, id);
			return true;
		}else{
			MessageUtil.notify_player(player, Notifys.ERROR,"您的座骑没有达到瑞角夜豹，不能使用该物品");
			return false;
		}
	}
}
