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

public class BETASumRecharge2000 implements IActivityScript {

	private static Logger faillog = Logger.getLogger("GIVEREWARDFAILED");
	private static String KEY = "";
	
	private int giftid = 8408;
	
	private int itemid1 = 30104;  //特等修为丹
	private int itemnum1 = 1;
	private int itemid2 = 30202;  //大真气丹
	private int itemnum2 = 5;
	private int itemid3 = 8406;   //银票
	private int itemnum3 = 1;
	
	@Override
	public int getId() {
		return ScriptEnum.BETA_SUMRECHARGE2000;
	}

	@Override
	public void getReward(Player player) {
		long actionid = Config.getId();
		List<Item> mailitems = new ArrayList<Item>();
		List<Item> items = new ArrayList<Item>();
		items.addAll(Item.createItems(itemid1, itemnum1, true, 0));
		items.addAll(Item.createItems(itemid2, itemnum2, true, 0));
		items.addAll(Item.createItems(itemid3, itemnum3, true, 0));
		if(BackpackManager.getInstance().hasAddSpace(player, items)){
			if(!BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, actionid)){
				mailitems = items;
			}
		}else{
			mailitems = items;
		}
		if(mailitems.size()>0){
			String title="累积充值2000元宝奖励";
			String content="累积充值2000元宝奖励";
			if(!MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), title, content, (byte)0, 0, mailitems)){
				faillog.info("[Player"+player.getName()+"|"+player.getId()+"]"+"累积充值2000元宝发奖励失败发邮件失败"+JSONserializable.toString(mailitems));
			}
		}
	}

	@Override
	public String getDescribe(Player player) {
		return "";
	}

	@Override
	public String getRewardInfo(Player player) { //特等修为丹1  大真气丹5  TODO 银票一张
		return "[{\"id\":"+giftid+", \"num\":" + 1 + "},{\"id\":"+itemid1+", \"num\":" + itemnum1 + "},{\"id\":"+itemid2+", \"num\":" + itemnum2 + "},{\"id\":"+itemid3+", \"num\":" + itemnum3 + "}]";
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
