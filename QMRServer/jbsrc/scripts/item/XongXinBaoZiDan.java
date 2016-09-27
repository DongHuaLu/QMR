package scripts.item;

import java.util.List;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.horse.manager.HorseManager;
import com.game.horse.struts.Horse;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;

/**
 * 熊心豹子胆
 * @author 赵聪慧
 * @2012-9-24 下午1:05:02
 */
public class XongXinBaoZiDan implements IItemScript {
	//奖励物品 座骑丹
	int rewardsModel=1011;
	//是否绑定
	boolean bind=true;
	//奖励物品数量
	int num=50;
	//最小随机坐骑
	int randommin=5;
	//最大随机坐骑
	int randommax=5;
	
	@Override
	public int getId() {
		return 1009104;
	}
	@Override
	public boolean use(Item item, Player player, String... parameters) {
//		使用后将您的坐骑随机进阶到熊、老虎、豹子中的一个坐骑。如果您的坐骑为熊以上坐骑则使用后可获得50颗坐骑进阶丹奖励
		long id = Config.getId();
		Horse horse = HorseManager.getInstance().getHorse(player);
		if(horse==null||horse.getLayer()<=0){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("您当前没有座骑，不能使用该物品"));
			return false;
		}
		if(horse.getLayer()>=5){
			long losttime =  System.currentTimeMillis()+24*60*60*1000; //开出来之后24小时
			List<Item> createItems = Item.createItems(rewardsModel, num, bind, losttime);
			if(!BackpackManager.getInstance().hasAddSpace(player, createItems)){
				MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("所需包裹位置不足,请先行清理包裹再领取"));
				return false;
			}
			BackpackManager.getInstance().addItems(player, createItems,Reasons.ACTIVITY_XXBZD,id );
		}else{
			if(RandomUtils.defaultIsGenerate(9500)){
				ManagerPool.horseManager.setHorseLayer(player, 5);
			}else{
				ManagerPool.horseManager.setHorseLayer(player, 6);
				MessageUtil.notify_player(player, Notifys.SROLL, ResManager.getInstance().getString("恭喜玩家")+player.getName()+ResManager.getInstance().getString("使用熊心豹子胆将坐骑直接提升至洪荒剑齿"));
			}
			
		}
		BackpackManager.getInstance().removeItem(player, item, 1, Reasons.GOODUSE, id);
		return true;
	}
}
