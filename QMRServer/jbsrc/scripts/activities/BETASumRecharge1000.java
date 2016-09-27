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

public class BETASumRecharge1000 implements IActivityScript {

	private static Logger faillog = Logger.getLogger("GIVEREWARDFAILED");
	private static String KEY = "BETASUMRECHARGE1000";
	
	private int giftid = 8407;
	
	private int itemid1 = -5;
	private int itemnum1 = 3000;
	private int itemid2 = 1018;
	private int itemnum2 = 2;
	private int itemid3 = 1100;
	private int itemnum3 = 10;
	private int itemid4 = 1015;
	private int itemnum4 = 5;
	
	@Override
	public int getId() {
		return ScriptEnum.BETA_SUMRECHARGE1000;
	}

	@Override
	public void getReward(Player player) {
		List<Item> items = new ArrayList<Item>();
		List<Item> mailitems = new ArrayList<Item>();
		items.addAll(Item.createItems(itemid2, itemnum2, true, 0));
		items.addAll(Item.createItems(itemid3, itemnum3, true, 0));
		items.addAll(Item.createItems(itemid4, itemnum4, true, 0));
		long actionid = Config.getId();
		
		BackpackManager.getInstance().changeBindGold(player, 3000, Reasons.ACTIVITY_GIFT, actionid);
		if(BackpackManager.getInstance().hasAddSpace(player, items)){ //可以放包裹
			if(!BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, actionid)){
				mailitems = items;
			}
		}else{
			mailitems = items;
		}
		if(mailitems.size()>0){
			String title = "累积充值1000元宝活动奖励";
			String content = "累积充值1000元宝活动奖励";
			if(!MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), title, content, (byte)0, 0, mailitems)){
				faillog.info("[Player"+player.getId()+"]"+"累积充值1000元宝奖励发送失败发送邮件失败"+JSONserializable.toString(mailitems));
			}
		}
		
	}

	@Override
	public String getDescribe(Player player) {
		return "累积充值1000元宝可领取以下奖励";
	}

	@Override
	public String getRewardInfo(Player player) {  //礼金3000  连斩延时丹2 红玫瑰10 双倍收益丹5
		return "[{\"id\":"+giftid+", \"num\":" + 1 + "},{\"id\":"+itemid1+", \"num\":" +itemnum1+ "},{\"id\":"+itemid2+", \"num\":" + itemnum2 + "},{\"id\":"+itemid3+", \"num\":" + itemnum3 + "},{\"id\":"+itemid4+", \"num\":" + itemnum4 + "}]";
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
