package com.game.ybcard.manager;

import com.game.config.Config;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.server.impl.WServer;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;
import com.game.ybcard.message.ReqYBCardToWorldMessage;
import com.game.ybcard.message.ResYBCardAddYBPlayerToGameMessage;
import com.game.ybcard.message.ResYBCardNoticeToGameMessage;
import com.game.ybcard.message.ResYBCardReceiveToGameMessage;


public class YbcardManager {
	
	private static Object obj = new Object();
	//玩家管理类实例
	private static YbcardManager manager;
	private YbcardManager() {
	}

	public static YbcardManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new YbcardManager();
			}
		}
		return manager;
	}
	
	
	private int YB_MAX;
	private long DAY;
	
	
	
	/**前端发起元宝卡消息
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqYBCardToGameMessage(Player player, byte type, int num) {
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("出现异常错误，请通知管理员处理"));
			return ;
		}
		
		ReqYBCardToWorldMessage wmsg = new ReqYBCardToWorldMessage();
		wmsg.setPlayerid(player.getId());
		wmsg.setType(type);
		wmsg.setNum(num);
		if (type== 1) {	//使用元宝卡type = 1;		//1封测期间（抽奖），2公测期间(领取元宝)
			long newday = TimeUtil.GetCurTimeInMin(4);
			if (newday != DAY) {
				YB_MAX = 0;
				DAY = newday;
			}
			
			if (YB_MAX >= 999999999) {
				MessageUtil.notify_player(player,Notifys.CUTOUT,ResManager.getInstance().getString("今日元宝卡发放元宝已经达到上限，明天再用吧"));
				return;
			}
			if (ManagerPool.backpackManager.removeItem(player, 8060, num, Reasons.ACTIVITY_YBCARD_USE, Config.getId()) == false) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您包裹里没有公测元宝卡。"));
				return;
			}
		}
		MessageUtil.send_to_world(wmsg);
	}
	
	/**得到公测卡元宝(公测后给玩家加元宝)
	 * 
	 * @param msg
	 */

	public void stResYBCardReceiveToGameMessage(ResYBCardReceiveToGameMessage msg) {
		Player player = ManagerPool.playerManager.getOnLinePlayer(msg.getPlayerid());
		if (player != null) {
			ManagerPool.backpackManager.changeBindGold(player, msg.getYuanbaonum(), Reasons.ACTIVITY_YBCARD, Config.getId());
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜您领取到了{1}礼金"), msg.getYuanbaonum()+"");
		}
	}

	
	/**（群发）通知服务器元宝达到上线
	 * 
	 * @param msg
	 */
	public void stResYBCardNoticeToGameMessage(ResYBCardNoticeToGameMessage msg) {
		long newday = TimeUtil.GetCurTimeInMin(4);
		if (newday != DAY) {
			YB_MAX = 0;
			DAY = newday;
		}else {
			YB_MAX = msg.getYuanbaonum();
		}
	}

	public void stResYBCardAddYBPlayerToGameMessage(ResYBCardAddYBPlayerToGameMessage msg) {
		Player player = ManagerPool.playerManager.getOnLinePlayer(msg.getPlayerid());
		if (player != null) {
			ManagerPool.backpackManager.changeBindGold(player, msg.getNum(), Reasons.ACTIVITY_TESTYB, Config.getId());
			//MessageUtil.notify_player(player, Notifys.SUCCESS, "恭喜您得到了{1}绑定元宝", msg.getNum()+"");
		}
		
	}
	
	
	
	
	
}
