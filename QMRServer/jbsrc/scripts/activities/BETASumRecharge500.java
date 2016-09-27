package scripts.activities;

import org.apache.log4j.Logger;

import java.util.*;

import com.game.activities.script.IActivityScript;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.json.JSONserializable;
import com.game.mail.manager.MailServerManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;

public class BETASumRecharge500 implements IActivityScript {

	private static Logger faillog = Logger.getLogger("GIVEREWARDFAILED");
	private static String KEY = "";
	
	private  int itemid1=9103; //美人百宝袋
	private  int itemnum1=1;
	
	@Override
	public int getId() {
		return ScriptEnum.BETA_SUMRECHARGE500;
	}

	@Override
	public void getReward(Player player) { 
		if(BackpackManager.getInstance().getAbleAddNum(player, itemid1, true, 0)>=itemnum1){ 
			List<Item> items = Item.createItems(itemid1, itemnum1, true, 0);
			long actionid = Config.getId();
			if(!BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, actionid)){
				String title="累积充值500元宝大礼包";
				String content = "累积充值500元宝大礼包";
				if(!MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), title, content, (byte)0, 0, items)){
					faillog.info("[Player"+player.getName()+"|"+player.getId()+"]"+"充500领奖给发邮件失败"+JSONserializable.toString(items));
				}
			}
		}else{
			List<Item> mailitems = Item.createItems(itemid1, itemnum1, true, 0);
			String title="累积充值500元宝大礼包";
			String content = "累积充值500元宝大礼包";
			if(!MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), title, content, (byte)0, 0, mailitems)){
				faillog.info("[Player"+player.getName()+"|"+player.getId()+"]"+"充500领奖给发邮件失败"+JSONserializable.toString(mailitems));
			}
		}
	}

	@Override
	public String getDescribe(Player player) {
		return "";
	}

	@Override
	public String getRewardInfo(Player player) {  
		return "[{\"id\":"+itemid1+", \"num\":" + itemnum1 + "},{\"id\":"+itemid1+", \"num\":" + itemnum1 + "}]";
	}

	@Override
	public int getState(Player player) {
		return 3; //世界服判断
	}

	@Override
	public boolean isOpen(Player player) {
		return true;
	}
	
	@Override
	public int getDuration(Player player){
		Date opendate = WServer.getGameConfig().getServerTimeByPlayer(player); 
		long opentime = opendate.getTime(); //开服时间
		int opensec = (int)(opentime/1000); //开服时候的秒数
		Calendar c = Calendar.getInstance();
		opentime = opentime+3*24*3600*1000; //开服三天后
		c.setTimeInMillis(opentime);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0); //开服第三天的凌晨
		long limit = c.getTimeInMillis(); //开服第三天凌晨
		int limitsec = (int)(limit/1000); //开服第三天凌晨的秒数
		//从开服到开服第三天凌晨的秒数
		return limitsec-opensec;
	}

}
