package scripts.activities;
import org.apache.log4j.Logger;

import com.game.activities.message.ResReceiveLiJinGiftResultMessage;
import com.game.activities.script.IReceiveLiJinGift;
import com.game.backpack.manager.BackpackManager;
import com.game.config.Config;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Reasons;
import com.game.utils.Global;
import com.game.utils.MessageUtil;

/**
 * 
 * @author 赵聪慧
 * @2012-9-12 下午8:24:13
 */
public class ReceiveLiJinGift implements IReceiveLiJinGift {
	protected Logger log = Logger.getLogger(ReceiveLiJinGift.class);
	@Override
	public int getId() {
		return ScriptEnum.RECEIVELIJINGIFT;
	}

	@Override
	public void receive(Player player) {
//		log.error("公测领取礼金调用："+player.getName()+","+player.getId());
//		ResReceiveLiJinGiftResultMessage msg=new ResReceiveLiJinGiftResultMessage();
//		if(player.getLastReceiveGiftTime()==0){
//			BackpackManager.getInstance().changeBindGold(player, 2000, Reasons.ACTIVITY_GIFT, Config.getId());
//			player.setLastReceiveGiftTime(System.currentTimeMillis());
//			msg.setNextReceiveNeedTime((int)(Global.RECEIVE_GIFT_TIMESTEP/1000));
//			msg.setResult((byte)1);
//			MessageUtil.tell_player_message(player, msg);
//			return;
//		}
//		int losttime=(int) (System.currentTimeMillis()-player.getLastReceiveGiftTime());
//		if(losttime>=Global.RECEIVE_GIFT_TIMESTEP){
//			BackpackManager.getInstance().changeBindGold(player, 2000, Reasons.ACTIVITY_GIFT, Config.getId());
//			player.setLastReceiveGiftTime(System.currentTimeMillis());
//			msg.setNextReceiveNeedTime((int)(Global.RECEIVE_GIFT_TIMESTEP)/1000);
//			msg.setResult((byte)1);
//		}else{
//			msg.setNextReceiveNeedTime((int)(Global.RECEIVE_GIFT_TIMESTEP-losttime)/1000);
//			msg.setResult((byte)0);
//		}
//		MessageUtil.tell_player_message(player, msg);
	}
	
}
