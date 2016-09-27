package scripts.activities;


import java.util.*;

import org.apache.log4j.Logger;

import com.game.activities.script.IActivityScript;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.json.JSONserializable;
import com.game.mail.manager.MailServerManager;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.structs.Reasons;

//累计充值500
public class GQRECHARGE500 implements IActivityScript {

	private static Logger faillog = Logger.getLogger("GIVEREWARDFAILED");
	@Override
	public int getId() {
		return ScriptEnum.GQRECHARGE500;
	}

	@Override //龙元秘籍*1  绑定 24小时
	public void getReward(Player player) {
		long losttime = System.currentTimeMillis()+24*60*60*1000;
		long actionid = Config.getId();
		if(BackpackManager.getInstance().getAbleAddNum(player, 8403, true, losttime)>0){
			List<Item> items = Item.createItems(8403, 1, true, losttime);
			BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, actionid);
		}else{
			String title="单日累计充值500元宝活动";
			String content="单日累计充值500元宝活动";
			List<Item> mailitems = Item.createItems(8403, 1, true, losttime);
			if(!MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), title, content, (byte)0, 0, mailitems)){
				faillog.info("[Player"+player.getName()+"|"+player.getId()+"]"+"国庆活动充500元宝领奖给发邮件失败"+JSONserializable.toString(mailitems));
			}
		}
	}

	@Override
	public String getDescribe(Player player) {
		return "a";
	}

	@Override
	public String getRewardInfo(Player player) {
		return "[{\"id\":"+8403+",\"num\":" + 1 + "}]";
	}

	@Override
	public int getState(Player player) {
		return 3;
	}

	@Override
	public boolean isOpen(Player player) {
		int sid = WServer.getInstance().getServerId();
		if(sid<3) return true;
		else return false;
	}

	@Override  //从10.01 07:00:00 到10.07 23:59:59 开服时间到10.07 23.59.59的秒数
	public int getDuration(Player player) {
		Date opendate = WServer.getGameConfig().getServerTimeByPlayer(player); 
		long opentime = opendate.getTime(); //开服时间
		int opensec = (int)(opentime/1000); //开服时候的秒数
		Calendar c = Calendar.getInstance();
		c.set(2012, 9, 8, 0, 0, 0); //10月8日的凌晨
		long limit = c.getTimeInMillis(); 
		int limitsec = (int)(limit/1000); 
		//从开服到10月07日的秒数
		return limitsec-opensec;
	}

}
