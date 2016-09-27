package scripts.activities;

import java.util.Calendar;
import java.util.Date;

import com.game.activities.script.IActivityScript;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;

public class BETAShopDiscount implements IActivityScript {

	@Override
	public int getId() {
		return ScriptEnum.BETA_SHOPDISCOUNT;
	}

	@Override
	public void getReward(Player player) {

	}

	@Override
	public String getDescribe(Player player) {
		return "";
	}

	@Override
	public String getRewardInfo(Player player) {
		return "";
	}

	@Override
	public int getState(Player player) {
		return 1;
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
