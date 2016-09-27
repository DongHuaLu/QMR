package scripts.item;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.count.structs.CountTypes;
import com.game.dazuo.message.ResCohesionZhenQiInadequateMessage;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.CommonConfig;
import com.game.utils.MessageUtil;


/**
 * 真气凝丹 9224
 * @author zhangrong
 *
 */

public class CohesionZhenQi implements IItemScript{
	private int itemmodelid= 9224;
	@Override
	public int getId() {
		return 1009224;
	}
	
	//玩家ID
	private String PLAYER_ID= "PLAYER_ID";
	//玩家名字
	private String PLAYER_NAME= "PLAYER_NAME";
	//真气数量
	private String ZHENQI_NUM= "ZHENQI_NUM";

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		String name = item.acqItemModel().getQ_name();
		if (item.getItemModelId() == itemmodelid ) {
			long id = Config.getId();
			if (item.getParameters().containsKey(PLAYER_ID)) {
				String strid = item.getParameters().get(PLAYER_ID);
				String playername = item.getParameters().get(PLAYER_NAME);
				int zhenqinum  = Integer.valueOf(item.getParameters().get(ZHENQI_NUM));
				long playerid= Long.valueOf(strid);
				int max = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.ZHENGQI_MAX.getValue()).getQ_int_value();
				if (player.getZhenqi() + zhenqinum > max ) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的真气值已经达到上限"));
					return false;
				}
				
				boolean isothers = false;	//是否使用他人凝丹
				
				if (playerid == player.getId()) {
					
				}else {//使用他人凝丹
					int usenum = (int) ManagerPool.countManager.getCount(player, CountTypes.USE_NINGDAN, null);
					if (usenum == 0) {	//每天使用计数
						ManagerPool.countManager.addCount(player, CountTypes.USE_NINGDAN, null,1, 0,0);
					}
					usenum = usenum +1;
					isothers = true;
					if(!ManagerPool.backpackManager.removeItem(player, 18174,usenum, Reasons.NINGZDAN_ZHENQI, id)){
						//数量不足，发消息给前端弹出面板
						int itemnum = ManagerPool.backpackManager.getItemNum(player, 18174);
						ResCohesionZhenQiInadequateMessage msg = new ResCohesionZhenQiInadequateMessage();
						msg.setLacknum(usenum - itemnum);	//缺少的数量
						msg.setPlayername(playername);
						msg.setUsenum(usenum);
						MessageUtil.tell_player_message(player, msg);
						return false;
					}
				}
				
				if(ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.NINGZDAN_ZHENQI, id)){
					ManagerPool.playerManager.addZhenqi(player, zhenqinum, AttributeChangeReason.NINGDAN);
					if (isothers) {
						ManagerPool.countManager.addCount(player, CountTypes.USE_NINGDAN, null,1);//每天使用他人凝丹+1
					}
					MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("使用{1}获得真气{2}"),name,zhenqinum+"");
					return true;
				}	
			}
		}
		return false;
	}

	
	
	
}
