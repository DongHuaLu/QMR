package scripts.item;

import java.util.ArrayList;
import java.util.List;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.gift.message.ResShuffleRaffleMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.spirittree.structs.FruitReward;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;

/**礼包翻牌（美人元旦礼包）
 * 
 * @author zhangrong
 *
 */
public class FlopBox implements IItemScript {
	@Override
	public int getId() {
		return 1009152;	
	}

	private int[][] rewdata={
	//-1铜币，-2元宝，-3真气，-4经验  -5礼金
	//{道具ID,数量,几率},
			{1011,1,1000,1},
			{1007,50,800,0},
			{-5,1000,100,1},
			{1031,2,779,1},
			{1024,1,500,1},
			{1019,1,5,1},
			{-6,500,1500,0},
			{-3,10000,1000,0},
			{-4,5000000,900,0},
			{-1,2000000,2000,0},
			{3019,2,300,1},
			{1100,5,1000,0},
			{1020,1,10,1},
			{1021,1,5,1},
			{1022,1,1,1},
			{-7,50,100,0}

		};
	
	
	
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		ResShuffleRaffleMessage msg = new ResShuffleRaffleMessage();
		for (int[] data : rewdata) {
			FruitReward  fruitreward = new FruitReward();
			fruitreward.setItemModelid(data[0]);
			fruitreward.setNum(data[1]);
			fruitreward.setBind(true);
			msg.getIteminfos().add(ManagerPool.zonesFlopManager.getItemInfo(fruitreward));
			msg.setType(0);
			msg.setItemid(""+item.getId());
		}
		MessageUtil.tell_player_message(player, msg);
		return false;
	}
	
	

	/**翻牌奖励
	 * ReqStartShuffleRaffleHandler转发到这里
	 */
	public void flopreward(List<Object> list){
		Player player =(Player)list.get(0);
		long itemid =Long.valueOf((String) list.get(1));
		Item item = ManagerPool.backpackManager.getItemById(player, itemid);
		if (player != null) {
			if (item!=null && item.getItemModelId() == 9152) {
				String name = item.acqItemModel().getQ_name();
				if(ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.YUANDANFANPAI,Config.getId())){
					List<Integer> rndlist=new ArrayList<Integer>();
					for (int[] data : rewdata) {
						rndlist.add(data[2]);
					}
					int idx = RandomUtils.randomIndexByProb(rndlist);
					ResShuffleRaffleMessage msg = new ResShuffleRaffleMessage();
					FruitReward  fruitreward = new FruitReward();
					fruitreward.setItemModelid(rewdata[idx][0]);
					fruitreward.setNum(rewdata[idx][1]);
					fruitreward.setBind(true);
					msg.getIteminfos().add(ManagerPool.zonesFlopManager.getItemInfo(fruitreward));
					msg.setType(1);
					MessageUtil.tell_player_message(player, msg);
					ManagerPool.zonesFlopManager.giveRewarded(player,fruitreward,2);
					if (rewdata[idx].length >= 4 &&  rewdata[idx][3] == 1) {	//是否公告
						Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(fruitreward.getItemModelid());
						if (model != null) {
							MessageUtil.notify_All_player(Notifys.CHAT_BULL,"恭喜『{1}』打开『{2}』，获得了『{3}*{4}』", player.getName() , name,model.getQ_name(),""+fruitreward.getNum());
						}
						
					}
				}
			}
		}
	}
	
	
	
}
