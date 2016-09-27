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
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.structs.Reasons;

public class BETASumRecharge5000 implements IActivityScript {
	private static Logger faillog = Logger.getLogger("GIVEREWARDFAILED");
	private static String KEY = "";
	
	private int itemid1=9104;  //雄心豹子胆
	private int itemnum1=1;    
	private int itemid2=30203;  //宗师真气丹
	private int itemnum2=5;
	@Override
	public int getId() {
		return ScriptEnum.BETA_SUMRECHARGE5000;
	}

	@Override
	public void getReward(Player player) {
		List<Item> items = new ArrayList<Item>();
		List<Item> mailitems = new ArrayList<Item>();
		Date opendate = WServer.getGameConfig().getServerTimeByPlayer(player); 
		long opentime = opendate.getTime(); //开服时间
		int opensec = (int)(opentime/1000); //开服时候的秒数
		Calendar c = Calendar.getInstance();
		opentime = opentime+3*24*3600*1000; //开服三天后 
		c.setTimeInMillis(opentime);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0); //开服第三天的凌晨
		long losttime =  c.getTimeInMillis();
		items.addAll(Item.createItems(itemid1, itemnum1, true, losttime));  //
		items.addAll(Item.createItems(itemid2, itemnum2, true, 0));
		
		long actionid = Config.getId();
		if(BackpackManager.getInstance().hasAddSpace(player, items)){ //可以放包裹
			if(!BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, actionid)){
				mailitems = items;
			}
		}else{
			mailitems = items;
		}
		if(mailitems.size()>0){
			String title = "累积充值5000元宝大礼包";
			String content = "累积充值5000元宝大礼包";
			if(!MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), title, content, (byte)0, 0, mailitems)){
				faillog.info("[Player"+player.getName()+"|"+player.getId()+"]"+"充5000领奖给奖励失败"+JSONserializable.toString(items));
			}
		}
	}

	@Override
	public String getDescribe(Player player) {
		return "";
	}

	@Override
	public String getRewardInfo(Player player) {  
		return "[{\"id\":"+8088+", \"num\":" + 1 + "},{\"id\":"+itemid1+", \"num\":"+itemnum1+"},{\"id\":"+itemid2+", \"num\":"+itemnum2+"}]";
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
