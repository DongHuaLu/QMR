package scripts.activities;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
public class GQRECHARGE2000 implements IActivityScript {

	private static Logger faillog = Logger.getLogger("GIVEREWARDFAILED");
	@Override
	public int getId() {
		return ScriptEnum.GQRECHARGE2000;
	}

	@Override //银票*6，坐骑进阶丹*10
	public void getReward(Player player) {
		long losttime = System.currentTimeMillis()+24*60*60*1000;
		long actionid = Config.getId();
		if(BackpackManager.getInstance().getEmptyGridNum(player)>=2){
			List<Item> items = Item.createItems(8406, 6, true, 0); //银票
			items.addAll(Item.createItems(1011, 10, true, losttime)); //坐骑进阶丹
			BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, actionid);
		}else{
			String title="单日累计充值2000元宝活动";
			String content="单日累计充值2000元宝活动";
			List<Item> mailitems = Item.createItems(8403, 6, true, 0);
			mailitems.addAll(Item.createItems(1011, 10, true, losttime)); //坐骑进阶丹
			if(!MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), title, content, (byte)0, 0, mailitems)){
				faillog.info("[Player"+player.getName()+"|"+player.getId()+"]"+"国庆活动充2000元宝领奖给发邮件失败"+JSONserializable.toString(mailitems));
			}
		}
	}

	@Override
	public String getDescribe(Player player) {
		return "a";
	}

	@Override
	public String getRewardInfo(Player player) {
		return "[{\"id\":"+8406+", \"num\":" + 6 + "},{\"id\":"+1011+", \"num\":" + 10 + "}]";
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

	@Override
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
